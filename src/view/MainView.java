package view;

import model.entities.Stack;

import java.util.Scanner;

public class MainView {

    public void init() {
        Scanner input = new Scanner(System.in);

//        String[] tests = {"A+B", "A+B-C", "(A+B)*(C-D)", "A^B*C-D+E/F/(G+H)",
//            "((A+B)*C-(D-E))^(F+G)", "A-B/(C*D^E)", "3+5*4/(4+3)", "3+5*C/(4+3)", "(3+5)/(1+3)", "3^2"};

//        for (String s : tests) {
//            System.out.println("Forma infixa: " + s);
//            String result = reversePolishNotation(s);
//            System.out.println("Forma pós-fixa: " + result);
//            if (isJustNumbers(s))
//                System.out.println("Resultado: " + calculate(result.split("")) + "\n");
//            else
//                System.out.println("Não é possível exibir o resultado.\nA expressão não é composta apenas de números\n");
//            //System.out.println("É apenas números? " + isJustNumbers(reversePolishNotation(s)) + "\n");
//        }

        System.out.print("Informe a expressão: ");
        String expression = input.next();
        if (expression != null || !expression.equals("")) {
            String result = reversePolishNotation(expression);
            System.out.println("Forma pós-fixa: " + result);
            if (isJustNumbers(expression))
                System.out.println("Resultado: " + calculate(result.split("")) + "\n");
            else
                System.out.println("Não é possível exibir o resultado.\nA expressão não é composta apenas de números\n");
        }
    }

    /** Método que converte a expressão na forma infixa para a forma pós-fixa.
     * @param expression Expressão que será convertida.
     * @return String contendo a forma pós-fixa da expressão.
     */
    private String reversePolishNotation(String expression) {
        String result = "";

        Stack<Character> characterStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if(Character.isLetterOrDigit(c))
                result += c;
            // Se o caractere for um '(' ele é adicionado a pilha
            else if (c == '(')
                characterStack.push(c);
            // Se o caractere for um ')' remove da pilha e adiciona na variável do resultado
                // até que seja encontrado um '('
            else if (c == ')') {
                while (!characterStack.isEmpty() && characterStack.peek() != '(')
                    result += characterStack.pop();

                characterStack.pop();
            }
            // Um operador foi encontrado
            else {
                while (!characterStack.isEmpty() && precedence(c) <= precedence(characterStack.peek()))
                    result += characterStack.pop();

                characterStack.push(c);
            }
        }

        // Remove todos os operadores da pilha
        while (!characterStack.isEmpty()) {
            if (characterStack.peek() == '(')
                return "Expressão inválida!";
            result += characterStack.pop();
        }
        
        return result;
    }

    /** Método que faz o cálculo da expressão na forma pós-fixa.
     * @param expression Array de String contendo a expressão na forma pós-fixa.
     * @return Inteiro contendo o resultado da expressão.
     */
    private int calculate(String[] expression) {
        int value = 0;
        String operators = "+-*/^";

        Stack<String> stringStack = new Stack<>();

        for (String s : expression) {
            // Se não for um operador a String é adicionada na pilha
            if (!operators.contains(s))
                stringStack.push(s);
            // Se for um operador os números são removidos da pilha e é realizada a operção de acordo
            // com o operador que foi encontrado.
            else {
                int a = Integer.valueOf(stringStack.pop());
                int b = Integer.valueOf(stringStack.pop());

                switch (s)  {
                    case "+":
                        stringStack.push(String.valueOf(a + b));
                        break;
                    case "-":
                        stringStack.push(String.valueOf(b - a));
                        break;
                    case "*":
                        stringStack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stringStack.push(String.valueOf(b / a));
                        break;
                    case "^":
                        stringStack.push(String.valueOf((int) Math.pow(b, a)));
                        break;
                }
            }
        }

        value = Integer.valueOf(stringStack.pop());

        return value;
    }

    /** Método que retorna a ordem de precedência do operador.
     * Quanto maior o valor maior a ordem de precedência.
     * @param c Operador que será verificado.
     * @return Inteiro que indica a ordem de precedência do operador.
     */
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

    /** Método que verifica se a expressão que foi passado por parâmetro é composta apenas de números.
     * Retorna true caso seja e false caso não.
     * @param expression Expressão a ser verificada
     * @return boolean
     */
    private boolean isJustNumbers(String expression) {
        if (expression == null || expression.equals(""))
            throw new NullPointerException("Foi passado um parâmetro nulo ou vazio.");
        String regex = "\\+|\\*|\\-|\\/|\\^|\\(|\\)"; // Expressão regular responsável por remover todos os caracteres não
                                                      // númericos da expressão
        String[] vector = expression.split(regex);
        for (String s : vector) {
            if (isDigit(s))
                continue;
            else
                return false;
        }
        return true;
    }

    /** Método responsável por verificar se a string é um número entre 0 e 9.
     * Caso seja um número dentro do intervalo retorna true, caso não retorn false.
     * @param s Valor que será verificado.
     * @return boolean
     */
    private boolean isDigit(String s) {
        return s.matches("[0-9]*");
    }

}
