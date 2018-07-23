package algsCS.sort.priorityQueue;

public interface IMinPQ<Key extends Comparable<Key>> extends IPriorityQueue<Key> {
    Key min();
    Key delMin();
}
