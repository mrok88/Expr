import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * Created by WSYOU on 2017-12-14.
 */
public class ExprWalker extends ExprBaseVisitor<Integer>{
    @Override
    public Integer visitOpExpr( ExprParser.OpExprContext ctx) {
        int left = visit(ctx.left);
        int right = visit(ctx.right);
        String op = ctx.op.getText();
        switch (op.charAt(0)) {
            case '*': return left * right;
            case '/': return left / right;
            case '+': return left + right;
            case '-': return left - right;
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Integer visitStart( ExprParser.StartContext ctx) {
        return this.visit(ctx.expr());
    }

    @Override
    public Integer visitAtomExpr( ExprParser.AtomExprContext ctx) {
        return Integer.valueOf(ctx.getText());
    }

    @Override
    public Integer visitParenExpr( ExprParser.ParenExprContext ctx) {
        return this.visit(ctx.expr());
    }

    public static void main(String[] args) throws IOException {

        String resDir = "src/main/resources";
        String filePath = resDir + "/" + args[0];
        System.out.println("FileName : " + filePath);
        ExprLexer lexer = new ExprLexer( new ANTLRFileStream(filePath));
        ExprParser parser = new ExprParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.start();
        Integer answer = new ExprWalker().visit(tree);
        System.out.println( readLineByLineJava8( filePath ));
        System.out.printf("%s\n", answer);
    }
    //Read file content into string with - Files.lines(Path path, Charset cs)
    private static String readLineByLineJava8(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}
