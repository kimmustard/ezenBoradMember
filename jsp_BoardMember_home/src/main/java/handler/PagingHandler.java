package handler;

import domain.PagingVO;

public class PagingHandler {

	private int startPage; //현재 화면에서 보여줄 시작 페이지네이션 번호
	private int endPage; //현재 화면에서 보여줄 끝 페이지네이션 번호
	private int realEndPage; //실제 전체 끝페이지 번호\
	private boolean prev, next; //"이전" , "다음" 버튼 스위치 역할 (true면 표시, false면 생략)
	
	private int totalCount; //전체 글 수
	
	PagingVO pgvo; //페이징VO 내부 변수 사용해야함
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		//동적인 페이지 끝번호 구하기
		//      (페이지번호 / 한 화면의 게시글 수) * 한 화면의 게시글 수
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double) pgvo.getQty()) * pgvo.getQty();
		
		//시작번호구하기
		this.startPage = this.endPage - 9; 
		
		//전체 게시글 수 / 한 화면에 나오는 게시글 수
		//나머지가 생기면 무조건 1페이지 더생겨야 함.
		//페이지네이션의 마지막 페이지
		this.realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		if(realEndPage < this.endPage) {
			this.endPage = this.realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;

		
	}
	


	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
