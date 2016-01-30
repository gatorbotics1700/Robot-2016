package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	DoubleSolenoid shooterSolenoid = new DoubleSolenoid (4,5);
	CANTalon shooterTalon = new CANTalon (RobotMap.SHOOTER_TALON_ID);

	public ShooterSubsystem() {
		shooterTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	}
	
	
	public void setHoodPosition(boolean position) {
		// have 1 boolean for in the shooting from tower position and 1 for the extended position
		// check whether i'm in the correct position and if not, change the actuation of the piston
		// this.shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
		
	}
	public void setWheelSpeed(double speed) { // bangbang control loop
		if (shooterTalon.getEncVelocity() < speed) {
			shooterTalon.set(1);
		} else {
			shooterTalon.set(0);
		}
	
	
	}
	public void backdrive(double speed) {
		shooterTalon.set (-speed/4);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
