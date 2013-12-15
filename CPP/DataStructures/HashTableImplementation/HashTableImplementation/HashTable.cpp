//
//  HashTable.cpp
//  HashTableImplementation
//
//  Created by Aakrit Prasad on 12/7/13.
//  Copyright (c) 2013 Aakrit Prasad. All rights reserved.
//

#include <iostream>
#include "HashTable.h"

//creating a hashtable
template<typename T, typename K>
HashTable<T,K>::HashTable(int tableSize):tableSize(tableSize){
    hashTable.resize(tableSize);
}

template<typename T, typename K>
HashTable<T,K>::~HashTable(){
    std::cout << "Destroying your HashTable" << std::endl;
}
//O(1) time for insertion of element
template<typename T, typename K>
bool HashTable<T,K>::insert(T newRecord){
    int index = newRecord.getHash(hashTable.size());
    hashTable[index].push_back(newRecord);
    return true;
}

template<typename T, typename K>
T* HashTable<T,K>::contains(K key){
    T findHashValue;
    findHashValue.setKey(key);
    
    int index = findHashValue.getHash(hashTable.size());
    for(int i=0; i<hashTable[index].size(); i++){
        if(hashTable[index][i].getKey() == key)
            return &hashTable[index][i];
    }
    
    return NULL;
}

template<typename T, typename K>
bool HashTable<T,K>::deleteValue(T* recordValue){
    if(recordValue == NULL) return false;//not proper value
    int index = recordValue->getHash(hashTable.size());//find the location in hashtable
    for(int i = 0; i < hashTable[index].size(); i++){
        if(hashTable[index][i].getKey() == recordValue->getKey()){
            //remove the name
            std::cout << "Current size " << hashTable[index].size();
            hashTable[index].erase(hashTable[index].begin()+i);
            std::cout << "Current size " << hashTable[index].size();

            return true;
        }
    }
    return false;
    
}



/*
template<typename T, typename K>
friend std::ostream& operator<<(std::ostream& out,const HashTable& right){
    
}
*/