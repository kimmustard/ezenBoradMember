package domain;

public class PagingVO {

	
	private int pageNo;
	private int qty;
	
	private String type;
	private String keyword;
	
	public PagingVO() {
		this(1,10);
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}

	
	//쿼리문 limit에 사용될 getter limit(0,10) 0번부터 10개 가져오기 
	public int getPageStart() {
		return (pageNo-1)*qty;
	}
	
	//동적쿼리문 검색어 나눌떄 쓸 메서드
	public String[] getTypeToArray() {
		return (this.type == null) ? new String[] {} : this.type.split("");
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
	
	
	
	
	
	
	
	
}
