package sg.edu.nus.iss.day24workshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day24workshop.Model.BankAccount;
import sg.edu.nus.iss.day24workshop.Service.BankService;

@RestController
@RequestMapping("/api")
public class BankRestController {

    @Autowired
    BankService service;
    
    @PostMapping("/create")
    public ResponseEntity<Boolean> createAccount (@RequestBody BankAccount ba){
        
        System.out.println("Bank Account created. Details: " + ba.toString());
        Boolean success = service.createAcc(ba);
        if (success) {
            return ResponseEntity.ok().body(success);
        } else {
            //custom exception
            return ResponseEntity.internalServerError().body(success);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccountById (@PathVariable("id") int id) {

        //no need model bcs its restcontroller
        BankAccount ba = service.retrieveAccountById(id);

        if (ba == null) {
            return ResponseEntity.internalServerError().body(ba);
        } else {
            return new ResponseEntity<>(ba, HttpStatus.OK);
        }
    }

    @PostMapping("/transfer/{W-id}/{D-id}")
    public ResponseEntity<Boolean> transferMoney (@PathVariable("W-id") int idW, @PathVariable("D-id") int idD, float transferAmount) {

        Boolean transferSuccess = service.transferMoney(idW, idD, transferAmount);
        System.out.println("Transfer success, " + transferAmount + "transferred to account with id" + idD);

        return new ResponseEntity<>(transferSuccess, HttpStatus.OK);
    }
}
