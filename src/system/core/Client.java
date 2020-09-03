package system.core;

public class Client {

	private int id;
	private String name;
	private float balance;
	private Account[] accounts = new Account[5];
	private float commissionRate;
	private float interestRate;
	private Logger logger;
	private int global_counter = 0;

	/**
	 * add the account to the array
	 * 
	 * log the operation.
	 * 
	 * You should seek the array and place the Account where the first null value is
	 * found.
	 */
	public void addAccount(Account account) {
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] == null) {
				accounts[i] = account;
				// log the operation - create a log and send to the logger
//				long timestamp = System.currentTimeMillis();
//				int clientId = this.id;
//				String description = "account added to client";
//				float amount = account.getBalance();
//				Log log = new Log(timestamp, clientId, description, amount);
//				logger.log(log);
				LogTheOperation("account added to client,", account.getBalance());
				global_counter++;
				return;
			}
		}
		System.out.println("account not added - you have " + accounts.length + " accounts");
	}

	public Account getAccount(int index) {
		if (index >= 0 && index < accounts.length) {
			return accounts[index];
		} else {
			System.out.println("Account not found");
			return null;
		}

	}

	public Account getAccountDor(int AccountId) {
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i].getId() == AccountId) {
				return accounts[i];
			}
		}
		System.out.println("Account not found");
		return null;
	}

	/*
	 * remove account (int id) : void - remove the account with the same id from the
	 * array (by assigning a 'null' value to the array[position]) & transfers the
	 * money to the clients balance. Log the operation via creating Log object with
	 * appropriate data and sending it to the Logger.log(..) method.
	 * 
	 * הפונקציה בעצם כשמוחקת את החשבון מחזירה את מה שהיה בחשבון ל-למשתמש ולא מחלקת
	 * בין החשבונות.
	 */
	public void removeAccount(int accountId) {
		System.out.println();
		for (int i = 0; i < accounts.length; i++) {
			Account curr = accounts[i];
			if (curr.getId() == accountId) {
				accounts[i] = null;
				this.balance += curr.getBalance();
//				long timestamp = System.currentTimeMillis();
//				int clientId = this.id;
//				String description = "Remove account from Client";
//				float amount = curr.getBalance();
//				Log log = new Log(timestamp, clientId, description, amount);
//				logger.log(log);
				LogTheOperation("account of the client was removed,", curr.getBalance());
				return; // exit the loop and exit the method.
			}

		}
		System.out.println("account with id " + accountId + "not found");
	}

	// method to log the operation and use it in other methods that needs logging.
	/**
	 * log the operation - create a log and send it to the logger
	 * 
	 * @param description = description of the action
	 * @param amount      = the amount of the operation
	 */
	private void LogTheOperation(String description, float amount) {
		long timestamp = System.currentTimeMillis();
		int clientId = this.id;
		Log log = new Log(timestamp, clientId, description, amount);
		logger.log(log);
	}

	/*
	 * deposit(float) : void - implement to add the amount to client's balance
	 * according to the commission (which is now zero). Use the commission data
	 * member in your calculation). log the operation
	 */
	public void deposit(float amount) {
		this.balance += amount;
		float commission = amount * this.commissionRate;
		this.balance -= amount;
		LogTheOperation("Deposit", amount);
		LogTheOperation("Commission for the deposit ", commission);
	}

	/*
	 * withdraw(float) : void - implement to remove the amount from client's balance
	 * according to the commission (which is now zero). Use the commission data
	 * member in your calculation). log the operation
	 */
	public void withdraw(float amount) {
		if(this.balance < amount) {
			System.out.println("not enough in balance");
		}else {
		//this.balance -= amount;
		float commission = amount * this.commissionRate;
		this.balance -= (commission + amount);
		Bank.addComittion(commission);
		LogTheOperation("Commission for the withdraw ", commission);
		}
		LogTheOperation("withdraw", amount);
	}
	
	/*
	 * autoUpdateAccounts() : void – run over the accounts, calculate the amount to
	 * add according to the client interest (meanwhile it is zero) and add it to
	 * each account balance. Use the interest data member in your calculation. Log
	 * this operation.
	 */
	public void autoUpdateAccounts() {
		for (Account account : accounts) {
			if (account != null) {
				// calculate the interest
				float interest = account.getBalance() * this.interestRate;
				// add the interest to the acount's balance
				account.setBalance(account.getBalance() + interest);
				// log the operation
				LogTheOperation("autoUpdateAccount id" + account.getId(), interest);
			}
		}
	}

	/*
	 * getFortune() : float – returns the sum of client balance + total account
	 * balance.
	 */
	public float getFortune() {
		float fortune = this.balance;
		for (Account account : accounts) {
			if (account != null) {
				fortune += account.getBalance();
			}
		}
		return fortune;
	}

	// [acc1 , null , null , acc, null]
	public void removeAccount_by_dor(int id) {
		// printing_accounts_data();
		System.out.println();

		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] != null && accounts[i].getId() == id) {

				float amount1 = accounts[i].getBalance(); // saving the amount.
				accounts[i] = null; // delete account.
				global_counter--;
				// function divide the balance between accounts
				existingAccounts(amount1);
				// printing_accounts_data();
				long timestamp = System.currentTimeMillis();
				int clientId = this.id;
				String description = "account added to client";
				float amount = getBalance();
				Log log = new Log(timestamp, clientId, description, amount);
				logger.log(log);
				return;
			}
		}
		System.out.println("account was not removed");
	}

	// steps:
	// we get amount to divide.
	// what we need to know?
	// 1. how many accounts are.V
	// 2. what is the indexes of the existing accounts.
	// 3. how much to give for each account.

	// 0 1 2 3 4
	// accounts: [acc, null, acc ,null, acc]
	// [ 0 , 2 , 4 ]
	public void existingAccounts(float amount) { // 1000%4 --> 250

//		int counter = 0;
//		for (int i = 0; i < accounts.length; i++) {
//			if(accounts[i] != null) {
//				counter++;
//			}
//		}

		int indexes[] = new int[global_counter];
		int in = 0;
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] != null) {
				indexes[in] = i;
				in++;
			}
		}

		float toDivide = amount / global_counter;
		for (int i = 0; i < indexes.length; i++) {
			int loc = indexes[i];
			float am = accounts[loc].getBalance();
			accounts[loc].setBalance(am + toDivide);
			// accounts[indexes[i]].setBalance(accounts[indexes[i]].getBalance()+toDivide);
		}

	}

	public Client(int id, String name, float balance) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		logger = new Logger(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public Account[] getAccounts() {
		return accounts;
	}

	public void printing_accounts_data() {
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] != null) {
				System.out.println("account id: " + accounts[i].getId() + ", " + "position in arr: " + i + ", amount: "
						+ accounts[i].getBalance());
			}
		}
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}
}
