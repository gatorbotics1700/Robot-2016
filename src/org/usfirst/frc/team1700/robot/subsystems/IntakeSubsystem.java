package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

// Initializes electronics for in-take subsystem and their associated methods.
public class IntakeSubsystem {
		Victor firstIntakeVictor;
		Victor secondIntakeVictor;
		DigitalInput beamBreak;
		
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
	public void startMotors(double speed) {
		firstIntakeVictor.set(speed);
			if (beamBreak.get()) {
				stopMotors();
			}
			// if flywheel is spinning fast enough (see boolean in command probably)
			// secondIntakeVictor.set(speed);
		
	}
	
	// Sets the motor speed to 0.
	public void stopMotors() {
		firstIntakeVictor.set(0);
	}
	
	// Sets speeds for back drive to shoot in low goal.
	public void backDrive(double speed) {
		secondIntakeVictor.set(-speed/4);
		firstIntakeVictor.set(-speed/4);
	}	
}

