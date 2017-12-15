import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by WSYOU on 2017-12-14.
 */
public class App {
    public static void main( String[] args) throws Exception
    {
        System.out.println(args[0]);
        App app = new App();
        app.printCwd();
        String resDir = "src/main/resources/";
        ExprLexer lexer = new ExprLexer( new ANTLRFileStream(resDir + "/" + args[0]));
        CommonTokenStream tokens = new CommonTokenStream( lexer );
        ExprParser parser = new ExprParser( tokens );
        ParseTree tree = parser.expr();
//        ParseTreeWalker walker = new ParseTreeWalker();
//        walker.walk( new ExprWalker(), tree );
    }
    /* 현재 Directory 알기 */
    public void printCwd() {
        String current = null;
        try {
            current = new File( "." ).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Current dir:"+current);
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);
    }
}
