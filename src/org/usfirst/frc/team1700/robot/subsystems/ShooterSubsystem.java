package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ShooterSubsystem extends Subsystems {
	DoubleSolenoid shooterSolenoid = new DoubleSolenoid (4,5);
	CANTalon shooterTalon = new CANTalon (RobotMap.SHOOTER_TALON_ID);
	
	public ShooterSubsystem() {
		super();
	}
	
	public void setHoodPosition(boolean position) {
	
	}
	
	private void setWheelSpeed(double speed) {
//		if(!shooterTalon.isControlEnabled()) {
//			shooterTalon.enableControl();
//		}
		if (shooterTalon.getEncVelocity() < speed) {
			shooterTalon.set(1);
		} else {
			shooterTalon.set(0);
		}
	}
	
	public void setSpeedForCloseShot() {
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_CLOSE);
	}
	
	public void setSpeedForFarShot() {
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_FAR);
	}
	
	public void backdrive() {
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_BACKDRIVE);
	}
	
	public void setSpeedToZero() {
		shooterTalon.disableControl();
	}
}
