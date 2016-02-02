package org.usfirst.frc.team1700.robot.subsystems;
import org.usfirst.frc.team1700.robot.OI;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	
	private HalfDriveSubsystem Left;
	private HalfDriveSubsystem Right;
//	private List<CANTalon> rightTalons;
//	private List<CANTalon> leftTalons;
	private static final double JOY_DEADBAND = 0.05;
	private OI oi;
//	private static boolean AUTOSHIFTING = false;
//	private static boolean SHIFTED_HIGH = false;
//	DoubleSolenoid leftSolenoid = new DoubleSolenoid (0,1);
//	DoubleSolenoid rightSolenoid = new DoubleSolenoid (2,3);
	
	
	/** actual driving stuff happens now */
	
	public DriveSubsystem() {
		
		
		Left = new HalfDriveSubsystem(RobotMap.LEFT_VICTOR_ID_1, RobotMap.LEFT_VICTOR_ID_2, RobotMap.LEFT_TALON_ID, RobotMap.LEFT_DRIVE_SOLENOID_ONE_PORT, RobotMap.LEFT_DRIVE_SOLENOID_TWO_PORT);
		Right = new HalfDriveSubsystem(RobotMap.RIGHT_VICTOR_ID_1, RobotMap.RIGHT_VICTOR_ID_2, RobotMap.RIGHT_TALON_ID, RobotMap.RIGHT_DRIVE_SOLENOID_ONE_PORT, RobotMap.RIGHT_DRIVE_SOLENOID_TWO_PORT);

//		this.rightTalons = new ArrayList<CANTalon>();
//		this.leftTalons = new ArrayList<CANTalon>();
//		this.rightTalons.add(new CANTalon(RobotMap.RIGHT_TALON_ID_1));
//		this.rightTalons.get(0).setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		this.rightTalons.add(new CANTalon(RobotMap.RIGHT_TALON_ID_2));
//		this.rightTalons.add(new CANTalon(RobotMap.RIGHT_TALON_ID_3));
//		this.leftTalons.add(new CANTalon(RobotMap.LEFT_TALON_ID_1));
//		this.leftTalons.get(0).setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		this.leftTalons.add(new CANTalon(RobotMap.LEFT_TALON_ID_2));
//		this.leftTalons.add(new CANTalon(RobotMap.LEFT_TALON_ID_3));
		


	}
	
	public void DriveTank (double speedLeft, double speedRight) {
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
		
		
	
	public void DriveCheesy (double throttle, double turnRate){
		double leftOutput, rightOutput, angularPower, linearPower;
		linearPower = throttle;
		angularPower = Math.abs(throttle) * turnRate;
		
		leftOutput = rightOutput = linearPower;
		leftOutput += angularPower;
		rightOutput -= angularPower;
		
		DriveTank (leftOutput, rightOutput);
		
 	}
//	
//	public void EnableAutoShifting (boolean on) {
//			boolean AUTOSHIFTING = true;
//	}
//	
//	private void IsLowGear () {
//		// if we're in low gear
//			boolean SHIFTED_HIGH = false;
//		// if high gear
//			// boolean SHIFTED_HIGH = true;
//	}
	
	public void ShiftHigh () {
		Left.ShiftHigh();
		Right.ShiftHigh();
	}
	
	public void ShiftLow(){
		Left.ShiftLow();
		Right.ShiftLow();

	}
// 
//	public void ShiftLow (int RPM) {
		//change solenoid setting
		
		//if (rightTalons.get(0).getEncVelocity() < RobotMap.RPM) {
			//figure out whether we're in low or high gear

		//}
//	}
//	
	
	public void ZeroEncoders (){
		Left.ZeroEncoder();
		Right.ZeroEncoder();

	}
	
	public void driveToDistance(int distance, double speed){
		if (Right.GetEncReading() < distance) {
			DriveTank (speed, speed);
		} else {
			DriveTank (0,0);
		}
	}
	
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand()); // drive command is always active
    }
	
	
}
