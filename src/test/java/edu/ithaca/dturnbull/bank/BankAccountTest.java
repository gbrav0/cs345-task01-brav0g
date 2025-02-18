package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        
        assertFalse(BankAccount.isEmailValid(""));         // empty string
        assertFalse(BankAccount.isEmailValid("a..b@gmail.com")); //should not have two consecutive dots
        assertFalse(BankAccount.isEmailValid("gb@@mail.com")); // should not contain more than one '@''
        assertFalse(BankAccount.isEmailValid(".hello@mail.com")); //prefix should not start with a special character

        //test push 
        assertTrue(BankAccount.isEmailValid("g.b@domain.com")); // valid with dots 
        assertTrue(BankAccount.isEmailValid("g_bravo@mail.test.com")); // valid with underscores and subdomains
        assertTrue(BankAccount.isEmailValid("g123@domain.com")); // valid with numbers
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}