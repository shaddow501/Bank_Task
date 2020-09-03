package tests;

import java.util.Arrays;

import system.core.Account;
import system.core.Bank;
import system.core.Client;

public class Banktest {

	public static void main(String[] args) {

		/*
		 * 1. create a bank
		 * 
		 * 2. add 3 clients
		 * 
		 * 3. deposit 100 to client 1
		 * 
		 * 4. withdraw 100 from client 2
		 * 
		 * 5. add 2 accounts to client 2
		 * 
		 * 6. remove client 2
		 * 
		 */
		Bank bank = Bank.getInstance();
		// adding clients
		bank.addClient(new Client(1, "aaa" , 10_000));
		bank.addClient(new Client(2, "bbb" , 15_000));
		bank.addClient(new Client(2, "ccc" , 7_000));
		
		// deposit
        bank.getClient(1).deposit(100);
        //withraw
//		bank.getClient(2).withdraw(100);
//		
//		// add account to the existing client.
//		bank.getClient(2).addAccount(new Account(101, 150_00));
//		bank.getClient(2).addAccount(new Account(102, 350_00));
//		
        bank = Bank.getInstance();
		//print all bank clients.
		System.out.println(Arrays.toString(bank.getClients()));
		// remove client 
		bank.removeClient(2);
		System.out.println(Arrays.toString(bank.getClients()));

	}

}
