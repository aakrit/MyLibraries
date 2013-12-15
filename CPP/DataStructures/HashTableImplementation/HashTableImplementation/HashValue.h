//
//  HashValue.h
//  HashTableImplementation
//
//  Created by Aakrit Prasad on 12/7/13.
//  Copyright (c) 2013 Aakrit Prasad. All rights reserved.
//

#ifndef __HashTableImplementation__HashValue__
#define __HashTableImplementation__HashValue__

#include <iostream>

class HashValue{
    
public:
    
    HashValue();
    std::string getKey();
    void setKey(std::string key);
    int getHash(int M);
    
    friend std::istream& operator>>(std::istream& in, HashValue& right);
    friend std::ostream& operator<<(std::ostream& out, HashValue& right);
    
    ~HashValue();
private:
    
    std::string studentName;
    std::string studentNumber;
    int classRank;
    double GPA;
    std::string major;
};

#endif /* defined(__HashTableImplementation__HashValue__) */
