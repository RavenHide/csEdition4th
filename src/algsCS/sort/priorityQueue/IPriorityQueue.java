package algsCS.sort.priorityQueue;

public interface IPriorityQueue<Key extends Comparable<Key>> {


    void insert(Key v);

    boolean isEmpty();

    int size();

}
