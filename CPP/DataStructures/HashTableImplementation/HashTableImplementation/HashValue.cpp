//
//  HashValue.cpp
//  HashTableImplementation
//
//  Created by Aakrit Prasad on 12/7/13.
//  Copyright (c) 2013 Aakrit Prasad. All rights reserved.
//

#include "HashValue.h"
#include <string>

using namespace std;

HashValue::HashValue(){
    
}
HashValue::~HashValue(){
    
}
string HashValue::getKey(){
    return this->studentNumber;
}

void HashValue::setKey(string number){
    this->studentNumber = number;
}

//get index on where to store the Value
//in the hash table
int HashValue::getHash(int tableSize){
    string key = getKey();
    int index = 0;
    for(int i = 0; i < key.length(); i++){
        index += (int) key[i];
    }
    index = index % tableSize;
    return index;
}




