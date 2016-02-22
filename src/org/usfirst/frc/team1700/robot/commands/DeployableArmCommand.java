package org.usfirst.frc.team1700.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;

/**
 *
 */
public class DeployableArmCommand extends Command {
	
	public final static int DESIRED_POSITION_RETRACTED = 1,
							 DESIRED_POSITION_INTAKE = 2,
							 DESIRED_POSITION_DEFENSE = 3;
	private DeployableArmSubsystem arm;
	private int desiredPosition;
	
    public DeployableArmCommand(int position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.deployableArm);
    	this.desiredPosition = position;
    	arm = Subsystems.deployableArm;
    }

    public void getPosition() {
    	
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (desiredPosition == DESIRED_POSITION_RETRACTED) {
        	arm.PIDSituation(RobotMap.RETRACTED_ARM_POSITION);
    	} else if (desiredPosition == DESIRED_POSITION_INTAKE) {
        	arm.PIDSituation(RobotMap.INTAKE_ARM_POSITION);
    	} else {
        	arm.PIDSituation(RobotMap.DEFENSE_ARM_POSITION);
    	}
    }
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (desiredPosition == DESIRED_POSITION_RETRACTED){
    		return arm.isRetracted();
    	} else if (desiredPosition == DESIRED_POSITION_DEFENSE) {
    		return arm.isAtDefense();
    	} else {
    		return false;
    	}
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
