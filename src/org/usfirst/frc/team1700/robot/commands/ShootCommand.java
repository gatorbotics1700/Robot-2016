package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCommand extends Command {

	private IntakeSubsystem intake;
	private OI oi;
	 
    public ShootCommand() {
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
			intake.moveBallToShootingPosition();
	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.holdOneBall();   
    	RobotMap.linedUp = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		intake.holdOneBall();
    }
}
