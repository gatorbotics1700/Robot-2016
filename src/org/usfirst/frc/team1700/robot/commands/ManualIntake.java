package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

//why do we need this command when the other command was working fine
public class ManualIntake extends Command {
	public static final int INTAKE = 1,
							BACKDRIVE = 2,
							STOP = 3;
	private IntakeSubsystem intake;
	int desiredAction;


    public ManualIntake(int desiredAction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.intake);
    	this.desiredAction = desiredAction;
    	intake = Subsystems.intake;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (desiredAction == INTAKE){
    		intake.manualIntake();
    	} else if (desiredAction == BACKDRIVE) {
    		intake.manualBackDrive();
    	} else {
    		intake.holdOneBall();
    	}   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.holdOneBall();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	intake.holdOneBall();
    }
}
