package akari;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import akari.Cell.State;

/**
 * The GameLogic class handles the game logic for the Akari puzzle.
 * It checks the game state, handles the end of the game, and manages player moves.
 */
public class GameLogic {
	
    /**
     * Constructor for the GameLogic class.
     *
     * @param gameBoard The GameBoard instance associated with the game logic.
     */
    private GameBoard gameBoard;
    
    public GameLogic(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    /**
     * Checks the current state of the game.
     *
     * @return True if the game rules are violated, false otherwise.
     */
    public boolean checkGame() {

    	boolean brokeRules = false;
    	for(int i = 0; i < gameBoard.getNumOfRows(); i++) {
        	for(int j = 0; j < gameBoard.getNumOfColumns(); j++) {
        		if(gameBoard.getCellPanel(i, j).getBackground() == Color.WHITE) {
					
        			brokeRules = true;

        		} else if(!brokeRules && gameBoard.getGameCell(i, j).getState() == State.BLACK && gameBoard.getGameCell(i, j).getNumber() >= 0) {
					
					int numberOfLamps = 0;

					if (i + 1 < gameBoard.getNumOfRows() && gameBoard.getGameCell(i + 1, j).getState() == State.LAMP) { numberOfLamps++; }
					if (i - 1 >= 0 && gameBoard.getGameCell(i - 1, j).getState() == State.LAMP) { numberOfLamps++; }
					if (j + 1 < gameBoard.getNumOfColumns() && gameBoard.getGameCell(i, j + 1).getState() == State.LAMP) { numberOfLamps++; }
					if (j - 1 >= 0 && gameBoard.getGameCell(i, j - 1).getState() == State.LAMP) { numberOfLamps++; }

					if(!brokeRules && numberOfLamps != gameBoard.getGameCell(i, j).getNumber()) {
						brokeRules = true;
					} else if(!gameBoard.checkLamps()) {
						brokeRules = true;

					}
				}
        	}
    	}

    	if(brokeRules) {
    		return true;
    	} else {
    		return false;
    	}

    }
    

    /**
     * Handles the end of the game, showing a message dialog based on the game state.
     */
    public void gameEnded() {
    if(checkGame()) {
    	JOptionPane.showMessageDialog(null, "A megoldásod nem helyes, próbálkozz tovább!");
       	} else {
    		JOptionPane.showMessageDialog(null, "A megoldás helyes!");
       		gameBoard.setVisible(false);
           	Menu menu = new Menu();
       	}
    }
    
    /**
     * Handles the player giving up, returning to the main menu.
     */
    public void giveUp() {
		gameBoard.setVisible(false);
        Menu menu = new Menu();
    }
        
    public void saveGame() throws Exception {
    	
        File folder = new File("saveFiles/");
        File[] files = folder.listFiles();
        int fileCount = 1;
        for (File file : files) {
            if (file.isFile()) {
                fileCount++;
            }
        }

    	String saveFolderPath = "saveFiles/";
        String outputFileName = "board" + fileCount + ".xml";
        String outputFilePath = saveFolderPath + outputFileName;
        FileHandler writer = new FileHandler(gameBoard);
        writer.createFileFromBoard(outputFilePath);
    }
    
    /**
     * Sets up mouse listeners for player moves on the game board.
     */
	public void playerMove() {
    	
    	int row = gameBoard.getNumOfRows();
    	int col = gameBoard.getNumOfColumns();
    	
    	for(int i = 0; i < row; i++) {
    		for(int j = 0; j < col; j++) {
    			JPanel cell = gameBoard.getCellPanel(i, j);
             	cell.addMouseListener(new MouseListener(gameBoard, cell, i, j));
    		}
    	}
    }
        
    /**
     * Inner class that implements the MouseListener for handling player moves.
     */
    private class MouseListener extends MouseAdapter {
    	
    	private GameBoard gameBoard;
    	private JPanel cell;
    	private int crtRow;
    	private int crtCol;
    	
        /**
         * Constructor for the MouseListener class.
         *
         * @param gameBoard The GameBoard instance associated with the mouse listener.
         * @param cell      The JPanel cell being clicked.
         * @param crtRow    The current row of the clicked cell.
         * @param crtCol    The current column of the clicked cell.
         */
    	public MouseListener(GameBoard gameBoard, JPanel cell, int crtRow, int crtCol) {
    		this.gameBoard = gameBoard;
    		this.cell = cell;
    		this.crtRow = crtRow;
    		this.crtCol = crtCol;
    	}
    	
    	/**
    	 * Invoked when a mouse button is pressed on the associated cell.
    	 *
    	 * @param e The MouseEvent representing the mouse press event.
    	 */
    	@Override
    	public void mousePressed(MouseEvent e) {

    	    // Retrieve the number of rows and columns on the game board
    	    int row = gameBoard.getNumOfRows();
    	    int col = gameBoard.getNumOfColumns();

    	    // Get the 2D array of game cells from the game board
    	    Cell[][] gameCells = gameBoard.getBoard();

    	    // Flag to track if the mouse has entered a block on the game board
    	    boolean enteredBlock = false;

    	    // Check if the left mouse button is pressed
    	    if (SwingUtilities.isLeftMouseButton(e)) {

    	        // Check if the current cell is in a BLACK state
    	        if (gameCells[crtRow][crtCol].getState() == State.BLACK) {
    	            enteredBlock = true;
    	        }
    	        // Check if the current cell is in a WHITE or MARKED state
    	        else if (!enteredBlock && (gameCells[crtRow][crtCol].getState() == State.WHITE ||
    	                gameCells[crtRow][crtCol].getState() == State.MARKED)) {

    	            // Increase the light level of cells in the same row and column as the lamp
    	            for (int i = crtRow; i >= 0; i--) {
    	                if (gameCells[i][crtCol].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[i][crtCol].addLight();
    	            }
    	            for (int i = crtRow; i < row; i++) {
    	                if (gameCells[i][crtCol].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[i][crtCol].addLight();
    	            }
    	            for (int j = crtCol; j >= 0; j--) {
    	                if (gameCells[crtRow][j].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[crtRow][j].addLight();
    	            }
    	            for (int j = crtCol; j < col; j++) {
    	                if (gameCells[crtRow][j].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[crtRow][j].addLight();
    	            }

    	            // Decrease the light level of the current cell after increasing it 4 times
    	            gameCells[crtRow][crtCol].subLight();
    	            gameCells[crtRow][crtCol].subLight();
    	            gameCells[crtRow][crtCol].subLight();

    	            // Set the lamp image to the current cell and update its state
    	            cell.removeAll();
    	            ImageIcon image = new ImageIcon("rsc/lightBulb.png");
    	            JLabel lightBulb = new JLabel(image);
    	            cell.add(lightBulb);
    	            gameCells[crtRow][crtCol].setState(State.LAMP);

    	            enteredBlock = true;
    	        }
    	        // Check if the current cell is in a LAMP state
    	        else if (gameCells[crtRow][crtCol].getState() == State.LAMP) {

    	            // Decrease the light level of cells in the same row and column as the lamp
    	            for (int i = crtRow; i >= 0; i--) {
    	                if (gameCells[i][crtCol].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[i][crtCol].subLight();
    	            }
    	            for (int i = crtRow; i < row; i++) {
    	                if (gameCells[i][crtCol].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[i][crtCol].subLight();
    	            }
    	            for (int j = crtCol; j >= 0; j--) {
    	                if (gameCells[crtRow][j].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[crtRow][j].subLight();
    	            }
    	            for (int j = crtCol; j < col; j++) {
    	                if (gameCells[crtRow][j].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[crtRow][j].subLight();
    	            }

    	            // Increase the light level of the current cell after decreasing it 4 times
    	            gameCells[crtRow][crtCol].addLight();
    	            gameCells[crtRow][crtCol].addLight();
    	            gameCells[crtRow][crtCol].addLight();

    	            // Set the cell to WHITE state and update its state
    	            cell.removeAll();
    	            gameCells[crtRow][crtCol].setState(State.WHITE);

    	            enteredBlock = true;
    	        }

    	        // Revalidate and repaint the cell to update its appearance
    	        cell.revalidate();
    	        cell.repaint();

    	        // Update the game board with the current cell's row and column
    	        gameBoard.setRow(crtRow, crtCol);
    	        gameBoard.setCol(crtCol, crtRow);

    	    }
    	    // Check if the right mouse button is pressed
    	    else if (SwingUtilities.isRightMouseButton(e)) {

    	        // Check if the current cell is in a BLACK state
    	        if (gameCells[crtRow][crtCol].getState() == State.BLACK) {
    	            enteredBlock = true;
    	        }
    	        // Check if the current cell is in a WHITE state
    	        else if (!enteredBlock && gameCells[crtRow][crtCol].getState() == State.WHITE) {

    	            // Set the X icon to the current cell and update its state
    	            ImageIcon image = new ImageIcon("rsc/xIcon.png");
    	            JLabel xIcon = new JLabel(image);
    	            cell.add(xIcon);
    	            gameCells[crtRow][crtCol].setState(State.MARKED);

    	            enteredBlock = true;
    	        }
    	        // Check if the current cell is in a LAMP state
    	        else if (!enteredBlock && gameCells[crtRow][crtCol].getState() == State.LAMP) {

    	            // Decrease the light level of cells in the same row and column as the lamp
    	            for (int i = crtRow; i >= 0; i--) {
    	                if (gameCells[i][crtCol].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[i][crtCol].subLight();
    	            }
    	            for (int i = crtRow; i < row; i++) {
    	                if (gameCells[i][crtCol].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[i][crtCol].subLight();
    	            }
    	            for (int j = crtCol; j >= 0; j--) {
    	                if (gameCells[crtRow][j].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[crtRow][j].subLight();
    	            }
    	            for (int j = crtCol; j < col; j++) {
    	                if (gameCells[crtRow][j].getState() == State.BLACK) {
    	                    break;
    	                }
    	                gameCells[crtRow][j].subLight();
    	            }

    	            // Increase the light level of the current cell after decreasing it 4 times
    	            gameCells[crtRow][crtCol].addLight();
    	            gameCells[crtRow][crtCol].addLight();
    	            gameCells[crtRow][crtCol].addLight();

    	            // Set the X icon to the current cell and update its state
    	            cell.removeAll();
    	            ImageIcon image = new ImageIcon("rsc/xIcon.png");
    	            JLabel xIcon = new JLabel(image);
    	            cell.add(xIcon);
    	            gameCells[crtRow][crtCol].setState(State.MARKED);

    	            enteredBlock = true;
    	        }
    	        // Check if the current cell is in a MARKED state
    	        else if (!enteredBlock && gameCells[crtRow][crtCol].getState() == State.MARKED) {

    	            // Set the current cell to WHITE state and update its state
    	            cell.removeAll();
    	            gameCells[crtRow][crtCol].setState(State.WHITE);

    	            enteredBlock = true;
    	        }

    	        // Revalidate and repaint the cell to update its appearance
    	        cell.revalidate();
    	        cell.repaint();

    	        // Update the game board with the current cell's row and column
    	        gameBoard.setRow(crtRow, crtCol);
    	        gameBoard.setCol(crtCol, crtRow);
    	    }
    	}
    }
    
}
