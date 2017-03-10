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
    vector<DMatch> matches;
    matcher.match(descriptor_object, descriptor_scene, matches);

    double max_dist = 0; double min_dist = 100;
    
    //-- Quick calculation of max and min distances between keypoints
    for (int i = 0; i < descriptor_object.rows; i++)
    {
        double dist = matches[i].distance;
        if (dist < min_dist) min_dist = dist;
        if (dist > max_dist) max_dist = dist;
    }

    printf("-- Max dist : %f \n", max_dist);
    printf("-- Min dist : %f \n", min_dist);

    //-- Draw only "good" matches (i.e. whose distance is less than 3*min_dist )
    std::vector< DMatch > good_matches;

    for (int i = 0; i < descriptor_object.rows; i++)
    {
        if (matches[i].distance <= 3 * min_dist)
        {
            good_matches.push_back(matches[i]);
        }
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
    imshow("Good Matches & Object detection", img_matches);

    //-- Show result
    Mat h = findHomography(scene, obj, RANSAC);
    Mat answerSheet;
    warpPerspective(answerImage, answerSheet, h, tableSize);
    imshow("Transform", answerSheet);

    // waitKey(0);
    return answerSheet;
}