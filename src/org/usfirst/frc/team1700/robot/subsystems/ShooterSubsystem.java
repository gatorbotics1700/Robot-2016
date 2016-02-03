package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ShooterSubsystem extends Subsystems {
	DoubleSolenoid shooterSolenoid;
	CANTalon shooterTalon;
	public boolean hoodUp = true;
	public boolean hoodDown = false;
	
	
	public ShooterSubsystem() {
		super();
		shooterTalon = new CANTalon (RobotMap.SHOOTER_TALON_ID);
		shooterSolenoid = new DoubleSolenoid (4,5);
		shooterTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		shooterTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterTalon.enableControl();
	}

	public void setHoodPosition(boolean position) {
		if (position){
			shooterSolenoid.set(DoubleSolenoid.Value.kForward);
		}
		else shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	private void setWheelSpeed(double speed) {
		if (shooterTalon.getEncVelocity() < speed) {
			shooterTalon.set(1);
		} else {
			shooterTalon.set(0);
		}
	}
	
	public void shootClose() {
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_CLOSE);
	}
	
	public void shootFar() {
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_FAR);
	}
	
	public void backdrive() {
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_BACKDRIVE);
	}
	
	public void setSpeedToZero() {
		shooterTalon.disableControl();
	}
	
}
