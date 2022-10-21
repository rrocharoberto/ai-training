package br.com.roberto.velha;

import java.util.Iterator;

public class BoardIterator implements Iterator<Cell> {

	private BoardMap board;
	private int count = 0;
	
	public BoardIterator(BoardMap board) {
		this.board = board;
	}
	
	@Override
	public boolean hasNext() {
		return count <= board.size();
	}
	
	@Override
	public Cell next() {
		int col = count % board.length();
		int row = count / board.length();
		count++;
		Position pos = new Position(col, row);
		return new Cell(pos, board.getPlayerAt(pos));
	}
	
	public boolean isLastRow() {
		int row = count / board.length();
		return row == 2;
	}
	
	public boolean isLastColumn() {
		int col = count % board.length();
		return col == 2;
	}
}
