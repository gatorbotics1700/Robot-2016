package org.usfirst.frc.team1700.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;

/**
 *
 */
public class ManualDeployableArmCommand extends Command {

	private DeployableArmSubsystem arm;
	double armDeadband;
	
    public ManualDeployableArmCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.deployableArm);
    	arm = Subsystems.deployableArm;
    	armDeadband = 0.3;
    }

    public void getPosition() {
    	
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.operatorJoystick.getY() > armDeadband || Robot.oi.operatorJoystick.getY() < -armDeadband) {
    		arm.manualMove(Robot.oi.operatorJoystick.getY());
    	}
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }
    // Called once after isFinished returns true
    protected void end() {
    	arm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	arm.stop();
    }
}
