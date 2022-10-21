package br.com.roberto.velha;

public enum PLAYER {
	PC('X', +1), HUMAM('O', -1), EMPTY(' ', 0);

	char character;
	public int score;

	PLAYER(char ch, int score) {
		this.character = ch;
		this.score = score;
	}
	
	@Override
	public String toString() {
		return Character.toString(character);
	}
}
