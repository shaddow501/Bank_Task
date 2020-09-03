package system.core;

public class Bank {

	private Client[] clients;
	private Logger logger;
	private Object accountUpdater;
	private static float balance;
	// The singleton Design pattern - only one bank in the system
	// =====================================
	// 1. Singleton -- the instance
	private static Bank instance = new Bank();
	private static float comisionSum = 0;

	// 2. define a private CTOR
	private Bank() {
		clients = new Client[100];
		logger = new Logger(null);
	}

	// 3. getter for the instance
	public static Bank getInstance() {
		return instance;
	}

	public float getBalance() {
		return balance;
	}
	// Bank
	// balance

	// will update allcommisions field when a withdraw is happend in one of the
	// clients.
	public static void addComittion(float commision) {
		comisionSum += commision;
		balance+=commision;
	}

	// getter
	public float getAllcommisions() {
		return comisionSum;
	}

	public float gettotalClientsMoney() {
		float Sum = 0;
		for (int i = 0; i < clients.length; i++) {
			if(clients[i]!=null) {
				Sum += clients[i].getFortune();
			}
		}
		return Sum;
	}

	/**
	 * this operation returns total clients fortune. This is calculated by summing
	 * the total clients fortune – you should use Client.getFortune() method of each
	 * client.
	 */
	public float getClientFortune() {
		float clientsFortune = 0;
		for (Client client : clients) {
			if (client != null) {
				clientsFortune += client.getFortune();
			}
		}
		return clientsFortune;
	}

	/**
	 * addClient(Client) : void - add the client to the array and log the operation.
	 * You should seek the array and place the Client where the first null value is
	 * found.
	 */
	public void addClient(Client client) {
		for (int i = 0; i < clients.length; i++) {
			if (clients[i] == null) {
				clients[i] = client;
				LogTheOperation("Client added to Bank,", client.getFortune(), client.getId());
				return;
			}
		}
		System.out.println("Client not added - you have " + clients.length + " Clients");
	}

	/**
	 * removeClient(int id) : void - remove the client with the same id from the
	 * array (by assigning a 'null' value to the array[position]). Log the operation
	 */
	public void removeClient(int clientId) {
		System.out.println();
		for (int i = 0; i < clients.length; i++) {
			Client curr = clients[i];
			if (curr != null && curr.getId() == clientId) {
				clients[i] = null;
				LogTheOperation("Client was removed from the Bank,", curr.getFortune(), clientId);
				return; // exit the loop and exit the method.
			}

		}
		System.out.println("Client with id " + clientId + " not found");
	}

	/** get an array of all the bank clients (without null elements) */
	public Client[] getClients() {
		Client[] temp = new Client[clients.length];
		int index = 0;
		// copy elements
		for (Client client : this.clients) {
			if (client != null) {
				temp[index++] = client;
			}
		}
		// create a temp (theClients) array of the right length
		Client[] theClients = new Client[index];
		// copy element to the temp (theClients) array
		System.arraycopy(temp, 0, theClients, 0, index);

		return theClients;
	}

	/**
	 * log the operation - create a log and send to the logger
	 * 
	 * @param description description description of the action
	 * @param amount      the amount of the operation
	 * @param clientId
	 */
	private void LogTheOperation(String Description, float amount, int clientId) {
		long timestamp = System.currentTimeMillis();
		Log log = new Log(timestamp, clientId, Description, amount);
		logger.log(log);
	}

	public void viewLogs() {
		System.out.println("not yet implemented");
	}

	public void startAccountUpdater() {
		System.out.println("not yet implemented");
	}

	/* returns the specified client */
	public Client getClient(int clientId) {
		for (Client client : clients) {
			if (client != null && client.getId() == clientId) {
				return client;
			}

		}
		return null;
	}

}
