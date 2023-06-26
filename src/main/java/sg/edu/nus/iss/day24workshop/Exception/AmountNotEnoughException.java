package sg.edu.nus.iss.day24workshop.Exception;

public class AmountNotEnoughException extends RuntimeException{
    
    public AmountNotEnoughException (){
        super();
    }
    public AmountNotEnoughException (String message){
        super(message);
    }
    public AmountNotEnoughException (Throwable t){
        super(t);
    }
    public AmountNotEnoughException (String message, Throwable t){
        super(message, t);
    }

}
