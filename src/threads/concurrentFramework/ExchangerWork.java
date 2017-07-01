package threads.concurrentFramework;

import java.util.concurrent.Exchanger;

public class ExchangerWork {
    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        /** Exchanger<> обмен данных в разных потоках
         * exchange() - передает эти данные между потоками, нужен общий exchange
         */
        new ExchangerInput(exchanger);
        new ExchangerOutput(exchanger);
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
                exchanger.exchange("First example");
                exchanger.exchange("Second example");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ExchangerOutput extends Thread {
        Exchanger<String> exchanger;

        ExchangerOutput(Exchanger<String> exchanger) {
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
}
