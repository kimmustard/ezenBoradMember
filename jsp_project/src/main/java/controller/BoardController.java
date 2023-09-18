package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.PagingHandler;
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
			
			
		case "insert":
			try {
				log.info("register check 1");
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				BoardVO bvo = new BoardVO(title, writer, content);
				log.info("bvo = {}", bvo);
				
				isOk = bsv.register(bvo);
				log.info((isOk > 0)? "Ok" : "Fail");
				destPage = "/index.jsp";
				
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
				log.info("edit check 1 ");
				int bno = Integer.parseInt(request.getParameter("bno"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				BoardVO bvo = new BoardVO(bno, title, content);
				log.info("bvo = {}", bvo);
				isOk = bsv.edit(bvo);
				log.info((isOk > 0)? "Ok" : "Fail");
				request.setAttribute("bvo", bvo);
				destPage = "detail?bno="+bno;

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
