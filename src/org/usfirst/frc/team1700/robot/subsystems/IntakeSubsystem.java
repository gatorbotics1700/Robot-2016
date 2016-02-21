package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.IntakeBallCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

// Initializes electronics for in-take subsystem and their associated methods.
public class IntakeSubsystem extends Subsystem {
		private Victor IntakeVictor;
		private Victor armIntakeVictor;
		private DigitalInput beamBreakFront;
		private DigitalInput beamBreakBack;
		private static final double INTAKE_ROLLER_SPEED = .5; //change this number to actual roller speed after testing
		
	// Constructor that initializes electronics.
	public IntakeSubsystem() {
		IntakeVictor =  new Victor(RobotMap.INTAKE_VICTOR_2_ID);
		armIntakeVictor = new Victor(RobotMap.INTAKE_VICTOR_1_ID);
		beamBreakFront = new DigitalInput(RobotMap.BEAM_BREAK_FRONT_PORT);
		beamBreakBack = new DigitalInput(RobotMap.BEAM_BREAK_BACK_PORT);
	}
		
	/* Starts the motors to intake the ball. Once the ball crosses the beam break sensors,
	 * the motors stop. It then checks if the flywheel is fast enough, and if so, spins until 
	 * it holds the ball in position. 
	 */
	
	public boolean beamBreakFrontBroken() {
		System.out.println("Front: " + beamBreakFront.get());
		return beamBreakFront.get();
	}
	public boolean beamBreakBackBroken() {
		System.out.println("Back:" + beamBreakBack.get());
		return !beamBreakBack.get();
	}
	
	public void intake() {
		IntakeVictor.set(INTAKE_ROLLER_SPEED);
		armIntakeVictor.set(INTAKE_ROLLER_SPEED);
	}
	
	// Moves ball into shooter wheel. 
	public void moveBallToShootingPosition() {
		IntakeVictor.set(INTAKE_ROLLER_SPEED);
	}

	// Sets the motor speed to 0.
	public void stopMotors() {
		IntakeVictor.set(0);
		armIntakeVictor.set(0);
	}
	
	// Sets speeds for back drive to shoot in low goal.
	public void backDrive() {
		IntakeVictor.set(-INTAKE_ROLLER_SPEED);
		armIntakeVictor.set(-INTAKE_ROLLER_SPEED);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new IntakeBallCommand());
		
	}	
}

