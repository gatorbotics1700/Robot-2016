package org.usfirst.frc.team1700.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.*;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class VisionTracking extends Subsystem {

		/**
		 * static method to load opencv and networkTables
		 */
		static{
			System.out.println(System.getProperty("java.library.path"));
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		}
//		constants for the color rbg values
		public static final Scalar
		RED = new Scalar(0, 0, 255),
		BLUE = new Scalar(255, 0, 0),
		GREEN = new Scalar(0, 255, 0),
		BLACK = new Scalar(0,0,0),
		YELLOW = new Scalar(0, 255, 255),
		
	//For RGB filter
//		these are the threshold values in order 
		//LOWER_BOUNDS = new Scalar(0,0,0),
		//UPPER_BOUNDS = new Scalar(180,100,100);
		//HSV bounds
		LOWER_BOUNDS = new Scalar(60, 50, 177.265),
		UPPER_BOUNDS = new Scalar(105, 255, 255);
		
//		the size for resting the image
		public static final Size resize = new Size(320,240);
		
		// window size to output image at processing stages
		public static final int CV_WINDOW_AUTOSIZE = 50;
		
//		ignore these
		public static VideoCapture videoCapture;
		public static Mat matOriginal, matHSV, matThresh, clusters, matHeirarchy;
		
		public static JFrame HSVImageFrame;
		public static JFrame contourImageFrame;
		public static JFrame thresholdImageFrame;
		
		public static JPanel HSVImagePanel;
		public static JPanel contourImagePanel;
		public static JPanel thresholdImagePanel;
		
		public static ImageIcon HSVImageIcon;
		public static ImageIcon contourImageIcon;
		public static ImageIcon thresholdImageIcon; 
		
		public static JLabel HSVImageLabel;
		public static JLabel contourImageLabel;
		public static JLabel thresholdImageLabel;
		
//		Constants for known variables
//		the height to the top of the target in first stronghold is 97 inches	
		public static final int TOP_TARGET_HEIGHT = 97;
//		the physical height of the camera lens
		public static final int TOP_CAMERA_HEIGHT = 13;
		
//		camera details, can usually be found on the datasheets of the camera
	//  FOV is the "Field of View" of the camera
		public static final double VERTICAL_FOV  = 50.25;
		public static final double HORIZONTAL_FOV  = 67;
	//Angle above horizontal at which camera is mounted
		public static final double CAMERA_ANGLE = 0;
		
		public static final double ROBOT_OFFSET_TO_FRONT = 13;
		public static boolean shouldRun = true;

		/**
		 * 
		 * @param args command line arguments
		 * just the main loop for the program and the entry points
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			/* INFO:
			 * Mat() creates a Matrix - 2D data storage
			 * QUESTION: But Java has its own way to create matrices, why use Mat()?
			 * ANSWER: OpenCV is meant to be generic and language independent.
			 *	So every language just has to define how it converts its data to fit into
			 *	a predefined OpenCV style matrix. That way, very few of OpenCV's default code
			 *	or libraries have to be rewritten based on the programming language used.
			 */
			matOriginal = new Mat();
			matHSV = new Mat();
			matThresh = new Mat();
			clusters = new Mat();
			matHeirarchy = new Mat();
			HSVImageFrame = new JFrame();
			contourImageFrame = new JFrame();
			thresholdImageFrame = new JFrame();
			
			HSVImageFrame.setSize(320, 240);
			contourImageFrame.setSize(320, 240);
			thresholdImageFrame.setSize(320, 240);
			
			HSVImageFrame.setVisible(true);
			contourImageFrame.setVisible(true);
			thresholdImageFrame.setVisible(true);
			
			HSVImagePanel = new JPanel();
			contourImagePanel = new JPanel();
			thresholdImagePanel = new JPanel();
			
			HSVImageIcon = new ImageIcon();
			contourImageIcon = new ImageIcon();
			thresholdImageIcon = new ImageIcon();
			
			HSVImageLabel = new JLabel(HSVImageIcon);
			thresholdImageLabel = new JLabel(thresholdImageIcon);
			contourImageLabel = new JLabel(contourImageIcon);
			
			HSVImageFrame.setContentPane(HSVImageLabel);
			thresholdImageFrame.setContentPane(thresholdImageLabel);
			contourImageFrame.setContentPane(contourImageLabel);
					
			
			/* INFO:
			 * Network tables are FIRST's method of network communication.
			 * Essentially, every computer on the network allocates an amount of memory for
			 * a number of variables. Each machine can modify its local copy of the table
			 * Every now and then, a computer will broadcast its updated table and all other
			 * computers will update their copies of the table to the new copy.
			 * QUESTION: What happens if two computers update the same variable to different values?
			 * 			Which copy wins?
			 * ANSWER: 
			 * 		Possibilities: One copy wins, the other copy of data is thrown away
			 * 		Some computers get one copy, others get another. Who knows what issues this will cause
			 * 		-THESE ARE ALL BAD THINGS. We just avoid this. (Technical term: Race Conditions)
			 * 		How to avoid: 
			 * 			-Only let computers modify certain variables. 
			 * 			-Have computers request access to modify. ("Talking stick")
			 * 			-Define one computer's copy as the master table.
			 * 			-Keep extra copies of everyone's stuff
			 */
			
			NetworkTable.setClientMode();
			NetworkTable.setTeam(1700);
			NetworkTable.setIPAddress("10.17.0.21");
//			NetworkTable.initialize();
			NetworkTable table = NetworkTable.getTable("TowerTrack");
//			while(!table.isConnected()){}
//			main loop of the program
			
			/* INFO: This is essentially while(true) -> terrible programming practice, loop never ends */
			int x = 0;
			while(true){
				/* 
				 * INFO: Usually if an error occurs, your program crashes. try-catch statements is Java's method
				 * of "error handling" - if an error occurs, just tell the user what happened and move along
				 */
				try {
				
//					opens up the camera stream and tries to load it
					System.out.println("Opening video feed");
					videoCapture = new VideoCapture();
//				Mat mat = Imgcodecs.imread("http://192.168.1.22/image.jpg");
//					Imgcodecs.imwrite("dlinktest.jpg", mat);
//					replaces the ##.## with your team number
					videoCapture.open("http://10.17.0.11/mjpg/video.mjpg");
//					Example
//					videoCapture.open("http://192.168.1.22/image.jpg");
//					wait until it is opened
					while(!videoCapture.isOpened()){System.out.println("stuck in a loop");}
//					time to actually process the acquired images
					System.out.println("Processing Image");
					processImage(table);
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
//			make sure the java process quits when the loop finishes
			//videoCapture.release();
			//System.exit(0);
		}
		
		
		/**
		 * 
		 * reads an image from a live image capture and outputs information to the SmartDashboard or a file
		 * @param table the network table you want to output to
		 */
		public static void processImage(NetworkTable table){
			ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
			double x,y,targetX,targetY,distance,azimuth;
//			frame counter
			int FrameCount = 0;

			long before = System.currentTimeMillis();
//			only run for the specified time
			
			while(FrameCount <5){
				contours.clear();
//				capture from the axis camera
				/* INFO: matOriginal now contains a 2D matrix where each element represents a pixel 
				 * and consists of 3 integers (RGB)
				 */
				System.out.println("Pushing data to MatOriginal");
				videoCapture.read(matOriginal);
				
//				captures from a static file for testing
//				matOriginal = Imgcodecs.imread("someFile.png");
				
				/* INFO: call some default OpenCV libraries, google "opencv (function name)"
				 * for more detailed information on what each does
				 * Example images here: http://imgur.com/a/qOOyu 
				   Example Image 1: original image*/
				   
				/* INFO: Convert original image into HSV (Hue, Saturation, Value) Space
					The wikipedia article on HSV or searching HSV vs. RGB tells you more about this
					Example Image 2: HSV Space */
				System.out.println("HSV Filter");
				Imgproc.cvtColor(matOriginal,matHSV,Imgproc.COLOR_BGR2HSV);		
				//displayImage(Mat2BufferedImage(matThresh), HSVImageFrame, HSVImagePanel);
				//displayImage(Mat2BufferedImage(matHSV), HSVImageFrame);
				HSVImageLabel.setIcon(new ImageIcon(Mat2BufferedImage(matHSV)));
				
				/* INFO: Apply bounded threshold limits to our HSV image. Each pixel is represented
					by three values, if all of those values fall into the required range, assign 1, otherwise
					assign 0. This gives us a binary (drawn as black/white image)
					Example Image 3: Thresholded Image */

				System.out.println("Thresholding");
				Core.inRange(matHSV, LOWER_BOUNDS, UPPER_BOUNDS, matThresh);
				thresholdImageLabel.setIcon(new ImageIcon(Mat2BufferedImage(matThresh)));
				//displayImage(Mat2BufferedImage(matThresh), thresholdImageFrame, thresholdImagePanel);
				/* INFO: Search for "contours" which are continuous curves in the image */

				System.out.println("Find Contours");
				Imgproc.findContours(matThresh, contours, matHeirarchy, Imgproc.RETR_EXTERNAL, 
				Imgproc.CHAIN_APPROX_SIMPLE);
				
//				make sure the contours that are detected are at least 15x15
//				and an aspect ration greater then 1
				
				
				for (Iterator<MatOfPoint> iterator = contours.iterator(); iterator.hasNext();) {
					MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
					Rect rec = Imgproc.boundingRect(matOfPoint);
						if(rec.height < 20 || rec.width < 20){
							iterator.remove();
						continue;
						}
						float aspect = (float)rec.width/(float)rec.height;
						if(aspect < 1.0)
							iterator.remove();
//						System.out.println( rec.height*rec.width);
					}
				
				for(MatOfPoint mop : contours){
					Rect rec = Imgproc.boundingRect(mop);
					Imgproc.rectangle(matOriginal, rec.br(), rec.tl(), RED);
				}
				contourImageLabel.setIcon(new ImageIcon(Mat2BufferedImage(matOriginal)));
				
				System.out.println(contours.size());
//				table.putNumber("contours", contours.size());
				// ??? INFO: I don't know what flushing the table does
//				table.flush();
//				if there is only 1 target, then we have found the target we want
				if(contours.size() == 1){
					/* INFO: the math here is not complex, but more easily illustrated that commented
						* See (pending) pdf document for the derivation of this
						* Essentially, we will bound our contour with a rectangle and the center of the rectangle
						* This center is an (x, y) coordinate where x is the number of pixels from the left of the image
						* and y is the number of pixels from the top of the image
						* Then, we map this pixel representation such that [0, pixelWidth] -> [-1, 1] for x 
						* and [0, pixelHeight] -> [-1, 1] for y.
						* In this new space:
						* Exact center of the image is (0,0)
						* Top left  is (-1, 1)
						* Top right is (1, 1)
						* Bottom left is (-1, -1)
						* Bottom right is (1, -1)
						* Now, the yaw (i.e. angle the robot needs to move) = mappedX*xFieldOfView/2
						* And, the altitude angle (aka pitch aka doesn't matter for us because we aren't controlled
						* the angle of the shooter) = mappedY*yFieldOfView/2
						* NOTE: The calculations of yaw/pitch maks an assumption that the change in angle is linearly 
						*   proportional to the change in number of pixels everywhere in the image. This is NOT TRUE 
						*   in general, but is good enough (small angle approximation) near the center of the image.
						*/
					Rect rec = Imgproc.boundingRect(contours.get(0));
					/* "fun math" is an absolutely shitty comment and demonstrates a piss-poor effort into documentation */
//					"fun" math brought to you by miss daisy (team 341)!
					/* Pixel space y */
					y = rec.br().y + rec.height / 2;
					/* Mapping to new y, they should have renamed the variable to avoid confusion */
					y= -((2 * (y / matOriginal.height())) - 1);
					/* Calculate altitude angle */
					distance = (TOP_TARGET_HEIGHT - TOP_CAMERA_HEIGHT) / 
							Math.tan((y * VERTICAL_FOV / 2.0 + CAMERA_ANGLE) * Math.PI / 180);
//					angle to target...would not rely on this
					/* Pixel space x */
					targetX = rec.tl().x + rec.width / 2;
					/* Remapping to new x, they actually did rename the variable here */
					targetX = (2 * (targetX / matOriginal.width())) - 1;
					/* Calculating azimuth, aka yaw, angle */
					azimuth = (targetX*HORIZONTAL_FOV /2.0 + 0);
//					drawing info on target
					Point center = new Point(rec.br().x-rec.width / 2 - 15,rec.br().y - rec.height / 2);
					Point centerw = new Point(rec.br().x-rec.width / 2 - 15,rec.br().y - rec.height / 2 - 20);
//					table.putNumber("distance", distance-ROBOT_OFFSET_TO_FRONT);
					System.out.println("azimuth: " + azimuth);
					table.putDouble("azimuth", azimuth);

					System.out.println("Horizontal Angular Displacement");
					System.out.println(azimuth);
//					Imgproc.putText(matOriginal, ""+(int)distance, center, Core.FONT_HERSHEY_PLAIN, 1, BLACK);
//					Imgproc.putText(matOriginal, ""+(int)azimuth, centerw, Core.FONT_HERSHEY_PLAIN, 1, BLACK);
				}
				/* INFO: Example Image 4: Plotting output data onto original image */
//				output an image for debugging
				Imgcodecs.imwrite("output.png", matOriginal);
//				FrameCount++;
			}
//			shouldRun = false;
		}
		
		public static BufferedImage Mat2BufferedImage(Mat m) {
			int type = BufferedImage.TYPE_BYTE_GRAY;
			if (m.channels() > 1) {
				type = BufferedImage.TYPE_3BYTE_BGR;
			}
			int bufferSize = m.channels()*m.cols()*m.rows();
			byte[] b = new byte[bufferSize];
			m.get(0, 0,b);
			BufferedImage image = new BufferedImage(320,240,type);
			final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			System.arraycopy(b, 0, targetPixels, 0, b.length);
			return image;
		}
		
		public static void displayImage(Image img2, JFrame frame, JPanel panel) {
			ImageIcon icon = new ImageIcon(img2);
			frame.setLayout(new FlowLayout());
			//frame.setSize(img2.getWidth(null)+50, img2.getHeight(null) + 50);
			panel.add(new JLabel(icon));
			frame.setContentPane(panel);
			//frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		public static void processImage(){
			ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
			double x,y,targetX,targetY,distance,azimuth;
//			frame counter
			int FrameCount = 0;

			long before = System.currentTimeMillis();
//			only run for the specified time
			
			while(FrameCount <5){
				contours.clear();
//				capture from the axis camera
				/* INFO: matOriginal now contains a 2D matrix where each element represents a pixel 
				 * and consists of 3 integers (RGB)
				 */
				System.out.println("Pushing data to MatOriginal");
				videoCapture.read(matOriginal);
				
//				captures from a static file for testing
//				matOriginal = Imgcodecs.imread("someFile.png");
				
				/* INFO: call some default OpenCV libraries, google "opencv (function name)"
				 * for more detailed information on what each does
				 * Example images here: http://imgur.com/a/qOOyu 
				   Example Image 1: original image*/
				   
				/* INFO: Convert original image into HSV (Hue, Saturation, Value) Space
					The wikipedia article on HSV or searching HSV vs. RGB tells you more about this
					Example Image 2: HSV Space */
				System.out.println("HSV Filter");
				Imgproc.cvtColor(matOriginal,matHSV,Imgproc.COLOR_BGR2HSV);		
				//displayImage(Mat2BufferedImage(matThresh), HSVImageFrame, HSVImagePanel);
				//displayImage(Mat2BufferedImage(matHSV), HSVImageFrame);
				HSVImageLabel.setIcon(new ImageIcon(Mat2BufferedImage(matHSV)));
				
				/* INFO: Apply bounded threshold limits to our HSV image. Each pixel is represented
					by three values, if all of those values fall into the required range, assign 1, otherwise
					assign 0. This gives us a binary (drawn as black/white image)
					Example Image 3: Thresholded Image */

				System.out.println("Thresholding");
				Core.inRange(matHSV, LOWER_BOUNDS, UPPER_BOUNDS, matThresh);
				thresholdImageLabel.setIcon(new ImageIcon(Mat2BufferedImage(matThresh)));
				//displayImage(Mat2BufferedImage(matThresh), thresholdImageFrame, thresholdImagePanel);
				/* INFO: Search for "contours" which are continuous curves in the image */

				System.out.println("Find Contours");
				Imgproc.findContours(matThresh, contours, matHeirarchy, Imgproc.RETR_EXTERNAL, 
				Imgproc.CHAIN_APPROX_SIMPLE);
				
//				make sure the contours that are detected are at least 15x15
//				and an aspect ration greater then 1
				
				
				for (Iterator<MatOfPoint> iterator = contours.iterator(); iterator.hasNext();) {
					MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
					Rect rec = Imgproc.boundingRect(matOfPoint);
						if(rec.height < 20 || rec.width < 20){
							iterator.remove();
						continue;
						}
						float aspect = (float)rec.width/(float)rec.height;
						if(aspect < 1.0)
							iterator.remove();
//						System.out.println( rec.height*rec.width);
					}
				
				for(MatOfPoint mop : contours){
					Rect rec = Imgproc.boundingRect(mop);
					Imgproc.rectangle(matOriginal, rec.br(), rec.tl(), RED);
				}
				contourImageLabel.setIcon(new ImageIcon(Mat2BufferedImage(matOriginal)));
				
				System.out.println(contours.size());
//				table.putNumber("contours", contours.size());
				// ??? INFO: I don't know what flushing the table does
//				table.flush();
//				if there is only 1 target, then we have found the target we want
				if(contours.size() == 1){
					/* INFO: the math here is not complex, but more easily illustrated that commented
						* See (pending) pdf document for the derivation of this
						* Essentially, we will bound our contour with a rectangle and the center of the rectangle
						* This center is an (x, y) coordinate where x is the number of pixels from the left of the image
						* and y is the number of pixels from the top of the image
						* Then, we map this pixel representation such that [0, pixelWidth] -> [-1, 1] for x 
						* and [0, pixelHeight] -> [-1, 1] for y.
						* In this new space:
						* Exact center of the image is (0,0)
						* Top left  is (-1, 1)
						* Top right is (1, 1)
						* Bottom left is (-1, -1)
						* Bottom right is (1, -1)
						* Now, the yaw (i.e. angle the robot needs to move) = mappedX*xFieldOfView/2
						* And, the altitude angle (aka pitch aka doesn't matter for us because we aren't controlled
						* the angle of the shooter) = mappedY*yFieldOfView/2
						* NOTE: The calculations of yaw/pitch maks an assumption that the change in angle is linearly 
						*   proportional to the change in number of pixels everywhere in the image. This is NOT TRUE 
						*   in general, but is good enough (small angle approximation) near the center of the image.
						*/
					Rect rec = Imgproc.boundingRect(contours.get(0));
					/* "fun math" is an absolutely shitty comment and demonstrates a piss-poor effort into documentation */
//					"fun" math brought to you by miss daisy (team 341)!
					/* Pixel space y */
					y = rec.br().y + rec.height / 2;
					/* Mapping to new y, they should have renamed the variable to avoid confusion */
					y= -((2 * (y / matOriginal.height())) - 1);
					/* Calculate altitude angle */
					distance = (TOP_TARGET_HEIGHT - TOP_CAMERA_HEIGHT) / 
							Math.tan((y * VERTICAL_FOV / 2.0 + CAMERA_ANGLE) * Math.PI / 180);
//					angle to target...would not rely on this
					/* Pixel space x */
					targetX = rec.tl().x + rec.width / 2;
					/* Remapping to new x, they actually did rename the variable here */
					targetX = (2 * (targetX / matOriginal.width())) - 1;
					/* Calculating azimuth, aka yaw, angle */
					azimuth = (targetX*HORIZONTAL_FOV /2.0 + 0);
//					drawing info on target
					Point center = new Point(rec.br().x-rec.width / 2 - 15,rec.br().y - rec.height / 2);
					Point centerw = new Point(rec.br().x-rec.width / 2 - 15,rec.br().y - rec.height / 2 - 20);
//					table.putNumber("distance", distance-ROBOT_OFFSET_TO_FRONT);
//					System.out.println(azimuth);
//					table.putNumber("azimuth", azimuth);

					System.out.println("Horizontal Angular Displacement");
					System.out.println(azimuth);
//					Imgproc.putText(matOriginal, ""+(int)distance, center, Core.FONT_HERSHEY_PLAIN, 1, BLACK);
//					Imgproc.putText(matOriginal, ""+(int)azimuth, centerw, Core.FONT_HERSHEY_PLAIN, 1, BLACK);
				}
				/* INFO: Example Image 4: Plotting output data onto original image */
//				output an image for debugging
				Imgcodecs.imwrite("output.png", matOriginal);
//				FrameCount++;
			}
//			shouldRun = false;
		}
		/**
		 * @param angle a nonnormalized angle
		 */
		public static double normalize360(double angle){
			// Mod the angle by 360 to give a value between (0, 360]
			// Make it positive (by adding 360) if required
			return (angle < 0) ? angle % 360 + 360 : angle % 360;
		}


		@Override
		protected void initDefaultCommand() {
			// TODO Auto-generated method stub
			
		}
	}
