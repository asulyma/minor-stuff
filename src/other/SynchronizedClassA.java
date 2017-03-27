package other;

public class SynchronizedClassA {
    synchronized void foo(SynchronizedClassB b) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " вошёл в А.foo");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Прерван А");
        }
        System.out.println(name + " пытается вызвать B.last()");
        b.last();
    }

    synchronized void last() {
        System.out.println("внутри А.last");
    }
}
