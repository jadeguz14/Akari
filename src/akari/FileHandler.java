package akari;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

/**
 * The FileHandler class is responsible for reading an Akari game board from an XML file
 * and creating a corresponding GameBoard instance.
 */
public class FileHandler {

    private GameBoard board;

    /**
     * Constructor for the FileHandler class.
     * Reads the XML file and creates a GameBoard instance.
     *
     * @param xmlFilePath The path to the XML file containing the Akari game board.
     */
    public FileHandler(GameBoard gameBoard) {
    	board = gameBoard;
    }

    /**
     * Gets the GameBoard instance created from the XML file.
     *
     * @return The GameBoard instance.
     */
    public GameBoard getBoard() {
        return board;
    }

    /**
     * Creates a GameBoard instance by parsing the information from the XML file.
     *
     * @param xmlFile The path to the XML file containing the Akari game board information.
     * @return The created GameBoard instance.
     * @throws Exception If an error occurs during XML parsing.
     */
    public GameBoard createBoardFromFile(String xmlFilePath) throws Exception {

        File xmlFile = new File(xmlFilePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList rowList = document.getElementsByTagName("Row");

        for (int i = 0; i < rowList.getLength(); i++) {
            Element row = (Element) rowList.item(i);
            NodeList colList = row.getElementsByTagName("Col");

            for (int j = 0; j < colList.getLength(); j++) {
                Element col = (Element) colList.item(j);
                Cell.State state = Cell.State.valueOf(col.getElementsByTagName("State").item(0).getTextContent());
                int lightLevel = Integer.parseInt(col.getElementsByTagName("lightLevel").item(0).getTextContent());
                int number = Integer.parseInt(col.getElementsByTagName("number").item(0).getTextContent());

                board.getGameCell(i, j).setState(state);
                board.getGameCell(i, j).setLightLevel(lightLevel);
                board.getGameCell(i, j).setNumber(number);
            }
        }

        board.updateGamePanel();

        return board;
    }
    
    /**
     * Creates an XML file based on the current state of the GameBoard.
     *
     * @param outputFilePath The path to the XML file to be created.
     * @throws Exception If an error occurs during XML creation.
     */
    public void createFileFromBoard(String outputFilePath) throws Exception {
    	
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // Create a new XML document
        Document doc = docBuilder.newDocument();

        // Create the root element (GameBoard)
        Element rootElement = doc.createElement("Board");
        doc.appendChild(rootElement);

        // Iterate through each row and column in the GameBoard
        for (int i = 0; i < board.getNumOfRows(); i++) {
            Element rowElement = doc.createElement("Row");
            rootElement.appendChild(rowElement);

            for (int j = 0; j < board.getNumOfColumns(); j++) {
                Element colElement = doc.createElement("Col");
                rowElement.appendChild(colElement);

                // Add State element
                Element stateElement = doc.createElement("State");
                stateElement.appendChild(doc.createTextNode(board.getGameCell(i, j).getState().toString()));
                colElement.appendChild(stateElement);

                // Add lightLevel element
                Element lightLevelElement = doc.createElement("lightLevel");
                lightLevelElement.appendChild(doc.createTextNode(String.valueOf(board.getGameCell(i, j).getLightLevel())));
                colElement.appendChild(lightLevelElement);

                // Add number element
                Element numberElement = doc.createElement("number");
                numberElement.appendChild(doc.createTextNode(String.valueOf(board.getGameCell(i, j).getNumber())));
                colElement.appendChild(numberElement);
            }
        }
        
        // Write the content into an XML file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(outputFilePath));

        transformer.transform(source, result);
    }

}
