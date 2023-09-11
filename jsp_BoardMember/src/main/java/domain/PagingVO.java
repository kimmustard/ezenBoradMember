package domain;

public class PagingVO {
	
	private int pageNo; //현재 화면에 출력되는 페이지네이션 번호
	private int qty; //한 페이지당 보여줄 게시굴 수
	
	
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
