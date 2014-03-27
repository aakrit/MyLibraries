/**
 * Created with IntelliJ IDEA.
 * User: axpras
 * Date: 3/25/14
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.HashMap;

//single machine cache
public class Cache<K extends String, V extends Comparable<? super V>>{
    public static int MAX_SIZE = Integer.MAX_VALUE - 10000;
    private int currentSize = 0; //
    private Node<V> head, tail;
    private HashMap<K, Node<V>> map;
    Cache(){map = new HashMap<>();}
    Cache(int size){
        if(size < MAX_SIZE) MAX_SIZE = size;
        map = new HashMap<>();
    }
    private void moveToFrontOfList(Node node){/*...*/}
    private void removeFromLinkedList(Node node){/*...*/}

    private V getFromCache(K key){
        if(!map.containsKey(key)) return null;
        Node<V> ret = map.get(key);
        moveToFrontOfList(ret);//move value to head of list list
        return ret.value;
    }
    public void insertQuery(K key, V value){
        V ret = getFromCache(key);//check if value is in the cache
        if(ret != null){ //value in cache, update hash and linkedList
            Node<V> node = map.get(key);//get the node
            moveToFrontOfList(node);//update to front of linkedList
            node.value = value;//update the results value
            return;
        }
        //value not in cache
        Node<V> node = new Node<V>(value);
        moveToFrontOfList(node);//update
        map.put(key,node);//update map
        if(this.currentSize > MAX_SIZE){
            map.remove(tail);//remove last node in the cache
            removeFromLinkedList(tail);
        }
    }
    class Node<V>{
        public V value;
        public Node<V> next;
        Node(V val){value = val;}
    }

    public static void main(String[] args){
        //time to test the cache
    }
}
class ValueNotFoundException extends Exception{
    ValueNotFoundException(String message){
        super(message);
    }
}
