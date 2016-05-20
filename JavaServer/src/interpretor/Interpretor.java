package interpretor;

import com.sun.deploy.util.StringUtils;
import interpretor.instructions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alistarle on 20/05/2016.
 */
public class Interpretor {

    private List<Instruction> instructions;

    public Interpretor(String code) {
        instructions = new ArrayList<>();
        parseCode(code);
    }

    public void parseCode(String code)
    {
        for(String s : code.split("\\n"))
        {
            if(StringUtils.trimWhitespace(s).matches("^[0-9]+:$")) {
                instructions.add(new Label(Integer.valueOf(s.trim().split(":")[0])));
            } else if(StringUtils.trimWhitespace(s).matches("^goto [0-9]+$")) {
                instructions.add(new Goto(Integer.valueOf(s.trim().split(" ")[1])));
            } else if(StringUtils.trimWhitespace(s).matches("^jump* [0-9]+ [0-9]+$")) {
                String[] firstSplit= s.trim().split(" ");
                instructions.add(new Jump(new Expression(firstSplit[0].split("\\(")[1].substring(0, s.length()-1)),new Label(Integer.valueOf(firstSplit[1])),new Label(Integer.valueOf(firstSplit[2]))));
            } else if(StringUtils.trimWhitespace(s).matches("a[0-9]+ = *$")) {
                instructions.add(new Assign());
            } else if(StringUtils.trimWhitespace(s).matches("call [0-9]+ *$")) {
                instructions.add(new ErlangCall());
            }
        }
    }
}
