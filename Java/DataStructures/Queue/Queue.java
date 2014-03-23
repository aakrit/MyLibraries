/**
 * Created with IntelliJ IDEA.
 * User: axpras
 * Date: 3/21/14
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */

class  Node<T extends Number>
{
    private Node next = null;
    private T value = null;

    Node(T val){
        value = val;
    }
    Node getNext() {return next;}
    void setNext(Node next) {this.next = next;}
    T getValue() {return value; }
    void setValue(T value) {this.value = value;}
}
//single linked list
public class Queue<T extends Number>
{
    int maxSize;
    int currentSize = 0;
    Node<T> head = null;
//    Node<T> tail = null;
    public Queue(){this.maxSize = 50;} //default size}
    public Queue(int size){this.maxSize = size;}

    public boolean isEmpty(){
        if(head.getValue() == null) return true;
        return false;
    }
    public boolean push(T value){
        if(currentSize == maxSize) return false; //cannot add more nodes
        if(head == null){//current Q is empty
            head = new Node(value);

            currentSize++;
            return true;
        }
        Node<T> n = new Node(value);   //create a new node to add
        n.setNext(head);
        head = n;      //make this new node point to the current head
        currentSize++;
        return true;
    }
    public Node<T> pop(){
        //remvoe the tail element from
        if(head == null) return null;     //empty queue
        if(head.getNext() == null){       //one element in queue
            Node temp = head;
            head = null;
            currentSize--;
            return temp;
        }
        Node<T> temp = head;
        while(temp.getNext().getNext() != null){    //find the last node in the Q
            temp = temp.getNext();
        }
        Node<T> pop = temp.getNext();
        temp.setNext(null);
        currentSize--;
        return pop;
    }
    public void printNodes(){
        if(head == null) return;
        if(head.getNext() == null) System.out.println(head.getValue()+", ");
        Node<T> temp = head;
        while(temp.getNext() != null){
            System.out.print(temp.getValue()+", ");
            temp = temp.getNext();
        }
        System.out.println(temp.getValue());
    }
    private Node<T> reverseQ_Recursively(Node<T> previousNode, Node<T> currentNode){
        Node<T> nextNode = currentNode.getNext();
        currentNode.setNext(previousNode);
        return (nextNode == null) ? currentNode : reverseQ_Recursively(currentNode, nextNode);
    }

    private Node<T> reverse(Node<T> head){  //reverse all the nodes
        if(head == null) return null;
        if(head.getNext() == null) return head;
        return reverseQ_Recursively(null, head);
    }
    public void reverseQueue(){
        head = reverse(head);
    }
    public static void print(Number s){System.out.println(s);}
    public static void main(){
        //testing
        Queue q = new Queue(10);
        q.push(1);q.push(0.94);q.push(3.5);q.push(4.98);
        System.out.println("Current Q size:");
        Queue.print(q.currentSize);
        System.out.println("Current elements in the Q:");
        q.printNodes();
        q.pop();
        q.pop();
        System.out.println("Lets pop");
        System.out.println("Current Q size:");
        Queue.print(q.currentSize);
        q.printNodes();
        System.out.println("Lets add 3 new values");
        q.push(10);q.push(5.5);q.push(100);
        System.out.println("Current elements in the Q:");
        q.printNodes();
        System.out.println("Now lets revese this Q:");
        q.reverseQueue();
        System.out.println("Current elements in the Q:");
        q.printNodes();
        System.out.println("Current Q size:");
        Queue.print(q.currentSize);
    }

}
