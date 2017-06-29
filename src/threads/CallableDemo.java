package threads;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer> futureInt;
        Future<Double> futureDouble;
        Future<Integer> futureIntSecond;

        System.out.println("Start!");

        futureInt = executorService.submit(new Sum(10));
        futureDouble = executorService.submit(new Hypot(3, 4));
        futureIntSecond = executorService.submit(new Factorial(5));

        try {
            System.out.println(futureInt.get());
            System.out.println(futureDouble.get());
            System.out.println(futureIntSecond.get());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e);
        }
        executorService.shutdown();
        System.out.println("End!");
    }

    static class Sum implements Callable<Integer> {
        int stop;

        Sum(int v) {
            stop = v;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i <= stop; i++) {
                sum += i;
            }
            return sum;
        }
    }

    static class Hypot implements Callable<Double> {
        double sideFirst, sideSecond;

        Hypot(double a, double b) {
            sideFirst = a;
            sideSecond = b;
        }

        @Override
        public Double call() throws Exception {
            return Math.sqrt((sideFirst * sideFirst) + (sideSecond * sideSecond));
        }
    }

    static class Factorial implements Callable<Integer> {
        int stop;

        Factorial(int v) {
            stop = v;
        }

        @Override
        public Integer call() throws Exception {
            int result = 1;
            for (int i = 0; i <= result; i++) {
                result *= i;
            }
            return result;
        }
    }
}
