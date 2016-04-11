package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousTurnToAngle extends Command {
	private double speed;
	private double targetAngle;
	DriveSubsystem drive;

	
	public AutonomousTurnToAngle (double speed, double targetAngle) {
		requires(Subsystems.drive);
	    drive = Subsystems.drive;	
		this.speed = Math.abs(speed);
		this.targetAngle = targetAngle;
	}
	@Override
	protected void initialize() {
			
	}

	@Override
	protected void execute() {
		Subsystems.robot.localizationFilter();
	double delta = targetAngle - Subsystems.robot.theta*180/3.14;
	while (delta < 0) {
		delta = delta + 360;
	}
	
	while (delta > 360) {
		delta = delta - 360;
	}
	
	System.out.println("theta" + Subsystems.robot.theta*180/3.14);
	System.out.println("thetaRAD" + Subsystems.robot.theta);

	System.out.println("delta" + delta);
	if (delta <= 180) {
		drive.driveTank(speed, -speed);
	} else {
		drive.driveTank(-speed, speed);
	}
	}

	@Override
	protected boolean isFinished() {
		double delta = targetAngle - Subsystems.robot.theta*180/3.14;
		while (delta < 0) {
			delta = delta + 360;
		}
		
		while (delta > 360) {
			delta = delta - 360;
		}
		
		if (delta < 2 || delta > 358) {
		return true;
		} else {
			return false;
		}
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
