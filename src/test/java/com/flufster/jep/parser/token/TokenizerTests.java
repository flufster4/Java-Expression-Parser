package com.flufster.jep.parser.token;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class TokenizerTests {

    @Test
    void basicOperations() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer("1+1-1*1/1^1");
        List<String> expected = Arrays.asList("1", "+", "1", "-", "1", "*", "1", "/", "1", "^", "1");
        assertLinesMatch(expected, tokenizer.tokenize());
    }

    @Test
    void advancedOperationWithoutBrackets() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer("TAN45");
        List<String> expected = Arrays.asList("TAN", "45");
        assertLinesMatch(expected, tokenizer.tokenize());
    }

    @Test
    void advancedOperationWithBrackets() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer("TAN(45)");
        List<String> expected = Arrays.asList("TAN", "(45)");
        assertLinesMatch(expected, tokenizer.tokenize());
    }

    @Test
    void basicParentheses() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer("1+(2*2)");
        List<String> expected = Arrays.asList("1", "+", "(2*2)");
        assertLinesMatch(expected, tokenizer.tokenize());
    }

    @Test
    void parentheses() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer("(2+2)");
        List<String> expected = List.of("(2+2)");
        assertLinesMatch(expected, tokenizer.tokenize());
    }

    @Test
    void nestedParentheses() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer("5^((1+1)*3)");
        List<String> expected = Arrays.asList("5", "^", "((1+1)*3)");
        assertLinesMatch(expected, tokenizer.tokenize());
    }

    @Test
    void impliedMultiplication() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer("2(1+1)");
        List<String> expected = Arrays.asList("2", "*", "(1+1)");
        assertLinesMatch(expected, tokenizer.tokenize());
    }
}
