public class ProcessorType1 implements Processor {

    private static int countID = 0;
    private final int id = ++countID;
    private Task task = null;
    private boolean isBusy = false;

    public void setBusy(boolean busy) {
        /* 15 */     this.isBusy = busy;
        /*    */   }

    public boolean isBusy() {
        /* 19 */     return this.isBusy;
        /*    */   }

    public int getId() {
        /* 23 */     return this.id;
        /*    */   }

    public Task getTask() {
        /* 27 */     return this.task;
        /*    */   }

    public void assignTask(Task task) {
        if (task == null) throw new IllegalArgumentException();
        this.task = task;
        this.task.setState(Task.state.executing);
        this.isBusy = true;
    }

    public void executeTask() {
        /* 38 */     this.task.setRemainingTime(this.task.getRemainingTime() - 1);
        /*    */   }

    public Task removeTask() {
        if (this.task.getRemainingTime() != 0) {
            this.task.setState(Task.state.waiting);
        } else {
            this.task.setState(Task.state.completed);
        }
        this.isBusy = false;
        Task t = this.task;
        this.task = null;
        return t;
    }
}