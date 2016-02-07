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
    	System.out.println("im in the intake command yo");
    	
    	if (this.oi.operatorJoystick.getRawButton(RobotMap.INTAKE_BUTTON)) {
    		intake.startMotors();
    	} else if (oi.operatorJoystick.getRawButton(RobotMap.BACKDRIVE_BUTTON)) {
    		intake.backDrive();
    	} else {
    		intake.stopMotors();
    	}
    	//nothing to do here, just automatically generated method
    }

    // Make this return true when this Command no longer needs to run execute()
//    protected void isFinished() {
////    	return intake.beamBreakBroken();
//    }

    // Called once after isFinished returns true
    protected void end() {
//    	intake.stopMotors();
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
