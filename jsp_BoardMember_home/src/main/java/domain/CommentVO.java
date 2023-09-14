package domain;

public class CommentVO {
	
	private int cno;	//코멘트 고유번호
	private int bcno;	//게시물 부모번호
	private String writer;
	private String content;
	private String regdate;
	
	public CommentVO() {
		
	}
	
	//댓글 등록
	public CommentVO(int bcno ,String writer ,String content) {
		this.bcno = bcno;
		this.writer =writer;
		this.content = content;
		
	}
	
	//전체
	public CommentVO(int bcno, String writer, String content, String regdate) {
		this.bcno = bcno;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
	}
	
	


	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBcno() {
		return bcno;
	}

	public void setBcno(int bcno) {
		this.bcno = bcno;
	}

	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bcno=" + bcno + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + "]";
	}
	
	
	 
	
}
