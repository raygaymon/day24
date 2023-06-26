package sg.edu.nus.iss.day24workshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.day24workshop.Exception.AccountInactiveOrBlocked;
import sg.edu.nus.iss.day24workshop.Exception.AmountNotEnoughException;
import sg.edu.nus.iss.day24workshop.Exception.BANotFoundException;
import sg.edu.nus.iss.day24workshop.Exception.TransferFailedException;
import sg.edu.nus.iss.day24workshop.Model.BankAccount;
import sg.edu.nus.iss.day24workshop.Repository.BankRepo;

@Service
public class BankService {
    
    @Autowired
    BankRepo repo;

    public BankAccount retrieveAccountById (int id) {

        BankAccount ba = repo.getAccountById(id);

        return ba;
    }

    public Boolean createAcc (BankAccount ba) {
        return repo.createAccount(ba);
    }

    public boolean withdraw (int id, float withdraw) {
        return repo.withdraw(id, withdraw);
    }

    public boolean deposit (int id, float deposit) {
        return repo.deposit(id, deposit);
    }

    //everything in one unit of work
    //writing records to more than one table
    //update more than one record in the same table
    @Transactional
    public Boolean transferMoney (int withdrawId, int depositId, float transferAmt) {

        //1. check that transferrer account exists
        //2. check that receiver exists
        Boolean existW = false;
        Boolean existD = false;
        BankAccount baW = repo.getAccountById(withdrawId);
        BankAccount baD = repo.getAccountById(depositId);

        if (baW != null) {
            existW = true;
        } else {
            throw new BANotFoundException("What account");
        }

        if (baD != null) {
            existD = true;
        } else {
            throw new BANotFoundException("What account");
        }

        //3. check that transferrer is active
        //4. check that receiver is active
        Boolean activeW = baW.getIsActive();
        Boolean activeD = baD.getIsActive();
        Boolean bothActive = false;

        if (activeW != false && activeD != false){
            bothActive = true;;
        } else {
            throw new AccountInactiveOrBlocked("The account(s) too long never use so grow mold alr");
        }

        //5. check transferrer and receiver are not blocked

        Boolean blockedW = baW.getIsBlocked();
        Boolean blockedD = baD.getIsBlocked();
        Boolean bothNotBlocked = false;

        if (!blockedW && !blockedD){
            bothNotBlocked = true;
        } else {
            throw new AccountInactiveOrBlocked("IDK wtf you did but you fucked up somewhere and now they dw let you use the account(s)");
        }

        //can combine both above into one function
        //check if withdraw and deposit accounts are both not blocked and are active
        
        // Boolean allowedW = false;
        // if (baW.getIsActive() && !baW.getIsBlocked()){
        //     allowedW = true;
        // }
        // Boolean allowedD = false;
        // if (baD.getIsActive() && !baD.getIsBlocked()){
        //     allowedD = true;
        // }

        //6. check transferrer has enough money to send
        Boolean enough = false;
        System.out.println(baW.getBalance());
        if (baW.getBalance() > transferAmt) {
            
            enough = true;
        } else {
            throw new AmountNotEnoughException("You poor fuck get a job");
        }

        Boolean transferSuccess = false;

        Boolean withdrawSuccess = repo.withdraw(withdrawId, transferAmt);
        Boolean depositSuccess = repo.deposit(depositId, transferAmt);

        if (withdrawSuccess == true && depositSuccess == true) {
            transferSuccess = true;
            return transferSuccess;
        } else {
            throw new TransferFailedException("Something fucked up here wait ah");
        }
  
    }
}
