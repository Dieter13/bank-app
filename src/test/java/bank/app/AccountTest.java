package bank.app;


import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;



public class AccountTest  {

    private Account account1;
    private Account account2;

    @Before
    public void setup(){
        account1 = new Account("00123", AccountType.CURRENT_ACCOUNT);
        account2 = new Account("00234", AccountType.SAVING_ACCOUNT);
        account2.setAccountBalance(20);
    }

    //Test1
    //Jesli wplacimy 10 stan konta ma byc 10
    @Test

    public void shouldReturnTenWhenDepositOnEmptyAccount() {
        account1.deposit(10);
        int result = account1.getAccountBalance();
        assertEquals(10,result,"Should return 10 when deposit was 10");

    }

    //Test2
    //Jesli wplacimy 10 na konto z saldem = 20 to stan konta ma byc 30
    @Test
    public void shouldReturnSumWhenDepositOnNonEmptyAccount(){
        account2.deposit(10);
        int result = account2.getAccountBalance();
        assertEquals(30, result, "Should return 30 when deposit was 10 as entry balance was 20");
    }

    //Test3
    //Jesli wyplacamy z konta 40 z saldem 20

    @Test
    public void shouldReturnAccountOverDrawn(){
        account2.withdraw(40);
        int result = account2.getAccountBalance();
        assertEquals(20, result, "Should return message when  --> brak srodkow na rachunku");
    }

    //Test4
    //Jesli wyplacamy 20 z saldem 20 to stan komnta ma byc 0, pusty

    @Test

    public void shouldReturnZeroWhenSaldoEqualsToWithDraw(){
        account2.withdraw(20);
        int result = account2.getAccountBalance();
        assertEquals(0,result,"Should return 0 when 20 is withdrawn from saldo 20 ");
    }

    //Test5
    //jesli wuplacamy 10 z saldem 20 to stan konta my byc 10

    @Test
    public void shouldReturn10When20WithdrawnFromAccount() {
        account2.withdraw(10);
        int result = account2.getAccountBalance();
        assertEquals(10, result, "Jesli wybieramy 10 to zostaje saldo 10 ");

    }
}

