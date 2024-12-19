import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        String expression1 = "2^2^(4*(-2)/(-4))";
        System.out.println("postfix form of the expression is: " + infixToPostfix(expression1));
        System.out.println("prefix form of the expression is: " + infixToPrefix(expression1));
        Double result = evaluateExpression(expression1);
        System.out.println("The result of the expression is: " + result);

        String expression2 = "x^x";
        //graph(expression2,-50,+50);
    }

    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '~':
                return 2;
            case '*':
            case '/':
                return 3;
            case '^':
                return 4;
        }
        return -1;
    }

    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean lastWasOperator = true;

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result.append(c);
                lastWasOperator = false;
            } else if (c == '(') {
                stack.push(c);
                lastWasOperator = true;
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                    lastWasOperator = false;
                }
                stack.pop();
            } else {
                if (c == '-' && lastWasOperator){
                    c = '~';
                }
                while (!stack.isEmpty() && precedence(c) < precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                while (!stack.isEmpty() && c != '^' && precedence(c) == precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
                lastWasOperator = false;
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static String infixToPrefix(String expression) {
        Stack<Character> operators = new Stack<>();
        Stack<String> operands = new Stack<>();
        boolean lastWasOperator = true;

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                operands.push(c + "");
                lastWasOperator = false;
            } else if (c == '(') {
                operators.push(c);
                lastWasOperator = true;
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    char op = operators.pop();
                    if (op == '~'){
                        String op1 = operands.pop();
                        String temp = op + op1;
                        operands.push(temp);
                    } else {
                        String op1 = operands.pop();
                        String op2 = operands.pop();
                        String temp = op + op2 + op1;
                        operands.push(temp);
                    }
                }
                operators.pop();
                lastWasOperator = false;
            } else {
                if (c == '-' && lastWasOperator) c = '~';
                while (!operators.isEmpty() && precedence(c) < precedence(operators.peek())) {
                    char op = operators.pop();
                    if (op == '~'){
                        String op1 = operands.pop();
                        String temp = op + op1;
                        operands.push(temp);
                    } else {
                        String op1 = operands.pop();
                        String op2 = operands.pop();
                        String temp = op + op2 + op1;
                        operands.push(temp);
                    }
                }
                while (!operators.isEmpty() && c != '^' && precedence(c) == precedence(operators.peek())) {
                    char op = operators.pop();
                    if (op == '~'){
                        String op1 = operands.pop();
                        String temp = op + op1;
                        operands.push(temp);
                    } else {
                        String op1 = operands.pop();
                        String op2 = operands.pop();
                        String temp = op + op2 + op1;
                        operands.push(temp);
                    }
                }
                operators.push(c);
                lastWasOperator = false;
            }
        }

        while (!operators.isEmpty()) {
            char op = operators.pop();
            if (op == '~'){
                String op1 = operands.pop();
                String temp = op + op1;
                operands.push(temp);
            } else {
                String op1 = operands.pop();
                String op2 = operands.pop();
                String temp = op + op2 + op1;
                operands.push(temp);
            }
        }
        return operands.peek();
    }

    public static Double evaluatePostfix(String expression) {
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                stack.push(Double.parseDouble(c + ""));
            } else if (c == '~') {
                double temp = stack.pop();
                stack.push(-temp);
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
                        stack.push(Math.pow(val2, val1));
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static Double evaluatePostfix(String expression, Double x) {
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == 'x') {
                stack.push(x);
            } else if (Character.isDigit(c)) {
                stack.push(Double.parseDouble(c + ""));
            } else if (c == '~') {
                double temp = stack.pop();
                stack.push(-temp);
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
                        stack.push(Math.pow(val2, val1));
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static Double evaluateExpression (String expression){
        String postfixExpression = infixToPostfix(expression);
        return evaluatePostfix(postfixExpression);
    }

    public static Double evaluateExpression(String expression, Double x) {
        String postfixExpression = infixToPostfix(expression);
        return evaluatePostfix(postfixExpression, x);
    }

    public static void graph(String expression, int start, int end) {
        ArrayList<ArrayList<Double>> points = new ArrayList<>();
        for(double i = start; i <= end; i = i + 0.02){
            ArrayList<Double> temp = new ArrayList<>();
            temp.add(i);
            temp.add(evaluateExpression(expression,i));
            points.add(temp);
        }
        Graph d = new Graph(points);
    }

}
