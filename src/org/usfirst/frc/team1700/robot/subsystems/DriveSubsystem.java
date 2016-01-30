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
	
	private List<CANTalon> rightTalons;
	private List<CANTalon> leftTalons;
	private static final double JOY_DEADBAND = 0.05;
	private OI oi;
	private static final boolean AUTOSHIFTING = false;
	private static final boolean SHIFTED_HIGH = false;
	DoubleSolenoid leftSolenoid = new DoubleSolenoid (0,1);
	DoubleSolenoid rightSolenoid = new DoubleSolenoid (2,3);
	
	
	/** actual driving stuff happens now */
	
	public DriveSubsystem() {
		
		
		this.rightTalons = new ArrayList<CANTalon>();
		this.leftTalons = new ArrayList<CANTalon>();
		this.rightTalons.add(new CANTalon(RobotMap.RIGHT_TALON_ID_1));
		this.rightTalons.get(0).setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		this.rightTalons.add(new CANTalon(RobotMap.RIGHT_TALON_ID_2));
		this.rightTalons.add(new CANTalon(RobotMap.RIGHT_TALON_ID_3));
		this.leftTalons.add(new CANTalon(RobotMap.LEFT_TALON_ID_1));
		this.leftTalons.get(0).setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		this.leftTalons.add(new CANTalon(RobotMap.LEFT_TALON_ID_2));
		this.leftTalons.add(new CANTalon(RobotMap.LEFT_TALON_ID_3));
		


	}
	
	public void DriveTank (double speedLeft, double speedRight) {
		for (CANTalon leftTalon: this.leftTalons) {
			if(speedLeft > JOY_DEADBAND || speedLeft < -JOY_DEADBAND){ 
					leftTalon.set(speedLeft);	
			} else {
					leftTalon.set(0);
			}
		}
		for (CANTalon rightTalon: this.rightTalons){
			if(speedRight > JOY_DEADBAND || speedRight < -JOY_DEADBAND){ 
				rightTalon.set(-speedRight);	
			} else {
				rightTalon.set(0);
			}
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
	
	public void EnableAutoShifting (boolean on) {
			boolean AUTOSHIFTING = true;
	}
	
	private void IsLowGear () {
		// if we're in low gear
			boolean SHIFTED_HIGH = false;
		// if high gear
			// boolean SHIFTED_HIGH = true;
	}
	
	public void ShiftHigh () {
		this.leftSolenoid.set(DoubleSolenoid.Value.kForward);
		this.rightSolenoid.set(DoubleSolenoid.Value.kForward);

	}
	
	public void ShiftLow () {
		this.leftSolenoid.set(DoubleSolenoid.Value.kReverse);
		this.rightSolenoid.set(DoubleSolenoid.Value.kReverse);

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
		rightTalons.get(0).setPosition(0);
		leftTalons.get(0).setPosition(0);

	}
	
	public void driveToDistance(int distance, double speed){
		if (rightTalons.get(0).getEncPosition() < distance) {
			DriveTank (speed, speed);
		} else {
			DriveTank (0,0);
		}
	}
	
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand()); // drive command is always active
    }
	
	
}
