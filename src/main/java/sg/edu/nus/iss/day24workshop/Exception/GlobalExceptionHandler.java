package sg.edu.nus.iss.day24workshop.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BANotFoundException.class)
    public ModelAndView handleBANotFoundException (BANotFoundException ex, HttpServletRequest request) {
        
        //creating the error message object and injecting details into it from servlet
        ErrorMessage erM = new ErrorMessage();
        erM.setStatusCode(404);
        erM.setTimeStamp(new Date());
        erM.setMessage("Nothing to see here asshole you couldn't make one");
        erM.setDescription(request.getRequestURI());

        //creating a model and view to add errormessage to
        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", erM);
        return mav;
    }

    @ExceptionHandler(AmountNotEnoughException.class)
    public ModelAndView handleAmountNotEnoughException(HttpServletRequest request, AmountNotEnoughException ex) {

        ErrorMessage erM = new ErrorMessage();
        erM.setDescription(request.getRequestURI());
        erM.setStatusCode(404);
        erM.setTimeStamp(new Date());
        erM.setMessage("You poor fuck");

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", erM);
        return mav;
    }

    @ExceptionHandler(AccountInactiveOrBlocked.class)
    public ModelAndView handleAccountInactiveOrBlockedException(HttpServletRequest request, AmountNotEnoughException ex) {

        ErrorMessage erM = new ErrorMessage();
        erM.setDescription(request.getRequestURI());
        erM.setStatusCode(404);
        erM.setTimeStamp(new Date());
        erM.setMessage("You done fucked up this account doesn't work or exist");

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", erM);
        return mav;
    }

    @ExceptionHandler(TransferFailedException.class)
    public ModelAndView handleTransactionFailedException(HttpServletRequest request, AmountNotEnoughException ex) {

        ErrorMessage erM = new ErrorMessage();
        erM.setDescription(request.getRequestURI());
        erM.setStatusCode(404);
        erM.setTimeStamp(new Date());
        erM.setMessage("Something went wrong oops");

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", erM);
        return mav;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(HttpServletRequest request, AmountNotEnoughException ex) {

        ErrorMessage erM = new ErrorMessage();
        erM.setDescription(request.getRequestURI());
        erM.setStatusCode(404);
        erM.setTimeStamp(new Date());
        erM.setMessage("What you tryna say the language is off");

        return new ResponseEntity<ErrorMessage>(erM, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorMessage> handleHttpServerErrorException(HttpServletRequest request, AmountNotEnoughException ex) {

        ErrorMessage erM = new ErrorMessage();
        erM.setDescription(request.getRequestURI());
        erM.setStatusCode(404);
        erM.setTimeStamp(new Date());
        erM.setMessage("server huai liao");
        return new ResponseEntity<ErrorMessage>(erM, HttpStatus.BAD_REQUEST);
    }
    


}
