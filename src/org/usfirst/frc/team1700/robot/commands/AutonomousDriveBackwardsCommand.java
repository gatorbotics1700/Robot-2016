package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.RobotUtils;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDriveBackwardsCommand extends Command {

    private DriveSubsystem drive;
	
	public AutonomousDriveBackwardsCommand() {
        requires(Subsystems.drive);
        drive = Subsystems.drive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.driveToDistance(RobotMap.AUTO_BACKDRIVE_DISTANCE, RobotMap.AUTO_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotUtils.checkDeadband(RobotMap.AUTO_BACKDRIVE_DISTANCE, drive.getLeftDistance(), 1.0); //change deadband later
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive.stop();
    }
}
