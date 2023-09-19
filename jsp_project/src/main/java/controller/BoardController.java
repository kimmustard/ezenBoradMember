package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardServiceImpl;
import service.Service;


/**
 * Servlet implementation class BoardController
 */
@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	private RequestDispatcher rdp;
	private Service bsv;
	private String destPage = "";
	private int isOk;
	private String savePath;	//파일 경로를 저장할 변수
	
    public BoardController() {
       //service
    	bsv = new BoardServiceImpl();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("path = {}" , path);
		
		
		switch(path){
		
		case "register":
				destPage = "/board/register.jsp";
				log.info("registerForm check");
				
			break;
			
			
//		case "insert":
//			try {
//				log.info("register check 1");
//				String title = request.getParameter("title");
//				String writer = request.getParameter("writer");
//				String content = request.getParameter("content");
//				BoardVO bvo = new BoardVO(title, writer, content);
//				log.info("bvo = {}", bvo);
//				
//				isOk = bsv.register(bvo);
//				log.info((isOk > 0)? "Ok" : "Fail");
//				destPage = "/index.jsp";
//				
//			} catch (Exception e) {
//				log.info("insert Error!");
//				e.printStackTrace();
//			}
//		
//		break;
			
		case "insert":
			try {
				//파일 업로드 할 경로 설정 (업로드 할 때 설정)
				//getServletContext() 톰캣서버의 설정 정보를 저장하고있는곳.
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				log.info("파일 저장 위치 : " + savePath);
				
				//파일 객체를 생성하기 위한 객체 (파일을 디스크에 쓰기위한 설정정보 객체)
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);	//저장 할 위치 set(file 객체로 지정해줘야한다.)
				fileItemFactory.setSizeThreshold(2*1024*1024);	//저장을 위한 임시 메모리 byte단위로 지정 (유연하게 변한다)
				BoardVO bvo = new BoardVO();
				
				/*
				 * contentType이 multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 객체형식으로 저장
				 */
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				
				//DB로 넘기기위한 BoardVO 객체로 변환, 이미지는 저장 (DB로는 파일 정보와 경로만)
				for (FileItem item : itemList) {
					switch(item.getFieldName()) {
						case "title":
							bvo.setTitle(item.getString("utf-8"));	//인코딩 형식을 담아서 변환
							
							
							break;
						case "writer":
							bvo.setWriter(item.getString("utf-8"));
							
							break;
							
						case "content":
							bvo.setContent(item.getString("utf-8"));
							
							break;
						case "image_file":
							//이미지 저장 처리가 필요
							//이미지가 필수는 X 없는 경우에도 처리
							//이미지가 있는지 먼저 체크
							if(item.getSize()>0) {	//item.getSize() 데이터 크기가 있다면 이미지가 있는걸로 처리
								//가끔 경로를 포함해서 들어오는 케이스가 있다.
								String fileName = item.getName()
											.substring(item.getName().lastIndexOf("/")+1);	//파일 이름만 분리
								//시스템의 현재시간으로 고유 파일이름을 생성함.jpg
								fileName = System.currentTimeMillis()+"_"+fileName;
								
								//파일 객체 생성 ( D:/~webapp/_fileUpload/시간_cat2.jpg 원래 경로)
								File uploadFilePath = new File(fileDir+File.separator+fileName);
								log.info("uploadFilePath = {}" , uploadFilePath);
								
								//저장
								try {
									item.write(uploadFilePath); //자바 객체를 디스크에 쓰기
									bvo.setImage_File(fileName);
									
									//썸네일 작업 : 트래픽 과다사용 방지
									Thumbnails.of(uploadFilePath)
									.size(60, 60).toFile(new File(fileDir+File.separator+"_th_"+fileName));
									
								} catch (Exception e) {
									log.info("file writer on disk Error!");
									e.printStackTrace();
								}
							}
							
							break;
							
						}
					
				}
				
				//DB에 bvo 저장
				isOk = bsv.register(bvo);
				log.info("insert " + (isOk>0 ? "Ok" : "Fail"));
				destPage = "pageList";
				
				
			} catch (Exception e) {
				log.info("insert Error!");
				e.printStackTrace();
			}
		
		break;
		
		
		case "list":
			try {
				log.info("List check 1");
				List<BoardVO> list = bsv.getList();
				log.info("List check 4");
				request.setAttribute("list", list);
				destPage = "/board/list.jsp";
				
			} catch (Exception e) {
				log.info("list Error!");
				e.printStackTrace();
			}
			
			break;
		
		case "detail":
			try {
				log.info("detail check 1");
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("bno = {}" , bno);
				BoardVO bvo = bsv.detail(bno);
				log.info("detail check 4");
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
				
				
			} catch (Exception e) {
				log.info("detail Error!");
				e.printStackTrace();
			}
		break;
		
		
		case "modify":
			try {
				log.info("modify check 1");
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = bsv.detail(bno);
				request.setAttribute("bvo", bvo);
				log.info("modify check 4");
				destPage= "/board/modify.jsp"; 
			} catch (Exception e) {
				log.info("modify Error!");
				e.printStackTrace();
			}
			
			
			break;
		
		
		
		case "edit":
			
			try {
				//파일 저장 경로 설정
				savePath = getServletContext().getRealPath("/_fileUpload");
				
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); // 저장 경로 설정
				fileItemFactory.setSizeThreshold(2*1024*1024); //임시 저장 용량 설정 2MB
				
				BoardVO bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">>>>>>>>update 준비");
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				
				String old_file = null;	//수정하기전 원래 그림파일
				for (FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString("utf-8")));
						break;
					case "title":
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "image_file":
						// 수정 이전 파일
						old_file = item.getString("utf-8");
						break;
					case "new_file":
						//새로운 파일이 있는지 확인
						if(item.getSize()>0) {
							if(old_file != null) {
								//기존 파일 삭제 (기존 파일이 있을 경우)
								FileHandler fileHandler = new FileHandler();
								isOk = fileHandler.deleteFile(old_file, savePath);
							}
							//new 파일의 경로와 파일명 생성
							String fileName = item.getName().substring(
									item.getName().lastIndexOf(File.separator)+1);
							
							log.info("new_fileName"+fileName);
							// 실제 저장될 파일이름
							fileName = System.currentTimeMillis()+"_"+fileName;
							
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							//저장
							
							try {
								item.write(uploadFilePath);
								bvo.setImage_File(fileName);
								
								//썸네일 작업
								Thumbnails.of(uploadFilePath)
								.size(60,60)
								.toFile(new File(fileDir+File.separator+"_th_"+fileName));
								
							} catch (Exception e) {
								log.info("new File save Error!");
								e.printStackTrace();
							}
							
						}else {	//새로운 파일이 없다면.. 기존파일을 그대로 둬야한다.
							bvo.setImage_File(old_file);
							
							
						}
						break;
					
					}
				}
				isOk = bsv.edit(bvo);
				log.info((isOk > 0)? "Ok" : "Fail");
				destPage = "pageList";
				
				
