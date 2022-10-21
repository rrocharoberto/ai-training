package br.com.roberto.velha;

public class Cell {
	public Position pos;
	public PLAYER player = PLAYER.EMPTY;
	
	public Cell(Position pos, PLAYER player) {
		this.pos = pos;
		this.player = player;
	}

	@Override
	public int hashCode() {
		return pos.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return pos.equals(obj);
	}
}
