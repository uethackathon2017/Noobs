#include <iostream>
#include "teacher.h"
#include "opencv2/opencv.hpp"

using namespace std;
using namespace cv;

int main(int argc, char** argv){
	if (argc != 4){
		cout << "usage: ChamDiem <img1> <img2> <minHessian>" << endl;
		return -1;
	}

	Mat img_object = imread(argv[1]);
	Mat img_scene = imread(argv[2]);

	if (!img_object.data || !img_scene.data)
	{
		cout << "error reading image!" << endl;
		return -1;
	}

	teacher a; 
	a.test();			 				//khong can thiet
	a.setMinHessian(atoi(argv[3]));		//500
	a.setSampleSheet(img_object);		//phieu tra loi mau
	a.setAnswerImage(img_scene);		//anh chup phieu tra loi cua hoc sinh
	a.chamDiem();						//bat dau cham diem
	
}