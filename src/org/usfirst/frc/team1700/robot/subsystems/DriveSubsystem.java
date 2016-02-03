package org.usfirst.frc.team1700.robot.subsystems;
import org.usfirst.frc.team1700.robot.OI;


import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	
	private HalfDriveSubsystem left;
	private HalfDriveSubsystem right;
	private static final double JOY_DEADBAND = 0.05;
	private OI oi;

	/** actual driving stuff happens now */
	
	public DriveSubsystem() {		
		left = new HalfDriveSubsystem(RobotMap.LEFT_VICTOR_ID_1, RobotMap.LEFT_VICTOR_ID_2, RobotMap.LEFT_TALON_ID, RobotMap.LEFT_DRIVE_SOLENOID_ONE_PORT, RobotMap.LEFT_DRIVE_SOLENOID_TWO_PORT);
		right = new HalfDriveSubsystem(RobotMap.RIGHT_VICTOR_ID_1, RobotMap.RIGHT_VICTOR_ID_2, RobotMap.RIGHT_TALON_ID, RobotMap.RIGHT_DRIVE_SOLENOID_ONE_PORT, RobotMap.RIGHT_DRIVE_SOLENOID_TWO_PORT);
	}
	
	public void driveTank (double speedLeft, double speedRight) { // tank drive
		if(speedLeft > JOY_DEADBAND || speedLeft < -JOY_DEADBAND){ 
				left.SetSpeed(speedLeft);	
		} else {
				left.SetSpeed(0);
		}
		if(speedRight > JOY_DEADBAND || speedRight < -JOY_DEADBAND){ 
			right.SetSpeed(-speedRight);	
		} else {
			right.SetSpeed(0);
		}
	}		
	
	public void driveCheesy (double throttle, double turnRate){ // cheesy drive!
		double leftOutput, rightOutput, angularPower, linearPower;
		// Linear power controls forward motion.
		linearPower = throttle;
		// Angular powers controls the ratio between the wheels. 
		angularPower = Math.abs(throttle) * turnRate;
		
		leftOutput = rightOutput = linearPower;
		leftOutput += angularPower;
		rightOutput -= angularPower;
		
		driveTank (leftOutput, rightOutput);		
 	}

//	public void EnableAutoShifting (boolean on) {
//			boolean AUTOSHIFTING = true;
//	}

	public void shiftHigh() { // shift into high gear
		left.shiftHighHalfDrive();
		right.shiftHighHalfDrive();
	}
	
	public void shiftLow() { // shift into low gear, call the shiftlow fxn from the half drive subsystem for ease of use
		left.shiftLowHalfDrive();
		right.shiftLowHalfDrive();

	}
	
	public void zeroEncoders() {
		left.zeroEncoderHalfDrive();
		right.zeroEncoderHalfDrive();
	}
	
	public void driveToDistance(double distance, double speed){
		if (right.GetEncReading() < distance)
			driveTank (speed, speed); // this is for autonomous
		else driveTank (0,0);
	}
	
	public double getLeftDistance() {
		return left.GetEncReading();
	}
	
	public double getRightDistance() {
		return right.GetEncReading();
	}
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand()); // drive command is always active
    }
	
	public void stop() {
		left.SetSpeed(0);
		right.SetSpeed(0);
	}
}
