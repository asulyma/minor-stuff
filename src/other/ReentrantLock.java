package other;

import java.util.concurrent.locks.Lock;

public class ReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        resource.i = 5;
        MyThread myThreadFirst = new MyThread();
        myThreadFirst.setName("one");
        MyThread myThreadSecond = new MyThread();
        myThreadFirst.resource = resource;
        myThreadSecond.resource = resource;
        myThreadFirst.start();
        myThreadSecond.start();
        myThreadFirst.join();
        myThreadSecond.join();
        System.out.println(resource.i);
    }

    static class MyThread extends Thread {
        Resource resource;

        @Override
        public void run() {
            resource.changeI();
        }
    }
}

class Resource {
    int i;
    private Lock lock = new java.util.concurrent.locks.ReentrantLock();

    void changeI() {
        lock.lock();
        int i = this.i;
        if (Thread.currentThread().getName().equals("one")) {
            Thread.yield();
        }
        i++;
        this.i = i;
        lock.unlock();
    }
}
