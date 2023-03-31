import java.io.IOException;

public class Simulation {
    private static Scheduler s = Scheduler.getInstance();

    public static void main(String[] args) throws IOException {
        int cycle = 1;
        int numOfTasks = 0;
        Writer w = Writer.getInstance();
        Reader r = Reader.getInstance();
        r.readProcessors();

        while (true) {
            if (numOfTasks != -1) numOfTasks = r.numOfTasks();
            if (numOfTasks > 0) r.readTasks(cycle, numOfTasks);
            w.write("cycle " + cycle + " :");
            s.contextSwitch(cycle);
            w.write("---------");
            cycle++;
            if (numOfTasks == -1 && s.allProcessorsAreEmpty()) {
                terminate(cycle);
                break;
            }
        }
        r.closeScanner();
    }

    public static void terminate(int simulationTime) throws IOException {
        simulationTime--;
        s.printFinishedTasks();
        Writer.getInstance().write("SIMULATION TIME: " + simulationTime + "\nTERMINATE SIMULATION");
        Writer.getInstance().closeFile();
    }

}

