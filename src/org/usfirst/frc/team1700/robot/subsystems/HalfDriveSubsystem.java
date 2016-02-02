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
	
	public HalfDriveSubsystem(int VictorOneID, int VictorTwoID,int CanTalonID, int SolenoidLeftID, int SolenoidRightID){
	CanTalon = new CANTalon(CanTalonID);
	CanTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	Victor1 = new Victor(VictorOneID);
	Victor2 = new Victor(VictorTwoID);
	Solenoid = new DoubleSolenoid(SolenoidLeftID, SolenoidRightID);
	}
	
	public void SetSpeed(double speed){
		CanTalon.set(speed);
		Victor1.set(speed);
		Victor2.set(speed);
	}
	
	public int GetEncReading(){
		return CanTalon.getEncPosition();	
	}	
	
	public void ShiftHigh(){
		if (CanTalon.getEncVelocity() > RobotMap.RPM) {
		Solenoid.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	public void ShiftLow(){
		if (CanTalon.getEncVelocity() < RobotMap.RPM) {

		Solenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	public void ZeroEncoder(){
		CanTalon.setPosition(0);
	}
}

