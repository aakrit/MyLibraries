package com.aakrit;

import java.util.ArrayList;
import java.util.HashSet;

//The following class is responsible for storing a Character and all its child characters that would help it make a word
public class TrieNode<T extends Comparable<? super T>>
{

    private ArrayList<TrieNode<T>> childNodes;
    private boolean endWord;//if storing words
    private T value;

    public TrieNode(T value){
        childNodes = new ArrayList<TrieNode<T>>();
        this.value = value;
        this.endWord = false;
    }
    
    public TrieNode(T value, boolean endWord){
        childNodes = new ArrayList<TrieNode<T>>();
        this.value = value;
        this.endWord = endWord;
    }
    public ArrayList<TrieNode<T>> getChildNodes() {
        return childNodes;
    }
    public void addChildNode(TrieNode<T> t){
        //check to avoid duplicates Node entries
        for(TrieNode<T> child : childNodes){
            if(child.getValue() == t.getValue()) return;
        }
        childNodes.add(t);
    }
    public boolean findChildNode(T c){
        if(childNodes.isEmpty()) return false;//no further children beyond this point
        for(TrieNode<T> t: childNodes){
            if(t.getValue() == c) return true;
        }
        return false;
    }
    public TrieNode<T> getChildNode(T c){
        for(TrieNode<T> t: childNodes){
            if(t.getValue() == c) return t;
        }
        return null;
    }
    public boolean isEndWord() {
        return endWord;
    }
    public T getValue() {
        return value;
    }
}
