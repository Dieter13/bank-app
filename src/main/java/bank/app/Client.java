package bank.app;

import java.util.*;

public class Client {

    private String name;
    private String surname;
    private String id;
    private Map<String, Account> accounts;
    private List<ClientHistory> clientHistory;
    ClientHistory entry;

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
        accounts = new HashMap<>();
        clientHistory = new LinkedList<>();
        addNewHistoryEntry("Client added to bank");
    }

    private void addNewHistoryEntry(String history) {
        entry = new ClientHistory(history);
        clientHistory.add(entry);
    }

    public String getName() {return name;}

    public String getSurname() {return surname;}

    public String getId() {return id;}

    public Map<String, Account> getAccounts() {return accounts;}

    public void setId(String id) {
        addNewHistoryEntry("Client added to bank");
        this.id = id;}

    public void setAccounts(Map<String, Account> accounts) {this.accounts = accounts;}

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Client client = (Client) o;
        return Objects.equals(getName(), client.getName()) &&
                Objects.equals(getSurname(), client.getSurname());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getSurname());
    }
}
