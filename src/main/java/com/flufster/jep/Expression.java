package com.flufster.jep;

import com.flufster.jep.parser.token.ExpressionTokenizer;
import com.flufster.jep.parser.tree.Node;
import com.flufster.jep.parser.tree.TokenizedExpressionParser;

import java.util.HashMap;

/**
 * The {@code Expression} class represents a mathematical expression. The primary
 * method of this call is {@code evaluate}. This method evaluates the expression
 * given on initialization and returns its value as a {@code Double}.
 */
@SuppressWarnings("unused")
public class Expression implements Comparable<Expression> {

    private String expression;
    private HashMap<Character, Double> variableMap;
    private HashMap<String, Expression> functionMap;

    public Expression(String expression) {
        this.expression = expression;
        this.variableMap = new HashMap<>();
        this.functionMap = new HashMap<>();
    }

    /**
     * Compares one {@code Expression}'s value to another. The rules for this
     * comparison are defined in {@linkplain java.lang.Double#compare(double, double)  Double.compare(this, another)}.
     *
     * @param another the object to be compared.
     * @return the value {@code -1} if the argument's value is larger than this,
     * or the value {@code 1} if it's the other way around.
     */
    @Override
    public int compareTo(Expression another) {
        return Double.compare(evaluate(), another.evaluate());
    }

    /**
     * Evaluates the expression given at initialization following the order of
     * operations (PEMDAS). Implied multiplication is allowed on both parentheses,
     * functions, and variables.
     *
     * @return the result of the expression as a {@code Double}.
     */
    public Double evaluate() {
        processVariables();
        ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
        TokenizedExpressionParser parser = new TokenizedExpressionParser(tokenizer.tokenize(), functionMap);
        Node tree = parser.parse();
        tree.token.execute(tree.left, tree.right);
        return tree.token.value();
    }


    /**
     * Creates a character in the expression with a value. Implied
     * multiplication is supported, and will automatically be added.
     *
     * @param variable The character to be associated with a value.
     * @param value The value that is to be associated with the variable.
     * @return An updated {@code Expression} object with the variable-value association.
     */
    public Expression variable(Character variable, Double value) {
        variableMap.put(variable, value);
        return this;
    }

    /**
     * Overwrites the current {@code HashMap} containing the expression's variable-value
     * associations with a new one.
     *
     * @param map your variable {@code HashMap}.
     * @return An {@code Expression} object with the updated {@code HashMap}.
     */
    public Expression variableMap(HashMap<Character, Double> map) {
        variableMap = map;
        return this;
    }

    /**
     * Associates a function with an all uppercase name, to an expression that evaluates it.
     * The operand of the function will be associated with the variable {@code x}
     * in the {@code Expression} object.
     *
     * @param name The name to associate the function with.
     * @param expression An {@code Expression} object to evaluate the function.
     * @return An updated {@code Expression} object with the function association.
     */
    public Expression function(String name, Expression expression) {
        functionMap.put(name.toUpperCase(), expression);
        return this;
    }

    /**
     * Overwrites the current {@code HashMap} containing the expression's function
     * associations with a new one.
     *
     * @param map your function {@code HashMap}.
     * @return An {@code Expression} object with the updated {@code HashMap}.
     */
    public Expression functionMap(HashMap<String, Expression> map) {
        functionMap = map;
        return this;
    }

    private void processVariables() {
        for (Character key : variableMap.keySet()) {
            int variableIndex = expression.indexOf(key);
            String replacement = variableMap.get(key).toString();

            if (!expression.isEmpty()) {
                if (variableIndex > 0
                        && (Character.isDigit(expression.charAt(variableIndex - 1))
                        || expression.charAt(variableIndex - 1) == ')'))
                    replacement = "*" + replacement;

                if (variableIndex < expression.length() - 1
                        && (Character.isDigit(expression.charAt(variableIndex + 1))
                        || expression.charAt(variableIndex + 1) == '('
                        || Character.isUpperCase(expression.charAt(variableIndex + 1))))
                    replacement = replacement + "*";
            }
            expression = expression.replace(key.toString(), replacement);
        }
    }

}
