package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;

import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1700.robot.RobotMap;

/**
 *
 */
public class AutonomousShootHighGoalCommand extends Command {
//	private Victor IntakeVictor;
	  private ShooterSubsystem shooter;
	  private IntakeSubsystem intake;

    public AutonomousShootHighGoalCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.shooter);
    	requires(Subsystems.intake);
    	shooter = Subsystems.shooter;
    	intake = Subsystems.intake;
//		IntakeVictor =  new Victor(RobotMap.FRAME_INTAKE_VICTOR);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.shootClose();
    	 if (intake.beamBreakBackBroken()) {
          	if (intake.beamBreakFrontBroken()) {
          		System.out.println("im holding");
          		RobotMap.ballHeld = true;
          	} else {        		
          		System.out.println("yes");
          		RobotMap.ballHeld = true;
          	}        
          } else {
          	if (intake.beamBreakBackBroken()) {
          		System.out.println("jk i am");

          		RobotMap.ballHeld = true;
          	} else {
          		System.out.println("i aint holding");
          		RobotMap.ballHeld = false; 
          	}
          }
    	if (Math.abs(shooter.readEncoder()) > 53000) {
    		System.out.println("in the intake loop");
    		intake.moveBallToShootingPosition();
    	} else {
    		intake.stopIntake();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if (RobotMap.ballHeld == true) {
    	   System.out.println("im not stopping");
    	   return false;
       } else {
    	   System.out.println("im stopping");
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

