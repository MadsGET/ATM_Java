package mads.com.company.atm;

public class Transaction
{
    public final int Amount;
    public final boolean IsDeposit;

    public Transaction(int amount, boolean isDeposit)
    {
        Amount = amount;
        IsDeposit = isDeposit;
    }

    @Override
    public String toString()
    {
        return "Transaction: " + Amount + " was " + ((IsDeposit)? "deposited" : "withdrawn");
    }
}
