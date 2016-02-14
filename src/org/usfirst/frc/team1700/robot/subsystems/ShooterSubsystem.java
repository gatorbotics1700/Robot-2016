package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	DoubleSolenoid shooterSolenoid;
	CANTalon shooterTalonOne, shooterTalonTwo;
	public boolean hoodUp = true;
	public boolean hoodDown = false;
	public double deadband = 0.05;
	
	
	public ShooterSubsystem() {
		super();
		shooterTalonOne = new CANTalon (RobotMap.SHOOTER_TALON_ONE_ID);
		shooterTalonTwo = new CANTalon (RobotMap.SHOOTER_TALON_TWO_ID);
		shooterSolenoid = new DoubleSolenoid (4,5);
		shooterTalonOne.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		shooterTalonOne.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterTalonOne.enableControl();
		shooterTalonTwo.enableControl();
	}

	private void setWheelSpeed(double speed) {
		if (shooterTalonOne.getEncVelocity() < speed) {
			shooterTalonOne.set(1);
			shooterTalonTwo.set(1);
		} else {
			shooterTalonOne.set(0);
			shooterTalonTwo.set(0);
		}
	}
	
	public void shootClose() {
		shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_CLOSE);
	}
	
	public void shootFar() {
		shooterSolenoid.set(DoubleSolenoid.Value.kForward);
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_FAR);
	}
	
	public void backdrive() {
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_BACKDRIVE);
	}
	
	public void setSpeedToZero() {
		shooterTalonOne.disableControl();
		shooterTalonTwo.disableControl();
	}
	
	public boolean readyToShootClose() {
		if (Math.abs(shooterTalonOne.getEncVelocity() - RobotMap.SHOOTER_MOTOR_SPEED_CLOSE) < deadband) {
			if (shooterSolenoid.get()==DoubleSolenoid.Value.kReverse) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	
	public boolean readyToShootFar() {
		if (Math.abs(shooterTalonOne.getEncVelocity() - RobotMap.SHOOTER_MOTOR_SPEED_FAR) < deadband) {
			if (shooterSolenoid.get()==DoubleSolenoid.Value.kForward) { 
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	
	
	public boolean readyToShoot(){
		if (readyToShootFar() || readyToShootClose()) {
			return true;
		} else {
			return false;
		}
	
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
}
