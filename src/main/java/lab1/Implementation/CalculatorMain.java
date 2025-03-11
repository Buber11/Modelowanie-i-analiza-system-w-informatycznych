package lab1.Implementation;


import lab1.antlr4.CalculatorLexer;
import lab1.antlr4.CalculatorParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CalculatorMain {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("> ");
        String inputLine = reader.readLine();  // Czytaj pojedynczą linię

        CharStream input = CharStreams.fromString(inputLine);
        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.prog();

        CalculatorVisitor visitor = new CalculatorVisitor();
        Double result = visitor.visit(tree);
        System.out.println(result);
    }

}