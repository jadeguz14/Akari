package akari;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.Color;

public class TestMenu {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    public void testCreateFrame() {
        JFrame frame = menu.createFrame("Test Frame");
        assertNotNull(frame);
        assertEquals("Test Frame", frame.getTitle());
        assertEquals(600, frame.getWidth());
        assertEquals(600, frame.getHeight());
    }

    @Test
    public void createPanel() {
        JPanel panel = menu.createPanel();
        Color c = new Color(0,0,255);
        assertNotNull(panel);
        assertEquals(c, panel.getBackground());
    }

    @Test
    public void createFileMenu() {
        JFrame frame = new JFrame();
        JMenu fileMenu = menu.createFileMenu(frame);

        assertNotNull(fileMenu);
        assertEquals("Fájl", fileMenu.getText());
        assertEquals(4, fileMenu.getItemCount());

        JMenuItem openItem = fileMenu.getItem(0);
        JMenuItem rulesItem = fileMenu.getItem(1);
        JMenuItem exitItem = fileMenu.getItem(3);

        assertNotNull(openItem);
        assertNotNull(rulesItem);
        assertNotNull(exitItem);

        assertEquals("Betöltés", openItem.getText());
        assertEquals("Szabályok", rulesItem.getText());
        assertEquals("Kilépés", exitItem.getText());
    }

    @Test
    public void createStartButton() {
        JFrame frame = new JFrame();
        String text = "Test Button";
        JComboBox<String> diffBox = new JComboBox<>(new String[]{"könnyű", "közepes", "nehéz"});
        JButton button = menu.createStartButton(text, diffBox, frame);

        assertNotNull(button);
        assertEquals(text, button.getText());
        assertEquals(Color.WHITE, button.getBackground());
        assertEquals(Color.BLACK, button.getForeground());
    }
}
