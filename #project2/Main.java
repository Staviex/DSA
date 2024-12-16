import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        String expression1 = "2*5^6/(7-4)+2";
        System.out.println(infixToPostfix(expression1));
        System.out.println(infixToPrefix(expression1));

        String expression2 = "x^3";
        Double x = 3.0;
        Double result = evaluateExpression(expression2, x);
        System.out.println("The result of the expression for x = " + x + " is: " + result);
        graph(expression2);
    }

    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static String infixToPrefix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> operators = new Stack<>();
        Stack<String> operands = new Stack<>();

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                operands.push(c + "");
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    String op1 = operands.pop();
                    String op2 = operands.pop();
                    char op = operators.pop();
                    String temp = op + op2 + op1;
                    operands.push(temp);
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    String op1 = operands.pop();
                    String op2 = operands.pop();
                    char op = operators.pop();
                    String temp = op + op2 + op1;
                    operands.push(temp);
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            String op1 = operands.pop();
            String op2 = operands.pop();
            char op = operators.pop();
            String temp = op + op2 + op1;
            operands.push(temp);
        }

        return operands.peek();
    }

    public static Double evaluatePostfix(String expression, Double x) {
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == 'x') {
                stack.push(x);
            } else if (Character.isDigit(c)) {
                stack.push(Double.parseDouble(c + ""));
            } else {
                Double val1 = stack.pop();
                Double val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                    case '^':
                        stack.push((Double) Math.pow(val2, val1));
                        break;
                    case '~':
                        stack.push(-val1);
                        break;
                }
            }
        }

        return stack.pop();
    }
    public static Double evaluateExpression(String expression, Double x) {
        String postfixExpression = infixToPostfix(expression);
        return evaluatePostfix(postfixExpression, x);
    }

    public static void graph(String expression) {
        ArrayList<ArrayList<Double>> points = new ArrayList<>();
        for(double i = -50; i <= 50; i = i + 0.02){
            ArrayList<Double> temp = new ArrayList<>();
            temp.add(i);
            temp.add(evaluateExpression(expression,i));
            points.add(temp);
        }
        Graph d = new Graph(points);
    }

}