//				log.info("edit check 1 ");
//				int bno = Integer.parseInt(request.getParameter("bno"));
//				String title = request.getParameter("title");
//				String content = request.getParameter("content");
//				BoardVO bvo = new BoardVO(bno, title, content);
//				log.info("bvo = {}", bvo);
//				isOk = bsv.edit(bvo);
//				log.info((isOk > 0)? "Ok" : "Fail");
//				request.setAttribute("bvo", bvo);
//				destPage = "detail?bno="+bno;

			} catch (Exception e) {
				log.info("modify Error!");
				e.printStackTrace();
			}
			
			
			
			break;
			
		case "remove":
			try {
				log.info("remove check 1 ");
				int bno = Integer.parseInt(request.getParameter("bno"));
				isOk = bsv.remove(bno);
				destPage = "pageList";
				
			} catch (Exception e) {
				log.info("remove Error!");
				e.printStackTrace();
			}
			
			break;
		
			
		case "pageList":
			try {
				log.info("pageList check 1 ");
				PagingVO pgvo = new PagingVO();
				if(request.getParameter("pageNo")!=null) {
					int pageNo = Integer.parseInt(request.getParameter("pageNo"));
					int qty = Integer.parseInt(request.getParameter("qty"));
					pgvo = new PagingVO(pageNo, qty);
				}
				
				//검색
				String type = request.getParameter("type");
				String keyword = request.getParameter("keyword");
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				
				
				int totalCount = bsv.getTotalCount(pgvo);	//DB에 카운트요청
				log.info("totalCount = {}" , totalCount);
				List<BoardVO> list = bsv.getPageList(pgvo);	//받아온 카운트를 다시 db로 보내 쿼리문 limit 작성후 리스트 출력
				request.setAttribute("list", list);
				
				//페이징 정보(핸들러로 계산한것) 내보내기
				PagingHandler ph = new PagingHandler(pgvo, totalCount);
				request.setAttribute("ph", ph);
				log.info("pageList check 4");
				destPage = "/board/list.jsp";
				
			} catch (Exception e) {
				log.info("pageList Error!");
				e.printStackTrace();
			}
			
			break;
			
		}
		
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
		
	}


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
