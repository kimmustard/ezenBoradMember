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
    private String destPage;
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
			
		case "join":	//회원가입 페이지 form
			log.info("회원가입 페이지 열기");
			destPage="/member/join.jsp";
			break;
			
			
		case "register":	//회원가입 페이지
			try {
				//JSP에서 보낸 파라미터 받기
				
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				MemberVO mvo = new MemberVO(id, pwd, email, age);
				log.info("register check 1, mvo = {}", mvo);
				isOk = msv.register(mvo);
				log.info("register check 4", ((isOk>0)? "OK" : "Fail"));
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("register error!");
				e.printStackTrace();
			}
			
			break;
			
			
		case "login":	// 로그인이 일어나는 case
			try {
				// JSP의 파라미터를 가져가서 DB의 값에 있는지 검증을 해야한다.
				// 해당 id , pw가 일치하는 데이터를 가져오기
				// 가져온 데이터를 세션에 저장
				// Session : 모든 jsp페이지에 공유되는 데이터
				// 만약에 아이디가 없다면? or 패스워드가 불일치하면? -> alert창 띄우기 
				
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				MemberVO mvo = new MemberVO(id, pwd);
				
				MemberVO loginmvo = msv.login(mvo);
				log.info("login check 1, loginmvo = {}", loginmvo);
				// 가져온 데이터를 세션에 저장
				// 세션 가져오기
				if(loginmvo != null) {
					// 연결된 세션이 있다면? 기존의 세션 가져오기, 없으면 새로 생성
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginmvo);
					ses.setMaxInactiveInterval(600); //초 단위로 설정 (로그인 유지 시간)
					
				}else {	
					//값이 null 일때
					request.setAttribute("msg_login", 0);
				}
				
				destPage="/index.jsp";
				
				
			} catch (Exception e) {
				log.info("login error!");
				e.printStackTrace();
			}
			
			break;
			
			
			
		case "logout":
			
			try {
				// 연결된 세션이 있다면 해당 세션을 가져오기
				HttpSession ses = request.getSession();	// 로그인한 세션
				//lastLogin 시간을 기록할건데, id가 필요하다
				//session에서 id 가져오기
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				log.info("Logout check , id = {}", id);
				isOk = msv.lastLogin(id);
				ses.invalidate();	//세션 끊기
				log.info("logout = {}", (isOk>0? "OK" : "Fail"));
				destPage= "/index.jsp";
				
			} catch (Exception e) {
				log.info("logout error!");
				e.printStackTrace();
			}
			
			
			break;
			
		case "list":
			
			try {
				List<MemberVO> list = msv.getList();
				request.setAttribute("list", list);
				destPage= "/member/list.jsp";
			} catch (Exception e) {
				log.info("list error!");
				e.printStackTrace();
			}
			
			break;
			
		}
		
		
		
		//목적지 주소값 세팅
		rdp = request.getRequestDispatcher(destPage);
		//정보를 req res에 담아 보내기
		rdp.forward(request, response);
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
