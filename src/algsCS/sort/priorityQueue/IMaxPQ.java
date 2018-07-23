package algsCS.sort.priorityQueue;

public interface IMaxPQ<Key extends Comparable<Key>> extends IPriorityQueue<Key>{
    Key max();
    Key delMax();
}
