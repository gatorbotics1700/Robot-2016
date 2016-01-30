package org.usfirst.frc.team1700.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1700.robot.Subsystems;

public class Autonomous extends Command{
	private static int DISTANCE = 20;
	private static double SPEED = .2;

	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		requires(Subsystems.drive);
		Subsystems.drive.ZeroEncoders();
		
	}

	@Override
	protected void execute() {
		Subsystems.drive.driveToDistance(DISTANCE, SPEED);
	}

	@Override
	protected boolean isFinished() { //if i wanted to have multiple auto commands id have to do something here
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
