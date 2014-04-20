package com.aakrit;

import java.util.ArrayList;
import java.util.HashSet;

//The following class is responsible for storing a Character and all its child characters that would help it make a word
public class TrieNode
{

    private ArrayList<TrieNode> childNodes;
    private boolean endWord;
    private Character value;

    public TrieNode(Character value, boolean endWord){
        childNodes = new ArrayList<TrieNode>();
        this.value = value;
        this.endWord = endWord;
    }
    public ArrayList<TrieNode> getChildNodes() {
        return childNodes;
    }
    public void addChildNode(TrieNode t){
        //check to avoid duplicates Node entries
        for(TrieNode child : childNodes){
            if(child.getValue() == t.getValue()) return;
        }
        childNodes.add(t);
    }
    public boolean findChildNode(Character c){
        if(childNodes.isEmpty()) return false;//no further children beyond this point
        for(TrieNode t: childNodes){
            if(t.getValue() == c) return true;
        }
        return false;
    }
    public TrieNode getChildNode(Character c){
        for(TrieNode t: childNodes){
            if(t.getValue() == c) return t;
        }
        return null;
    }
    public boolean isEndWord() {
        return endWord;
    }
    public Character getValue() {
        return value;
    }
}
