package com.jdbc.board.model.dto;

import java.sql.Date;
import java.util.Objects;

public class BoardComment {
	private int commentNo;
	private String boardConect;
	private String commentWirter;
	// 원래는 member써야 함
	private Date commentDate;
	
	public BoardComment() {
		// TODO Auto-generated constructor stub
	}

	public BoardComment(int commentNo, String boardConect, String commentWirter, Date commentDate) {
		super();
		this.commentNo = commentNo;
		this.boardConect = boardConect;
		this.commentWirter = commentWirter;
		this.commentDate = commentDate;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getBoardConect() {
		return boardConect;
	}

	public void setBoardConect(String boardConect) {
		this.boardConect = boardConect;
	}

	public String getCommentWirter() {
		return commentWirter;
	}

	public void setCommentWirter(String commentWirter) {
		this.commentWirter = commentWirter;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "BoardComment [commentNo=" + commentNo + ", boardConect=" + boardConect + ", commentWirter="
				+ commentWirter + ", commentDate=" + commentDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(boardConect, commentDate, commentNo, commentWirter);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardComment other = (BoardComment) obj;
		return Objects.equals(boardConect, other.boardConect) && Objects.equals(commentDate, other.commentDate)
				&& commentNo == other.commentNo && Objects.equals(commentWirter, other.commentWirter);
	}
	
	
}
