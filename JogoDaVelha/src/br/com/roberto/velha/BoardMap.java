package br.com.roberto.velha;

import java.util.HashMap;
import java.util.Map;

import br.com.roberto.velha.ai.SearchStategy;

public class BoardMap {

	private SearchStategy searchStategy;
	
	private Map<Position, PLAYER> map = new HashMap<>();
	
	private int amount = 0;
	
	public BoardMap(SearchStategy searchStategy) {
		this.searchStategy = searchStategy;
		setMove(PLAYER.EMPTY, new Position(0, 0));
		setMove(PLAYER.EMPTY, new Position(0, 1));
		setMove(PLAYER.EMPTY, new Position(0, 2));
		setMove(PLAYER.EMPTY, new Position(1, 0));
		setMove(PLAYER.EMPTY, new Position(1, 1));
		setMove(PLAYER.EMPTY, new Position(1, 2));
		setMove(PLAYER.EMPTY, new Position(2, 0));
		setMove(PLAYER.EMPTY, new Position(2, 1));
		setMove(PLAYER.EMPTY, new Position(2, 2));
	}

	public void setMove(PLAYER player, Position pos) {
		map.put(pos, player);
		if (PLAYER.EMPTY.equals(player)) {
			amount--;
		} else {
			amount++;
		}
	}

	public boolean isEmpty(Position pos) {
		return map.get(pos) == null || PLAYER.EMPTY.equals(map.get(pos));
	}

	public boolean gameFinished() {
		return amount >= 9;
	}

	private boolean equals(PLAYER a, PLAYER b, PLAYER c) {
		return a != null && a != PLAYER.EMPTY && a.equals(b) && b.equals(c);
	}
	
	public int length() {
		return 3;
	}
	
	public int size() {
		return map.size();
	}
	
	public PLAYER getPlayerAt(Position pos) {
		return map.get(pos);
	}

	private PLAYER getPlayerAt(int i, int j) {
		return map.get(new Position(i, j));
	}

	public void nextPCTurn() {
		Position pos = searchStategy.getNextPosition();
		if (pos != null) {
			this.setMove(PLAYER.PC, pos);
		} else {
			System.out.println("Fim de jogo. Sem opções disponíveis.");
		}
	}

	public void nextHumanTurn(Position pos) {
		if (isEmpty(pos)) { // está livre
			setMove(PLAYER.HUMAM, pos);
		} else {
			System.out.println("Essa posição já está ocupada.");
		}
	}

	public PLAYER checkWinner() {
		PLAYER winner = null;
		// horizontal
		for (int i = 0; i < 3; i++) {
			if (equals(getPlayerAt(i,0), getPlayerAt(i,1), getPlayerAt(i,2))) {
				winner = getPlayerAt(i,0);
			}
		}
		// vertical
		for (int i = 0; i < 3; i++) {
			if (equals(getPlayerAt(0,i), getPlayerAt(1,i), getPlayerAt(2,i))) {
				winner = getPlayerAt(0,i);
			}
		}
		// diagonal
		if (equals(getPlayerAt(0,0), getPlayerAt(1,1), getPlayerAt(2,2))) {
			winner = getPlayerAt(0,0);
		}
		if (equals(getPlayerAt(0,2), getPlayerAt(1,1), getPlayerAt(2,0))) {
			winner = getPlayerAt(0,2);
		}
		if (winner == null && gameFinished()) { // Empate
			return PLAYER.EMPTY;
		}
		return winner;
	}
	
	//utility methods
	public Position getRandomEmptyPosition() {
		for (Position pos : map.keySet()) {
			if(map.get(pos).equals(PLAYER.EMPTY)) {
				return pos;
			}
		}
		return null;
	}

	public BoardIterator iterator() {
		return new BoardIterator(this);
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();

		for (int row = 0; row < length(); row++) {
			for (int col = 0; col < length(); col++) {
				Position pos = new Position(row, col);
				PLAYER player = map.get(pos);
				string.append(" ")
					.append(player != null ? player : PLAYER.EMPTY)
					.append(" ");
				if (col < 2) {
					string.append("|");
				}
			}
			if (row < 2) {
				string.append("\n-----------\n");
			}
		}
		string.append("\n\n");
		return string.toString();
	}
}
