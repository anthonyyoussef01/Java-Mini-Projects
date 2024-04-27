import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// This class is used to demonstrate how to use Lock and Condition objects to synchronize threads.
// It gets a much closer result to the expected balance than without the use of Synchronized methods, but still not the expected result.
public class AccountWithSync {
    private static Account userAccount = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executor.execute(new AddAPenny());
        }
        executor.shutdown();
        while (!executor.isShutdown()) {
        }
        System.out.println("What is balance? " + userAccount.getBalance());
    }

    private static class AddAPenny implements Runnable {
        public synchronized void run() {
            userAccount.deposit(1);
        }
    }

    private static class Account {
        private static Lock lock = new ReentrantLock(true);
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            lock.lock();
            try {
                int newBalance = balance + amount;
                balance = newBalance;
            } finally {
                lock.unlock();
            }

        }

    }
}
