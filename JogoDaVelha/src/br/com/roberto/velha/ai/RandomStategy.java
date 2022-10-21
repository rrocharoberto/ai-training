package br.com.roberto.velha.ai;

import br.com.roberto.velha.BoardMap;
import br.com.roberto.velha.Position;

public class RandomStategy implements SearchStategy {

	private BoardMap board;

	@Override
	public Position getNextPosition() {
		return board.getRandomEmptyPosition();
	}

	public void setBoard(BoardMap board) {
		this.board = board;
	}
}