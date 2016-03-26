
package org.usfirst.frc.team1700.robot;
import java.lang.Math;

import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team1700.robot.commands.Autonomous;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private static Subsystems subsystems; // collection of all subsystems	 
	public static OI oi;
	private DriveSubsystem drive;
	private DeployableArmSubsystem arm;
	private Autonomous autonomous;

	
    Command autonomousCommand;
    SendableChooser chooser;
    private AHRS navX;
    private BuiltInAccelerometer RRA;
    double X_initial, Y_initial, theta_initial, dispX_field, dispY_field, theta, loopTime,
    accX_robot_navX_meas, accX_robot_RRA_meas, accY_robot_navX_meas, accY_robot_RRA_meas,
    alpha_navX_meas, motorCmdLeft, motorCmdRight, RRA_weight, navX_acc_weight, accX_robot_prev,
    velX_robot_prev, accX_robot, velX_robot, dispX_robot, accY_robot_prev, velY_robot_prev, accY_robot,
    velY_robot, dispY_robot, alpha_prev, omega_prev, alpha, omega, velX_field, accX_field,
    velY_field, accY_field, wheelRPM_robot_left_enc_meas, wheelRPM_robot_right_enc_meas, 
    dispY_robot_left_enc_meas_prev, dispY_robot_right_enc_meas_prev, dispY_robot_left_enc_meas, 
    dispY_robot_right_enc_meas, slip_ratio_left, slip_ratio_right, wheel_diameter;
    

    public Robot () {
		drive = Subsystems.drive;
		arm = Subsystems.deployableArm;
		//what do I assign RRA and navX to?
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	drive.zeroEncoders();
    	autonomous = new Autonomous();
    	
        oi = new OI();
    	subsystems = new Subsystems();
    	
    	arm.enable();
    	arm.calibrate();

    	//System.out.println(e + " " + e.getStackTrace());
    	
        // instantiate the command used for the autonomous period
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
         // schedule the autonomous command (example)
        if (autonomous != null) autonomous.start();
        arm.calibrate();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
//    	arm.calibrate();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
//    Notes:
//    	dispX, velX, and accX should all be approximately 0 (except during turns/collisions)
//    	Need to check if angled surfaces causes additional errors
//    	Need to check in navX/RRA buffer sensor readings and average over time
//    	What do we need for the roborio accelerometer in robotInit
//    	Use raw data for navX and ? for RR
//
//    	To implement:
//    	Moving average filter, Long-Short Term Filter, Encoder/Motor as sanity check, AccX as sanity check
//    	TO DO items for Christine
    public void localizationFilter() {
		/*theta is angular displacement
		* omega is angular velocity
		* alpha is angular acceleration
		*/
		
		/* disp is positional displacement
		*  vel is velocity (positional derivative with respect to time)
		*  acc is acceleration (second positional derivative with respect to time)
		*/
		
		/* Global coordinate frame origin at overall center of field
		*  X is the width of the field
		*  Y is the length of the field
		*  theta is counter-clockwise rotation
		*/
		
		//Starting position of robot (choose based on autonomous starting position)
		//TO DO: Instantiate variables here (set everything to zero except dispX_field, dispY_field, theta, loopTime)
    	X_initial = 0; //for now
    	Y_initial = 0; //for now
    	theta_initial = 0; //for now
    	accX_robot = 0;
    	velX_robot = 0;
    	dispX_robot = 0;
    	accY_robot = 0;
    	velY_robot = 0;
    	dispY_robot = 0;
    	dispY_robot_left_enc_meas = 0;
    	dispY_robot_right_enc_meas = 0;
		dispX_field = X_initial;
		dispY_field = Y_initial;
		theta = theta_initial;
		loopTime = 0.02; //(20ms, 50Hz)
		slip_ratio_left = 1; //arbitrary
		slip_ratio_right = 1; //arbitrary
		wheel_diameter = 6.125;
		
		/*Inputs:
		* Left/Right Drive Encoder (position measurements)
		* Roborio Accelerometer (RRA) (robot acceleration measurements)
		* NavX Accelerometer (robot acceleration measurements)
		* NavX Gyro (robot angular acceleration measurements)
		* Left/Right Drive Commands 
		*/
		
		//TO DO:  This is where all of your sensor.get statements go - watch out for RoboRio/navX orientations
			
		//TO DO: Remember all previous sensor values by assigning to measured value before overwriting the measured value with current value
		
		/*
		* Optional - Can use just as sanity test
		*/
		wheelRPM_robot_left_enc_meas = drive.getLeftDistance(); //Left side of robot encoder measurement
		wheelRPM_robot_right_enc_meas = drive.getRightDistance(); //Right side of robot encoder measurement
		dispY_robot_left_enc_meas_prev = dispY_robot_right_enc_meas;
		dispY_robot_right_enc_meas_prev = dispY_robot_left_enc_meas;
		dispY_robot_left_enc_meas = Math.PI*wheel_diameter*wheelRPM_robot_left_enc_meas*(slip_ratio_left);
		dispY_robot_right_enc_meas = Math.PI*wheel_diameter*wheelRPM_robot_right_enc_meas*(slip_ratio_right);
		
		/* See orientation specified on Roborio/NavX sensors for X-Y conventions and convert accordingly
		*/
		
		// Use accZ to compensate if not done for you (on angled surfaces)
		accX_robot_navX_meas = navX.getRawAccelY();
		accX_robot_RRA_meas = RRA.getX();
		
		accY_robot_navX_meas = -navX.getRawAccelX();
		accY_robot_RRA_meas = RRA.getY();
		
		alpha_navX_meas = navX.getRawGyroZ();
		
		/* 
		* Optional - Can use just as sanity test
		*/
		//motorCmdLeft = ...
		//motorCmdRight = ...
		
		/*Decisions to make
		How much prior information to store -> current only storing one previous measurement for trapezoidal integration
		Which sensors to include -> only accelerometers/gyro for estimate. encoder/motorCmd as sanity check
		Weighting
		
		*/
		
		/*
		* Tuning Parameters
		*/
		RRA_weight = 0.3;
		navX_acc_weight = 0.7;
		
		/* Local coordinate frame origin on the accelerometers of the robot
		* X is to the right of the robot
		* Y is forward on the robot
		*/
		
		/*
		* Intermediate Calculations
		* Displacement is previous displacement + the average displacement * time
		*/
		accX_robot_prev = accX_robot;
		velX_robot_prev = velX_robot;
		accX_robot = (accX_robot_RRA_meas * RRA_weight + accX_robot_navX_meas * navX_acc_weight)/(RRA_weight + navX_acc_weight);
		velX_robot = velX_robot_prev + (accX_robot_prev+accX_robot)/2*loopTime;
		dispX_robot = dispX_robot + (velX_robot_prev+velX_robot)/2*loopTime;
		
		accY_robot_prev = accY_robot;
		velY_robot_prev = velY_robot;
		accY_robot = (accY_robot_RRA_meas * RRA_weight + accY_robot_navX_meas * navX_acc_weight)/(RRA_weight + navX_acc_weight);
		velY_robot = velY_robot_prev + (accY_robot_prev+accY_robot)/2*loopTime;
		dispY_robot = dispY_robot + (velY_robot_prev+velY_robot)/2*loopTime;
		
		alpha = alpha_navX_meas;
		alpha_prev = alpha;
		omega_prev = omega;
		omega = omega_prev +(alpha_navX_meas + alpha_prev)/2*loopTime;
		theta = theta + (alpha_navX_meas + alpha_prev)/2*loopTime;
		
		//Outputs: Robot and field positional derivatives (displacements, velocities, accelerations)
		
		/*
		Explanation of frame transformation
		*/
		dispX_field = dispX_robot*Math.cos(theta) - dispY_robot*Math.sin(theta);
		velX_field = velX_robot*Math.cos(theta) - velY_robot*Math.sin(theta);
		accX_field = accX_robot*Math.cos(theta) - accY_robot*Math.sin(theta);
		
		dispY_field = dispY_robot*Math.cos(theta) + dispX_robot*Math.sin(theta);
		velY_field = velY_robot*Math.cos(theta) + velX_robot*Math.sin(theta);
		accY_field = accY_robot*Math.cos(theta) + accX_robot*Math.sin(theta);
    }
}
