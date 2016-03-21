package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;

/**
 *
 */
public class LowGoal extends Command {

    private IntakeSubsystem intake;
    private OI oi;
    
	public LowGoal() {
		super();
		this.oi = Robot.oi;
		requires(Subsystems.intake);
        intake = Subsystems.intake;

    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	intake.fastBackdrive();
    }
    

	protected boolean isFinished() {
		return false;
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
