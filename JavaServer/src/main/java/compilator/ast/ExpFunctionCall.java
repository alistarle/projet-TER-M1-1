package compilator.ast;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpErlangExit;
import compilator.exceptions.ReferenceIndefinie;
import compilator.intermediate.Frame;
import compilator.intermediate.Intermediate;
import compilator.table.Table;
import executor.Executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 24/02/16.
 */
public class ExpFunctionCall extends Expression {
    public FunctionCall fc;

    public ExpFunctionCall(Position pos, FunctionCall fc) {
        this.pos = pos;
        this.fc = fc;
    }

    public String toString() {
        return fc.toString();
    }

    @Override
    public Type.EnumType getType() throws Exception{
        //Table.getInstance().
        Type.EnumType t = Table.getInstance().lookUp(fc.id,true);
        if(t!=null){
            return t;
        }else{
            throw new ReferenceIndefinie(fc.id,pos);
        }
    }

    @Override
    public int evaluate(List<Integer> stack) throws InterruptedException, OtpErlangExit, OtpAuthException, IOException {
        Frame f = Intermediate.getFrameList().get(Table.getInstance().getFunc(fc.id).getIndex());
        ArrayList<Integer> params = new ArrayList<Integer>();
        for (Expression e : fc.param) {
            params.add(e.evaluate(stack));
        }
        return Executor.erlang.execute(Reserver.Function.valueOf(fc.id), params);
    }

    @Override
    public void verifSemantique() throws Exception {
        fc.verifSemantique();
    }
}
