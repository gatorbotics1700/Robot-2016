package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.DeployableArmCommand;
import org.usfirst.frc.team1700.robot.commands.IntakeBallCommand;
import org.usfirst.frc.team1700.robot.commands.ManualDeployableArmCommand;
import org.usfirst.frc.team1700.robot.commands.ManualIntake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

// Initializes electronics for in-take subsystem and their associated methods.
public class IntakeSubsystem extends Subsystem {
		private Victor IntakeVictor;
		private Victor armIntakeVictor;
		private DigitalInput beamBreakFront;
		private DigitalInput beamBreakBack;
//		private boolean isUsingBeamBreak;
		private static final double INTAKE_ROLLER_SPEED = .35;
		private static final double INTAKE_BACKDRIVE_SPEED = -.25;//change this number to actual roller speed after testing
		private static final double SLOW_INTAKE_ROLLER_SPEED = .25;
		
	// Constructor that initializes electronics.
	public IntakeSubsystem() {
		IntakeVictor =  new Victor(RobotMap.FRAME_INTAKE_VICTOR);
		armIntakeVictor = new Victor(RobotMap.ARM_INTAKE_VICTOR);
		beamBreakFront = new DigitalInput(RobotMap.BEAM_BREAK_FRONT_PORT);
		beamBreakBack = new DigitalInput(RobotMap.BEAM_BREAK_BACK_PORT);
//		isUsingBeamBreak = true;
	}
		
	/* Starts the motors to intake the ball. Once the ball crosses the beam break sensors,
	 * the motors stop. It then checks if the flywheel is fast enough, and if so, spins until 
	 * it holds the ball in position. 
	 */
	
	public boolean beamBreakFrontBroken() {
		//System.out.println("Front: " + !beamBreakFront.get());
		return !beamBreakFront.get();
	}
	public boolean beamBreakBackBroken() {
		//System.out.println("Back:" + !beamBreakBack.get());
		return !beamBreakBack.get();
	}
	
	public void intake() {
		IntakeVictor.set(INTAKE_ROLLER_SPEED);
		if (RobotMap.atIntakePosition) {
			if (RobotMap.ballHeld) {
			armIntakeVictor.set(1);
			} else {
				armIntakeVictor.set(-INTAKE_ROLLER_SPEED);
			}		
		} else {
			armIntakeVictor.set(0);
		}
	}

	
	public void fastBackdrive() {
		IntakeVictor.set(-1);
	}
	
	public void manualIntake() {
		IntakeVictor.set(SLOW_INTAKE_ROLLER_SPEED);
		armIntakeVictor.set(-INTAKE_ROLLER_SPEED);
	}
	
	// Moves ball into shooter wheel. 
	public void moveBallToShootingPosition() {
		IntakeVictor.set(INTAKE_ROLLER_SPEED);
	}
	
	public void slowIntake() {
		IntakeVictor.set(SLOW_INTAKE_ROLLER_SPEED);
	}
	public void moveBallToWaitingPosition() {
		if (!beamBreakFrontBroken()) {
			backDrive();
		} else {
			stopMotors();
		}
	}

	// Sets the motor speed to 0.
	public void stopMotors() {
		IntakeVictor.set(0);
		armIntakeVictor.set(0);
	}
	
	// Sets speeds for back drive to shoot in low goal.
	public void backDrive() {
		IntakeVictor.set(INTAKE_BACKDRIVE_SPEED);
		if (RobotMap.ballHeld) {
		armIntakeVictor.set(-1);
		} else {
			armIntakeVictor.set(INTAKE_ROLLER_SPEED);
		}		
	}
	
	public void manualBackDrive() {
		IntakeVictor.set(INTAKE_BACKDRIVE_SPEED);
		armIntakeVictor.set(INTAKE_ROLLER_SPEED);
	}
	



	@Override

	protected void initDefaultCommand() {
 		//add something here when we figure out what we want
		auto();
	}

	public void manual() {
		setDefaultCommand(new ManualIntake(ManualIntake.STOP));
	}

	public void auto() {
		setDefaultCommand(new IntakeBallCommand());

	}
}

