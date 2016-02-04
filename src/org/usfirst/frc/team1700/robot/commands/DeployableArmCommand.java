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
    	switch (desiredPosition) {
			case "retracted":
				arm.goToRetracted();
				break;
			case "intake":
				arm.goToIntake();
				break;
			case "defense":
				arm.goToDefense();
				break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	switch (desiredPosition) {
			case "retracted":
				return arm.isRetracted();
			case "intake":
				return arm.isAtIntake();
			case "defense":
				return arm.isAtDefense();
    	}
    	return true;
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
