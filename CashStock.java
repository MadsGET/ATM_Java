package mads.com.company.atm;

public class CashStock extends Transferable
{
    public final int Value;
    public int Amount;

    public int TotalValue ()
    {
        return Value * Amount;
    }

    public CashStock(int value, int amount)
    {
        Value = value;
        Amount = amount;
    }

    public int Withdraw(int amount)
    {
        if(amount <= 0 || amount > Amount) return -1;
        else
        {
            Amount -= amount;
            return amount * Value;
        }
    }

    public void Deposit(int amount)
    {
        Amount += amount;
    }

    @Override
    public String toString()
    {
        return Value + "x" + Amount + " total: " + TotalValue();
    }
}
