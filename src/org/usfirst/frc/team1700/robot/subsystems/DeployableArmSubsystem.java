package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.RobotUtils;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.commands.DriveCommand;
import org.usfirst.frc.team1700.robot.commands.ManualDeployableArmCommand;
import org.usfirst.frc.team1700.robot.commands.ManualJoystickArm;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/* Initializes electronics associates with the deployable arm and 
 * provides methods to control them.
 */
public class DeployableArmSubsystem extends Subsystem {
	DigitalInput frontLimitSwitch;
	DigitalInput backLimitSwitch;
	CANTalon armTalon;
	double shooterDeadband;
	double p;
	double i;
	double d;
	double f;
	double RETRACTED_ARM_POSITION;
	double STRAIGHT_UP_POSITION;
	double GROUND_ARM_POSITION;
	double INTAKE_ARM_POSITION, DEFENSE_ARM_POSITION;


	/* Constructor that initializes class variables and sets up armTalon
	 * attributes. */
	public DeployableArmSubsystem() {
		backLimitSwitch = new DigitalInput (RobotMap.BACK_LIMIT_SWITCH);
		frontLimitSwitch = new DigitalInput(RobotMap.FRONT_LIMIT_SWITCH);
		shooterDeadband = 1.0;
		armTalon = new CANTalon(RobotMap.ARM_TALON_ID_ONE);
		armTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		// deployable arm
				RETRACTED_ARM_POSITION = armTalon.getEncPosition();
				STRAIGHT_UP_POSITION = RETRACTED_ARM_POSITION + RobotMap.RETRACTED_TO_STRAIGHT_UP;
				INTAKE_ARM_POSITION = STRAIGHT_UP_POSITION + RobotMap.STRAIGHT_UP_TO_INTAKE;
				GROUND_ARM_POSITION = STRAIGHT_UP_POSITION + RobotMap.STRAIGHT_UP_TO_GROUND;
				DEFENSE_ARM_POSITION = STRAIGHT_UP_POSITION + RobotMap.STRAIGHT_UP_TO_DEFENSE;
	}
	
	public void enable() {
		//need to add get position and check if that really is what I assigned currentPosition to
		armTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		armTalon.enableControl();
		System.out.println("arm is " + armTalon.getEncPosition());
		
		// deployable arm
		RETRACTED_ARM_POSITION = armTalon.getEncPosition();
		STRAIGHT_UP_POSITION = RETRACTED_ARM_POSITION + RobotMap.RETRACTED_TO_STRAIGHT_UP;
		INTAKE_ARM_POSITION = STRAIGHT_UP_POSITION + RobotMap.STRAIGHT_UP_TO_INTAKE;
		GROUND_ARM_POSITION = STRAIGHT_UP_POSITION + RobotMap.STRAIGHT_UP_TO_GROUND;
		DEFENSE_ARM_POSITION = STRAIGHT_UP_POSITION + RobotMap.STRAIGHT_UP_TO_DEFENSE;
	}
	
	public void PIDSituation(int desiredPositionEnum) {
		//DESIRED_POSITION_RETRACTED = 1,
		//DESIRED_STRAIGHT_UP= 2,
		//DESIRED_POSITION_DEFENSE = 3;
		double position;
		if (desiredPositionEnum == 1){
			double DEFENSE_ARM_POSITION;
			position = RETRACTED_ARM_POSITION;
		}
		else if (desiredPositionEnum == 2){
			position = STRAIGHT_UP_POSITION;
		} else if (desiredPositionEnum == 3){
			position = INTAKE_ARM_POSITION;
		}
		else{
			position = armTalon.getEncPosition();
		}
		p = .2*((position - armTalon.getEncPosition()/(GROUND_ARM_POSITION)));
		i = 0;
		d = -.2*(armTalon.getEncVelocity()/21000);
		f = 0.25*(Math.sin(((armTalon.getEncPosition()-STRAIGHT_UP_POSITION)/48/(2*Math.PI/360)))); // 48 ticks per degree
		armTalon.set(p+i+d+f);
	}
	
	public void gravity() {
		f = .25*(Math.sin(((armTalon.getEncPosition()-STRAIGHT_UP_POSITION)/48*(2*Math.PI/360)))); // 48 ticks per degree
		System.out.println(armTalon.getEncPosition() + "\t" + STRAIGHT_UP_POSITION + "\t" + (armTalon.getEncPosition()-STRAIGHT_UP_POSITION)/48*(2*Math.PI/360) + "\t" + (Math.sin(((armTalon.getEncPosition()-STRAIGHT_UP_POSITION)/48*(2*Math.PI/360)))) + "\t" + f);
		armTalon.set(f);
	}
	/* Returns boolean value if the arm is retracted, depending on 
	 * if the limit switch is hit. */
	public boolean isRetracted(){
		return backLimitSwitch.get();
	}
	
	/* Returns boolean value is the arm is at intake level, depending on
	 * if it is in deadband range. */
	public boolean isAtIntake() { 
		return RobotUtils.checkDeadband((double)INTAKE_ARM_POSITION, (double)armTalon.getEncPosition(), shooterDeadband);
	}
	
	/* Returns boolean value if the arm is at defense level, depending on 
	 * if the limit switch is hit. */
	public boolean isAtDefense() {
		return frontLimitSwitch.get();
	}	
	
	//manual control for moving arm up
	public void moveUp() {
		armTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		System.out.println("The arm enc value is:" + armTalon.getEncPosition());
		//armTalon.set(RobotMap.MANUAL_ARM_SPEED);
	}
	
	//manual control for moving arm down
	public void moveDown() {
		armTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		System.out.println("The arm enc value is:" + armTalon.getEncPosition());
		//armTalon.set(-RobotMap.MANUAL_ARM_SPEED);
	}
	
	// Stops the arm.
	public void stop() {
		armTalon.set(armTalon.getEncPosition());
	}
	
	public int readEncoder() {
		return(armTalon.getEncPosition());
	}
	
	/* While the back limit switch isn't hit, move to the retracted
	 * position. */
	public void goToRetracted() {
		armTalon.enableControl();
//		if (!backLimitSwitch.get())
//			//armTalon.set(RobotMap.RETRACTED_ARM_POSITION);
//		else stop();
	}
	
	// Move to intake.
	// Need to add PID?
	public void goToIntake() {
		//armTalon.enableControl();
		//armTalon.set(RobotMap.INTAKE_ARM_POSITION);
	}
	
	/* While the front limit switch isn't hit, move to the retracted
	 * position. */
	public void goToDefense() {
		armTalon.enableControl();
//		if (!frontLimitSwitch.get())
//			//armTalon.set(RobotMap.DEFENSE_ARM_POSITION);
//		else stop();		
	}
	
	public void manualMove(double position) {
		if (!frontLimitSwitch.get() || !backLimitSwitch.get()) {
			//armTalon.set(position);
		} else {
			//armTalon.set(0);
		}
	}
	
	public void zeroEncoders() {
		armTalon.setPosition(0);
	}
	


	@Override
	protected void initDefaultCommand() {
	 		//add something here when we figure out what we want
		setDefaultCommand(new ManualJoystickArm());
	}
}
