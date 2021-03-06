package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

/**
 *
 */
public class IntakeBallCommand extends Command {

    private IntakeSubsystem intake;
    private OI oi;

    
	public IntakeBallCommand() {
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
        if (intake.beamBreakBackBroken()) {
        	if (intake.beamBreakFrontBroken()) {
        		intake.holdOneBall();  
        		RobotMap.ballHeld = true;
        	} else {        		
        		intake.backDrive();   
        		RobotMap.ballHeld = false;
        	}        
        } else {
        	if (intake.beamBreakBackBroken()) {
        		intake.slowIntake();
        		RobotMap.ballHeld = false;
        	} else {
        		intake.intake(); 
        		RobotMap.ballHeld = false; 
        	}
        }

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

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
