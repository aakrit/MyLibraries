import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;



/*
The Purpose of this class is to:

1. Read words (delimited with spaces) from a file (passed in as an argument or hardcoded)
and create a directed (possibly cyclic) graph in memory where each directed edge represents not only the words that follow a word, but also
how often they follow one another, as read from the File. (more about the function is explained below).

2. To visit the directed graph built, choose a random vertex and start to build a sentence of a specified length by visiting
words followed by other words based on the proabability of the occurrence of that word (more about the function is explained below).

This class maintains, in memory, a HashMap of all the words reads as Strings (HashMap Key) and their corresponding Vertex object (HashMap Value)
*/
public class DirectedWordGraph
{
    private static BufferedReader bf;
    private static String filename;
    private static HashMap<String, Vertex> words = new HashMap<String, Vertex>();

    /*
    PURPOSE: To read a file and build a graph where each vertex represents a unique word read.
            More information about the Vertex object is provided in the vertex class below
    INPUT: Name of the file from where to read the words. Note full path should be specifed
    OUTPUT: A directed (possibly cyclic) graph where the verticies are the words and the edges
            represent all the words following a given word (Vertex) and how often (the edge weight if you will)
    */
    public static void buildDirectedWordGraph(String file){
        filename = file;
        try {
            bf = new BufferedReader(new FileReader(new File(filename)));           //try to open a reader to the file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try{
            while(bf.ready()){
                String nextLine = bf.readLine();                                     //read a line with words
                String[] nextWords = nextLine.split(" ");                           //split the words by spaces

                for(int i = 0; i < nextWords.length; i++){
                    if(words.containsKey(nextWords[i])){                             //if word is already a vertex
                        if(i < nextWords.length - 1)                                //and is followed by another word
                            words.get(nextWords[i]).addNextWord(nextWords[i+1]);    //add link to followed word
                        //System.out.println("Adding1: "+nextWords[i]+" -> "+nextWords[i+1]);
                    }
                    else{                                                           //create the word and add it
                        Vertex newWord = new Vertex(nextWords[i]);                  //create a new word vertex
                        words.put(nextWords[i], newWord);                           //add the vertex to the hashMap of Verticies
                        //System.out.println("Adding: "+nextWords[i]);
                        if(i < nextWords.length - 1){                               //if there is a next word then add it, hence not a last word
                            newWord.addNextWord(nextWords[i+1]);
                            //System.out.println("Adding2: "+nextWords[i]+" -> "+nextWords[i+1]);
                        }

                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    PURPOSE: To pick a random starting vertex
    INPUT: lengths of the sentence to create
    OUTPUT: Print to Standard out all the words visited by the graph
    */
    public static void visitGraphByOccurenceProabability(int sentenceLen){
        int i = 0;
        Object[] allWords = words.keySet().toArray();                           //create an object array frm the words collection hashMap
        if(allWords.length == 0) return;                                        //check if any words in the graph exists
        Object startVertex = allWords[new Random().nextInt(allWords.length)];   //get a random starting vertex
        String startWord = (String) startVertex;                                //get the String word
        Vertex currentVertex = words.get(startWord);                            //get the starting Vertex object
        System.out.print(currentVertex.getValue());
        while(i < sentenceLen-1){                                               //iterate through all the vertices until sentence length asked is reached
            String word = currentVertex.getNextWord();                          //get the next word to build the sentence based on proabibility of following
            if(word == null) break;                                             //if we come to a word not followed by any word, then exit loop
            System.out.print(" ");
            System.out.print(word);
            currentVertex = words.get(word);//get the next word                 //update the current vertex
            i++;
        }
    }
    /*
    PURPOSE: To print each vertex and the total edges following it. I enjoyed working on this project and hence added an extra function
    for extended functionality of the application
    OUTPUT: Print each vertiex and the total edges it has (ie. total words following it)
    */
    public static void countEdges(){
        Iterator i = words.entrySet().iterator();
        while(i.hasNext()){
            java.util.Map.Entry vertex = (java.util.Map.Entry) i.next();
            String key = (String) vertex.getKey();
            Vertex value = (Vertex) vertex.getValue();
            System.out.println("Word: "+key+" has "+value.totalEdges+" edge(s)");

        }
    }

    /*
    PURPOSE: This is the main function which begin the application
    */
    public static void main(String[] args){

        String filename = "/Users/aakritprasad/IdeaProjects/BayesianClassifier/wordfile";          //hardcode the filepath
        if(args.length == 1){
            filename = args[0];                                             //pass the filename as 1st argument
        }
        DirectedWordGraph.buildDirectedWordGraph(filename);                                         //call the learn function by passing a filename & path
        DirectedWordGraph.countEdges();
        DirectedWordGraph.visitGraphByOccurenceProabability(5);                                               //call the mumble function to build a sentence from the directed graph

    }
}


/*
The Purpose of this class is to create a Vertex, which represents a word (as its value type String) and has a
HashTable (HashMap) of all the words in the readfile which follow this word (HashMap Key) as well as how often
they follow this word (HashMap Value). This class also has the ability to return a word following it based on
the proabability of its occurance
*/
class Vertex{

    private String value;                                               //store the value of the word itself as String
    private HashMap<String, Integer> nextWord;                          //store connecting words and occurrence count
    public int totalEdges = 0;

    //Simple Vertex class constructor that takes a String (the word) as its value
    Vertex(String nodeValue){
        value = nodeValue;                                              //set the vertex node value to the word value string
        nextWord = new HashMap<String, Integer>();                      //initialize the hashmap object
    }
    public String getValue(){
        return value;
    }
    public HashMap<String, Integer> getNextWordList(){
        return nextWord;
    }
    public void addNextWord(String word){
        totalEdges++;
        if(nextWord.containsKey(word))                  //if this word has previously followed
            nextWord.put(word, nextWord.get(word)+1);   //then increment its occurrence count
        else                                            //otherwise add it and set its occurrence to 1
            nextWord.put(word, 1);
    }

    /*
    PURPOSE: To get a word following the current word based on probability of occurence.
            If no words follow this word, then return null, else call another function (below)
    OUTPUT: a next word as String
    */
    public String getNextWord(){
        return (nextWord.isEmpty()) ? null : getNextWordByProbability();
    }

    /*
    PURPOSE: To get a word following the current word based on probability of occurence.
            If no words follow this word, then return null, else call another function (below)
    OUTPUT: the next word as String but based on the proabability of occurrence of that word as represented by the edge weights connecting the words
    */
    private String getNextWordByProbability(){
        //need to convert the hashset to an array
        int randomWordIndex = new Random().nextInt(totalEdges);                        //get a random number between 0 and totalEdges count
        int proabSum = 0;                                                              //set a proabSum calculation to 0 to use to determine which word to return
        Iterator i = nextWord.entrySet().iterator();                                   //get an iterator to a randomly chosen word from the list of following words
        String retVal = null;
        while(proabSum <= randomWordIndex){                                            //visits all edges until total edges visited is greater than randomWordIndex
            java.util.Map.Entry word = (java.util.Map.Entry) i.next();
            retVal = (String) word.getKey();                                           //store the value of the current iterating word
            proabSum += (Integer) word.getValue();                                     //update the value of the number of edges visited so far

        }
        return retVal;                                                                 //return the latest word whose edges we were visiting
    }

}


