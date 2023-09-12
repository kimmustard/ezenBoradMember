package domain;

public class PagingVO {
	
	private int pageNo; //현재 화면에 출력되는 페이지네이션 번호
	private int qty; //한 페이지당 보여줄 게시굴 수
	
	//검색 멤버변수 추가
	private String type; // 검색대상 || 어떤 검색을 할지.. 제목, 작성자,내용등등
	private String keyword; // 검색어
	
	
	//페이지네이션을 클릭하기전 기본리스트 출력 값
	public PagingVO() {	
		this(1,10);
	}
	
	//페이지네이션을 클릭하면 설정되는 값
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	
	// DB 내부에서 limit 쿼리문에 적용할 메서드
	public int getPageStart() {
		return (pageNo-1)*qty; //DB에서 조회할 시작페이지
		
	}
	
	/*
	 * DB는 항상 getter로 값을 찾아오기때문에 반드시 메서드 명은 게터를 사용해야한다
	 * */
	//검색기능 (동적쿼리 시행시)
	//type이 여러개 들어올때 값을 배열로 리턴
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
