package threads.concurrentFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerExecutorServiceWork {
    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        /** Запуск потока через определённое время
         * Указываем через какое время должен произойти запуст потока
         * shutdown() - ожидание завершения и закрытие пула
         */
        scheduledExecutorService.schedule(new Pool(), 3, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(new Pool(), 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }

    static class Pool implements Callable<String> {     //or implements Runnable
        @Override
        public String call() {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        }
    }
}
