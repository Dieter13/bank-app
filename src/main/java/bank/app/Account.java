package bank.app;

public class Account {

    private String iban;
    private AccountType accountType;
    private int accountBalance;

    public Account(String iban, AccountType accountType) {
        this.iban = iban;
        this.accountType = accountType;
        this.accountBalance = 0;
    }

    public Account(String iban) {
        this.iban = iban;
        this.accountBalance = 0;
    }

    public void setAccountKind(AccountType accounttype) {
        this.accountType = accountType;
    }

    public String getIban() {
        return iban;
    }

    public AccountType getAccountKind() {
        return accountType;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean deposit(int amount){
        this.accountBalance += amount;
        System.out.println("Wpłata wykonana, stan konta = "+accountBalance);
        return true;
    }

    public boolean withdraw(int amount){
        if (accountBalance>= amount) {
            accountBalance -= amount;
            return true;
        }
        System.out.println("Brak srodkow na rachunku");
        return false;
    }

}
