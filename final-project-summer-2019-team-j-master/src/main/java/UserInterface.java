import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface extends JFrame implements ActionListener {

    private JTextField zipcodeField;
    private JTextField venuePrefField;
    private JTextArea outputArea;

    @Override
    public void actionPerformed(ActionEvent e) {
        String zipCode = zipcodeField.getText();
        String venuePreference = cleanString(venuePrefField.getText());
        InputProcessor inputProcessor = new InputProcessor(zipCode, venuePreference);
        outputArea.setText(inputProcessor.getOutputString());
        zipcodeField.setText("");

        if (inputProcessor.isZipValid()) {
            venuePrefField.setText("");
        }
    }

    public void setUp() {
        JFrame f = new JFrame("Finder - Find where you want to go, then go");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 700);

        //creating the bottom panel that will house the user inputs for zip code and venue preference
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Zip Code");
        zipcodeField = new JTextField(8);
        JLabel label2 = new JLabel("Venue Preference");
        venuePrefField = new JTextField(8);
        JButton send = new JButton("Send");
        send.addActionListener(this);

        panel.add(label);
        panel.add(zipcodeField);
        panel.add(label2);
        panel.add(venuePrefField);
        panel.add(send);

        //create the menu bar on top that will house reset and then start over and exit underneath that layer
        JMenuBar menuBar = new JMenuBar();
        JMenu menuChoice = new JMenu("Reset");
        menuBar.add(menuChoice);
        //Creating menu items under one tab
        JMenuItem menuItem1 = new JMenuItem("Start Over");
        JMenuItem menuItem2 = new JMenuItem("Exit");
        menuChoice.add(menuItem1);
        menuChoice.add(menuItem2);

        // center area where all the text will be displayed
        this.outputArea = new JTextArea("Thank you for using Finder, the latest app to search venues near you, for you!\n"
                + "\nPlease enter your zipcode and venue preference!");
        JScrollPane sp = new JScrollPane(outputArea);

        //adding the components together to create the GUI
        f.getContentPane().add(BorderLayout.NORTH, menuBar);
        f.getContentPane().add(BorderLayout.SOUTH, panel);
        f.getContentPane().add(BorderLayout.CENTER, sp);
        f.setVisible(true);
    }

    /**
     * Cleans the user's input for venue/category for any special characters and spaces, which could
     * cause an error when the input ultimately gets called by the Foursquare API.
     *
     * @param input
     * @return clean string removed of any special characters and spaces.
     */
    public String cleanString(String input) {
        String cleanedInput = input;
        Pattern pt = Pattern.compile("[^a-zA-Z0-9]");  // Regex pattern signifies all special characters and spaces.
        Matcher match = pt.matcher(cleanedInput);      // matches the pattern against the input in question
        while (match.find()) {         // while a match is found
            String s = match.group();  // return the matched pattern
            cleanedInput = cleanedInput.replaceAll(String.format("\\%s", s), "");    // reassign the cleanedInput variable with it, but with the found pattern replaced with ""
        }
        return cleanedInput;
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.setUp();

    }
}
