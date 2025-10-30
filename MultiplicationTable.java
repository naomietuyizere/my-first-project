import java.awt.*;
import java.awt.event.*;

public class MultiplicationTable extends Frame implements ActionListener {

    private TextField numberField;
    private TextArea resultArea;
    private Button displayButton;

    public MultiplicationTable() {
        // Frame setup
        setTitle("Multiplication Table - Project24RP00900");
        setSize(400, 400);
        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);

        // Label and TextField for user input
        add(new Label("Enter a number:"));
        numberField = new TextField(10);
        add(numberField);

        // Display button
        displayButton = new Button("Display");
        displayButton.addActionListener(this);
        add(displayButton);

        // TextArea to show multiplication table
        resultArea = new TextArea(15, 30);
        resultArea.setEditable(false);
        add(resultArea);

        // Close window when X is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Button click handler
    public void actionPerformed(ActionEvent e) {
        resultArea.setText(""); // Clear previous result
        try {
            int num = Integer.parseInt(numberField.getText());
            for (int i = 1; i <= 12; i++) {
                resultArea.append(num + " x " + i + " = " + (num * i) + "\n");
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Please enter a valid integer number!");
        }
    }

    // Main method
    public static void main(String[] args) {
        new MultiplicationTable();
    }
}

