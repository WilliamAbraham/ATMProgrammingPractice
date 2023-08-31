import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Atm{
    Map<String, Double> accounts = new HashMap<>();

    public void openAccount(String userID, double amount){
        if (!accounts.containsKey(userID)){
            accounts.put(userID, amount);
        } else {
            throw new IllegalArgumentException("User already exists");
        }
    }
    
    public void closeAccount(String userID){
        if (accounts.get(userID) != 0){
            throw new IllegalArgumentException("User must withdraw: " + accounts.get(userID) + " before closing.");
        } else {
            accounts.remove(userID);
        }
    }

    public Double checkBalance(String userID){
        if (!accounts.containsKey(userID)){
            throw new IllegalArgumentException("No account found.");
        } else {
            return accounts.get(userID);
        }
    }

    public Double depositMoney(String userID, Double amount){
        if (accounts.containsKey(userID)){
            accounts.put(userID, accounts.get(userID) + amount);
            return accounts.get(userID);
        } else {
            throw new IllegalArgumentException("You're broke AF");
        }
    }

    public Double withdrawMoney(String userID, double amount){
        if ((accounts.get(userID) - amount) >= 0){
            accounts.put(userID, accounts.get(userID) - amount);
            return accounts.get(userID);
        } else {
            throw new IllegalArgumentException("You're broke AF");
        }
    }

    public Boolean transferMoney(String fromAccount, String toAccount, Double amount){
        try {
            this.withdrawMoney(fromAccount, amount);
            this.depositMoney(toAccount, amount);
        } catch(IllegalArgumentException a) {
            return false;
        }
        return true;
    }

    public void audit() throws IOException{
        try {
            File audit = new File("AccountAudit.txt");
            if (audit.exists()) {
                audit.delete();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("AccountAudit.txt"));
            for (Map.Entry<String, Double> entry : accounts.entrySet()) {
                String accountInfo = entry.getKey() + " : " + entry.getValue();
                writer.write(accountInfo);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred during audit: " + e.getMessage());
        }
    }
}

