import java.util.HashMap;
import java.util.Map;

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
}

