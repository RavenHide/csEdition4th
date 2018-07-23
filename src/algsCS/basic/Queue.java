package algsCS.basic;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{
    private Node first; //最早添加的元素
    private Node last; //最近添加的元素

    private int N = 0;


    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    /**
     * 进入队列
     * @param item
     */
    public void enqueue(Item item){
        if(item == null){
            throw new NullPointerException("item 不能为空");
        }
        Node oldNode = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        }else {
            oldNode.next = last;
        }
        N++;
    }

    /**
     * 出列
     * @return
     */
    public Item dequeue(){
        if(isEmpty())
            return null;

        Item item = first.item;

        first = first.next;
        if(size() == 1){
            last = null;
        }
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }


    }

}
