package com.example.domain;

import lombok.Data;

@Data // lombok으로 게터세터 처리
public class Board {
	
	private int boardSeq;
	private String boardType;
	private String title;
	private String contents;
	private String regDate;
	private String userName;
	
//	public String getBoardType() {
//		return boardType;
//	}
//	public void setBoardType(String boardType) {
//		this.boardType = boardType;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getRegDate() {
//		return regDate;
//	}
//	public void setRegDate(String regDate) {
//		this.regDate = regDate;
//	}
//	
//	public int getBoardSeq() {
//		return boardSeq;
//	}
//	public void setBoardSeq(int boardSeq) {
//		this.boardSeq = boardSeq;
//	}
//	public String getContents() {
//		return contents;
//	}
//	public void setContents(String contents) {
//		this.contents = contents;
//	}
	
}
