package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display; // Display field for showing the current equation/result
    private JTextArea history; // Text area for showing the history of calculations
    private Calculator calculator; // Calculator instance for performing calculations
    private StringBuilder equation; // StringBuilder for constructing the equation
    private Stack<String> historyStack; // Stack for storing the history of calculations

    public CalculatorGUI() {
        calculator = new Calculator();
        equation = new StringBuilder();
        historyStack = new Stack<>();

        // Set up the frame
        setTitle("Calculator By Aditya");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the display field with larger size
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 32));
        display.setEditable(false);
        display.setPreferredSize(new Dimension(800, 100));
        display.setBackground(new Color(30, 15, 30)); // Dark background
        display.setForeground(new Color(0, 255, 0)); // Bright green text
        add(display, BorderLayout.NORTH);

        // Create the history area
        history = new JTextArea();
        history.setFont(new Font("Arial", Font.PLAIN, 16));
        history.setEditable(false);
        history.setBackground(new Color(50, 50, 50)); // Dark gray background
        history.setForeground(new Color(255, 255, 255)); // White text
        history.setText("History:\n"); // Add default text to the history section
        JScrollPane scrollPane = new JScrollPane(history);
        scrollPane.setPreferredSize(new Dimension(800, 150));
        add(scrollPane, BorderLayout.SOUTH);

        // Create the buttons panel with smaller buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 6, 5, 5));

        String[] buttons = {
            "AC", "±", "%", "√", "n!", "1/x",
            "7", "8", "9", "/", "x²", "^",
            "4", "5", "6", "*", "|x|", "sin",
            "1", "2", "3", "-", "log", "cos",
            "0", ".", "=", "+", "ln", "tan",
        };

        // Add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.setPreferredSize(new Dimension(75, 75));
            button.setBackground(new Color(70, 70, 70)); // Darker gray background
            button.setForeground(new Color(255, 255, 255)); // White text
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle number and decimal point inputs
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            equation.append(command);
            display.setText(equation.toString());
        } else if (command.equals("=")) {
            // Handle the equals button
            try {
                double result = evaluate(equation.toString());
                String resultString = equation.append(" = ").append(result).toString();
                display.setText(resultString);
                historyStack.push(resultString);
                updateHistory();
                equation.setLength(0); // Clear the equation for new input
            } catch (Exception ex) {
                display.setText("Error");
                equation.setLength(0); // Clear the equation for new input
            }
        } else if (command.equals("AC")) {
            // Handle the AC (All Clear) button
            equation.setLength(0);
            display.setText("");
        } else if (command.equals("%")) {
            // Handle the percentage button
            equation.append(" % ");
            display.setText(equation.toString());
        } else if (command.equals("x²")) {
            // Handle the square button
            double number = Double.parseDouble(equation.toString().trim());
            double result = number * number;
            display.setText(equation + "² = " + result);
            equation.setLength(0);
        } else if (command.equals("x³")) {
            // Handle the cube button
            double number = Double.parseDouble(equation.toString().trim());
            double result = number * number * number;
            display.setText(equation + "³ = " + result);
            equation.setLength(0);
        } else if (command.equals("√")) {
            // Handle the square root button
            double number = Double.parseDouble(equation.toString().trim());
            double result = calculator.sqrt(number);
            display.setText("√" + equation + " = " + result);
            equation.setLength(0);
        } else if (command.equals("1/x")) {
            // Handle the reciprocal button
            double number = Double.parseDouble(equation.toString().trim());
            double result = calculator.reciprocal(number);
            display.setText("1/" + equation + " = " + result);
            equation.setLength(0);
        } else if (command.equals("±")) {
            // Handle the plus/minus button
            double number = Double.parseDouble(equation.toString().trim());
            double result = -number;
            display.setText(equation.toString() + " = " + result);
            equation.setLength(0);
        } else if (command.equals("^")) {
            // Handle the power button
            equation.append(" ^ ");
            display.setText(equation.toString());
        } else if (command.equals("|x|")) {
            // Handle the absolute value button
            double number = Double.parseDouble(equation.toString().trim());
            double result = calculator.abs(number);
            display.setText("|" + equation + "| = " + result);
            equation.setLength(0);
        } else if (command.equals("n!")) {
            // Handle the factorial button
            int number = Integer.parseInt(equation.toString().trim());
            long result = calculator.factorial(number);
            display.setText(number + "! = " + result);
            equation.setLength(0);
        } else if (command.equals("sin")) {
            // Handle the sine button
            double number = Double.parseDouble(equation.toString().trim());
            double result = calculator.sin(number);
            display.setText("sin(" + equation + ") = " + result);
            equation.setLength(0);
        } else if (command.equals("cos")) {
            // Handle the cosine button
            double number = Double.parseDouble(equation.toString().trim());
            double result = calculator.cos(number);
            display.setText("cos(" + equation + ") = " + result);
            equation.setLength(0);
        } else if (command.equals("tan")) {
            // Handle the tangent button
            double number = Double.parseDouble(equation.toString().trim());
            double result = calculator.tan(number);
            display.setText("tan(" + equation + ") = " + result);
            equation.setLength(0);
        } else if (command.equals("log")) {
            // Handle the logarithm button
            double number = Double.parseDouble(equation.toString().trim());
            double result = calculator.log(number);
            display.setText("log(" + equation + ") = " + result);
            equation.setLength(0);
        } else if (command.equals("ln")) {
            // Handle the natural logarithm button
            double number = Double.parseDouble(equation.toString().trim());
            double result = Math.log(number);
            display.setText("ln(" + equation + ") = " + result);
            equation.setLength(0);
        } else if (command.equals("exp")) {
            // Handle the exponential button
            double number = Double.parseDouble(equation.toString().trim());
            double result = calculator.exp(number);
            display.setText("exp(" + equation + ") = " + result);
            equation.setLength(0);
        } else if (command.equals("π")) {
            // Handle the pi button
            double result = Math.PI;
            display.setText("π = " + result);
            equation.setLength(0);
        } else {
            // Handle other operators
            equation.append(" ").append(command).append(" ");
            display.setText(equation.toString());
        }
    }

    // Method to evaluate the equation
    private double evaluate(String equation) {
        String[] tokens = equation.split(" ");
        double result = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);

            switch (operator) {
                case "+":
                    result = calculator.add(result, operand);
                    break;
                case "-":
                    result = calculator.subtract(result, operand);
                    break;
                case "*":
                    result = calculator.multiply(result, operand);
                    break;
                case "/":
                    result = calculator.divide(result, operand);
                    break;
                case "%":
                    result = result * operand / 100;
                    break;
                case "^":
                    result = calculator.power(result, operand);
                    break;
            }
        }

        return result;
    }

    // Method to update the history area
    private void updateHistory() {
        history.setText("");
        for (String entry : historyStack) {
            history.append(entry + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculatorGUI = new CalculatorGUI();
            calculatorGUI.setVisible(true);
        });
    }
}