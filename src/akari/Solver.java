/*package akari;

import java.util.HashMap;
import java.util.Map;

import akari.Cell.State;

public class Solver {
	
	private GameBoard gameBoard;
	
	public Solver(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public void solve() {
		
		patternMatch();
		localSearch();
	}
	
	public void patternMatch() {
		
		patternOne();
		patternTwo();
		patternThree();
		patternFour();
	}
	
	public void localSearch() {
		
	}
	
	public void patternOne() {
		
		Map<Integer, Integer> blackMap = blackSquaresWithNumbers();
		
		for(Map.Entry<Integer, Integer> entry : blackMap.entrySet()) {
			
			int row = entry.getKey();
			int col = entry.getValue();
			
			int spaceI = spaceI(row, col);
			int haveI = haveI(row, col);
			int needI = gameBoard.getGameCell(row, col).getNumber();

			if(gameBoard.getGameCell(row, col).getNumber() == 4) {
				gameBoard.getGameCell(row + 1, col).setState(State.LAMP);
				gameBoard.getGameCell(row - 1, col).setState(State.LAMP);
				gameBoard.getGameCell(row, col + 1).setState(State.LAMP);
				gameBoard.getGameCell(row, col - 1).setState(State.LAMP);
			} else if(gameBoard.getGameCell(row, col).getNumber() == 3) {
				if()
			}
		}
	}
	
	public void patternTwo() {
		
	}

	public void patternThree() {
		
	}

	public void patternFour() {
		
	}
	
	public Map<Integer, Integer> blackSquaresWithNumbers() {
		
		Map<Integer, Integer> blackMap = new HashMap<>();
		for(int i = 0; i < gameBoard.getNumOfRows(); i++) {
			for(int j = 0; j < gameBoard.getNumOfColumns(); j++) {
				if(gameBoard.getGameCell(i, j).getState() == State.BLACK && gameBoard.getGameCell(i, j).getNumber() > -1) {
					blackMap.put(i, j);
				}
			}
		}
		return blackMap;
	}
	
	public int spaceI(int row, int col) {
		
		int spaceI = 0;
		if(row + 1 < gameBoard.getNumOfRows() && gameBoard.getGameCell(row + 1, col).getState() == State.WHITE) { spaceI++; }
		if(row - 1 >= 0 && gameBoard.getGameCell(row - 1, col).getState() == State.WHITE) { spaceI++; }
		if(col + 1 < gameBoard.getNumOfColumns() && gameBoard.getGameCell(row, col + 1).getState() == State.WHITE) { spaceI++; }
		if(col - 1 >= 0 && gameBoard.getGameCell(row, col - 1).getState() == State.WHITE) { spaceI++; }
		
		return spaceI;
	}
	
	public int haveI(int row, int col) {
		
		int haveI = 0;
		if(row + 1 < gameBoard.getNumOfRows() && gameBoard.getGameCell(row + 1, col).getState() == State.LAMP) { haveI++; }
		if(row - 1 >= 0 && gameBoard.getGameCell(row - 1, col).getState() == State.LAMP) { haveI++; }
		if(col + 1 < gameBoard.getNumOfColumns() && gameBoard.getGameCell(row, col + 1).getState() == State.LAMP) { haveI++; }
		if(col - 1 >= 0 && gameBoard.getGameCell(row, col - 1).getState() == State.LAMP) { haveI++; }
		
		return haveI;
	}

	
}*/