package org.usfirst.frc.team1700.robot.subsystems;
import org.usfirst.frc.team1700.robot.OI;


import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	
	private HalfDriveSubsystem Left;
	private HalfDriveSubsystem Right;
	private static final double JOY_DEADBAND = 0.05;
	private OI oi;

	/** actual driving stuff happens now */
	
	public DriveSubsystem() {		
		Left = new HalfDriveSubsystem(RobotMap.LEFT_VICTOR_ID_1, RobotMap.LEFT_VICTOR_ID_2, RobotMap.LEFT_TALON_ID, RobotMap.LEFT_DRIVE_SOLENOID_ONE_PORT, RobotMap.LEFT_DRIVE_SOLENOID_TWO_PORT);
		Right = new HalfDriveSubsystem(RobotMap.RIGHT_VICTOR_ID_1, RobotMap.RIGHT_VICTOR_ID_2, RobotMap.RIGHT_TALON_ID, RobotMap.RIGHT_DRIVE_SOLENOID_ONE_PORT, RobotMap.RIGHT_DRIVE_SOLENOID_TWO_PORT);
	}
	
	public void DriveTank (double speedLeft, double speedRight) { // tank drive
			if(speedLeft > JOY_DEADBAND || speedLeft < -JOY_DEADBAND){ 
					Left.SetSpeed(speedLeft);	
			} else {
					Left.SetSpeed(0);
			}
			if(speedRight > JOY_DEADBAND || speedRight < -JOY_DEADBAND){ 
				Right.SetSpeed(-speedRight);	
			} else {
				Right.SetSpeed(0);
			}
		}		
	
	public void DriveCheesy (double throttle, double turnRate){ // cheesy drive!
		double leftOutput, rightOutput, angularPower, linearPower;
		linearPower = throttle;
		angularPower = Math.abs(throttle) * turnRate;
		
		leftOutput = rightOutput = linearPower;
		leftOutput += angularPower;
		rightOutput -= angularPower;
		
		DriveTank (leftOutput, rightOutput);
		
 	}

//	public void EnableAutoShifting (boolean on) {
//			boolean AUTOSHIFTING = true;
//	}

	public void ShiftHigh () { // shift into high gear
		Left.ShiftHigh();
		Right.ShiftHigh();
	}
	
	public void ShiftLow(){ // shift into low gear, call the shiftlow fxn from the half drive subsystem for ease of use
		Left.ShiftLow();
		Right.ShiftLow();

	}
	
	public void ZeroEncoders (){
		Left.ZeroEncoder();
		Right.ZeroEncoder();

	}
	
	public void driveToDistance(int distance, double speed){
		if (Right.GetEncReading() < distance) {
			DriveTank (speed, speed); // this is for autonomous
		} else {
			DriveTank (0,0);
		}
	}
	
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand()); // drive command is always active
    }
	
	
}
