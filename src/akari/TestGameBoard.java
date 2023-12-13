package akari;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.Test;

import org.junit.Before;

public class TestGameBoard {
	GameBoard gameBoard;

	@Before
	public void setUp() {
		gameBoard = new GameBoard();
	}

	@Test
	public void testGetBoard() {
        Cell[][] board = gameBoard.getBoard();
        assertNotNull(board);
        assertEquals(7, board.length);
        assertEquals(7, board[0].length);
	}

	@Test
	public void testGetGameCell() {
        Cell cell = gameBoard.getGameCell(2, 3);
        assertNotNull(cell);
        assertEquals(Cell.State.WHITE, cell.getState());
        assertEquals(0, cell.getLightLevel());
        assertEquals(-1, cell.getNumber());
	}

	@Test
	public void testGetCellPanel() {
        assertNotNull(gameBoard.getCellPanel(1, 1));
	}

	@Test
	public void testGetNumOfRows() {
        assertEquals(7, gameBoard.getNumOfRows());
    }

	@Test
	public void testGetNumOfColumns() {
        assertEquals(7, gameBoard.getNumOfColumns());
    }

	@Test
	public void testCheckLamps() {
        assertTrue(gameBoard.checkLamps());
    }

	@Test
	public void testSetCellLit() {
		gameBoard.getGameCell(2, 4).addLight();
        gameBoard.setCellLit(2, 4);
        assertEquals(Color.YELLOW, gameBoard.getCellPanel(2, 4).getBackground());
	}

	@Test
	public void testSetRow() {
		
		for(int j = 0; j < gameBoard.getNumOfColumns(); j++) {
			gameBoard.getGameCell(0, j).addLight();
		}
		gameBoard.setRow(0, 0);
		for(int j = 0; j < gameBoard.getNumOfColumns(); j++) {
			assertEquals(gameBoard.getCellPanel(0, j).getBackground(), Color.YELLOW);
		}
	}

	@Test
	public void testSetCol() {
		for(int j = 0; j < gameBoard.getNumOfRows(); j++) {
			gameBoard.getGameCell(j, 0).addLight();
		}
		gameBoard.setCol(0, 0);
		for(int j = 0; j < gameBoard.getNumOfRows(); j++) {
			assertEquals(gameBoard.getCellPanel(j, 0).getBackground(), Color.YELLOW);
		}
	}

}
