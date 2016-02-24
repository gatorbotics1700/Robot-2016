package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.Robot;
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
	public double counter = 0.0;
	
	
	public ShooterSubsystem() {
		super();
		shooterTalonOne = new CANTalon (RobotMap.SHOOTER_TALON_ONE_ID);
		shooterTalonTwo = new CANTalon (RobotMap.SHOOTER_TALON_TWO_ID);
		shooterSolenoid = new DoubleSolenoid (RobotMap.SHOOTER_SOLENOID_ONE_PORT, RobotMap.SHOOTER_SOLENOID_TWO_PORT);
		shooterTalonOne.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		shooterTalonOne.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterTalonOne.enableControl();
		shooterTalonTwo.enableControl();
//		shooterTalonOne.setVoltageRampRate(6);
//		shooterTalonTwo.setVoltageRampRate(6);
	}

//talons are set opposite voltages because motors are wired opposite
	private void setWheelSpeed(double speed) {
			shooterTalonOne.set(speed);
			shooterTalonTwo.set(-speed);
			//shooterTalonOne.set(Robot.oi.operatorJoystick.getY());
			//shooterTalonTwo.set(Robot.oi.operatorJoystick.getY());
			//System.out.println(shooterTalonOne.get());
	
	}		
//		if (shooterTalonOne.getEncVelocity() < speed) {
//			shooterTalonOne.set(1);
//			shooterTalonTwo.set(-1);
//		}
//			//System.out.println("at wheel speed");
//			counter++;
//			if(counter <= 50) {
//				shooterTalonOne.set(.5);
//				shooterTalonTwo.set(-.5);
//			}
//		} else {
//			shooterTalonOne.set(0);
//			shooterTalonTwo.set(0);
//		}
//	}
	
	public void shootClose() {
//		System.out.println("The shooter enc value is: " + shooterTalonOne.getEncVelocity());
		//System.out.println("hood down");
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_CLOSE);
	}
	
	public void shootFar() {
		shooterSolenoid.set(DoubleSolenoid.Value.kForward);
		//System.out.println("hood up");
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_FAR);
	}
	
	public void backdrive() {
		setWheelSpeed(RobotMap.SHOOTER_MOTOR_SPEED_BACKDRIVE);
	}
	
	public void setSpeedToZero() {
		shooterTalonOne.set(0);
		shooterTalonTwo.set(0);
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
	
	public void encoder() {
		//System.out.println(shooterTalonOne.getPosition());
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
}
