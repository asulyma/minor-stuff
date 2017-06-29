package threads.concurrentFramework;

import java.util.concurrent.Semaphore;

public class SemaphoreWork {
    private static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        /** Semaphore - общий ресурс, который состоит из количество потоков, которому семафор будет разрешать одновременно использовать заданный ресурс
         * acquire() - блочит один ресурс
         * release() - говорит, что ресурс свободен
         */
        SemaphorePerson person1 = new SemaphorePerson();
        person1.table = semaphore;
        person1.start();
        SemaphorePerson person2 = new SemaphorePerson();
        person2.table = semaphore;
        person2.start();
        SemaphorePerson person3 = new SemaphorePerson();
        person3.table = semaphore;
        person3.start();
        SemaphorePerson person4 = new SemaphorePerson();
        person4.table = semaphore;
        person4.start();
    }

    static class SemaphorePerson extends Thread {
        Semaphore table;

        @Override
        public void run() {
            System.out.println(this.getName() + " waiting for table");
            try {
                table.acquire();
                System.out.println(this.getName() + " eat at the table");
                sleep(1000);
                System.out.println(this.getName() + " release table");
                table.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
