package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class DeployableArmSubsystem {
	DigitalInput frontLimitSwitch = new DigitalInput(RobotMap.FRONT_LIMIT_SWITCH);
	DigitalInput backLimitSwitch = new DigitalInput (RobotMap.BACK_LIMIT_SWITCH);
	// DigitalInput armBeamBreakSensor = new DigitalInput (RobotMap.ARM_BEAM_BREAK_PORT);
	CANTalon armTalon = new CANTalon(RobotMap.ARM_TALON_ID);
	
	public DeployableArmSubsystem() {
		armTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	}
	
	public boolean isRetracted(double position){ // set a position for the retracted arm in robot map
		if (armTalon.getEncPosition() == position) { // probably have a deadband for this bc the arm's not always gonna be in the same place
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

	
}
