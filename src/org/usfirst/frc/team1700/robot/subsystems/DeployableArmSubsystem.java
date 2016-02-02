package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class DeployableArmSubsystem {
	DigitalInput frontLimitSwitch;
	DigitalInput backLimitSwitch;
	CANTalon armTalon;
	double shooterDeadband;
	
	public DeployableArmSubsystem() {
		backLimitSwitch = new DigitalInput (RobotMap.BACK_LIMIT_SWITCH);
		frontLimitSwitch = new DigitalInput(RobotMap.FRONT_LIMIT_SWITCH);
		shooterDeadband = 1.0;
		
		armTalon = new CANTalon(RobotMap.ARM_TALON_ID);
		armTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		armTalon.enableControl();
		armTalon.changeControlMode(CANTalon.ControlMode.Speed);
	}
	
	public void disable() {
		stop();
		armTalon.disableControl();
	}
	
	public void limitSwitchHit() {
		stop();
	}
	
	public boolean isRetracted(){
		if (armTalon.getEncPosition() < RobotMap.RETRACTED_ARM_POSITION + shooterDeadband && 
			armTalon.getEncPosition() > RobotMap.RETRACTED_ARM_POSITION - shooterDeadband) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAtCollectorLevel() { // same deal as the other ones
		return false;
	}
	
	public boolean isAtPortcullisLevel(){
		return false;	
	}	
	
	// stop the arm
	public void stop() {
		armTalon.set(armTalon.getEncPosition()); //need to check if this is actually the current position
	}
	
	
}
