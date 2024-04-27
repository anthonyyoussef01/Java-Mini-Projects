import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class AccountWithConditions {
    private static Account account = new Account();

    public static void main(String[] args) {
        System.out.println("Thread 1\t\tThread 2\t\t\tBalance");
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());    					// doesn't need to wait for the previous line to finish
        executor.shutdown();                    					// allows the program to end when the threads finish

        // keeps the program running until the threads finish (the executor is shut down)
        while (!executor.isShutdown()) {
        }
    }

    public static class DepositTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    account.deposit((int) (Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static class WithdrawTask implements Runnable {
        public void run() {
            while (true) {
                account.withdraw((int) (Math.random() * 10) + 1);
            }
        }
    }

    private static class Account {
        private static Lock lock = new ReentrantLock(true);
        private static Condition newDeposit = lock.newCondition();	// allows us to only pass a lock if there is an appropriate balance in the account so that the withdrawal action can be completed
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            lock.lock();
            try {
                while (balance < amount) {
                    System.out.println("\t\t\t\tWait for a deposit\n\t\t\t\t(tried to withdraw " + amount + ")");
                    newDeposit.await();
                }
                balance -= amount;
                System.out.println("\t\t\t\tWithdraw " + amount + "\t\t\t" + getBalance());
            } catch (InterruptedException ex) {						// if the thread is interrupted while waiting, it will throw an exception
                ex.printStackTrace();
            } finally {												// allows the lock to be released after the thread is done
                lock.unlock();
            }
        }

        public void deposit(int amount) {
            lock.lock();
            try {
                balance += amount;
                System.out.println("Deposit " + amount +
                    "\t\t\t\t\t\t\t" + getBalance());
                newDeposit.signalAll();								// pauses this thread and wakes up all threads that are waiting on the condition (signals the deposit is done, so the withdrawal can be completed)
            } finally {
                lock.unlock();
            }
        }

    }
}
