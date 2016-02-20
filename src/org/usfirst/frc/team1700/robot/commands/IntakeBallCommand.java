package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

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
    	System.out.println("In the intake command");
        if (intake.beamBreakBackBroken()) {
        	System.out.println("Back broken");
        	if (intake.beamBreakFrontBroken()) {   // got it!!
        		System.out.println("Front also broken");
        		intake.stopMotors();    
   	
        	} else {                // went too far  
        		System.out.println("Front not also broken");
        		intake.backDrive();            
        	}        
   
        } else {
        	System.out.println("Back and front not broken");
        	intake.intake();   // maybe change this if we want different speeds for the full input and for the bopping around   
        	}
        
        }
        
    
    	//intake.intake(); // we don't have the beambreak sensors up and running, so alas we can't do this cool ass code
    //}
    	//	if (!intake.beamBreakFrontBroken() && !intake.beamBreakBackBroken()) {
//		intake.intake();
//	} else if (intake.beamBreakFrontBroken() && !intake.beamBreakBackBroken()) {
//		intake.intake();
//	} else if (intake.beamBreakFrontBroken() && intake.beamBreakBackBroken()) {
//		intake.stopMotors();
//	} else if (!intake.beamBreakFrontBroken() && intake.beamBreakBackBroken()) {
//		intake.backDrive();
//	}
	

    

    // Make this return true when this Command no longer needs to run execute()
//    protected void isFinished() {
////    	return intake.beamBreakBroken();
//    }

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
