package other;

public class SynchronizedClassB {
    synchronized void bar(SynchronizedClassA a) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в B.bar");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("B прерван");
        }
        System.out.println(name + " пытается вызвать A.last()");
        a.last();
    }

    synchronized void last() {
        System.out.println("внутри А.last");
    }
}
