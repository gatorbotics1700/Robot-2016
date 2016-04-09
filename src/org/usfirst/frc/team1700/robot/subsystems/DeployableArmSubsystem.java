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
	double VERTICAL_OFFSET;
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
				VERTICAL_OFFSET = RETRACTED_ARM_POSITION + RobotMap.VERTICAL_OFFSET;
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
	
	
	public double PIDSituation(int desiredPositionEnum) {
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
		
			double error = position - armTalon.getEncPosition();
		integralQueue.add(error);
		currentIntegral += (position-armTalon.getEncPosition() - integralQueue.remove()); 
		p = -.43*((error)/(GROUND_ARM_POSITION-RETRACTED_ARM_POSITION)); // used to be .55
		i =  -.0002* currentIntegral; // used to be .001
		if (Math.abs(i) > .1 ) {
			i = Integer.signum((int)( i*10)) * .1;
		}
		d = 1.2*((double) armTalon.getEncVelocity()/6000.0);
		f =.25*(Math.sin(((armTalon.getEncPosition()-VERTICAL_OFFSET)/48*(2*Math.PI/360)))); // 48 ticks per degree
//		System.out.println(armTalon.getEncVelocity() + "\t" + armTalon.getEncPosition() + "\t" + position + "\t" + p + "\t" + d + "\t" + f);
//		this.readEncoder();
		this.setSpeed(p+i+d+f, position);
		return error;
	}
	
	public void gravity() {
		f = .25*(Math.sin((((armTalon.getEncPosition()-VERTICAL_OFFSET))/48*(2*Math.PI/360)))); // 48 ticks per degree
		this.setSpeed(f);
		this.getEncValue();
		//System.out.println(STRAIGHT_UP_POSITION + "\t" + "f =" + f + "\t" + " in gravity loop");
	}




	public void moveAnalog(double speed) {
		armTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		this.setSpeed(speed*speed*speed);
		this.readEncoder();
	}
	public int readEncoder() {
		return(armTalon.getEncPosition());
	}
	
	private void checkLimitSwitch(double position) {
		
	}
	
	private void setSpeed (double speed) {
//		if (fjsklfja;kj && checkLImistSwitch(position))
		
		if (!backLimitSwitch.get() && (speed > 0)) { // add front switch soon
			armTalon.set(0);
			this.calibrate();
		} else  if (!frontLimitSwitch.get() && speed < 0 ){
			armTalon.set(0);
		} else {
			armTalon.set(speed);
		}

	}
	
	private void setSpeed (double speed, double position) {
		
		if (!backLimitSwitch.get() && (speed > 0) && position == RETRACTED_ARM_POSITION) { // add front switch soon
			armTalon.set(0);
			this.calibrate();
		} else  if (!frontLimitSwitch.get() && speed < 0 ){
			armTalon.set(0);
		} else {
			armTalon.set(speed);
		}

	}
	
	public void stopMotors() {
		this.setSpeed(0);
	}

	
	public void getEncValue() {
		System.out.println(armTalon.getEncPosition());
	}

	public int getEncPos() {
		return armTalon.getEncPosition();
	}
	
	@Override
	protected void initDefaultCommand() {
	 		//add something here when we figure out what we want
		auto();
//		manual();
	}
	
	public void manual() {
		setDefaultCommand(new ManualDeployableArmCommand(ManualDeployableArmCommand.STOP));
	}
	
	public void auto() {
		setDefaultCommand(new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_RETRACTED));

	}
}
