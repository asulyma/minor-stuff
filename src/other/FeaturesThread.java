package other;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class FeaturesThread {
    private static Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private static int money = 0;
    private static Condition condition = lock.newCondition();
    private static Callable<Integer> callable = new MyCallable();
    private static FutureTask<Integer> futureTask = new FutureTask<>(callable);
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private static Semaphore semaphore = new Semaphore(2);
    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    private static Exchanger<String> exchanger = new Exchanger<>();
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicRun());
    private static Phaser phaser = new Phaser(2);
    private static BlockingQueue<String> queue = new PriorityBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /** Пример реализации Lock
         * Лочит нить, аналог synchronized, методом tryLock() проверяет доступна(занята) ли нить
         */
        //new MyThread().start();
        //new MyThreadSecond().start();

        /** Пример реализации Condition
         * Поток должен получить сигнал о готовности вызовом метода .signal()
         * Другой поток будет ждать этого сигнала с помощью метода .await()
         */
        //new MoneyRemove().start();
        //new MoneyAdd().start();


        /** Аналог Runnable
         *  Запускает нить, выполняет её и методом .get() ждёт окончания её работы(подобие .join())
         *  Callable - интерфейс, позволяет запустить нить и получить с неё результат( .call())
         *  FutureTask - запускает нить и имеет дополнительные удобные методы
         */
        //new Thread(futureTask).start();
        //System.out.println(futureTask.get());

        /** Pool потоков
         * Повышенная производительность, расход ресурсов минимум
         * submit() - добавление в пул
         * shutdown() - ожидание завершения и закрытие пула
         */
        //executorService.submit(new Pool());
        //executorService.shutdown();

        /** Запуск потока через определённое время
         * Указываем через какое время должен произойти запуст потока
         * shutdown() - ожидание завершения и закрытие пула
         */
        //scheduledExecutorService.schedule(new Pool(), 3, TimeUnit.SECONDS);
        //scheduledExecutorService.shutdown();

        /** Semaphore - общий ресурс, который состоит из количество потоков, которому семафор будет разрешать одновременно использовать заданный ресурс
         * acquire() - блочит один ресурс
         * release() - говорит, что ресурс свободен
         */
        //SemaphorePerson person1 = new SemaphorePerson();
        //person1.table = semaphore;
        //person1.start();
        //SemaphorePerson person2 = new SemaphorePerson();
        //person2.table = semaphore;
        //person2.start();
        //SemaphorePerson person3 = new SemaphorePerson();
        //person3.table = semaphore;
        //person3.start();
        //SemaphorePerson person4 = new SemaphorePerson();
        //person4.table = semaphore;
        //person4.start();

        /** CountDownLatch - предоставляет возможность любому количеству потоков ожидать, пока не завершаться все потоки методом countDown()
         * countDown() - уменьшает на единицу
         * await() - ждёт, чтобы счётчик дошёл до нуля
         */
        //new LatchWork(countDownLatch);
        //new LatchWork(countDownLatch);
        //new LatchWork(countDownLatch);
        //countDownLatch.await();
        //System.out.println("All job done");

        /** Exchanger<> обмен данных в разных потоках
         * exchange() - передает эти данные между потоками, нужен общий exchange
         */
        //new ExchangerExampleInfo(exchanger);
        //new ExchangerInput(exchanger);

        /** CyclicBarrier - циклический барьер. Позволяет не начинать работу потоку CyclinRun, пока все 3 потока не вызовут метод await()
         */
        //new CyclicItem(cyclicBarrier);
        //new CyclicItem(cyclicBarrier);
        //new CyclicItem(cyclicBarrier);

        /** Phaser - регистрирует два потока, и пока два потока не вызовут метод arriveAndAwaitAdvance(), не сможет идти дальше.
         */
        //new WorkPhaser(phaser);
        //new WorkPhaser(phaser);

        /** BlockingQueue - потокобезопасная очередь
         * Будет заблочена, если элемента нету
         */
        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> System.out.println(queue.add("item"))).start();
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

    static class MoneyAdd extends Thread {
        @Override
        public void run() {
            lock.lock();
            money += 10;
            condition.signal();
            lock.unlock();
        }
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int j = 0;
            for (int i = 0; i < 10; i++, j++) {
                Thread.sleep(300);
            }
            return j;
        }
    }

    static class Pool implements Callable<String> {     //or implements Runnable
        @Override
        public String call() {
            //do smth
            return "1";
        }
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

    static class LatchWork extends Thread {
        CountDownLatch countDownLatch;

        LatchWork(CountDownLatch countDownLatch) {
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

    static class ExchangerExampleInfo extends Thread {
        Exchanger<String> exchanger;

        ExchangerExampleInfo(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                exchanger.exchange("First example");
                exchanger.exchange("Second example");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ExchangerInput extends Thread {
        Exchanger<String> exchanger;

        ExchangerInput(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println(exchanger.exchange(null));
                System.out.println(exchanger.exchange(null));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class CyclicRun extends Thread {
        @Override
        public void run() {
            System.out.println("Run is begin");
        }
    }

    static class CyclicItem extends Thread {
        CyclicBarrier cyclicBarrier;

        public CyclicItem(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            start();
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class WorkPhaser extends Thread {
        Phaser phaser;

        public WorkPhaser(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println(getName() + " washing the car " + i);
                phaser.arriveAndAwaitAdvance();
            }
        }
    }
}
