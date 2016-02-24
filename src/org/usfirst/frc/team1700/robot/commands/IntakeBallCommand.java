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

	public static final int BEAMBREAK = 1,
							 OVERRIDE = 2;
    private IntakeSubsystem intake;
    private ShooterSubsystem shooter;
    private OI oi;
    private int counter;
    private int mode;
    
	public IntakeBallCommand(int mode) {
		super();
		this.oi = Robot.oi;
		requires(Subsystems.intake);
		requires(Subsystems.shooter);
        intake = Subsystems.intake;
        shooter = Subsystems.shooter;
        counter = 0;
        this.mode = mode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	counter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    if (mode == BEAMBREAK){
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
    } else {
    	intake.intake();
    }
    }
	

    

    // Make this return true when this Command no longer needs to run execute()
//    protected void isFinished() {
////    	return intake.beamBreakBroken();
//    }
    

    // Called once after isFinished returns true
    protected void end() {
    if (mode == BEAMBREAK) {
    	intake.stopMotors();
    } else {
    	if (counter <= 5) {
    		intake.stopMotors();
    		counter ++;
    	} else if (counter > 5 && counter < 20) {
    		intake.backDrive();
    		shooter.backdrive();
    		counter ++;
    	} else {
    		intake.stopMotors();
    	}
    }
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
