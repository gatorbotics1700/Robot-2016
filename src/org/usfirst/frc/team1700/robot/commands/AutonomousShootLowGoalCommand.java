package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousShootLowGoalCommand extends Command {

    private IntakeSubsystem intake;
    private DriveSubsystem drive;
	
	public AutonomousShootLowGoalCommand() {
        requires(Subsystems.intake);
        requires(Subsystems.drive);
        intake = Subsystems.intake;
        drive = Subsystems.drive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.startMotors();
    	drive.zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
