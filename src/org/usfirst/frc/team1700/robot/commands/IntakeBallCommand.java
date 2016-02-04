package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeBallCommand extends Command {

    private IntakeSubsystem intake;
	
	public IntakeBallCommand() {
        requires(Subsystems.intake);
        intake = Subsystems.intake;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.startMotors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//nothing to do here, just automatically generated method
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return intake.beamBreakBroken();
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	intake.stopMotors();
    }
}
