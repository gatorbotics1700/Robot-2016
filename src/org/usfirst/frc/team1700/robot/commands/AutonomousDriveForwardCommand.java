package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousDriveForwardCommand extends Command {
	DriveSubsystem drive;
	double distance;
	

	public AutonomousDriveForwardCommand (double autoDistance) {
		 distance = autoDistance;
	}
	
	
	protected void initialize() {
		requires(Subsystems.drive);
	    drive = Subsystems.drive;
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		drive.DriveTank(RobotMap.AUTO_SPEED, RobotMap.AUTO_SPEED);

	}

	@Override
	protected boolean isFinished() {
		if (drive.getRightDistance() < (distance - 10) ) { // change 90 to the actual distance we want to go - 10
			return false;
		} else if (drive.getRightDistance() > (distance + 10) ) {
			return false;
		} else {
			return true;
		}
	}


	@Override
	protected void end() {
		drive.driveTank(0,0);
		
	}

	@Override
	protected void interrupted() {
		drive.DriveTank(0, 0);
		
	}
	

	
}
