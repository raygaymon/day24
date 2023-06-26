package sg.edu.nus.iss.day24workshop.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount implements Serializable{

    private int id;
    private float balance;
    private String name;
    private Boolean isActive;
    private Boolean isBlocked;

}
