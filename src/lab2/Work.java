package lab2;

public class Work {

    public static final int N = 50;

    public static void main(String[] args) {
        FirstTask firstTask = new FirstTask();
        SecondTask secondTask = new SecondTask();
        ThirdTask thirdTask = new ThirdTask();

        firstTask.run();
        secondTask.run();
        thirdTask.run();
    }
}
