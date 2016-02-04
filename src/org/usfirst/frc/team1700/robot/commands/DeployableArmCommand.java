package org.usfirst.frc.team1700.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;

/**
 *
 */
public class DeployableArmCommand extends Command {
	
	private String desiredPosition;
	private DeployableArmSubsystem arm;

    public DeployableArmCommand(String position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.deployableArm);
    	desiredPosition = position;
    	arm = Subsystems.deployableArm;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (desiredPosition.equals("retracted")) {
    		arm.goToRetracted();
    	} else if (desiredPosition.equals("intake")) {
    		arm.goToIntake();
    	} else arm.goToDefense();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (desiredPosition.equals("retracted")) {
    		return arm.isRetracted();
    	} else if (desiredPosition.equals("intake")) {
    		return arm.isAtIntake();
    	} else return arm.isAtDefense();
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
