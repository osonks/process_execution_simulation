import java.io.IOException;
import java.util.ArrayList;

public class Scheduler {
    private static Scheduler instance = null;

    private static int numOfProcessors = 0;

    private ArrayList<Processor> allProcessors = new ArrayList<>();
    private ArrayList<Processor> idleProcessors = new ArrayList<>();
    private ArrayList<Processor> busyProcessors = new ArrayList<>();
    private ArrayList<Task> finishedTasks = new ArrayList<>();

    public static Scheduler getInstance() {
        if (instance == null) {
            instance = new Scheduler();
        }
        return instance;
    }

    public void addProcessor(Processor p) {
        this.allProcessors.add(p);
        this.idleProcessors.add(p);
        numOfProcessors++;
    }

    public void removeFinishedTasks(int cycle) {
        ArrayList<Processor> toRemove = new ArrayList<>();
        for (Processor p : this.busyProcessors) {
            if (p.getTask().getRemainingTime() == 0) {
                p.getTask().setState(Task.state.completed);
                p.getTask().setCompletionTime(cycle);
                this.finishedTasks.add(p.removeTask());
                toRemove.add(p);
                this.idleProcessors.add(p);
            }
        }
        this.busyProcessors.removeAll(toRemove);
    }

    public void fillIdleProcessors() {
        while (!Queue.getInstance().isEmpty() && !this.idleProcessors.isEmpty()) {
            Task t = Queue.getInstance().dequeue();
            Processor p = this.idleProcessors.remove(0);
            p.assignTask(t);
            this.busyProcessors.add(p);
        }
    }

    public void putHighestPriority() {
        Task t = Queue.getInstance().dequeue();
        for (Processor p : this.busyProcessors) {
            if (t != null &&
                    t.compareTo(p.getTask()) != 1) {
                Queue.getInstance().enqueue(p.removeTask());
                p.assignTask(t);
                t = Queue.getInstance().dequeue();
            }
        }

        if (t != null) Queue.getInstance().enqueue(t);

    }

    public void execute() throws IOException {
        for (Processor p : this.allProcessors) {
            if (p.isBusy()) {
                p.executeTask();
                Writer.getInstance().write("P" + p.getId() + " -> T" + p.getTask().getTaskID());
                continue;
            }
            Writer.getInstance().write("P" + p.getId() + " -> __");
        }
    }

    public void contextSwitch(int cycle) throws IOException {
        fillIdleProcessors();

        putHighestPriority();

        execute();

        removeFinishedTasks(cycle);
    }

    public void printFinishedTasks() throws IOException {
        for (Task t : this.finishedTasks) {
            Writer.getInstance().write(t.toString());
        }
    }

    public boolean allProcessorsAreEmpty() {
        /* 98 */     return (this.idleProcessors.size() == numOfProcessors);
        /*    */   }
}


