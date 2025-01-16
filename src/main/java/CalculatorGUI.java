package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private Calculator calculator;
    private StringBuilder equation;

    public CalculatorGUI() {
        calculator = new Calculator();
        equation = new StringBuilder();

        // Set up the frame
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the display field with larger size
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 32));
        display.setEditable(false);
        display.setPreferredSize(new Dimension(400, 100));
        add(display, BorderLayout.NORTH);

        // Create the buttons panel with smaller buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+",
            "AC", "%", "x²", "x³"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.setPreferredSize(new Dimension(75, 75));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            equation.append(command);
            display.setText(equation.toString());
        } else if (command.equals("=")) {
            try {
                double result = evaluate(equation.toString());
                display.setText(equation.append(" = ").append(result).toString());
                equation.setLength(0); // Clear the equation for new input
            } catch (Exception ex) {
                display.setText("Error");
                equation.setLength(0); // Clear the equation for new input
            }
        } else if (command.equals("AC")) {
            equation.setLength(0);
            display.setText("");
        } else if (command.equals("%")) {
            equation.append(" % ");
            display.setText(equation.toString());
        } else if (command.equals("x²")) {
            double number = Double.parseDouble(equation.toString().trim());
            double result = number * number;
            display.setText(equation + "² = " + result);
            equation.setLength(0);
        } else if (command.equals("x³")) {
            double number = Double.parseDouble(equation.toString().trim());
            double result = number * number * number;
            display.setText(equation + "³ = " + result);
            equation.setLength(0);
        } else {
            equation.append(" ").append(command).append(" ");
            display.setText(equation.toString());
        }
    }

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
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculatorGUI = new CalculatorGUI();
            calculatorGUI.setVisible(true);
        });
    }
}