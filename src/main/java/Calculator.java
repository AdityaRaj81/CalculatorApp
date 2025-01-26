package main.java;
public class Calculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public double power(double a, double b) {
        return Math.pow(a, b);
    }

    public double sqrt(double a) {
        return Math.sqrt(a);
    }

    public double reciprocal(double a) {
        return 1 / a;
    }

    public double abs(double a) {
        return Math.abs(a);
    }

    public long factorial(int n) {
        if (n == 0) return 1;
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public double sin(double a) {
        return Math.sin(Math.toRadians(a));
    }

    public double cos(double a) {
        return Math.cos(Math.toRadians(a));
    }

    public double tan(double a) {
        return Math.tan(Math.toRadians(a));
    }

    public double log(double a) {
        return Math.log10(a);
    }

    public double exp(double a) {
        return Math.exp(a);
    }
}