#include <iostream>
#include "teacher.h"
#include "opencv2/opencv.hpp"

using namespace std;
using namespace cv;

int main(int argc, char** argv){
	// if (argc != 4){
	// 	cout << "usage: ChamDiem <img1> <img2> <minHessian>" << endl;
	// 	return -1;
	// }

	Mat img_object = imread(argv[1], IMREAD_GRAYSCALE);

	teacher a;	
	a.setMinHessian(atoi(argv[2]));		//500
	a.setSampleSheet(img_object);		//phieu tra loi mau

	// Mat img_scene = imread(argv[2], IMREAD_GRAYSCALE);

	// if (!img_object.data || !img_scene.data)
	// {
	// 	cout << "error reading image!" << endl;
	// 	return -1;
	// }

	VideoCapture cap("http://192.168.43.1:8080/video");
	if (!cap.isOpened()) {
		cout << "capture failed!" << endl;
		return -1;
	}

	Mat frame;
	while(1){
		cap >> frame;
		if (frame.data)
			imshow("frame", frame);
			a.setAnswerImage(frame);			//anh chup phieu tra loi cua hoc sinh
			a.chamDiem();						//bat dau cham diem
		if (waitKey(1) == 27) break;
	}
}