package ezen.toyBoard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private String moddate;

	public BoardVO() {
	}
	
	//글 쓰기
	public BoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	
	
	//리스트 , 수정
	public BoardVO(int bno, String title, String content, String regdate) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	
	//디테일
	public BoardVO(int bno, String title, String writer, String content, String regdate, String moddate) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.moddate = moddate;
	}

	
	
}
