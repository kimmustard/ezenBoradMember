package domain;

public class PagingVO {
	
	private int pageNo; //현재 화면에 출력되는 페이지네이션 번호
	private int qty;	//한 페이지당 보여줄ㅇ 게시글 수

	public PagingVO() {
		this(1,10);
	}

	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	//검색 기능추가 검색에 필요한 멤버변수
	private String type; //검색 할타입or대상 ex) 제목만, 글쓴이만, 내용만, 제목+내용 ,,, 등등
	private String keyword; //검색어
	
	
	/*
	 * DB limit은 n , 10  n부터 10개까지 출력하는 쿼리문이다.
	 * 페이지-1 * 게시물수 = 1페이지는 limit 0,10// 2페이지는 10,10 // 3페이지는 20, 10
	 * 앞선 공식으로 각 페이지별로 10개씩 추려낼수 있다.
	 */
	
	//DB 내부에서 limit 쿼리문에 적용할 메서드다.
	public int getPageStart() {
		return (pageNo-1)*qty;
		
	}
	
	//null이면 빈 배열을 , null이 아니면 넘어온 타입 
	//ex) twc면 t , w , c 각각 배열에 저장해서 DB로 보내는역할
	public String[] getTypeToArray() {
		return (this.type == null) ? new String[] {} : this.type.split("");
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	

}
