package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.RobotUtils;
import org.usfirst.frc.team1700.robot.Subsystems;
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
	//ShooterSubsystem shooter;
	
	/* Constructor that initializes class variables and sets up armTalon
	 * attributes. */
	public DeployableArmSubsystem() {
		backLimitSwitch = new DigitalInput (RobotMap.BACK_LIMIT_SWITCH);
		frontLimitSwitch = new DigitalInput(RobotMap.FRONT_LIMIT_SWITCH);
		shooterDeadband = 1.0;
		//shooter = Subsystems.shooter;
		armTalon = new CANTalon(RobotMap.ARM_TALON_ID_ONE);
		armTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	}
	
	public void enable() {
		//need to add get position and check if that really is what I assigned currentPosition to
		armTalon.changeControlMode(CANTalon.TalonControlMode.Position);
		armTalon.setPID(0,0.0,0);
		armTalon.enableControl();
		System.out.println("arm is " + armTalon.getEncPosition());
	}
	
	public void PIDSituation(double position) {
		p = .2*((position - armTalon.getEncPosition()/(RobotMap.GROUD_ARM_POSITION)));
		i = 0;
		d = .2*(-armTalon.getEncVelocity()/21000);
		f = .3*(-Math.sin(((armTalon.getEncPosition()-RobotMap.STRAIGHT_UP_POSITION)/48)));
		armTalon.setPID(p, i, d, f, 0, 6 ,0);
	}
	
	public void gravity() {
		armTalon.set(.3*-Math.sin(((armTalon.getEncPosition()-RobotMap.STRAIGHT_UP_POSITION)/48)));
	}
	/* Returns boolean value if the arm is retracted, depending on 
	 * if the limit switch is hit. */
	public boolean isRetracted(){
		return backLimitSwitch.get();
	}
	
	/* Returns boolean value is the arm is at intake level, depending on
	 * if it is in deadband range. */
	public boolean isAtIntake() { 
		return RobotUtils.checkDeadband((double)RobotMap.INTAKE_ARM_POSITION, (double)armTalon.getEncPosition(), shooterDeadband);
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
		armTalon.set(RobotMap.MANUAL_ARM_SPEED);
	}
	
	//manual control for moving arm down
	public void moveDown() {
		armTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		System.out.println("The arm enc value is:" + armTalon.getEncPosition());
		armTalon.set(-RobotMap.MANUAL_ARM_SPEED);
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
		if (!backLimitSwitch.get())
			armTalon.set(RobotMap.RETRACTED_ARM_POSITION);
		else stop();
	}
	
	// Move to intake.
	// Need to add PID?
	public void goToIntake() {
		armTalon.enableControl();
		armTalon.set(RobotMap.INTAKE_ARM_POSITION);
	}
	
	/* While the front limit switch isn't hit, move to the retracted
	 * position. */
	public void goToDefense() {
		armTalon.enableControl();
		if (!frontLimitSwitch.get())
			armTalon.set(RobotMap.DEFENSE_ARM_POSITION);
		else stop();		
	}
	
	public void manualMove(double position) {
		if (!frontLimitSwitch.get() || !backLimitSwitch.get()) {
			armTalon.set(position);
		} else {
			armTalon.set(0);
		}
	}
	
	public void zeroEncoders() {
		armTalon.setPosition(0);
	}
	

	@Override
	protected void initDefaultCommand() {
	 	setDefaultCommand(new ManualDeployableArmCommand());		
	}
}
