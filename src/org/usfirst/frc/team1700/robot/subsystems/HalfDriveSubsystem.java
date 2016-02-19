package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;

public class HalfDriveSubsystem {
	CANTalon CanTalon1;
	CANTalon CanTalon2;
	CANTalon CanTalon3;
	
	// Constructor initializes electronics for HalfDrive. 
	public HalfDriveSubsystem(int TalonOneID, int TalonTwoID,int TalonThreeID){
		CanTalon1 = new CANTalon(TalonOneID);
		CanTalon1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		CanTalon2 = new CANTalon(TalonTwoID);
		CanTalon3 = new CANTalon(TalonThreeID);
		CanTalon1.setVoltageRampRate(6);
		CanTalon2.setVoltageRampRate(6);
		CanTalon3.setVoltageRampRate(6);
	}
	
	// Sets motor speed for three motors on gear box for given side of robot. 
	public void setSpeed(double speed){
		CanTalon1.set(speed);
		CanTalon2.set(speed);
		CanTalon3.set(speed);
	}
	
	public int getEncReading(){
		return CanTalon1.getEncPosition();	
	}	
	
	public double getEncVelocity(){
		return CanTalon1.getEncVelocity();
	}
	
	// If the motors are fast enough, shift up a gear. 
//	public void autoShiftHighHalfDrive() {
//		if (CanTalon1.getEncVelocity() > RobotMap.RPM)
//			solenoid.set(DoubleSolenoid.Value.kForward);
//	}
//	
//	// If the motors are slow enough, shift daown a gear. 
//	public void autoShiftLowHalfDrive() {
//		if (CanTalon1.getEncVelocity() < RobotMap.RPM)
//			solenoid.set(DoubleSolenoid.Value.kReverse);
//	}
	
	public void shiftHighHalfDrive() {
//		solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void shiftLowHalfDrive() {
//		solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	public void zeroEncoderHalfDrive() {
		CanTalon1.setPosition(0);
	}
}

