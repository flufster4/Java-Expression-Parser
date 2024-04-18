package com.flufster.jep.parser.token;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ExpressionTokenizer {

    private final String expression;
    private final List<String> result;
    public static final String basicOperationRegex = "[+\\-*/^]";
    public static final String advancedOperationRegex = "[A-Z]";

    public ExpressionTokenizer(String expression) {
        this.expression = expression;
        this.result = new ArrayList<>();
    }

    public List<String> tokenize() {
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/^()ABCDEFGHIJKLMNOPQRSTUVWXYZ", true);
        StringBuilder stringBuilder = new StringBuilder();
        String previousToken = "";
        String token = "";
        boolean updateToken = true;

        while (tokenizer.hasMoreTokens()) {
            if (updateToken)
                token = tokenizer.nextToken();
            else
                updateToken = true;

            if (token.equals("(")) {
                if (!previousToken.isEmpty() && !previousToken.matches(basicOperationRegex))
                    result.add("*");
                int depth = 0;
                while (true) {
                    if (token.equals("("))
                        depth++;
                    if (token.equals(")"))
                        depth--;
                    stringBuilder.append(token);
                    if (depth == 0)
                        break;
                    token = tokenizer.nextToken();
                }
                result.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                continue;
            }

            if (token.matches(advancedOperationRegex)) {
                if (!previousToken.isEmpty() && !previousToken.matches(basicOperationRegex))
                    result.add("*");
                while (token.matches(advancedOperationRegex)) {
                    stringBuilder.append(token);
                    token = tokenizer.nextToken();
                }
                result.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                updateToken = false;
                if (!token.equals("("))
                    result.add(token);
                continue;
            }

            previousToken = token;
            result.add(token);
        }
        return result;
    }

}
