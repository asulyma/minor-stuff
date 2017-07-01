package threads.concurrentFramework;

import java.util.Date;
import java.util.concurrent.*;

public class ForkJoinWork {

    private static int numOfThread = Runtime.getRuntime().availableProcessors();
    private static ForkJoinPool pool = new ForkJoinPool(numOfThread);
    private static long valueOfOperation = 10_000_000_000L;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /** ForkJoinFramework
         * Позволяет разбить операцию на части рекурсивно и добиться увеличения производительности в разы за счёт
         * переложения задач на другия ядра
         */
        System.out.println(new Date());
        System.out.println(pool.invoke(new MyFork(0, valueOfOperation)));
        System.out.println(new Date());
    }

    static class MyFork extends RecursiveTask<Long> {
        long from, to;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if ((to - from) <= valueOfOperation / numOfThread) {
                long j = 0;
                for (long i = from; i < to; i++) {
                    j += i;
                }
                return j;
            } else {
                long middle = (to + from) / 2;
                MyFork firstHalf = new MyFork(from, middle);
                firstHalf.fork();
                MyFork secondHalf = new MyFork(middle + 1, to);
                long secondValue = secondHalf.compute();
                return firstHalf.join() + secondValue;
            }
        }
    }
}
