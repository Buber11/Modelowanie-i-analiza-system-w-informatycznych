package lab1.Implementation;


import lab1.antlr4.CalculatorBaseVisitor;
import lab1.antlr4.CalculatorParser;

public class CalculatorVisitor extends CalculatorBaseVisitor<Double> {
    @Override
    public Double visitProg(CalculatorParser.ProgContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Double visitExpression(CalculatorParser.ExpressionContext ctx) {
        Double result = visit(ctx.term(0));
        for (int i = 1; i < ctx.term().size(); i++) {
            Double termVal = visit(ctx.term(i));
            if (ctx.PLUS(i - 1) != null) {
                result += termVal;
            } else if (ctx.MINUS(i - 1) != null) {
                result -= termVal;
            }
        }
        return result;
    }

    @Override
    public Double visitTerm(CalculatorParser.TermContext ctx) {
        Double result = visit(ctx.factor(0));
        for (int i = 1; i < ctx.factor().size(); i++) {
            Double factorVal = visit(ctx.factor(i));
            if (ctx.MULT(i - 1) != null) {
                result *= factorVal;
            } else if (ctx.DIV(i - 1) != null) {
                result /= factorVal;
            }
        }
        return result;
    }

    @Override
    public Double visitFactor(CalculatorParser.FactorContext ctx) {
        if (ctx.NUMBER() != null) {
            return Double.parseDouble(ctx.NUMBER().getText());
        } else if (ctx.LPAREN() != null) {
            return visit(ctx.expression());
        } else {
            Double value = visit(ctx.factor());
            return ctx.MINUS() != null ? -value : value;
        }
    }
}
