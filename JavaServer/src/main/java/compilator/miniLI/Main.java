package compilator.miniLI;

import compilator.ast.Ast;
import compilator.ast.Program;
import compilator.intermediate.Intermediate;
import compilator.parser.MiniliLexer;
import compilator.parser.MiniliParser;
import compilator.parser.MiniliVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by thomas on 29/02/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        //Enable debug in Error Handling
        ErrorHandling.DEBUG = false;

        // FIRST STEP: analysis
        // Creation of the stream of characters
        File file = new File("/Users/alistarle/Documents/Master/Compilation/exemples/abs_main.miniLI");
        FileInputStream fis = new FileInputStream(file);
        ANTLRInputStream input = new ANTLRInputStream(fis);
        // Creation of the lexer for pico programs
        MiniliLexer lexer = new MiniliLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Creation of the compilator.parser for pico programs
        MiniliParser parser = new MiniliParser(tokens);
        // Parse the input: the result is a parse tree
        ParseTree tree = parser.minili();
        // Walk the parse tree in order to create an
        // abstract syntax tree

        MiniliVisitor<Ast> buildAST = new BuildAST();
        Program program = (Program) buildAST.visit(tree);
        program.verifSemantique();

        System.out.println("=========== Affichage du pretty printer ===========");
        System.out.println(program.toString());

        System.out.println("=========== Language Intermediaire ===========");
        Intermediate.genIntermediate(program);
        System.out.println(Intermediate.printIntermediate());
    }
}
