/**
 * Created by Vladimir on 11/03/2016.
 */
public class CustomProtocol {

    public String processInput(String input){
        if(input == null)
            return "";


        if(input.equals("disconnect")) {
            return "disconnect";
        }

        else{
            return "execute_code";
        }

    }

}
