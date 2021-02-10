package mads.com.company.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Client extends Transferable
{
    public int Balance;
    public String AccountNumber;
    public ArrayList<Transaction> History = new ArrayList<Transaction>();

    public Client(int balance, String accountNumber)
    {
        Balance = balance;
        AccountNumber = accountNumber;
    }

    public int Withdraw(int amount)
    {
        if(Balance < amount) return -1;
        Balance -= amount;
        History.add(new Transaction(amount, false));
        return Balance;
    }

    protected void Deposit(int amount)
    {
        Balance += amount;
        History.add(new Transaction(amount, true));
    }

    public String LastTransaction ()
    {
        if(History.size() == 0) return "";
        else return History.get(History.size() - 1) + " to account " + AccountNumber + "\n";
    }

    public boolean CheckBalance(int amount)
    {
        return (Balance >= amount);
    }

    @Override
    public String toString() {
        return "Client: " + AccountNumber + " balance: " + Balance;
    }
}
