package com.jdbc.board.model.dao;

import static com.jdbc.board.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jdbc.board.model.dto.Board;
import com.jdbc.board.model.dto.BoardComment;

//1.BS계정의 board테이블에서 board테이블에 저장된 게시글을 조회하는 기능을 구현
//2. 연결된 댓글도 조회하기 -> 게시글의 댓글 수를 같이 출력(테이블 옆에 출력

public class BoardDao {
	
	private Properties sql=new Properties();
	
	{
		String path=BoardDao.class
				.getResource("/sql/board/board_sql.properties").getPath();
		try(FileReader fr=new FileReader(path);){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Board> selectBoardAll(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=this.sql.getProperty("selectBoardAll");
		List<Board> boards=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=getBoard(rs);
				b.setComments(selectBoardComment(conn, b.getBoardNo()));
				boards.add(b);
				//boards.add(getBoard(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boards;
			
	}
	public List<BoardComment> selectBoardComment(Connection conn,int boardNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardComment> comments=new ArrayList();
		String sql=this.sql.getProperty("selectBoardCommentByBoardNo");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			while(rs.next()) 
				comments.add(getComment(rs));
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return comments;
	}
	
	public List<Board> searchBoard(Connection conn, Map param){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> boards=new ArrayList();
		String sql=this.sql.getProperty("selectBoardByCol");
		sql=sql.replace("#COL", (String)param.get("col"));
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+(String)param.get("keyword")+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=getBoard(rs);
				b.setComments(selectBoardComment(conn, b.getBoardNo()));
				boards.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boards;
	}
	
	
	
	private BoardComment getComment(ResultSet rs) throws SQLException{
		BoardComment bc=new BoardComment();
		bc.setCommentNo(rs.getInt("comment_no"));
		bc.setBoardConect(rs.getString("comment_content"));
		bc.setCommentWirter(rs.getString("comment_writer"));
		bc.setCommentDate(rs.getDate("comment_date"));
		return bc;
	}
	
	
	private Board getBoard(ResultSet rs) throws SQLException {
		Board b=new Board();
		b.setBoardNo(rs.getInt("board_no"));
		b.setBoardTitle(rs.getString("board_title"));
		b.setBoardContent(rs.getString("board_content"));
		b.setBoardWriter(rs.getString("board_writer"));
		b.setBoardDate(rs.getDate("board_date"));
		return b;
	}
	
	
}









