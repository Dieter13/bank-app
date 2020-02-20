package bank.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private static Integer clientNumber = 0;
    private static Integer accountNumber = 0;

    private String name;
    private Map<String, Client> clients;
    //private List<Account> accounts;

    public Bank(String name) {
        this.name = name;
        clients = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Client> getClients() {
        return clients;
    }

    public boolean addClient(Client client) {
        //check if client already exists in bank
        if(clients.containsValue(client)) {
            System.out.println("Klient " + client + " już istnieje w banku");
            return false;
        }
        //add client when not found
        client.setId(clientNumber.toString());
        clients.put(clientNumber.toString(), client);
        clientNumber++;

        System.out.println("Klient " + client + " założony");
        return true;
    }

    public boolean removeClient(Client client) {
        //check if client exists in bank
        if(clients.containsValue(client)) {
            for(String clientNumber : clients.keySet()) {
                if (clients.get(clientNumber).equals(client)){
                    clients.remove(clientNumber);
                    System.out.println("Klient " + client + " usuniety z systemu");
                    return true;
                }
            }
        }
        //client not found
        System.out.println("Nie znaleziono klienta " + client + ", nie można usunąć");
        return  false;

    }

    //dodanie konta klientowi
    public boolean addAccount(String clientNumber, AccountType accountType) {
        if(clients.containsKey(clientNumber)){
            Client client = clients.get(clientNumber);
            Account account = new Account("iban"+accountNumber.toString(), accountType);
            Map<String, Account> accounts = client.getAccounts();
            accounts.put("iban"+accountNumber.toString(), account);
            client.setAccounts(accounts);
            System.out.println("Dla klienta " + client + " założono rachunek "
                    + account.getIban() + " " + account.getAccountKind());
            accountNumber++;
            return true;
        }
        System.out.println("Nie znaleziono klienta o numerze " +  clientNumber + "założenie konta niemożliwe");
        return clientNotFound(clientNumber,"zalozenie konta niemozliwe");
    }

    //usuniecie konta klienta

    public boolean deleteAccount(String clientNumber, String accountNumber, int amount){

        if(clients.containsKey(clientNumber)) {
            Client client = clients.get(clientNumber);
            if(client.getAccounts().containsKey(accountNumber)){
                Account account = client.getAccounts().get(accountNumber);
                if(account.getAccountBalance() == 0){
                    Map<String, Account> clientAccounts = client.getAccounts();
                    clientAccounts.remove(accountNumber);
                    client.setAccounts(clientAccounts);
                    System.out.println("Dla klienta " + client + " usunieto konto " + accountNumber);
                    return true;
                }
                System.out.println("Dla klienta " + client + " nie mozna zliwkwidowac konta " + accountNumber +
                        " bo sa na nim srodki " +account.getAccountBalance() );
            }
        }
        return clientNotFound(clientNumber, "Nie znaleziono klienta o numerze ");
    }


    //wpłata na konto
    public boolean depositMoney(String clientNumber, String accountNumber, int amount) {
        if(clients.containsKey(clientNumber)){
            Client client = clients.get(clientNumber);
            if(client.getAccounts().containsKey(accountNumber)){
                client.getAccounts().get(accountNumber).deposit(amount);
                System.out.println("Wpłata poprawnie zaksięgowana na koncie " + accountNumber);
                return true;
            }
            System.out.println("Dla klienta " + client + " nie znaleziono konta " + accountNumber);
            return false;
        }
        return clientNotFound(clientNumber, "Nie znaleziono klienta ");
    }

    public boolean withdrawMoney(String clientNumber, String accountNumber, int amount) {
        if(clients.containsKey(clientNumber)){
            List<Account> clientAccounts;
            Client client = clients.get(clientNumber);
            if(client.getAccounts().containsKey(accountNumber)){
                boolean withdrawPassed = isWithdrawPassed(accountNumber, amount, client);
                return withdrawPassed;

            }
            System.out.println("Dla klienta " + client + " nie znaleziono konta " + accountNumber);
            return false;
        }
        return clientNotFound(clientNumber, "Nie znaleziono klienta ");
    }

    private boolean isWithdrawPassed(String accountNumber, int amount, Client client) {
        boolean withdrawPassed =  client.getAccounts().get(accountNumber).withdraw(amount);
        if(withdrawPassed) {
            System.out.println("Wyplata " + amount + " poprawnie wykonana");
        } else {
            System.out.println("Wyplata " + amount + " zakonczona niepowodzeniem");
        }
        return withdrawPassed;
    }

    private boolean clientNotFound(String clientNumber, String additionalMessage) {
        System.out.println("Nie znaleziono klienta " + clientNumber + additionalMessage);
        return false;
    }


    public void printClients(boolean printAccountBalances){
        for (String clientNumber : clients.keySet()) {
            System.out.println(clients.get(clientNumber));
            //add print account balances here
            printCustomerAccounts(clientNumber, printAccountBalances);
        }
    }

    public void printCustomerAccounts(String clientNumber, boolean printAccountBalances){
        if(clients.containsKey(clientNumber)){
            Client client = clients.get(clientNumber);
            for(Account account : client.getAccounts().values()){
                System.out.println("Konto: " + account.getIban() +
                        " rodzaj " + account.getAccountKind() +
                        (printAccountBalances ? account.getAccountBalance() : ""));
            }
        } else {
            System.out.println("Client not found" );
        }
    }
}
