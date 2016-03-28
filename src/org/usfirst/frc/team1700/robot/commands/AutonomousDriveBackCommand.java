package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
/*
 * Our autonomous drive command
 * A distance is passed in from the autonomous command (can be a positive or negative distance)
 * The robot drives forward until the encoder reading is greater than the auto distance passed in
 */
public class AutonomousDriveBackCommand extends Command {
	DriveSubsystem drive;
	DeployableArmSubsystem arm;
	double autoDistance;
	
	public AutonomousDriveBackCommand (double autoDistance) {
		requires(Subsystems.drive);
	    drive = Subsystems.drive;
		this.autoDistance = autoDistance;
	}
	
	
	protected void initialize() {
		System.out.println("in the back auto");
		drive.ShiftLow();
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		System.out.println("in the execute");
		drive.ShiftLow();
		drive.driveTank(RobotMap.AUTO_SPEED, RobotMap.AUTO_SPEED);


		
	} 

	@Override
	protected boolean isFinished() {
		if (Math.abs(drive.getRightDistance()) > Math.abs(autoDistance) || Math.abs(drive.getLeftDistance()) > Math.abs(autoDistance)) {
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
		drive.driveTank(0,0);
		
	}	
}