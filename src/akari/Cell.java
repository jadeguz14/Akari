package akari;

/**
 * The Cell class represents a single cell in an Akari puzzle grid.
 * Each cell can have a specific state, light level, and a number associated with it.
 */
public class Cell {

    /**
     * Enumeration representing the possible states of a cell.
     * - WHITE: The cell is empty.
     * - BLACK: The cell contains a black square.
     * - LAMP: The cell contains a lamp that emits light.
     * - MARKED: The cell is marked (for example, as a potential location for a lamp).
     */
    public enum State {
        WHITE, BLACK, LAMP, MARKED
    }

    private State state;         // The current state of the cell
    private int lightLevel;      // The level of light emitted by the cell (used for lamps)
    private int number;          // The number associated with the cell (used in the puzzle)

    /**
     * Constructs a Cell with the specified initial state.
     *
     * @param initialState The initial state of the cell.
     */
    public Cell(State initialState) {
        state = initialState;
        lightLevel = 0;
        number = -1;
    }

    /**
     * Gets the current state of the cell.
     *
     * @return The state of the cell.
     */
    public State getState() {
        return state;
    }

    /**
     * Gets the level of light emitted by the cell.
     *
     * @return The light level of the cell.
     */
    public int getLightLevel() {
        return lightLevel;
    }

    /**
     * Gets the number associated with the cell.
     *
     * @return The number associated with the cell.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the state of the cell.
     *
     * @param state The new state to set for the cell.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Sets the number associated with the cell.
     *
     * @param number The number to set for the cell.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Sets the light level of the cell.
     *
     * @param lightLevel The light level to set for the cell.
     */
    public void setLightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
    }

    /**
     * Increases the light level of the cell by 1.
     */
    public void addLight() {
        lightLevel++;
    }

    /**
     * Decreases the light level of the cell by 1.
     */
    public void subLight() {
        lightLevel--;
    }
}
