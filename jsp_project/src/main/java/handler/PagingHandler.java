package handler;

import domain.PagingVO;

public class PagingHandler {
	private int startPage;	//시작 페이지네이션
	private int endPage;	//끝 페이지네이션
	private int realEndPage;	//진짜 끝 페이지 네이션
	private boolean prev, next;	//버튼 
	
	private int totalCount;	//전체 게시글 수 
	
	private PagingVO pgvo; //게시물 상태 객체
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		
		this.endPage = 
				// 페이지 넘버 / 
				(int)Math.ceil(pgvo.getPageNo() / (double) pgvo.getQty()) * pgvo.getQty();
		
		this.startPage = endPage - 9;
		
		
		this.realEndPage = (int)Math.ceil(totalCount / (double) pgvo.getQty());
		if(realEndPage < this.endPage) {
			this.endPage = this.realEndPage;
		}
		
		this.prev = this.startPage > 1;	//왼쪽화살표 존재여부
		this.next = this.endPage < realEndPage; //오른쪽 화살표 존재여부


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
