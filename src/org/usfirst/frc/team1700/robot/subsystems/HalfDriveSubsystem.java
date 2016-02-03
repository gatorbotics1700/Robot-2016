package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;

public class HalfDriveSubsystem {
	CANTalon CanTalon;
	Victor victor1;
	Victor victor2;
	DoubleSolenoid solenoid;
	
	// Constructor initializes electronics for HalfDrive. 
	public HalfDriveSubsystem(int VictorOneID, int VictorTwoID,int CanTalonID, int SolenoidLeftID, int SolenoidRightID){
		CanTalon = new CANTalon(CanTalonID);
		CanTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		victor1 = new Victor(VictorOneID);
		victor2 = new Victor(VictorTwoID);
		solenoid = new DoubleSolenoid(SolenoidLeftID, SolenoidRightID);
	}
	
	// Sets motor speed for three motors on gear box for given side of robot. 
	public void setSpeed(double speed){
		CanTalon.set(speed);
		victor1.set(speed);
		victor2.set(speed);
	}
	
	public int getEncReading(){
		return CanTalon.getEncPosition();	
	}	
	
	// If the motors are fast enough, shift up a gear. 
	public void shiftHighHalfDrive() {
		if (CanTalon.getEncVelocity() > RobotMap.RPM)
			solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	// If the motors are slow enough, shift down a gear. 
	public void shiftLowHalfDrive() {
		if (CanTalon.getEncVelocity() < RobotMap.RPM)
			solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void zeroEncoderHalfDrive() {
		CanTalon.setPosition(0);
	}
}

