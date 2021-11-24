/*
 * NAME: Xing Hong
 * PID: A15867895
 */

/**
 * Round-Robin Scheduling implementation using Doubly-linked list.
 * a scheduling policy that was proposed as a process scheduler for kernels
 *
 * @author Xing Hong
 * @since 2/1/2021
 */
public class RoundRobin {

    /* constants */
    private static final String TASK_HANDLED = "All tasks are already handled.";
    private static final int DEFAULT_QUANTUM = 4;

    /* instance variables */
    private DoublyLinkedList<Task> waitlist, finished;
    private int quantum, burstTime, waitTime, size;

    public RoundRobin(Task[] toHandle) {
        this(DEFAULT_QUANTUM, toHandle);
    }

    public RoundRobin(int quantum, Task[] toHandle) {
        if (quantum < 1 || toHandle == null || toHandle.length == 0) {
            throw new IllegalArgumentException();
        }

        waitlist = new DoublyLinkedList<Task>();
        finished = new DoublyLinkedList<Task>();
        this.quantum = quantum;
        waitTime = 0;
        burstTime = 0;

        for (int i = 0; i < toHandle.length; i++) { // iterate and save into waitList
            waitlist.add(toHandle[i]);
        }

        this.size = waitlist.size();                // save the size of list
    }

    /**
     * the fundamental method of this class that does most of the job.
     * It goes through the tasks in the waitlist, schedules them in order
     * for one quantum period and then returns it to the queue or marks it
     * completed as necessary. It keeps track of the burst and wait times.
     * It loops through the waitlist until no more tasks need to be scheduled.
     *
     * @return the format return of handle
     * @throws IndexOutOfBoundsException
     */
    public String handleAllTasks() {
        if (this.size == 0) {
            return TASK_HANDLED;
        }

        String out = "";
        while (this.size > 0) {                 // run until the tasks are all finished
            for (int i = 0; i < waitlist.size(); i++) {            // iterate through waitList
                Task currentTask = waitlist.get(i);

                if (currentTask.isFinished()) {                  // skip task if finished
                    continue;
                }

                for (int j = 0; j < quantum; j++) {            // run a quantum time
                    if (!currentTask.isFinished()) {          // if not finished
                        currentTask.handleTask();            // handle task for a quantum
                        this.burstTime++;                   // record burst
                        this.waitTime += (this.size - 1);  // record wait time
                    }
                }

                if (currentTask.isFinished()) {         // if finished after a quantum
                    finished.add(currentTask);         // add into finished
                    this.size--;                      // record the number of undone tasks
                }
            }
        }


        for (int i = 0; i < finished.size() - 1; i++) {
            out += finished.get(i).toString() + " -> ";
        }
        out += finished.get(finished.size() - 1);

        return "All tasks are handled within " + burstTime +
                " units of burst time and " + waitTime +
                " units of wait time. The tasks are finished " +
                "in this order:\n" +
                out;
    }

    /**
     * Main method for testing.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Task[] test1 = {new Task("A", 3), new Task("B", 4),
                new Task("C", 4), new Task("D", 12),
                new Task("E", 6), new Task("F", 3)};
        RoundRobin rr1 = new RoundRobin(3, test1);     // Quantum: 3, ToHandle: test1
        System.out.println(rr1.handleAllTasks());   // Burst: 32, Wait: 86, Order: AFBCED
        System.out.println();
        System.out.println(rr1.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test2 = {new Task("A", 9), new Task("B", 8),
                new Task("C", 6), new Task("D", 4),
                new Task("E", 4), new Task("F", 3)};
        RoundRobin rr2 = new RoundRobin(test2);  // Quantum: 4, ToHandle: test2
        System.out.println(rr2.handleAllTasks());   // Burst: 34, Wait: 123, Order: DEFBCA
        System.out.println();
        System.out.println(rr2.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test3 = {new Task("A", 7), new Task("B", 5),
                new Task("C", 3), new Task("D", 1),
                new Task("E", 2), new Task("F", 4),
                new Task("G", 6), new Task("H", 8)};
        RoundRobin rr3 = new RoundRobin(3, test3);     // Quantum: 3, ToHandle: test3
        System.out.println(rr3.handleAllTasks());   // Burst: 36, Wait: 148, Order: CDEBFGAH
        System.out.println();
        System.out.println(rr3.handleAllTasks());   // TASK_HANDLED
        System.out.println();
    }
}