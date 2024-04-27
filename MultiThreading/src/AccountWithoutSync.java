import java.util.concurrent.*;

// This  doesn't work because threads are dynamic, so the amount of time it takes to run the threads is not guaranteed
public class AccountWithoutSync {
	private static Account userAccount = new Account();
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for(int i = 0; i < 100; i++) {
			executor.execute(new AddAPenny());
		}
		// This finishes any tasks that are currently running, but does not allow any new tasks to be submitted
		executor.shutdown();
		while(!executor.isShutdown()) {
			
		}
		System.out.println("What is balance? " + userAccount.getBalance());
	}
	private static class AddAPenny implements Runnable{
		public void run() {
			synchronized(userAccount) {
				userAccount.deposit(1);
			}
		}
	}
	
	private static class Account{
		private int balance = 0;
		
		public void deposit(int amount) {
			int newBalance = balance + amount;
			try {
				Thread.sleep(1);
			}
			catch(InterruptedException ex) {
				
			}
			balance = newBalance;
		}
		
		public int getBalance() {
			return balance;
		}
		
		
	}
}
