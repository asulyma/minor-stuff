package other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class FeaturesThread {
    static Lock lock = new java.util.concurrent.locks.ReentrantLock();
    static int money = 0;
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        //new MyThread().start();
        //new MyThreadSecond().start();

        new MoneyRemove().start();
        new MoneyAdd().start();

    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            lock.lock();
            System.out.println(getName() + " start working");
            try {
                sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " stop working");
            lock.unlock();
        }
    }

    static class MyThreadSecond extends Thread {
        @Override
        public void run() {
            System.out.println(getName() + " begin working");
            while (true) {
                if (lock.tryLock()) {
                    System.out.println(getName() + " working");
                    break;
                } else System.out.println(getName() + " waiting");
            }
        }
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

    static class MoneyAdd extends Thread{
        @Override
        public void run(){
            lock.lock();
            money += 10;
            condition.signal();
            lock.unlock();
        }
    }
}
