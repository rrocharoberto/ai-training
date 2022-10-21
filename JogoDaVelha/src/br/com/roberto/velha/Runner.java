package br.com.roberto.velha;

import java.util.Scanner;

import br.com.roberto.velha.ai.MinimaxStategy;
import br.com.roberto.velha.ai.RandomStategy;

public class Runner {

	private BoardMap board;

	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.setAIStrategy();
		runner.executeGame();
	}
	
	private void setAIStrategy() {
		RandomStategy aiStrategy = new RandomStategy();
//		MinimaxStategy aiStrategy = new MinimaxStategy();
		board = new BoardMap(aiStrategy);
		aiStrategy.setBoard(board);
	}
	
	public void executeGame() {
		System.out.println(board);
		while (!board.gameFinished()) {
			System.out.print("Digite sua jogada (x e y): ");
			int x = sc.nextInt();
			int y = sc.nextInt();
			Position pos = new Position(x, y);

			if (board.isEmpty(pos)) {
				board.nextHumanTurn(pos);
				if (checkWinner()) {
					break;
				}
				board.nextPCTurn();
				if (checkWinner()) {
					break;
				}
			} else {
				System.out.println("Essa posição já está ocupada.");
			}
			System.out.print(board);
		}
		System.out.println("Tabuleiro final: \n" + board);
	}

	public boolean checkWinner() {
		PLAYER winner = board.checkWinner();
		if (winner == null) {
			return false;
		}
		if (PLAYER.HUMAM.equals(winner)) {
			System.out.println("O humano ganhou!");
		} else if (PLAYER.PC.equals(winner)) {
			System.out.println("O PC ganhou!");
		} else {
			System.out.println("Empate!");
		}
		return true;
	}

	public void test() {
		System.out.println(board);
		board.setMove(PLAYER.PC, new Position(1, 1));
		System.out.println(board);
		board.setMove(PLAYER.HUMAM, new Position(1, 3));
		System.out.println(board);
		board.setMove(PLAYER.PC, new Position(3, 2));
		System.out.println(board);
	}
}
