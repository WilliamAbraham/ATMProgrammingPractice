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

    public static void main(String[] args) {
        Atm atm = new Atm();

        try {
            atm.openAccount("user123", 100.0);
            System.out.println("Account opened successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to open account: " + e.getMessage());
        }

        try {
            atm.depositMoney("user123", 50.0);
            System.out.println("Deposit successful. New balance: " + atm.checkBalance("user123"));
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to deposit money: " + e.getMessage());
        }

        try {
            double withdrawalAmount = 70.0;
            atm.withdrawMoney("user123", withdrawalAmount);
            System.out.println("Withdrawal of " + withdrawalAmount + " successful. New balance: " + atm.checkBalance("user123"));
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to withdraw money: " + e.getMessage());
        }

        try {
            atm.closeAccount("user123");
            System.out.println("Account closed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to close account: " + e.getMessage());
        }
    }
}

