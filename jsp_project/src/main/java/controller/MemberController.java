package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	private RequestDispatcher rdp;
	private String destPage = "";
	private int isOk;
	
	private MemberService msv;
	
    public MemberController() {
    	msv = new MemberServiceImpl();
    	
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("path = {}", path);
		
		switch(path) {
		
			case "join":
				
				destPage = "/member/join.jsp";
			
			break;
		
		
			case "register":
				try {
					log.info("register check 1");
					String id = request.getParameter("id");
					String pwd = request.getParameter("pwd");
					String email = request.getParameter("email");
					int age = Integer.parseInt(request.getParameter("age"));
					MemberVO mvo = new MemberVO(id, pwd, email, age);
					isOk = msv.register(mvo);
					log.info((isOk > 0)? "Ok" : "Fail");
					destPage = "/index.jsp";
					
				} catch (Exception e) {
					log.info("register Error !");
					e.printStackTrace();
				}
				
			
			break;
		
		
			case "login":
			try {
				log.info("login check 1");
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				MemberVO mvo = new MemberVO(id , pwd);
				MemberVO loginmvo = msv.login(mvo); 
				log.info("loginmvo = {}", loginmvo);
				log.info("login check 4");
				
				if(loginmvo != null) {
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginmvo);
					ses.setMaxInactiveInterval(300); // 로그인 지속시간 10분
				}else {
					request.setAttribute("msg_login", 0);	//로그인이 실패했을때
				}
				
				
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("login Error !");
				e.printStackTrace();
			}
			
			break;
				
			
			case "logout":
				
				try {
					log.info("logout check 1");
					HttpSession ses = request.getSession();
					MemberVO mvo = (MemberVO) ses.getAttribute("ses"); 
					String id = mvo.getId();
					log.info("id = {} , logout check 1", id);
					isOk = msv.lastLogin(id);
					log.info((isOk > 0)? "Ok" : "Fail");
					ses.invalidate();	//세션끊기
					
					destPage = "/index.jsp";
				
					
				} catch (Exception e) {
					log.info("logout Error !");
					e.printStackTrace();
				}
				
				
			break;
			
			
			case "modify":
				
					destPage ="/member/modify.jsp";
					
			break;
			
			
			case "update":
				try {
					log.info("Member modify check 1");
					String id = request.getParameter("id");
					String pwd = request.getParameter("pwd");
					String email = request.getParameter("email");
					int age = Integer.parseInt(request.getParameter("age"));
					MemberVO mvo = new MemberVO(id, pwd, email, age);
					isOk = msv.modify(mvo);
					log.info((isOk > 0)? "Ok" : "Fail");
					destPage = "logout";
					
					
				} catch (Exception e) {
					log.info("update Error !");
					e.printStackTrace();
				}
				
				
				break;
				
				
			case "remove":
				try {
				log.info("Member delete check 1");
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO) ses.getAttribute("ses");
				isOk = msv.remove(mvo.getId());
				ses.invalidate();
				destPage = "/index.jsp";
					
				} catch (Exception e) {
					log.info("delete Error !");
					e.printStackTrace();
				}
				
				
				break;
				
				
			case "list":
				try {
					log.info("Member list check 1");
					List<MemberVO> list = msv.getList();
					request.setAttribute("list", list);
					
					destPage = "/member/list.jsp";
					
				} catch (Exception e) {
					log.info("list Error !");
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
