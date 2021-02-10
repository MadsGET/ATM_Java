package mads.com.company.atm;

import java.util.ArrayList;
import java.util.List;

public class ATM
{
    public ArrayList<CashStock> Stock;

    public ATM(ArrayList<CashStock> stock)
    {
        Stock = stock;
    }

    public int TotalValue()
    {
        int result = 0;

        for(CashStock s: Stock)
        {
            result += s.TotalValue();
        }

        return result;
    }

    public void Withdraw(int amount, Client client)
    {
        // If ATM out of cash stock or client does not have the required balance.
        if(!CanWithdraw(amount) || !client.CheckBalance(amount) || !CalculateWithdrawl(amount))
        {
            System.out.println("Cannot complete transaction from account " + client.AccountNumber + " amount " + amount + "\n");
        }
        else
        {
            client.Withdraw(amount);
            System.out.println(client.LastTransaction());
            System.out.println(this);
        }
    }

    public void Deposit(int value, int amount)
    {
        CashStock foundStock = TryFetchStock(value);
        if(foundStock != null)
        {
            foundStock.Deposit(amount);
            System.out.println("Deposited to ATM machine " + value + "x" + amount + "\n");
            System.out.println(this);
        };
    }

    private boolean CanWithdraw(int amount) // -> Client must implement this as well
    {
        return (TotalValue() >= amount);
    }

    private boolean CalculateWithdrawl(int amount)
    {
        if(!CanWithdraw(amount)) return false;

        int calculatedSize = 0;
        ArrayList<Integer> withdrawalRequest = new ArrayList<Integer>();

        for(CashStock s : Stock)
        {
            int requestAmount = 0;

            // Is this value less than the amount or equal?
            if(s.Value <= amount)
            {
                for(int i = 0; i < s.Amount; i++)
                {
                    // If we are still not at the requested amount and next step does not go out of range of the request.
                    if(calculatedSize != amount && calculatedSize + s.Value <= amount)
                    {
                        calculatedSize += s.Value;
                        requestAmount++;
                    }
                }
            }
            withdrawalRequest.add(requestAmount);
        }

        if(calculatedSize == amount)
        {
            // Update cash stock
            for(int i = 0; i < Stock.size(); i++)
            {
                CashStock s = Stock.get(i);
                //System.out.println("Requested: " + s.Value + " amount " + withdrawalRequest.get(i));
                Stock.get(i).Withdraw(withdrawalRequest.get(i));
            }

            return true;
        }

        return false;
    }

    private CashStock TryFetchStock(int value)
    {
        for (CashStock s : Stock)
        {
            if(s.Value == value) return s;
        }

        return null;
    }

    @Override
    public String toString()
    {
        String result = "";

        for(CashStock s : Stock)
        {
            result += s + "\n";
        }

        return result + "Total: " + TotalValue() + "\n";
    }
}
