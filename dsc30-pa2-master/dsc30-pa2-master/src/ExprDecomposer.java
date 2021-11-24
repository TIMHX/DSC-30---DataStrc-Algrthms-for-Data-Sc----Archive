/*
    Name: Xing Hong
    PID:  A15867895
 */

import java.util.EmptyStackException;

/**
 * A class that will decompose a normal arithmetic expression into a special kind of
 * expression. The normal expression that we are familiar with is having the operators
 * between the operands, e.g. (1 + (2 * 1)) + (2 + 3). On the contrary, this special
 * expression will have the operators appearing after the operands, e.g. 1 2 1 * + 2 3 + +.
 * With an inner class Charstack.
 *
 * @author Xing Hong
 * @since  1/18/2021
 */
public class ExprDecomposer {

    /**
     * A method that change infix calculation into postfix, that all the operators go
     * behind the numbers
     * @param expr is a string
     * @return out is a char array
     */
    public char[] decompose(String expr) {
        char[] postfix = new char[expr.length()];
        char ch;
        CharStack stack = new CharStack(expr.length());


        int j = 0;

        for (int i = 0; i < expr.length(); i++) {
            ch = expr.charAt(i);
            if (ch == '(')
                stack.push(ch);
            else if (isDigit(ch)) {
                postfix[j++] = ch;
            } else if (isOperator(ch)) {
                stack.push(ch);
            } else if (ch == ')') {
                char c = stack.pop();
                while (c != '(') {
                    postfix[j++] = c;
                    c = stack.pop();
                }
            }
        }


        int out_Count = 1;
        for (int i = 0; i < postfix.length; i++) {
            if (postfix[i] != '\0') {
                out_Count++;
            }
        }
        out_Count++;

        char[] out = new char[out_Count - 1];
        for (int i = 0; i < out_Count - 1; i++) {
            out[i] = postfix[i];
        }
        out[out_Count - 2] = stack.pop();
        return out;
    }

    /**
     * UTILITY METHOD, DO NOT MODIFY *
     * Check if the given token represents a digit
     * @param token to check
     * @return boolean true if token is a digit, false otherwise
     */
    private boolean isDigit(char token) {
        return (token >= '0') && (token <= '9');
    }

    /**
     * UTILITY METHOD, DO NOT MODIFY *
     * Check if the given token represents an operator
     * @param token to check
     * @return boolean true if token is an operator, false otherwise
     */
    private boolean isOperator(char token) {
        return (token == '+') || (token == '-') || (token == '*') || (token == '/');
    }

    /**
     * Inner class CharStack.
     * Note: You can remove methods and variables that you will not use for
     * this question, but you must keep both push() and pop() methods and they
     * should function properly.
     */
    protected class CharStack {

        char stack[];
        int top;

        public CharStack(int capacity) {
            /**
             * Initializes a stack with the given capacity.
             * @param capacity is an int
             */
            stack = new char[capacity];
            top = -1;
        }

        public void push(char element) {
            /**
             * Pushes the given element to the stack.
             * @param element is a char
             */
            if (top >= stack.length)
                System.out.println("Push fail");
            else {
                top += 1;
                stack[top] = element;
            }
        }

        public char pop() {
            /** Returns and removes the top element of the stack.
             * @return a char
             */
            if (top == -1) {
                System.out.println("Pop fail");
                return 0;
            } else
                top -= 1;
                return stack[top];
        }
    }
}
