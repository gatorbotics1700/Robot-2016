package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1700.robot.RobotMap;

/**
 *
 */
public class AutonomousShootHighGoalCommand extends Command {
	  private ShooterSubsystem shooter;
	  private IntakeSubsystem intake;

    public AutonomousShootHighGoalCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.shooter);
    	requires(Subsystems.intake);
    	shooter = Subsystems.shooter;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.shootClose();
    	if (shooter.readEncoder() > 53000) {
    		intake.moveBallToShootingPosition();
    	} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if (RobotMap.ballHeld = true) {
    	   return false;
       } else {
    	   return true;
       }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Subsystems.shooter.setSpeedToZero();
    	shooter.retractHood();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
