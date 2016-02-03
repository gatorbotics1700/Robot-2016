package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;

public class HalfDriveSubsystem {
	CANTalon CanTalon;
	Victor Victor1;
	Victor Victor2;
	DoubleSolenoid Solenoid;
	
	// Constructor initializes electronics for HalfDrive. 
	public HalfDriveSubsystem(int VictorOneID, int VictorTwoID,int CanTalonID, int SolenoidLeftID, int SolenoidRightID){
		CanTalon = new CANTalon(CanTalonID);
		CanTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		Victor1 = new Victor(VictorOneID);
		Victor2 = new Victor(VictorTwoID);
		Solenoid = new DoubleSolenoid(SolenoidLeftID, SolenoidRightID);
	}
	
	// Sets motor speed for three motors on gear box for given side of robot. 
	public void SetSpeed(double speed){
		CanTalon.set(speed);
		Victor1.set(speed);
		Victor2.set(speed);
	}
	
	public int GetEncReading(){
		return CanTalon.getEncPosition();	
	}	
	
	// If the motors are fast enough, shift up a gear. 
	public void shiftHighHalfDrive() {
		if (CanTalon.getEncVelocity() > RobotMap.RPM)
			Solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	// If the motors are slow enough, shift down a gear. 
	public void shiftLowHalfDrive() {
		if (CanTalon.getEncVelocity() < RobotMap.RPM)
			Solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void zeroEncoderHalfDrive() {
		CanTalon.setPosition(0);
	}
}

