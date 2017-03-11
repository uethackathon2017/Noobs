#include "com_noobs_noobshackathon_OpenCvNativeClass.h"

JNIEXPORT void JNICALL Java_com_noobs_noobshackathon_OpenCvNativeClass_imgProcess
  (JNIEnv *, jclass, jlong matArrSrc, jlong matArrDst){
    Mat& src=*(Mat*)matArrSrc;
Mat& dst=*(Mat*)matArrSrc;
toGray(src,dst);
}
void toGray(Mat src,Mat& dst){
    cvtColor(src,dst,CV_BGR2GRAY);
}
