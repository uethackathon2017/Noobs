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
	Point tableSize; 
	Ptr<SURF> detector;
	vector<KeyPoint> keypoints_object;
	Mat descriptor_object;
public:
	student result;

	void setAnswerImage(Mat answerImage = Mat()){
		if (!answerImage.data) return;
		cvtColor(answerImage, this->answerImage, CV_BGRA2GRAY);
	}

	void setKeyImage(Mat keyImage = Mat()){
		if (!keyImage.data) return;
		cvtColor(keyImage, this->keyImage, CV_BGRA2GRAY);
	}

	void setSampleSheet(Mat sampleSheet = Mat()){
		if (!sampleSheet.data) return;
		cvtColor(sampleSheet, this->sampleSheet, CV_BGRA2GRAY);
		detector->detect(sampleSheet, keypoints_object);
		detector->compute(sampleSheet, keypoints_object, descriptor_object);
	}

	void setMinHessian(int minHessian){
		this->minHessian = minHessian;
	}

	teacher(Mat sampleSheet = Mat(), Mat answerImage = Mat(), Mat keyImage = Mat(), int minHessian = 500){
		//todo: add code grayscale for everything & train input sample

		detector = SURF::create(minHessian);
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