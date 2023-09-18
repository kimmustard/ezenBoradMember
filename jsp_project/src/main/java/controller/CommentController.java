package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
    
	/*
	 * 비동기 방식 => 페이지 이동 방식 X
	 * destPage X, RequestDispatcher X
	 * Command -> Service
	 */
	
	private CommentService csv;
	private int isOk;
	

    public CommentController() {
    	csv = new CommentServiceImpl();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//response의 setContentType 설정은 하지않음. 왜냐? 리스폰 방식이 JSON이기 때문
		
		String uri = request.getRequestURI();
		/*
		 * 기존 주소 방식 : /brd/detail?값=1
		 * 동기방식 => get, post
		 * 
		 * RestAPI 방식 : /cmt/list/10, ----> 실제로는 /cmt/post , /cmt/update
		 * get => 리스트 조회,
		 * post => 등록
		 * put => 업데이트할때 (기존껄 삭제하고 업데이트함)
		 * fetch => 기존꺼 내용변경
		 * delete => 삭제
		 * 
		 * 애매하면 post
		 */
		String pathUri = uri.substring("/cmt/".length());	// /cmt/"여기까지 자르고 뒤에 내용 가져옴"list/10
		String path = pathUri;
		String pathVar = ""; //없으면 공백처리
		if(pathUri.contains("/")) {	//패스값을 달고왔는지 확인
			path = pathUri.substring(0, pathUri.lastIndexOf("/")); // list
			pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1); // 10
		}
		
		log.info("uri = {}", uri);	///cmt/list/글번호
		log.info("path = {}", path);	//list 
		log.info("pathUri = {}", pathUri);	// list/글번호
		log.info("pathVar = {}", pathVar);	// 글번호
		
		switch(path) {
		case "post":
			try {
				//JSON방식으로 화면에서 보낸 데이터를 받는다
				//String 형태로 값을 받아 객체로 변환 JSON
				//Json-simple-1.1.1 라이브러리를 사용하여
				//Json 형태의 스트링 객체 형태로 변환
				StringBuffer sb = new StringBuffer();
				//append (String을 누적 해서 저장 할 수있다.)
				String line ="";
				BufferedReader br = request.getReader();	//cmtData를 받아오는 객체
				while((line= br.readLine()) != null) {
					sb.append(line);
				}
				log.info("sb = {}", sb.toString());	//JSON 객체
				
				//객체로 변환
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				//jsonObj는 map형태로 되어있다.
				//CommentVO 형태로 변환 
				int bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				
				//csv DB로 저장
				CommentVO cvo = new CommentVO(bno, writer, content);
				log.info("cvo ={}", cvo);
				isOk = csv.post(cvo);
				log.info((isOk>0)? "Ok":"Fail");
				
				//화면에 출력
				//화면에 뿌린다(X) 해당 페이지로 데이터를 보낸다.
				PrintWriter pw = response.getWriter();	
				pw.print(isOk);
				
			} catch (Exception e) {
				log.info("Comment Error");
				e.printStackTrace();
			}
			break;
			
			
		case "list": //   list/151 
			try {
				
				int bno = Integer.parseInt(pathVar);
				List<CommentVO> list = csv.getList(bno);
				log.info("Comment , list = {}" , list);
				
				//JSON 형태로 변환 => 화면에 전송
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				
				JSONArray jsonList = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					/*
					 * JSON으로 보내기전 key : value 형태로 값을만들어준다.
					 */
					jsonObjArr[i] = new JSONObject();
					jsonObjArr[i].put("cno", list.get(i).getCno());
					jsonObjArr[i].put("bno", list.get(i).getBno());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("content", list.get(i).getContent());
					jsonObjArr[i].put("regdate", list.get(i).getRegdate());
					
					jsonList.add(jsonObjArr[i]);
				}
				//JSON 값을 String으로 변환 (전송용)
				String jsonData = jsonList.toJSONString();
				
				//전송 객체에 싣고 화면으로 전송
				PrintWriter out = response.getWriter();
				out.print(jsonData);
				
				
				
			} catch (Exception e) {
				log.info("Comment list Error!");
				e.printStackTrace();
			}
			
			
			
			break;
		
		case "modify":
			
			try {
				StringBuffer sb = new StringBuffer();
				String line = "";
				
				BufferedReader br = request.getReader();
				while((line=br.readLine()) != null) {
					sb.append(line);
				}
				log.info("Modify sb = {}", sb.toString());
				
				//JSON형태의 객체로 변환
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				int cno = Integer.parseInt(jsonObj.get("cno").toString());
				String content = jsonObj.get("content").toString();
				CommentVO cvo = new CommentVO(cno, content);
				
				isOk = csv.modify(cvo);
				log.info((isOk>0)? "Ok" : "Fail");
				
				PrintWriter out = response.getWriter();
				out.print(isOk);
				
			} catch (Exception e) {
				log.info("Comment modify Error!");
				e.printStackTrace();
			}
			break;
			
			
			
		case "remove":
			
			try {
				// 쿼리 스트링으로 데이터를 보냈을 경우.
				int cno = Integer.parseInt(pathVar);
				isOk = csv.remove(cno);
				log.info((isOk>0)? "Ok" : "Fail");
				
				PrintWriter out = response.getWriter();
				out.print(isOk);
				
			} catch (Exception e) {
				log.info("Comment remove Error!");
				e.printStackTrace();
			}
			
			break;
			
		}
		
		
		
		
		
		
		
		
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
