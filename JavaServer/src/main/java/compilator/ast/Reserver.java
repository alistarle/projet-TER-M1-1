package compilator.ast;

import compilator.intermediate.Instruction;
import compilator.intermediate.Intermediate;
import compilator.intermediate.instruction.Label;
import compilator.table.FunctionIdentificateur;

import java.util.ArrayList;

/**
 * Created by thomas on 22/02/16.
 */
public class Reserver extends Assign {

    public enum Function {
        ETEINDRELED(new FunctionIdentificateur(Type.EnumType.VOIDVAL, "EteindreLed")),
        ALLUMERLED(new FunctionIdentificateur(Type.EnumType.VOIDVAL, "AllumerLed")),
        MOTEUR(new FunctionIdentificateur(Type.EnumType.VOIDVAL, "Moteur")),
        TOURNERTETE(new FunctionIdentificateur(Type.EnumType.VOIDVAL, "TournerTete")),
        INFRAROUGEDROIT(new FunctionIdentificateur(Type.EnumType.INTVAL, "InfrarougeDroit")),
        INFRAROUGECENTRE(new FunctionIdentificateur(Type.EnumType.INTVAL, "InfrarougeCentre")),
        INFRAROUGEGAUCHE(new FunctionIdentificateur(Type.EnumType.INTVAL, "InfrarougeGauche")),
        ODOMETRE(new FunctionIdentificateur(Type.EnumType.INTVAL, "Odometre")),
        ULTRASON(new FunctionIdentificateur(Type.EnumType.INTVAL, "Ultrason"));

        private FunctionIdentificateur fi;

        Function(FunctionIdentificateur fi){
            fi.setIndex(Intermediate.fresh_lbl());
            this.fi = fi;
        }

        public FunctionIdentificateur getFi() { return fi; }

        public Label getLabel(){
            return new Label(fi.getIndex());
        }
    }

    public Function var;

    public Reserver(Position pos, String var) {
        this.pos = pos;
        this.var = Function.valueOf(var);
    }

    public String toString() {
        return var.name();
    }

    @Override
    public void verifSemantique() throws Exception {

    }

    @Override
    public ArrayList<Instruction> genIntermediate() {
        return null;
    }
}
