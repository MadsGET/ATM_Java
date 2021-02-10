package mads.com.company.atm;

import java.io.Console;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        ArrayList<CashStock> stock = new ArrayList<CashStock>();
        stock.add(new CashStock(500, 1));
        stock.add(new CashStock(200, 2));
        stock.add(new CashStock(100, 3));
        stock.add(new CashStock(50, 4));

        ATM atm = new ATM(stock);
        Client client1 = new Client(100, "20008.522.3009");

        // Print client & atm
        System.out.println(atm);
        System.out.println(client1);

        // Try withdraw
        atm.Withdraw(1500, client1);

        // Deposit to client account
        client1.Deposit(2000);
        System.out.println(client1.LastTransaction());

        // Deposit to atm machine
        atm.Deposit(100, 8);

        // Withdraw from atm machine
        atm.Withdraw(1500, client1);
        System.out.println(client1);
    }
}
