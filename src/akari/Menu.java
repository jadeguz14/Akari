package akari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The Menu class represents the main menu of the Akari game.
 * It provides options for starting a new game, loading a saved game, viewing game rules, and exiting the application.
 */
public class Menu {

    /**
     * Constructor for the Menu class.
     * Initializes the menu by creating the main JFrame, adding components, and setting up event listeners.
     */
    public Menu() {

        JFrame frame = createFrame("AKARI");

        JPanel panel = createPanel();

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = createFileMenu(frame);
        menuBar.add(fileMenu);

        String[] options = { "könnyű", "közepes", "nehéz" };
        JComboBox<String> diffBox = new JComboBox<>(options);
        diffBox.setPreferredSize(new Dimension(100, 10));
        diffBox.setMaximumSize(new Dimension(300, 50));


        JButton startButton = createStartButton("Játék indítása", diffBox, frame);

        panel.add(Box.createRigidArea(new Dimension(0, 150)));
        panel.add(startButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(diffBox);
        panel.add(Box.createRigidArea(new Dimension(0, 200)));

        frame.add(panel);

    }

    /**
     * Creates a JFrame with specified text and settings.
     *
     * @param text The title of the JFrame.
     * @return The created JFrame.
     */
    public JFrame createFrame(String text) {
        JFrame frame = new JFrame(text);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Creates a JPanel with BoxLayout and a specified background color.
     *
     * @return The created JPanel.
     */
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLUE);
        return panel;
    }

    /**
     * Creates a file menu for the application with options for loading, viewing rules, and exiting the game.
     *
     * @param frame The main JFrame of the application.
     * @return The created file menu.
     */
    public JMenu createFileMenu(JFrame frame) {
        JMenu fileMenu = new JMenu("Fájl");

        JMenuItem openItem = new JMenuItem("Betöltés");

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					loadGame();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                frame.setVisible(false);
            }
        });

        JMenuItem rulesItem = new JMenuItem("Szabályok");

        /**
         * Displays the game rules in a JOptionPane.
         */
        rulesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = "";
                try {
                    content = new String(Files.readAllBytes(Paths.get("rsc/rules.txt")));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, content, "Szabályok", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem exitItem = new JMenuItem("Kilépés");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(openItem);
        fileMenu.add(rulesItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        return fileMenu;
    }

    /**
     * Creates a start button with specified text, JComboBox, and JFrame.
     *
     * @param text    The text displayed on the button.
     * @param diffBox The difficulty level JComboBox.
     * @param frame   The main JFrame of the application.
     * @return The created start button.
     */
    public JButton createStartButton(String text, JComboBox<String> diffBox, JFrame frame) {

        JComboBox<String> _diffBox = diffBox;

        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 100));
        button.setMaximumSize(new Dimension(300, 100));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					startGame(_diffBox);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                frame.setVisible(false);
            }
        });

        return button;
    }

    /**
     * Starts a new game based on the selected difficulty level.
     *
     * @param diffBox The difficulty level JComboBox.
     * @throws Exception 
     */
    private void startGame(JComboBox<String> diffBox) throws Exception {

        String path = "boards/" + (String) diffBox.getSelectedItem();
        File folder = new File(path);
        File[] files = folder.listFiles();
        int fileCount = 0;
        for (File file : files) {
            if (file.isFile()) {
                fileCount++;
            }
        }

        Random random = new Random();
        int ran = random.nextInt(fileCount) + 1;

        String boardFile = path + "/board" + Integer.toString(ran) + ".xml";

        GameBoard board = new GameBoard();
        FileHandler reader = new FileHandler(board);
        reader.createBoardFromFile(boardFile);
        board = reader.getBoard();
        GameLogic game = new GameLogic(board);
        game.playerMove();
    }

    /**
     * Loads a game from the selected file using a file chooser.
     * @throws Exception 
     */
    private void loadGame() throws Exception {

    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setCurrentDirectory(new File("C:/Users/Jadeguz/Documents/Egyetem/BME_VIK_2023_24_1/A programozás alapjai 3/Házi/saveFiles"));
    	int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            GameBoard board = new GameBoard();
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            FileHandler reader = new FileHandler(board);
            reader.createBoardFromFile(path);
            board = reader.getBoard();
            GameLogic game = new GameLogic(board);
            game.playerMove();

        } else {
            JOptionPane.showMessageDialog(null, "Nincs kiválasztva fájl", "Hiba", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
