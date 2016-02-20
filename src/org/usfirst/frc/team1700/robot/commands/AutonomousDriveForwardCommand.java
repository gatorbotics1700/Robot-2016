package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
/*
 * Our autonomous drive command
 * A distance is passed in from the autonomous command (can be a positive or negative distance)
 * The robot drives forward until the encoder reading is greater than the auto distance passed in
 */
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
		// TODO Auto-generated method stub
		if (drive.getRightDistance() < autoDistance || drive.getLeftDistance() < autoDistance) {
			drive.driveTank(RobotMap.AUTO_SPEED, RobotMap.AUTO_SPEED);
		} else {
			end();
		}
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
