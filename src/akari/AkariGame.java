package akari;

import javax.swing.SwingUtilities;

/**
 * The main class for the Akari game, responsible for launching the game by creating
 * and displaying the initial menu using Java Swing. The game follows the Akari puzzle rules,
 * where the player places lamps on a grid to illuminate cells while avoiding certain rules violations.
 * The main method of this class invokes the creation of the menu through the SwingUtilities.invokeLater
 * method to ensure proper handling of Swing components on the event dispatch thread.
 *
 * @see Menu
 */
public class AkariGame {

    /**
     * The entry point of the Akari game. Invokes the creation and display of the menu
     * through the SwingUtilities.invokeLater method.a
     *
     * @param args The command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create and display the initial menu
            Menu menu = new Menu();
        });
    }
}
