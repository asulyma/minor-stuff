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
         * shutdown() - ожидание завершения и закрытие пула.
         */
        executorService.submit(new Pool());
        executorService.shutdown();

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
}
