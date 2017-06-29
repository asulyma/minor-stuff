package threads.concurrentFramework;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class BlockingQueueWork {
    private static BlockingQueue<String> queue = new PriorityBlockingQueue<>();

    public static void main(String[] args) {
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
}
