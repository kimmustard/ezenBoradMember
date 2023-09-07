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
import service.BoardServiceImpl;
import service.Service;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//로그 객체 선언
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	//requestDispatcher 객체 rdp
	private RequestDispatcher rdp;
	//service interface
	private Service bsv;
	//destPage
	private String destPage = "";
	private int isOk;
	
	
    public BoardController() {
        //Service
    	bsv = new BoardServiceImpl();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// encoding 설정, contentType 설정, 요청경로 파악
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//JSP에서 오는 요청 주소
		String uri = request.getRequestURI();	// /brd/register
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("path = {}", path);
		
		switch(path) {
		
		
		case "register":
			destPage="/board/register.jsp";
			log.info("register seccess");
		
		
		break;
		
		
		case "insert":
			try {
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				log.info("insert check 1");
				BoardVO bvo = new BoardVO(title, writer, content);
				log.info("bvo = {}",bvo);
				isOk = bsv.register(bvo);
				log.info((isOk>0)? "Ok": "Fail");
				
				destPage="/index.jsp";
			} catch (Exception e) {
				log.info("insert error!");
				e.printStackTrace();
			}
			
			break;
			
		case "list":
			try {
				log.info("list check 1");
				List<BoardVO> list = bsv.getList();
				log.info("list check 4");
				request.setAttribute("list", list);
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("list error!");
				e.printStackTrace();
			}
			
			break;
			
			
		case "detail":
			
			try {
				log.info("detail check1");
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("bno ={}", bno);
				BoardVO bvo = bsv.detail(bno);
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
				
			} catch (Exception e) {
				log.info("detail error!");
				e.printStackTrace();
			}
			
			break;
			
		case "modify":
			try {
				log.info("modify check 1");
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = bsv.detail(bno);
				request.setAttribute("bvo", bvo);
				destPage = "/board/modify.jsp";
				
			} catch (Exception e) {
				log.info("modify error!");
				e.printStackTrace();
			}
			
			break;
			
		case "edit":
			try {
				log.info("edit check 1");
				int bno = Integer.parseInt(request.getParameter("bno"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				BoardVO bvo = new BoardVO(bno, title, content);
				isOk = bsv.edit(bvo);
				log.info((isOk>0)? "Ok": "Fail");
				
				destPage = "detail?bno="+bno;
				
			} catch (Exception e) {
				log.info("edit error!");
				e.printStackTrace();
			}
			
			break;
			
		case "remove":
			try {
				log.info("remove check 1");
				int bno = Integer.parseInt(request.getParameter("bno"));
				isOk = bsv.remove(bno);
				log.info((isOk>0)? "Ok": "Fail");
				destPage = "list";
			} catch (Exception e) {
				log.info("remove error!");
				e.printStackTrace();
			}
			
			
			break;
			
		}
		
		
		
		// 경로 보내줌
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
