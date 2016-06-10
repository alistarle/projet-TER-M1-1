package compilator.miniLI;

/**
 * Created by alistarle on 02/04/2016.
 */
public class StringOffseter {

    public static int offset = 0;
    private StringBuilder builder;

    public StringOffseter()
    {
        builder = new StringBuilder();
    }

    public StringOffseter(String string)
    {
        builder = new StringBuilder(writeOffset()+string);
    }

    public StringOffseter(String string, boolean offset)
    {
        builder = (offset) ? new StringBuilder(writeOffset()+string) : new StringBuilder(string);
    }

    public void append(String string)
    {
        builder.append(writeOffset()+string);
    }

    public void appendNoOffset(String string) { builder.append(string); }

    public static String writeOffset() {
        String offsetString = "";
        for(int i = 0; i < offset; i++)
        {
            offsetString+="\t";
        }
        return offsetString;
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    public String toString()
    {
        return builder.toString();
    }

}
