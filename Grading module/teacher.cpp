#include "teacher.h"

void teacher::test(){
	cout << "teacher ok hahaha" << endl;
	result = student();
	result.test();
}

void teacher::chamDiem(){
	Mat answerSheet = getAnswerSheet();
	//TODO: add code cham diem vao day
}

Mat teacher::getAnswerSheet(){
	Ptr<SURF> detector = SURF::create(minHessian);

	vector<KeyPoint> keypoints_object;
	vector<KeyPoint> keypoints_scene;

	detector->detect(sampleSheet, keypoints_object);
	detector->detect(answerImage, keypoints_scene);

	//extract descriptors
	Mat descriptor_object;
	Mat descriptor_scene;

	detector->compute(sampleSheet, keypoints_object, descriptor_object);
	detector->compute(answerImage, keypoints_scene, descriptor_scene);

	//matching descriptor:
	FlannBasedMatcher matcher;
    vector<vector<DMatch> > matches;
    vector< DMatch > good_matches;

    matcher.knnMatch(descriptor_object, descriptor_scene, matches, 2);
    const float minRatio = 1.f / 1.5f;

    for (int i = 0; i < matches.size(); i++){
        const DMatch& bestMatch = matches[i][0];
        const DMatch& betterMatch = matches[i][1];

        float distanceRatio = bestMatch.distance / betterMatch.distance;
        if (distanceRatio < minRatio) 
            good_matches.push_back(bestMatch);
    }
    
    Mat img_matches;
    drawMatches(sampleSheet, keypoints_object, answerImage, keypoints_scene,
        good_matches, img_matches, Scalar::all(-1), Scalar::all(-1),
        std::vector<char>(), DrawMatchesFlags::NOT_DRAW_SINGLE_POINTS);

    //-- Localize the object
    std::vector<Point2f> obj;
    std::vector<Point2f> scene;

    for (int i = 0; i < good_matches.size(); i++)
    {
        //-- Get the keypoints from the good matches
        obj.push_back(keypoints_object[good_matches[i].queryIdx].pt);
        scene.push_back(keypoints_scene[good_matches[i].trainIdx].pt);
    }

    Mat H = findHomography(obj, scene, RANSAC);

    //-- Get the corners from the image_1 ( the object to be "detected" )
    std::vector<Point2f> obj_corners(4);
    obj_corners[0] = cvPoint(0, 0); 
    obj_corners[1] = cvPoint(sampleSheet.cols, 0);
    obj_corners[2] = cvPoint(sampleSheet.cols, sampleSheet.rows); 
    obj_corners[3] = cvPoint(0, sampleSheet.rows);
    std::vector<Point2f> scene_corners(4);

    perspectiveTransform(obj_corners, scene_corners, H);

    //-- Draw lines between the corners (the mapped object in the scene - image_2 )
    for (int i = 0; i < 4; i++)
    	line(img_matches, scene_corners[i] + Point2f(sampleSheet.cols, 0), scene_corners[(i + 1) % 4] + Point2f(sampleSheet.cols, 0), Scalar(0, 255, 0), 4);

    //-- Show detected matches
    resize(img_matches, img_matches, Size(800, 800));
    imshow("Good Matches & Object detection", img_matches);

    //-- Show result
    Mat h = findHomography(scene, obj, RANSAC);
    Mat answerSheet;
    warpPerspective(answerImage, answerSheet, h, Size(sampleSheet.cols, sampleSheet.rows * 3.2));
    imshow("Transform", answerSheet);

    waitKey(0);
    return answerSheet;
}