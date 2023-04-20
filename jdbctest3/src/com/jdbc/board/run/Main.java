package com.jdbc.board.run;

import com.jdbc.board.controller.BoardController;

public class Main {
	public static void main(String[] args) {
		new BoardController().mainMenu();
		System.out.println("내가 추가한것");
	}

	
}
