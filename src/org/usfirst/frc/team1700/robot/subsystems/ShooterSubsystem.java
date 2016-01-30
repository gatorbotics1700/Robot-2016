package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class ShooterSubsystem extends Subsystems {

	private CANTalon shooterTalon = new CANTalon(RobotMap.SHOOTER_TALON_ID);
	private Compressor compressor;
	private Solenoid solenoid;
	
	public void setHoodPosition(boolean position) {
		
	}
	
	private void setWheelSpeed(double speed) {
		if(!shooterTalon.isControlEnabled()) {
			shooterTalon.enableControl();
		}
		shooterTalon.set(speed);
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