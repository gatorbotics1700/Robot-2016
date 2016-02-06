package org.usfirst.frc.team1700.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team1700.robot.OI;


import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SPI;

public class DriveSubsystem extends Subsystem {
	
	private HalfDriveSubsystem left;
	private HalfDriveSubsystem right;
	private static final double JOY_DEADBAND = 0.05;
	private OI oi;
	private AHRS navX;

	/** actual driving stuff happens now */
	public DriveSubsystem() {	
		
		navX = new AHRS(SPI.Port.kMXP); 
		left = new HalfDriveSubsystem(RobotMap.LEFT_VICTOR_ID_1, RobotMap.LEFT_VICTOR_ID_2, RobotMap.LEFT_TALON_ID, RobotMap.LEFT_DRIVE_SOLENOID_ONE_PORT, RobotMap.LEFT_DRIVE_SOLENOID_TWO_PORT);
		right = new HalfDriveSubsystem(RobotMap.RIGHT_VICTOR_ID_1, RobotMap.RIGHT_VICTOR_ID_2, RobotMap.RIGHT_TALON_ID, RobotMap.RIGHT_DRIVE_SOLENOID_ONE_PORT, RobotMap.RIGHT_DRIVE_SOLENOID_TWO_PORT);
	}
	
	public void driveTank (double speedLeft, double speedRight) { // tank drive
		if(speedLeft > JOY_DEADBAND || speedLeft < -JOY_DEADBAND){ 
				left.setSpeed(speedLeft);	
		} else {
				left.setSpeed(0);
		}
		if(speedRight > JOY_DEADBAND || speedRight < -JOY_DEADBAND) { 
			right.setSpeed(-speedRight);	
		} else {
			right.setSpeed(0);
		}
	}		

	public void navX (){
		System.out.println(navX.getAngle());
	}

	public void DriveTank (double speedLeft, double speedRight) { // tank drive
			if(speedLeft > JOY_DEADBAND || speedLeft < -JOY_DEADBAND) { // maybe take out the deadband later in life
					left.setSpeed(speedLeft);
			} else {
					left.setSpeed(0);
			}
			if(speedRight > JOY_DEADBAND || speedRight < -JOY_DEADBAND){ 
				right.setSpeed(-speedRight);	
			} else {
				right.setSpeed(0);
			}
			
			navX();
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
		if (right.getEncReading() < distance)
			driveTank (speed, speed); // this is for autonomous
		else driveTank (0,0);
	}
	
	public double getLeftDistance() {
		return left.getEncReading();
	}
	
	public double getRightDistance() {
		return right.getEncReading();
	}
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand()); // drive command is always active
    }
	
	public void stop() {
		left.setSpeed(0);
		right.setSpeed(0);
	}
}
