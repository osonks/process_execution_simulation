public interface Processor {
    int getId();

    boolean isBusy();

    Task getTask();

    Task removeTask();

    void assignTask(Task task);

    void executeTask();
}
