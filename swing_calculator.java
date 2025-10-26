import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class swing_calculator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new swing_calculator().createUI());
    }

    private void createUI() {
        // Frame setup
        JFrame frame = new JFrame("Calculator Using Swing");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(245, 250, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Components
        JLabel label1 = new JLabel("Enter First Number:");
        JTextField text1 = new JTextField(10);

        JLabel label2 = new JLabel("Enter Second Number:");
        JTextField text2 = new JTextField(10);

        JLabel label3 = new JLabel("Result:");
        JTextField resultField = new JTextField(10);
        resultField.setEditable(false);
        resultField.setBackground(new Color(230, 255, 230));

        JButton addButton = new JButton("Add");
        JButton subButton = new JButton("Subtract");
        JButton mulButton = new JButton("Multiply");
        JButton divButton = new JButton("Divide");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.BOLD, 13);
        for (JButton btn : new JButton[]{addButton, subButton, mulButton, divButton, clearButton, exitButton}) {
            btn.setFont(buttonFont);
        }

        // Add Components
        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(label1, gbc);
        gbc.gridx = 1;
        frame.add(text1, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        frame.add(label2, gbc);
        gbc.gridx = 1;
        frame.add(text2, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        frame.add(label3, gbc);
        gbc.gridx = 1;
        frame.add(resultField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        frame.add(buttonPanel, gbc);

        // Action Listeners
        addButton.addActionListener(e -> performOperation(text1, text2, resultField, "add"));
        subButton.addActionListener(e -> performOperation(text1, text2, resultField, "sub"));
        mulButton.addActionListener(e -> performOperation(text1, text2, resultField, "mul"));
        divButton.addActionListener(e -> performOperation(text1, text2, resultField, "div"));

        clearButton.addActionListener(e -> {
            text1.setText("");
            text2.setText("");
            resultField.setText("");
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void performOperation(JTextField text1, JTextField text2, JTextField resultField, String op) {
        try {
            double num1 = Double.parseDouble(text1.getText());
            double num2 = Double.parseDouble(text2.getText());
            double result = 0;

            switch (op) {
                case "add": result = num1 + num2; break;
                case "sub": result = num1 - num2; break;
                case "mul": result = num1 * num2; break;
                case "div":
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(null, "Cannot divide by zero!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            resultField.setText(String.valueOf(result));
            JOptionPane.showMessageDialog(null, "Operation successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }
}
