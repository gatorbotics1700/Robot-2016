package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

// Initializes electronics for in-take subsystem and their associated methods.
public class IntakeSubsystem {
		private Victor firstIntakeVictor;
		private Victor secondIntakeVictor;
		private DigitalInput beamBreak;
		private static final double INTAKE_ROLLER_SPEED = 0.34567; //change this number to actual roller speed after testing
		
	// Constructor that initializes electronics.
	public IntakeSubsystem() {
		firstIntakeVictor =  new Victor(RobotMap.INTAKE_VICTOR_1_ID);
		secondIntakeVictor = new Victor(RobotMap.INTAKE_VICTOR_2_ID);
		beamBreak = new DigitalInput(RobotMap.BEAM_BREAK_PORT);
	}
		
	/* Starts the motors to intake the ball. Once the ball crosses the beam break sensors,
	 * the motors stop. It then checks if the flywheel is fast enough, and if so, spins until 
	 * it holds the ball in position. 
	 */
	public void startMotors() {
		firstIntakeVictor.set(INTAKE_ROLLER_SPEED);
		if (beamBreak.get())
			stopMotors();
	}
	
	// Moves ball into shooter wheel. 
	public void moveBallToShootingPosition() {
		secondIntakeVictor.set(INTAKE_ROLLER_SPEED);
	}
	
	public boolean beamBreakBroken() {
		return beamBreak.get();
	}

	// Sets the motor speed to 0.
	public void stopMotors() {
		firstIntakeVictor.set(0);
	}
	
	// Sets speeds for back drive to shoot in low goal.
	public void backDrive() {
		secondIntakeVictor.set(-INTAKE_ROLLER_SPEED);
		firstIntakeVictor.set(-INTAKE_ROLLER_SPEED);
	}	
}

