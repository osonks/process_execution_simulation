import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Reader
{
    private static Reader instance = null;

    private Scanner sc;

    private Reader() throws FileNotFoundException {
        sc = new Scanner(new File("input.txt"));
    }

    public static Reader getInstance() throws IOException {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }

    public void readProcessors() {
        while (!this.sc.hasNextInt()) {
            this.sc.next();
        }
        int numOfProcessors = this.sc.nextInt();
        for (int i = 0; i < numOfProcessors; i++) {
            Scheduler.getInstance().addProcessor(new ProcessorType1());
        }
    }

    public void readTasks(int cycle, int numOfTasks) {
        for (int i = 0; i < numOfTasks; i++) {

            for (; !this.sc.hasNextBoolean(); this.sc.next());
            boolean priority = this.sc.nextBoolean();

            for (; !this.sc.hasNextInt(); this.sc.next());
            int requested = this.sc.nextInt();

            Queue.getInstance().enqueue(new TaskType1(priority, cycle, requested));
        }
    }

    public int numOfTasks() {
        for (; !this.sc.hasNextInt(); this.sc.next());
        return this.sc.nextInt();
    }

    public void closeScanner(){
        sc.close();
    }
}

