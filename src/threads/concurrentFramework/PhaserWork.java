package threads.concurrentFramework;

import java.util.concurrent.Phaser;

public class PhaserWork {
    private static Phaser phaser = new Phaser(2);

    public static void main(String[] args) {
        /** Phaser - регистрирует два потока, и пока два потока не вызовут метод arriveAndAwaitAdvance()
         *  не сможет идти дальше.
         */
        new WorkPhaser(phaser);
        new WorkPhaser(phaser);
    }

    static class WorkPhaser extends Thread {
        Phaser phaser;

        WorkPhaser(Phaser phaser) {
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
