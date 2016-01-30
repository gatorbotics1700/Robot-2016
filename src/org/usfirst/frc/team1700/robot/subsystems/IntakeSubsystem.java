package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class IntakeSubsystem {
		Victor firstIntakeVictor = new Victor(RobotMap.INTAKE_VICTOR_1_ID);
		Victor secondIntakeVictor = new Victor(RobotMap.INTAKE_VICTOR_2_ID);
		DigitalInput beamBreak = new DigitalInput(RobotMap.BEAM_BREAK_PORT);
		
	public void startMotors(double speed) {
		firstIntakeVictor.set(speed);
			if (beamBreak.get()) {
				stopMotors();
			}
			
			// if flywheel is spinning fast enough (see boolean in command probably)
			// secondIntakeVictor.set(speed);
		
	}
	public void stopMotors() {
		firstIntakeVictor.set(0);
	}
	public void backDrive(double speed) {
		secondIntakeVictor.set(-speed/4);
		firstIntakeVictor.set(-speed/4);
	}

	
}

