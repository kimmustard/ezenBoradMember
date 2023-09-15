package domain;

public class CommentVO {

	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String regedate;

	public CommentVO() {

	}
	
	
	//작성
	public CommentVO(int bno, String writer, String content) {
		this.bno = bno;
		this.writer = writer;
		this.content = content;
	}
	
	//수정
	public CommentVO(int cno, String content) {
		this.cno = cno;
		this.content = content;
	}
	

	//전체조회
	public CommentVO(int cno, int bno, String writer, String content, String regedate) {
		this.cno = cno;
		this.bno = bno;
		this.writer = writer;
		this.content = content;
		this.regedate = regedate;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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

	public String getRegedate() {
		return regedate;
	}

	public void setRegedate(String regedate) {
		this.regedate = regedate;
	}

	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", writer=" + writer + ", content=" + content + ", regedate="
				+ regedate + "]";
	}

	
	
}


