package compilator.ast;

import com.ericsson.otp.erlang.OtpAuthException;
import com.ericsson.otp.erlang.OtpErlangExit;

import java.io.IOException;
import java.util.List;

public abstract class Expression extends Ast {

	abstract public Type.EnumType getType() throws Exception;

	abstract public int evaluate(List<Integer> stack) throws InterruptedException, OtpErlangExit, OtpAuthException, IOException;
}
