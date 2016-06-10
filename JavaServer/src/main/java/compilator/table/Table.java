package compilator.table;

import compilator.ast.Reserver;
import compilator.ast.Type;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by max2 on 10/03/2016.
 */
public class Table {

    public static Table instance;
    private Stack<Block> blocks;
    private Stack<Block> globals;

    private Table(){
        blocks = new Stack<Block>();
        globals = new Stack<Block>();
    }

    public static Table getInstance(){
        if(instance == null){
            instance = new Table();
            //On ajoute les frames reservés dans la compilator.table des symboles pour les functionCall
            for(Reserver.Function func : Reserver.Function.values())
                Table.getInstance().addTopBlock(func.getFi(), true);
        }
        return instance;
    }

    public static void reset(){
        instance = null;
    }

    public void pushBlock(Block b){
        blocks.push(b);
    }

    public void newBlock(){
        blocks.push(new Block());
    }

    public void addTopBlock(AbstractIdentificateur i,boolean isGlobal){
        if(!isGlobal) {
            try {
                Block b = blocks.peek();
                b.addIdentificateur(i);
            } catch (EmptyStackException e) {
                System.out.println("EMPTY STACK, creating block");
                blocks.add(new Block());
                blocks.peek().addIdentificateur(i);
            }
        }else{
            try {
                Block b = globals.peek();
                b.addIdentificateur(i);
            } catch (EmptyStackException e) {
                System.out.println("EMPTY STACK, creating block");
                globals.add(new Block());
                globals.peek().addIdentificateur(i);
            }
        }
    }

    public Type.EnumType lookUp(String n, boolean isFunction) {
        try {
            Type.EnumType exists;
            for (Block b : blocks) {
                exists = b.exists(n, isFunction);
                if (b.exists(n, isFunction) != null) {
                    return exists;
                }
            }
            exists = globals.peek().exists(n, isFunction);
            if(exists != null){
                return exists;
            }
        } catch (EmptyStackException e) {
            System.out.println("EMPTY STACK");
        }

        return null;
    }

    public int lookUpIndex(String n) {
        try {
            int exists;
            for (Block b : blocks) {
                exists = b.exists(n);
                if (b.exists(n) != 0) {
                    return exists;
                }
            }
            exists = globals.peek().exists(n);
            if(exists != 0){
                return exists;
            }
        } catch (EmptyStackException e) {
            System.out.println("EMPTY STACK");
        }

        return 0;
    }

    public void popBlock(){
        try {
            blocks.pop();
        }catch(EmptyStackException e){
            System.out.println("Pile vide");
        }
    }

    @Override
    public String toString() {

        String st = "BLOCS :";
        for(Block block : blocks)
            st+= "\n\t"+block.toString();

        st+= "\nGLOBALS:";
        for(Block block : globals)
            st+= "\n\t"+block.toString();
        return st;
    }

    public FunctionIdentificateur getFunc(String n){
        return globals.peek().getFunc(n);
    }

}
