package threads.concurrentFramework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierWork {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicRun());

    public static void main(String[] args) {
        /** CyclicBarrier - циклический барьер. Позволяет не начинать работу потоку CyclinRun, пока все 3 потока не вызовут метод await()
         */
        System.out.println("Start!");

        new CyclicItem(cyclicBarrier, "A");
        new CyclicItem(cyclicBarrier, "B");
        new CyclicItem(cyclicBarrier, "C");

        new CyclicItem(cyclicBarrier, "X");
        new CyclicItem(cyclicBarrier, "Y");
        new CyclicItem(cyclicBarrier, "Z");
    }

    static class CyclicRun extends Thread {
        @Override
        public void run() {
            System.out.println("The barrier is reached!");
        }
    }

    static class CyclicItem extends Thread {
        CyclicBarrier cyclicBarrier;
        String name;

        public CyclicItem(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
            start();
        }

        @Override
        public void run() {
            System.out.println(name);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
