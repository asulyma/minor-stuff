package threads.concurrentFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceWork {
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        /** Pool потоков
         * Повышенная производительность, расход ресурсов минимум
         * submit() - добавление в пул
         * shutdown() - ожидание завершения и закрытие пула
         */
        executorService.submit(new Pool());
        executorService.submit(new Pool());
        executorService.submit(new Pool());
        executorService.submit(new Pool());
        executorService.shutdown();
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
