package sg.edu.nus.iss.day24workshop.Exception;

public class BANotFoundException extends RuntimeException{
    
    public BANotFoundException(){
        super();
    }

    public BANotFoundException (String message) {
        super(message);
    }

    public BANotFoundException (String message, Throwable cause){
        super(message, cause);
    }

    public BANotFoundException (Throwable cause) {
        super(cause);
    }
}
