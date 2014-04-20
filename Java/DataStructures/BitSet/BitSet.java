public class BitSet
{
    int[] bitset;
    public BitSet(int size){
        bitset = new int[size >> 5];
    }
    public void addToBitSet(int num){
        int index = num >> 5;
        int bitVal = num & 0x1F;
        bitset[index] |= 1 << bitVal;
    }
    public boolean checkIfExistsInBitSet(int num){
        int index = num >> 5;
        int bitVal = num & 0x1F;
        return (bitset[index] & 1 << bitVal) != 0;
    }


//    Testing
    public static void main(String[] args){

    }
}
