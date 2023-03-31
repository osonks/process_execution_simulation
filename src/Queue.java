import java.util.PriorityQueue;

public class Queue {
    private static Queue instance = null;

    private PriorityQueue<Task> queue = new PriorityQueue<>();

    public static Queue getInstance() {
        if (instance == null) {
            instance = new Queue();
        }
        return instance;
    }

    public Task dequeue() {
        /* 20 */     return this.queue.poll();
        /*    */   }

    public void enqueue(Task task) {
        if (task == null) throw new IllegalArgumentException();
        if (!contains(task)) {
            this.queue.add(task);
        }
    }

    private boolean contains(Task task) {
        for (Task t : this.queue) {
            if (t.equals(task))
                return true;
        }
        return false;
    }

    public boolean isEmpty() {
        /* 40 */     return this.queue.isEmpty();
        /*    */   }

}
