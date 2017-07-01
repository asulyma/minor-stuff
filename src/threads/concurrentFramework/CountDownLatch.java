package threads.concurrentFramework;

public class CountDownLatch {
    private static java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        /** CountDownLatch - предоставляет возможность любому количеству потоков ожидать, пока не завершаться все потоки методом countDown()
         * countDown() - уменьшает на единицу
         * await() - ждёт, чтобы счётчик дошёл до нуля
         */
        new LatchWork(countDownLatch);
        new LatchWork(countDownLatch);
        new LatchWork(countDownLatch);
        countDownLatch.await();
        System.out.println("All job done");
    }

    static class LatchWork extends Thread {
        java.util.concurrent.CountDownLatch countDownLatch;

        LatchWork(java.util.concurrent.CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
            start();
        }

        @Override
        public void run() {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Done work");
            countDownLatch.countDown();
        }
    }
}
