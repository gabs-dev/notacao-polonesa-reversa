package view;

import model.entities.Stack;

import java.util.Scanner;

public class MainView {

    public void init() {
        Scanner input = new Scanner(System.in);

        String[] tests = {"a+b*(c^d-e)^(f+g*h)-i", "A+B", "A+B-C", "(A+B)*(C-D)", "A^B*C-D+E/F/(G+H)",
            "((A+B)*C-(D-E))^(F+G)", "A-B/(C*D^E)", "3+5*14/(4+3)"};

        for (String s : tests) {
            System.out.println("Forma infixa: " + s);
            System.out.println("Forma pós-fixa: " + reversePolishNotation(s) + "\n");
        }

//        System.out.print("Informe a expressão: ");
//        String expression = input.next();
//        if (expression != null || !expression.equals("")) {
//            System.out.println("Forma infixa: " + expression);
//            System.out.println("Forma pós-fixa: " + reversePolishNotation(expression));
//        }
    }

    private String reversePolishNotation(String expression) {
        String result = "";

        Stack<Character> characterStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if(Character.isLetterOrDigit(c))
                result += c;
            else if (c == '(')
                characterStack.push(c);
            else if (c == ')') {
                while (!characterStack.isEmpty() && characterStack.peek() != '(')
                    result += characterStack.pop();

                characterStack.pop();
            } else {
                while (!characterStack.isEmpty() && precedence(c) <= precedence(characterStack.peek()))
                    result += characterStack.pop();

                characterStack.push(c);
            }
        }

        while (!characterStack.isEmpty()) {
            if (characterStack.peek() == '(')
                return "Expressão inválida!";
            result += characterStack.pop();
        }
        
        return result;
    }

    private int precedence(char c) {
        switch (c) {
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

}
