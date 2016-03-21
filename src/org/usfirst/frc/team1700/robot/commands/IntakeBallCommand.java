package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;

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
        	System.out.println("back broken");
        	if (intake.beamBreakFrontBroken()) {
        		System.out.println("front broken");
        		intake.stopMotors();   
        	} else {        		
        		intake.backDrive();            
        	}        
   
        } else {
        	if (intake.beamBreakBackBroken()) {
        		intake.slowIntake();
        	} else {
        	intake.intake();   // maybe change this if we want different speeds for the full input and for the bopping around   
        	}
        }

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

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
