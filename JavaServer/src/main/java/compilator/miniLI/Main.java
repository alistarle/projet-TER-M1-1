package compilator.miniLI;

import compilator.ast.Ast;
import compilator.ast.Program;
import compilator.intermediate.Intermediate;
import compilator.parser.MiniliLexer;
import compilator.parser.MiniliParser;
import compilator.parser.MiniliVisitor;
import compilator.table.Table;
import executor.Executor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by thomas on 29/02/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ErrorHandling.DEBUG = false;

        String code = args[0];

        // FIRST STEP: analysis
        // Creation of the stream of characters
        ANTLRInputStream input = new ANTLRInputStream(code);
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

        System.out.println("=========== Debut de l'éxécution ===========");
        Executor exec = new Executor(Intermediate.getFrameList(), Table.getInstance().getFunc("main").getIndex());
        exec.execute();

    }
}
