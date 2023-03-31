public interface Task extends Comparable<Task> {
    void setState(state paramstate);
    void setRemainingTime(int paramInt);
    int getRemainingTime();
    void setCompletionTime(int paramInt);
    int getTaskID();
    boolean getPriority();
    public enum state { waiting, executing, completed; }
}
