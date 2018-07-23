package algsCS.sort.collection;

public interface IQueue<K> extends IBaseCollection {
    void enqueue(K item);
    K dequeue();
}
