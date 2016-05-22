package compilator.table;

import compilator.ast.Type;

/**
 * Created by max2 on 10/03/2016.
 */
public abstract class AbstractIdentificateur {
    String nom;
    Type.EnumType t;
    int reg_index;

    public void setIndex(int reg_index) {
        this.reg_index = reg_index;
    }

    public int getIndex() {
        return reg_index;
    }

    @Override
    public String toString() {
        return "Identificateur{" +
                "nom='" + nom + '\'' +
                ", t=" + t +
                '}';
    }
}
