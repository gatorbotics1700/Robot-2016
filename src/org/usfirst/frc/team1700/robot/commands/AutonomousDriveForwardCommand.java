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
public class AutonomousDriveForwardCommand extends Command {
	DriveSubsystem drive;
	double autoDistance;
	
	public AutonomousDriveForwardCommand (double autoDistance) {
		requires(Subsystems.drive);
	    drive = Subsystems.drive;
		this.autoDistance = autoDistance;
	}
	
	
	protected void initialize() {
		drive.zeroEncoders();
		drive.ShiftLow();
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		drive.ShiftLow();
			drive.driveTank(-RobotMap.AUTO_SPEED, -RobotMap.AUTO_SPEED);
			

		
	} 

	@Override
	protected boolean isFinished() {
		if (Math.abs(drive.getRightDistance()) < autoDistance || Math.abs(drive.getLeftDistance()) < autoDistance) {
			return false;
		} else {
			return true;
		}
	} 
	

	@Override
	protected void end() {
		System.out.println("ending the forward");
		drive.driveTank(0,0);
		
	}

	@Override
	protected void interrupted() {
		drive.driveTank(0,0);
		
	}	
}