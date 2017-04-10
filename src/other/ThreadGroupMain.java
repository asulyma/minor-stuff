package other;

public class ThreadGroupMain {
    public static void main(String[] args) {
        ThreadGroup groupA = new ThreadGroup("Group A");
        ThreadGroup groupB = new ThreadGroup("Group B");

        NewThread ob1 = new NewThread("One", groupA);
        NewThread ob2 = new NewThread("Two", groupA);
        NewThread ob3 = new NewThread("Three", groupB);
        NewThread ob4 = new NewThread("Four", groupB);

        System.out.println("\nList: ");
        groupA.list();
        groupB.list();
        System.out.println();

        System.out.println("GroupA closed.");
        Thread tga[] = new Thread[groupA.activeCount()];
        groupA.enumerate(tga);      //get threads in group

        for (int i = 0; i < tga.length; i++) {
            ((NewThread) tga[i]).mySuspend();       //stopped each thread
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("Main thread closed.");
        }

        System.out.println("GroupA alive.");

        for (int i = 0; i < tga.length; i++) {
            ((NewThread) tga[i]).meResume();        //each stream is alive
        }

        try {
            System.out.println("Waiting stop threads.");
            ob1.join();
            ob2.join();
            ob3.join();
            ob4.join();
        } catch (Exception e) {
            System.out.println("Exception in main thread.");
        }
        System.out.println("Main thread stopped.");


    }
}
