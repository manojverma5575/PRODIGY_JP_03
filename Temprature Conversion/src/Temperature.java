import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Temperature extends JFrame implements ActionListener {
    private JComboBox<String> fromUnit, toUnit;
    private JTextField inputField;
    private JLabel resultLabel;
    private JButton convertButton, clearButton, exitButton;

    public Temperature() {
        setTitle("🌡️ Temperature Converter");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Title Label
        JLabel titleLabel = new JLabel("Temperature Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // Underline
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Input Field Label
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel inputLabel = new JLabel("Enter Temperature:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 18));
        inputLabel.setForeground(Color.BLACK);
        inputLabel.setOpaque(false); // Remove background color
        add(inputLabel, gbc);

        // Input Field
        inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        add(inputField, gbc);

        // From Unit Dropdown
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel fromLabel = new JLabel("From Unit:");
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(fromLabel, gbc);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        fromUnit = new JComboBox<>(units);
        fromUnit.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        add(fromUnit, gbc);

        // To Unit Dropdown
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel toLabel = new JLabel("To Unit:");
        toLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(toLabel, gbc);

        toUnit = new JComboBox<>(units);
        toUnit.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        add(toUnit, gbc);

        // Convert Button
        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 18));
        convertButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(convertButton, gbc);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.addActionListener(this);
        gbc.gridx = 1;
        add(clearButton, gbc);

        // Result Label
        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(resultLabel, gbc);

        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 18));
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridy = 6;
        add(exitButton, gbc);

        setVisible(true);
    }

    // Conversion Logic
    private double convertTemperature(double temp, String from, String to) {
        if (from.equals(to)) {
            return temp;
        }
        switch (from + " to " + to) {
            case "Celsius to Fahrenheit":
                return (temp * 9 / 5) + 32;
            case "Celsius to Kelvin":
                return temp + 273.15;
            case "Fahrenheit to Celsius":
                return (temp - 32) * 5 / 9;
            case "Fahrenheit to Kelvin":
                return (temp - 32) * 5 / 9 + 273.15;
            case "Kelvin to Celsius":
                return temp - 273.15;
            case "Kelvin to Fahrenheit":
                return (temp - 273.15) * 9 / 5 + 32;
            default:
                return temp;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            try {
                double inputTemp = Double.parseDouble(inputField.getText());
                String from = (String) fromUnit.getSelectedItem();
                String to = (String) toUnit.getSelectedItem();
                double result = convertTemperature(inputTemp, from, to);
                resultLabel.setText(String.format("Result: %.2f %s", result, to));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == clearButton) {
            inputField.setText("");
            resultLabel.setText("Result: ");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Temperature::new);
    }
}
