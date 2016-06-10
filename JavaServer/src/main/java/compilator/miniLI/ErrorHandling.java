package compilator.miniLI;

import compilator.exceptions.CompilationException;

/**
 * Created by alistarle on 01/04/2016.
 */
public class ErrorHandling {


    public static boolean DEBUG = false;

    /**
     * Handle an error in compilation
     *
     * @param e exception who are triggered
     */
    public static void handleError(CompilationException e)
    {
        if(DEBUG) e.printStackTrace();
        System.out.println(e.getMessage()+" at "+e.getPos().toString());
        handleCriticalError();
    }

    /**
     * Behaviour of the compiler when an error is found
     */
    public static void handleCriticalError()
    {
        System.out.println("Arrêt du compilateur dû à une erreur de compilation");
        System.exit(0);
    }

    /**
     * Behaviour of the compiler when a warning is found
     *
     * @param e
     */
    public static void handleWarning(Exception e)
    {
        System.out.println("Warning : "+e.getMessage());
    }
}
