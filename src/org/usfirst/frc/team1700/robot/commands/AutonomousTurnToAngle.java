package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousTurnToAngle extends Command {
	private double speed;
	private double targetAngle;
	DriveSubsystem drive;
	private double turnEffort;
	private double thetaInitial;
	private static final double P = 1;
	private static final double D = 0;
	private static final double MINIMUM_EFFORT = 0.05;

	
	public AutonomousTurnToAngle (double targetAngle) {
		requires(Subsystems.drive);
	    drive = Subsystems.drive;	
		this.speed = Math.abs(speed);
		this.targetAngle = targetAngle;
		turnEffort = 0;
		thetaInitial = Subsystems.robot.theta;
	}
	@Override
	protected void initialize() {
			
	}

	@Override
	protected void execute() {
    // p * how far away you are
    turnEffort = P * (targetAngle + (Subsystems.robot.theta - thetaInitial)) // negative = left
  				 + D * Subsystems.robot.omega;
    turnEffort += Math.signum(turnEffort) * MINIMUM_EFFORT;  
   	drive.driveTank(-turnEffort, turnEffort);
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(targetAngle - Subsystems.robot.theta) < RobotMap.TURNING_ANGLE_DEADBAND 
   			 && Math.abs(Subsystems.robot.omega) < RobotMap.TURNING_ANGULAR_VELOCITY_DEADBAND);
	}

	@Override
	protected void end() {
		drive.driveTank(0, 0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
