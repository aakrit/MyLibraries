/**
 * Date: 5/11/14
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinarySearchTree<K extends Comparable<? super K>, T extends Number>
{

    private BSTNode root;

    private class BSTNode
    {
        private K key;
        private T value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, T val){
            this.value = val;
            this.key = key;
        }

    }
    //check if BST is empty
    public boolean isEmpty(){
        return root == null ? true : false;
    }

    //get Min and Max Values
    public T getMinValue(){
        if(root == null) return null;
        BSTNode temp = root.left;
        while(temp.left != null) temp = temp.left;
        return temp.value;
    }
    public T getMaxValue(){
        if(root == null) return null;
        BSTNode temp = root.right;
        while(temp.right != null) temp = temp.right;
        return temp.value;
    }

    //add a Node to BST
    public void add(K key, T val){
        if (val == null) return;
        root = add(root, key, val);
    }
    private BSTNode add(BSTNode n, K key, T val){
        if(n == null) return new BSTNode(key, val);
        if(key.compareTo(n.key) < 0) n.left = add(n.left, key, val);
        else if(key.compareTo(n.key) > 0) n.right = add(n.right, key, val);
        else n.value = val;//this is our node
        return n;
    }


    //delete a Node from BST
//    public BSTNode deleteNode(int value){
//
//    }


    //get a Node from BST
    public T get(K key){
        return isEmpty() ? null : get(root, key);
    }

    private T get(BSTNode n, K key){
        if(root.key.equals(key)) return root.value;
        else if(root.key.compareTo(key) > 0) return get(root.left, key);
        else return get(root.right, key);
    }

    //check if a node exists
    public boolean hasElement(K key){
        return get(root, key) != null;
    }

    //Sum the values of all Nodes
    //Doing a pre-order traversal
    public float getSumOfValuesAsFloats(){
        if(isEmpty()) return (float) 0.0;
        return getSumOfValuesAsFloats(root);
    }
    private float getSumOfValuesAsFloats(BSTNode n){
        if(n == null) return (float) 0.0;
        float root = n.value.floatValue();
        float lTree = getSumOfValuesAsFloats(n.left);
        float rTree = getSumOfValuesAsFloats(n.right);
        return root + lTree + rTree;
    }

    //The number of links to travel from the smallest number to the largest number
    public int getCountOfLinksBetweenSmallestAndLargestValues(){
        int count = 0;
        if(root == null) return count;
        BSTNode left = root.left;
        BSTNode right = root.right;
        while(left != null) {
            left = left.left;
            count++;//add up the lenght to the left most node
        }
        while(right != null) {
            right = right.right;
            count++;//add up the lenght to the right most node
        }
        return count;
    }


    //The maximum distance between two nodes in the tree
    public int getMaxDistanceBetweenTwoNodes(){ //also known as the tree diameter
        if(isEmpty()) return 0;
        return getTreeDiameter(root) - 1;
    }
    private int getTreeDiameter(BSTNode n){
        if(n == null) return 0;
        int lTreeHeight = treeHeight(n.left);
        int rTreeHeight = treeHeight(n.right);
        int lTreeDiameter = getTreeDiameter(n.left);
        int rTreeDiameter = getTreeDiameter(n.right);
        return Math.max(lTreeHeight + rTreeHeight + 1, Math.max(lTreeDiameter, rTreeDiameter));
    }
    private int treeHeight(BSTNode root){
        if(root == null) return 0;
        else return 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
    }
}
