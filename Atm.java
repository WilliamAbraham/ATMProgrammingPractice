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
}

