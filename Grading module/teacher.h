#include <iostream>
#include "opencv2/opencv.hpp"
#include "opencv2/xfeatures2d.hpp"
#include "student.h"

using namespace std;
using namespace cv;
using namespace cv::xfeatures2d;

#ifndef TEACHER_H
#define TEACHER_H

class teacher{
private:
	Mat sampleSheet;
	Mat answerImage;
	Mat keyImage;
	Mat getAnswerSheet();
	int minHessian;
	Point tableSize; //this teacher's table size is limited =))
public:
	student result;

	void setAnswerImage(Mat answerImage = Mat()){
		if (!answerImage.data) return;
		resize(answerImage, this->answerImage, tableSize);
	}

	void setKeyImage(Mat keyImage = Mat()){
		if (!keyImage.data) return;
		resize(keyImage, this->keyImage, tableSize);
	}

	void setSampleSheet(Mat sampleSheet = Mat()){
		if (!sampleSheet.data) return;
		resize(sampleSheet, this->sampleSheet, tableSize);
	}

	void setMinHessian(int minHessian){
		this->minHessian = minHessian;
	}

	teacher(Mat sampleSheet = Mat(), Mat answerImage = Mat(), Mat keyImage = Mat(), int minHessian = 500){
		//todo: add code grayscale for everything & train input sample
		tableSize = Point(600, 600);
		setAnswerImage(answerImage);
		setKeyImage(keyImage);
		setSampleSheet(sampleSheet);
		setMinHessian(minHessian);
	}

	void test();
	void chamDiem();
};

#endif