package other;

public class NewThread extends Thread {
    boolean suspendFlag;

    NewThread(String threadName, ThreadGroup threadGroup) {
        super(threadGroup, threadName);
        System.out.println("New thread: " + this);
        suspendFlag = true;
        start();
    }

    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(getName() + ": " + i);
                Thread.sleep(1000);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + getName());
        }
        System.out.println(getName() + " complete...");
    }

    synchronized void mySuspend() {
        suspendFlag = true;
    }

    synchronized void meResume() {
        suspendFlag = false;
        notify();
    }
}
