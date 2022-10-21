package br.com.roberto.velha.ai;

import java.util.function.BiFunction;

import br.com.roberto.velha.BoardIterator;
import br.com.roberto.velha.BoardMap;
import br.com.roberto.velha.Cell;
import br.com.roberto.velha.PLAYER;
import br.com.roberto.velha.Position;

public class MinimaxStategy implements SearchStategy {

	private BoardMap board;

	@Override
	public Position getNextPosition() {
		return getBestPosition();
	}

	public void setBoard(BoardMap board) {
		this.board = board;
	}
	
	private Position getBestPosition() {
		int bestScore = Integer.MIN_VALUE;
		Position bestMove = null;
		BoardIterator iterator = board.iterator();

		while (iterator.hasNext()) {
			Cell cell = iterator.next();
			if (cell.player == PLAYER.EMPTY) {
				board.setMove(PLAYER.PC, cell.pos);
				int score = minimax(0, false); // call minimax for each empty cell
				board.setMove(PLAYER.EMPTY, cell.pos);
				if (score > bestScore) {
					bestScore = score;
					bestMove = cell.pos;
				}
			}
		}
		return bestMove;
	}

	private int minimax(int depth, boolean isMaximizing) {
		PLAYER winner = board.checkWinner();
		if (winner != null) { // critério de parada
			return winner.score;
		}
		PLAYER player;
		int bestScore;
		BiFunction<Integer, Integer, Integer> checker = null;
		if (isMaximizing) {
			player = PLAYER.PC;
			bestScore = Integer.MIN_VALUE;
			checker = (x, y) -> Integer.max(x, y);
		} else {
			player = PLAYER.HUMAM;
			bestScore = Integer.MAX_VALUE;
			checker = (x, y) -> Integer.min(x, y);
		}
		BoardIterator iterator = board.iterator();
		while (iterator.hasNext()) {
			Cell cell = iterator.next();
			if (cell.player == PLAYER.EMPTY) {
				board.setMove(player, cell.pos);
				int score = minimax(depth + 1, !isMaximizing);
				board.setMove(PLAYER.EMPTY, cell.pos);
				bestScore = checker.apply(score, bestScore);
			}
		}
		return bestScore;
	}
}
