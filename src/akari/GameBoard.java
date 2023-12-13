package akari;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import akari.Cell.State;

/**
 * The GameBoard class represents the graphical user interface for the Akari puzzle game.
 * It extends JFrame and includes the game board panel, control panel, and methods for updating the display.
 */
public class GameBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	
    private JPanel gamePanel;          // Panel for displaying the game board
    private Cell[][] board;            // 2D array representing the game board cells
    private JPanel[][] cells;          // 2D array of JPanels representing individual cells on the game board

    private JPanel controlPanel;       // Panel for game controls
    private JButton checkButton;       // Button to check the puzzle solution
    private JButton giveUpButton;      // Button to give up the game
    private JButton saveButton;		   // Button to save the game
    
    /**
     * Constructs a GameBoard object, initializing the graphical user interface.
     */
	public GameBoard() {
		
		init();
	}
	
    /**
     * Gets the 2D array representing the game board cells.
     *
     * @return The game board cells.
     */
    public Cell[][] getBoard() {
        return board;
    }
    
    /**
     * Gets the Cell object at the specified row and column on the game board.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The Cell object at the specified position.
     */
    public Cell getGameCell(int row, int col) {
    	return board[row][col];
    }
    
    /**
     * Gets the JPanel representing an individual cell at the specified row and column.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The JPanel representing the cell.
     */
	public JPanel getCellPanel(int row, int col){
		return cells[row][col];
	}
    
    /**
     * Gets the number of rows in the game board.
     *
     * @return The number of rows.
     */
    public int getNumOfRows() {
    	return board.length;
    }
    
    /**
     * Gets the number of columns in the game board.
     *
     * @return The number of columns.
     */
    public int getNumOfColumns() {
    	return board[0].length;
    }
        
    /**
     * Checks if there are adjacent lamps in each row of the game board.
     * If two lamps are found consecutively in a row, the method returns false, indicating an invalid configuration.
     * Otherwise, it returns true, indicating a valid configuration of lamps.
     *
     * @return true if there are no adjacent lamps in any row, false otherwise.
     */
    public boolean checkLamps() {

        List<State> rowState = new ArrayList<>();
        for(int i = 0; i < getNumOfRows(); i++) {
            for(int j = 0; j < getNumOfColumns(); j++) {
                if(board[i][j].getState() == State.BLACK || board[i][j].getState() == State.LAMP) {
                    rowState.add(board[i][j].getState());
                }
            }

            for(int k = 0; k < rowState.size() - 1; k++) {
                if(rowState.get(k) == State.LAMP && rowState.get(k + 1) == State.LAMP) {
                    return false;
                }
            }
            rowState.clear();
        }
        return true;
    }
    
    /**
     * Sets the background color of the specified cell panel based on its state and light level.
     * If the cell is WHITE or MARKED with a light level greater than 0, or it is a LAMP, the background is set to YELLOW.
     * Otherwise, if the cell is WHITE or MARKED, the background is set to WHITE.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     */
    public void setCellLit(int row, int col) {
    	if(((board[row][col].getState() == State.WHITE || board[row][col].getState() == State.MARKED) && board[row][col].getLightLevel() > 0) ||
    			board[row][col].getState() == State.LAMP) {
    		cells[row][col].setBackground(Color.YELLOW);
    	} else if (board[row][col].getState() == State.WHITE || board[row][col].getState() == State.MARKED) {
    		cells[row][col].setBackground(Color.WHITE);
    	}
    }

    /**
     * Sets the background color of cells in a specified row based on the position of a lamp.
     * The cells before the lamp column are set to YELLOW, and the cells after the lamp column are also set to YELLOW.
     *
     * @param row     The row index of the cells.
     * @param lampCol The column index of the lamp in the row.
     */
    public void setRow(int row, int lampCol) {
    	List<Integer> blackIndex = new ArrayList<>();
    	
    	for(int i = 0; i < getNumOfColumns(); i++) {
    		if(board[row][i].getState() == State.BLACK) {
    			blackIndex.add(i);
    		}
    	}
    	
    	int firstIndex = 0;
    	int lastIndex = getNumOfRows();
    	
    	if(blackIndex.size() == 1) {
		    int current = blackIndex.get(0);
		    if(current < lampCol) {
		    	firstIndex = current;
		    } else {
		    	lastIndex = current;
		    }
    	} else if(blackIndex.size() > 1) {
    		if(lampCol < blackIndex.get(0)) {
    			lastIndex = blackIndex.get(0);
    		} else if(lampCol > blackIndex.get(blackIndex.size() - 1)) {
    			 firstIndex = blackIndex.get(blackIndex.size() - 1);
    		} else {
        		for(int i = 0; i < blackIndex.size() - 1; i++) {
                	int current = blackIndex.get(i);
                	int next = blackIndex.get(i + 1);
                	if(current < lampCol && next > lampCol) {
                		firstIndex = current;
                		lastIndex = next;
                	}
    		    }
    		}
    	}
    	
    	for(int i = firstIndex; i < lampCol; i++) {
    		setCellLit(row, i);
    	}
    	for(int i = lampCol; i < lastIndex; i++) {
    		setCellLit(row, i);
    	}
    }
    
    /**
     * Sets the background color of cells in a specified column based on the position of a lamp.
     * The cells before the lamp row are set to YELLOW, and the cells after the lamp row are also set to YELLOW.
     *
     * @param col     The column index of the cells.
     * @param lampRow The row index of the lamp in the column.
     */
    public void setCol(int col, int lampRow) {
        
    	List<Integer> blackIndex = new ArrayList<>();
    	
    	for(int i = 0; i < getNumOfRows(); i++) {
    		if(board[i][col].getState() == State.BLACK) {
    			blackIndex.add(i);
    		}
    	}
    	
		int firstIndex = 0;
		int lastIndex = getNumOfColumns();
		
    	if(blackIndex.size() == 1) {
		    int current = blackIndex.get(0);
		    if(current < lampRow) {
		    	firstIndex = current;
		    } else {
		    	lastIndex = current;
		    }
    	} else if(blackIndex.size() > 1) {
    		if(lampRow < blackIndex.get(0)) {
    			lastIndex = blackIndex.get(0);
    		} else if(lampRow > blackIndex.get(blackIndex.size() - 1)) {
    			 firstIndex = blackIndex.get(blackIndex.size() - 1);
    		} else {
        		for(int i = 0; i < blackIndex.size() - 1; i++) {
                	int current = blackIndex.get(i);
                	int next = blackIndex.get(i + 1);
                	if(current < lampRow && next > lampRow) {
                		firstIndex = current;
                		lastIndex = next;
                	}
    		    }
    		}
    	}
		    	
    	for(int i = firstIndex; i < lampRow; i++) {
    		setCellLit(i, col);
    	}
    	for(int i = lampRow; i < lastIndex; i++) {
    		setCellLit(i, col);
    	}
    }
    
    /**
     * Initializes the graphical user interface by setting up the frame and creating the game and control panels.
     */
	private void init() {
		
		setTitle("AKARI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        createBoard();
        createGamePanel();
        createControlPanel();
        
        add(gamePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        
        setVisible(true);
	}
	
	/**
	 * Initializes the game board by creating a 2D array of Cell objects with an initial state of WHITE.
	 * The size of the board is 7x7.
	 */
    private void createBoard() {
    	
		board = new Cell[7][7];
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				board[i][j] = new Cell(Cell.State.WHITE);
			}
		}

    }
    
    /**
     * Creates the control panel for the game interface.
     * It includes "Ellenőrzés", "Feladás" and "Mentés" buttons with associated actions.
     */
    private void createControlPanel() {
        controlPanel = new JPanel();
        checkButton = new JButton("Ellenőrzés");
        giveUpButton = new JButton("Feladás");
        saveButton = new JButton("Mentés");
                
        // Associate the buttons with the corresponding method
        GameLogic game = new GameLogic(this);
        checkButton.addActionListener(e -> game.gameEnded());
        giveUpButton.addActionListener(e -> game.giveUp());
        saveButton.addActionListener(e -> {
			try {
				game.saveGame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

        controlPanel.add(checkButton);
        controlPanel.add(giveUpButton);
        controlPanel.add(saveButton);
    }    
    
    /**
     * Creates the game panel for displaying the game board.
     * Each cell is represented by a JPanel with appropriate background color and content based on the cell's state.
     *
     * @return The created game panel.
     */
	private JPanel createGamePanel() {
		
		int row = getNumOfRows();
		int col = getNumOfColumns();
        gamePanel = new JPanel(new GridLayout(row, col));
        cells = new JPanel[row][col];
        
        for(int i = 0; i < row; i++) {
        	for(int j = 0; j < col; j++) {
        		cells[i][j] = new JPanel();
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                // Set the background color and content of the cell based on its state
                switch (board[i][j].getState()) {
                case WHITE:
                    cells[i][j].setBackground(Color.WHITE);
                    break;

                case BLACK:
                    if (board[i][j].getNumber() != -1) {
                        // Add a JLabel with the cell number to the cell
                        String cellNumber = Integer.toString(board[i][j].getNumber());
                        JLabel numberLabel = new JLabel(cellNumber);
                        numberLabel.setHorizontalAlignment(JLabel.CENTER);
                        numberLabel.setVerticalAlignment(JLabel.CENTER);
                        numberLabel.setFont(new Font(numberLabel.getName(), Font.PLAIN, 60));
                        numberLabel.setBackground(Color.BLACK);
                        numberLabel.setForeground(Color.WHITE);
                        cells[i][j].setBackground(Color.BLACK);
                        cells[i][j].add(numberLabel);
                    } else {
                        cells[i][j].setBackground(Color.BLACK);
                    }
                    break;

                default:
                    break;
            }
                // Add the cell panel to the game panel
                gamePanel.add(cells[i][j]);
        	}
        }
        return gamePanel;
	}
	
	/**
	 * Updates the game panel to reflect changes in the game board.
	 * It adjusts the background color and content of each cell based on its current state.
	 */
    public void updateGamePanel() {
        int row = getNumOfRows();
        int col = getNumOfColumns();
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                switch (board[i][j].getState()) {
                    case WHITE:
                        cells[i][j].setBackground(Color.WHITE);
                        break;

                    case BLACK:
                        if (board[i][j].getNumber() != -1) {
                            // Update the JLabel with the current cell number
                            String cellNumber = Integer.toString(board[i][j].getNumber());
                            JLabel numberLabel = new JLabel(cellNumber);
                            numberLabel.setHorizontalAlignment(JLabel.CENTER);
                            numberLabel.setVerticalAlignment(JLabel.CENTER);
                            numberLabel.setFont(new Font(numberLabel.getName(), Font.PLAIN, 60));
                            numberLabel.setBackground(Color.BLACK);
                            numberLabel.setForeground(Color.WHITE);
                            cells[i][j].setBackground(Color.BLACK);
                            
                            // Remove existing components and add the updated JLabel to the cell
                            cells[i][j].removeAll();
                            cells[i][j].add(numberLabel);
                        } else {
                            cells[i][j].setBackground(Color.BLACK);
                            cells[i][j].removeAll();
                        }
                        break;
                        
                    case LAMP:
        	            // Increase the light level of cells in the same row and column as the lamp
        	            for (int _row = i; _row >= 0; _row--) {
        	                if (board[_row][j].getState() == State.BLACK) {
        	                    break;
        	                }
        	                board[_row][j].addLight();
        	            }
        	            for (int _row = i; _row < getNumOfRows(); _row++) {
        	                if (board[_row][j].getState() == State.BLACK) {
        	                    break;
        	                }
        	                board[_row][j].addLight();
        	            }
        	            for (int _col = j; _col >= 0; _col--) {
        	                if (board[i][_col].getState() == State.BLACK) {
        	                    break;
        	                }
        	                board[i][_col].addLight();
        	            }
        	            for (int _col = j; _col < getNumOfColumns(); _col++) {
        	                if (board[i][_col].getState() == State.BLACK) {
        	                    break;
        	                }
        	                board[i][_col].addLight();
        	            }

        	            // Decrease the light level of the current cell after increasing it 4 times
        	            board[i][j].subLight();
        	            board[i][j].subLight();
        	            board[i][j].subLight();

        	            // Set the lamp image to the current cell and update its state
        	            ImageIcon image = new ImageIcon("rsc/lightBulb.png");
        	            JLabel lightBulb = new JLabel(image);
        	            cells[i][j].add(lightBulb);
                    	break;
                    	
                    case MARKED:
        	            ImageIcon xImage = new ImageIcon("rsc/xIcon.png");
        	            JLabel xIcon = new JLabel(xImage);
        	            cells[i][j].add(xIcon);
                    	break;
                        
                    default:
                        break;
                }
                setRow(i, j);
                setCol(i, j);
            }
        }
        
        // Refresh the game panel to reflect the changes
        gamePanel.revalidate();
        gamePanel.repaint();
    }
        
}