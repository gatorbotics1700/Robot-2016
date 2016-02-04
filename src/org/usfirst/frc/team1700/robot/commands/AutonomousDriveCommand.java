package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotUtils;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;


public class AutonomousDriveCommand extends Command {

    private static final double AUTO_DISTANCE = 45.0; //change after testing
    private static final double AUTO_SPEED = 0.3; //change after testing
    private DriveSubsystem drive;
	
	public AutonomousDriveCommand() {
        requires(Subsystems.drive);
        drive = Subsystems.drive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.driveToDistance(AUTO_DISTANCE, AUTO_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return RobotUtils.checkDeadband(AUTO_DISTANCE, drive.getLeftDistance(), 1.0); //change this deadband later
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
