package other;

public class Deadlock implements Runnable {

    SynchronizedClassA a = new SynchronizedClassA();
    SynchronizedClassB b = new SynchronizedClassB();

    public Deadlock() {
        Thread.currentThread().setName("MainThread");
        Thread t = new Thread(this, "RacingThread");
        t.start();

        a.foo(b);

        System.out.println("Назад в главный поток");
    }

    @Override
    public void run() {
        b.bar(a);
        System.out.println("Назад в другой поток");
    }

    public static void main(String[] args) {
        new Deadlock();
    }
}
