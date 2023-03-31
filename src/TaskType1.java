import java.util.Objects;

public class TaskType1 implements Comparable<Task>, Task {

    private final int taskID;
    private final int creationTime;
    private final int requestedTime;
    private int completionTime;
    private static int countID = 0;
    private int remainingTime;
    private final boolean highPriority;
    private Task.state s;

    public TaskType1(boolean highPriority, int creationTime, int requestedTime) {
        this.taskID = ++countID;
        this.highPriority = highPriority;
        this.creationTime = creationTime;
        this.requestedTime = requestedTime;
        this.remainingTime = requestedTime;
        this.completionTime = 0;
        this.s = Task.state.waiting;
    }

    public int getTaskID() {
        /* 21 */     return this.taskID;
        /*    */   } public int getRequestedTime() {
        /* 23 */     return this.requestedTime;
        /*    */   } public int getRemainingTime() {
        /* 25 */     return this.remainingTime;
        /*    */   } public int getCreationTime() {
        /* 27 */     return this.creationTime;
        /*    */   } public int getCompletionTime() {
        /* 29 */     return this.completionTime;
        /*    */   } public boolean getPriority() {
        /* 31 */     return this.highPriority;
        /*    */   } public Task.state getState() {
        /* 33 */     return this.s;
        /*    */   }

    public void setCompletionTime(int t) {
        if (t > 0) { this.completionTime = t; }
        else { throw new IllegalArgumentException(); }
    }

    public void setState(Task.state s) {
        /* 40 */     this.s = s;
        /*    */   }

    public void setRemainingTime(int r) {
        if (r >= 0) { this.remainingTime = r; }
        else { throw new IllegalArgumentException(); }

    }

    public String toString() {
        return "taskType1{taskID=" + this.taskID + ", creationTime=" + this.creationTime + ", requestedTime=" + this.requestedTime + ", completionTime=" + this.completionTime + ", remainingTime=" + this.remainingTime + ", Priority=" + this.highPriority + ", state=" + this.s + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskType1 taskType1 = (TaskType1) o;
        return taskID == taskType1.taskID && creationTime == taskType1.creationTime && requestedTime == taskType1.requestedTime && completionTime == taskType1.completionTime && remainingTime == taskType1.remainingTime && highPriority == taskType1.highPriority && s == taskType1.s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskID, creationTime, requestedTime, completionTime, remainingTime, highPriority, s);
    }

    public int compareTo(Task t) {
        if (this.highPriority && !t.getPriority()) return -1;
        if (!this.highPriority && t.getPriority()) return 1;
        return 0;
    }
}
