package org.usfirst.frc.team1700.robot.subsystems;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Queue;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.RobotUtils;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.commands.DeployableArmCommand;
import org.usfirst.frc.team1700.robot.commands.DriveCommand;
import org.usfirst.frc.team1700.robot.commands.ManualDeployableArmCommand;

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
	Queue<Double> integralQueue;
	static final int integralWindow = 5;
	double currentIntegral;


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
		integralQueue = new LinkedList<Double>();
		for (int i=0; i < integralWindow; i++) {
			integralQueue.add(0.0);
		}
		currentIntegral = 0;
	
	}
	
	public void enable() {
		//need to add get position and check if that really is what I assigned currentPosition to
		armTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		armTalon.enableControl();
		System.out.println("arm is " + armTalon.getEncPosition());
	}
	
	public void calibrate() {
		// deployable arm
		RETRACTED_ARM_POSITION = armTalon.getEncPosition();
		STRAIGHT_UP_POSITION = RETRACTED_ARM_POSITION + RobotMap.RETRACTED_TO_STRAIGHT_UP;
		INTAKE_ARM_POSITION = STRAIGHT_UP_POSITION + RobotMap.STRAIGHT_UP_TO_INTAKE;
		GROUND_ARM_POSITION = STRAIGHT_UP_POSITION + RobotMap.STRAIGHT_UP_TO_GROUND;
		DEFENSE_ARM_POSITION = STRAIGHT_UP_POSITION + RobotMap.STRAIGHT_UP_TO_DEFENSE;
	}
	
	
	public void PIDSituation(int desiredPositionEnum) {
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
		integralQueue.add(position-armTalon.getEncPosition());
		currentIntegral += (position-armTalon.getEncPosition() - integralQueue.remove()); 
		p = -.55*(((position - armTalon.getEncPosition())/(GROUND_ARM_POSITION-RETRACTED_ARM_POSITION)));
		i =  -.001* currentIntegral;
		if (Math.abs(i) > .1 ) {
			i = Integer.signum((int)( i*10)) * .1;
		}
		d = .8*((double) armTalon.getEncVelocity()/6000.0);
		f =.25*(Math.sin(((armTalon.getEncPosition()-STRAIGHT_UP_POSITION)/48*(2*Math.PI/360)))); // 48 ticks per degree
		System.out.println(armTalon.getEncVelocity() + "\t" + armTalon.getEncPosition() + "\t" + position + "\t" + p + "\t" + d + "\t" + f);
		armTalon.set((p+i+d+f));
	}
	
	private void gravity() {
		f = .25*(Math.sin((((armTalon.getEncPosition()-STRAIGHT_UP_POSITION))/48*(2*Math.PI/360)))); // 48 ticks per degree
		armTalon.set(f);
		System.out.println(STRAIGHT_UP_POSITION + "\t" + "f =" + f + "\t" + " in gravity loop");
	}

	public boolean isRetracted(){
		return RobotUtils.checkDeadband((double)RETRACTED_ARM_POSITION, (double)armTalon.getEncPosition(), shooterDeadband);
	}
	
	/* Returns boolean value is the arm is at intake level, depending on
	 * if it is in deadband range. */
//	public boolean isAtIntake() { 
//		return RobotUtils.checkDeadband((double)INTAKE_ARM_POSITION, (double)armTalon.getEncPosition(), shooterDeadband);
//	}
//	
	/* Returns boolean value if the arm is at defense level, depending on 
	 * if the limit switch is hit. */
	public boolean isAtDefense() {
		return RobotUtils.checkDeadband((double)INTAKE_ARM_POSITION, (double)armTalon.getEncPosition(), shooterDeadband);

//		return frontLimitSwitch.get();
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
	

	
	public int readEncoder() {
		return(armTalon.getEncPosition());
	}
	
	public void manualMove(double position) {
		if (!frontLimitSwitch.get() || !backLimitSwitch.get()) {
			//armTalon.set(position);
		} else {
			//armTalon.set(0);
		}
	}
	
	public void stopMotors() {
		armTalon.set(0);
	}
	
	public void zeroEncoders() {
		armTalon.setPosition(0);
	}
	


	@Override
	protected void initDefaultCommand() {
	 		//add something here when we figure out what we want
		auto();
	}
	
	public void manual() {
		setDefaultCommand(new ManualDeployableArmCommand(ManualDeployableArmCommand.STOP));
	}
	
	public void auto() {
		setDefaultCommand(new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_RETRACTED));

	}
}
