#include <iostream>
#include <string>

using namespace std;

#ifndef STUDENT_H
#define STUDENT_H

class student{
public:
	string ID;
	int mark;
	student(string ID = "NO ID", int mark = 0){
		this->ID = ID;
		this->mark = mark;
	}
	void test(){
		cout << "student ok " << ID << " " << mark << endl;
	}
};

#endif