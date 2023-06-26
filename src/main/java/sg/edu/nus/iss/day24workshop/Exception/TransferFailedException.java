package sg.edu.nus.iss.day24workshop.Exception;

public class TransferFailedException extends RuntimeException{
    
    public TransferFailedException (){
       super();
    }
    public TransferFailedException (String message){
        super(message);
    }
    public TransferFailedException (Throwable t){
        super(t);
    }
    public TransferFailedException (String message, Throwable t){
        super(message, t);
    }
}
