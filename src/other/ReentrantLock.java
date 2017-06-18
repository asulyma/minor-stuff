package other;

import java.util.concurrent.locks.Lock;

public class ReentrantLock {
    static Lock lock = new java.util.concurrent.locks.ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new MyThread().start();
        new MyThreadSecond().start();
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
}
