package algsCS.basic;

import java.util.Iterator;

/**
 * 背包链表实现
 * @param <Item>
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first; //链表头

    private class Node{
        Item item;
        Node next;
    }

    public void add(Item item){
        Node oldNode = first;

        first = new Node();
        first.item = item;
        first.next = oldNode;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements   Iterator{
        private Node currnt = first;

        @Override
        public boolean hasNext() {
            return currnt!= null ;
        }

        @Override
        public Item next() {
            Item item =  currnt.item;
            currnt = currnt.next;
            return item;
        }
    }

}
