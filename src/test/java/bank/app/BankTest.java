package bank.app;


import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BankTest {

    private Bank bank1;
    private Bank bank2;

    @Before
    public void setUp() throws Exception {
        bank1 = new Bank("Bank Testowy 1");
        bank2 = new Bank("Bank testowy 2");
        Client client = new Client("Jan", "Nowak");
        bank2.addClient(client);
    }

    //Test 1
    //dodanie pierwszego klienta
    @Test
    public void shouldReturnOneWhenAddClientToEmptyList() {
        Client client = new Client("Jan", "Kowalski");
        bank1.addClient(client);
        int result = bank1.getClients().size();
        assertEquals(1, result, "Should return 1 when add client to empty list");


    }

    //Test 2

    @Test
    public void shouldReturnTwoWhenAddClientToEmptyList() {
        Client client = new Client("Tomasz", "Kowalski");
        bank2.addClient(client);
        int result = bank2.getClients().size();
        assertEquals(2, result, "Should return 1 when add client to empty list");


    }


    //Test 3
    //dodanie  klienta gdy jeden juz wpisany
    @Test
    public void shouldReturnzOneWhenAddClientThatAlreadyExists() {
        Client client = new Client("Jan", "Nowak");
        bank2.addClient(client);
        int result = bank2.getClients().size();
        assertEquals(1, result, "Should return 1 when add client to empty list");

    }

    //usuniecie klienta z pustej listy
    //Test 4
    @Test
    public void shouldReturnZeroWhenRemoveClientFromEmptyList() {
        Client client = new Client("Jan" , "Nowak");
        bank1.removeClient(client);
        int result = bank1.getClients().size();
        assertEquals(0, result, "Should return 0 when removing client from an empty list");

    }

    //usuniecie kilenta ktory istnieje
    //Test 5
    @Test
    public void shouldReturnZeroWhenRemoveClientFromNoNEmptyList() {
        Client client = new Client("Jan","Nowak");
        bank2.removeClient(client);
        int result = bank2.getClients().size();
        assertEquals(0, result, "Should return 0 when removing client from non empty list");

    }

    //usuniecie klienta ktorego ni ma

    //Test 6
    @Test
    public void shouldReturnZeroWhenClientRemovedDoesNotExist() {
        Client client = new Client("Jan","Nowak");
        bank2.removeClient(client);
        int result = bank2.getClients().size();
        assertEquals(0, result, "Should return 0 when removing client from non empty list");

    }

    // adding account to nonexistent client
    //Test8
    @Test
    public void shouldReturnFalseWhenAddAccountToNonExistingClient(){
        Client client = new Client("Jan","Nowak");
        bank1.addClient(client);
        //  boolean isAddAccount = bank1.addAcccount("2", CURRENT_ACCOUNT);
        bank1.printClients(true);

        // Assertions.assertEquals(false, isAddAccount, "Should return false when adding account to non existing client");

    }

    // adding account to an existing client
    //Test9
    @Test
    public void shouldReturnTrueWhenAddAccountToExistingClient(){
        Client client = new Client("Jan","Nowak");
        bank1.addClient(client);
        // boolean isAddAccount = bank1.addAcccount("1", CURRENT_ACCOUNT);
        bank1.printClients(true);

        //   Assertions.assertEquals(true, isAddAccount, "Should return true when adding account to existing client");

    }

    @Test
    public void shouldReturnFalseWhenWithdrawOnNONExistingClient(){
        Client client = new Client("Adam", "Kowalski");
        bank1.addClient(client);
        bank1.addAccount("1",AccountType.CURRENT_ACCOUNT);
        bank1.printClients(true);
        boolean isWithdraw = bank1.withdrawMoney("1","iban_4",20);
        assertEquals(false, isWithdraw, "Should return false when withdraw from not existing client");

    }
    @Test
    public void shouldReturnFalseWhenWithdrawOnExistingClientFromNotExistingAccount(){
        Client client = new Client("Adam", "Kowalski");
        bank1.addClient(client);
        bank1.addAccount("1",AccountType.CURRENT_ACCOUNT);
        bank1.printClients(true);
        boolean isWithdraw = bank1.withdrawMoney("1","iban",20);
        assertEquals(false, isWithdraw, "Should return false when withdraw from not existing client");

    }

}

