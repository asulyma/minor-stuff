package threads.concurrentFramework;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionWork {
    private static Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static int money = 0;

    public static void main(String[] args) {
        /** Пример реализации Condition
         * Поток должен получить сигнал о готовности вызовом метода .signal()
         * Другой поток будет ждать этого сигнала с помощью метода .await()
         */
        new MoneyRemove().start();
        new MoneyAdd().start();
    }

    static class MoneyRemove extends Thread {
        @Override
        public void run() {
            if (money < 10) {
                try {
                    lock.lock();
                    System.out.println(money);
                    condition.await();              //The stream will wait until it receives a signal()
                    System.out.println(money);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            money -= 10;
        }
    }

    static class MoneyAdd extends Thread {
        @Override
        public void run() {
            lock.lock();
            money += 10;
            condition.signal();
            lock.unlock();
        }
    }
}
