package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousDriveForwardCommand extends Command {
	DriveSubsystem drive;
	double autoDistance;
	

	public AutonomousDriveForwardCommand (double autoDistance) {
		requires(Subsystems.drive);
	    drive = Subsystems.drive;
		this.autoDistance = autoDistance;
	}
	
	
	protected void initialize() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		//TODO FOR CHRISTINE (I WILL DO THIS TOMORROW): Read encoder value to get to distance 	  
		// TODO Auto-generated method stub
		drive.driveTank(RobotMap.AUTO_SPEED, RobotMap.AUTO_SPEED);

	} 

	@Override
	protected boolean isFinished() {
//		if (drive.getRightDistance() < (autoDistance - 10) ) { // change 90 to the actual distance we want to go - 10
//			return false;
//		} else if (drive.getRightDistance() > (autoDistance + 10) ) {
//			return false;
//		} else {
//			return true;
//		}
		return false;
	}


	@Override
	protected void end() {
		drive.driveTank(0,0);
		
	}

	@Override
	protected void interrupted() {
		drive.driveTank(0,0);
		
	}
	

	
}
