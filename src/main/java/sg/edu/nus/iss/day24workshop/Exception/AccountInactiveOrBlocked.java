package sg.edu.nus.iss.day24workshop.Exception;

public class AccountInactiveOrBlocked extends RuntimeException {
    
    public AccountInactiveOrBlocked (){
        super();
    }
    public AccountInactiveOrBlocked (String message){
        super(message);
    }
    public AccountInactiveOrBlocked (Throwable t){
        super(t);
    }
    public AccountInactiveOrBlocked (String message, Throwable t){
        super(message, t);
    }
}
