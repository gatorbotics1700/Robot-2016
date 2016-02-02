package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

/* Initializes electronics associates with the deployable arm and 
 * provides methods to control them.
 */
public class DeployableArmSubsystem {
	DigitalInput frontLimitSwitch;
	DigitalInput backLimitSwitch;
	CANTalon armTalon;
	double shooterDeadband;
	
	/* Constructor that initializes class variables and sets up armTalon
	 * attributes. */
	public DeployableArmSubsystem() {
		backLimitSwitch = new DigitalInput (RobotMap.BACK_LIMIT_SWITCH);
		frontLimitSwitch = new DigitalInput(RobotMap.FRONT_LIMIT_SWITCH);
		shooterDeadband = 1.0;
		
		armTalon = new CANTalon(RobotMap.ARM_TALON_ID);
		armTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

	}
	
	public void enable() {
		//need to add get position and check if that really is what I assigned currentPosition to

		armTalon.changeControlMode(CANTalon.ControlMode.Position);
		armTalon.setPID(0,0,0);
		armTalon.enableControl();
	}
	
	//Disables the armTalon 
	public void disable() {
		stop();
		armTalon.disableControl();
	}
	
	/* Returns boolean value if the arm is retracted, depending on 
	 * if the limit switch is hit. */
	public boolean isRetracted(){
		return backLimitSwitch.get();
	}
	
	/* Returns boolean value is the arm is at intake level, depending on
	 * if it is in deadband range. */
	public boolean isAtIntake() { 
		if (armTalon.getEncPosition() < RobotMap.INTAKE_ARM_POSITION + shooterDeadband && 
			armTalon.getEncPosition() > RobotMap.INTAKE_ARM_POSITION - shooterDeadband) {
			return true;
		} else {
			return false;
		}
	}
	
	/* Returns boolean value if the arm is at defense level, depending on 
	 * if the limit switch is hit. */
	public boolean isAtDefense() {
		return frontLimitSwitch.get();
	}	
	
	// Stops the arm.
	public void stop() {
		armTalon.set(armTalon.getEncPosition());
	}
	
	/* While the back limit switch isn't hit, move to the retracted
	 * position. */
	public void goToRetracted() {
		if (!backLimitSwitch.get())
			armTalon.set(RobotMap.RETRACTED_ARM_POSITION);
		else stop();
	}
	
	// Move to intake.
	public void goToIntake() {
		armTalon.set(RobotMap.INTAKE_ARM_POSITION);
	}
	
	/* While the front limit switch isn't hit, move to the retracted
	 * position. */
	public void goToDefense() {
		if (!frontLimitSwitch.get())
			armTalon.set(RobotMap.DEFENSE_ARM_POSITION);
		else stop();		
	}
}
