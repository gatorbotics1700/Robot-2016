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
							 //DESIRED_POSITION_INTAKE = 2,
							DESIRED_POSITION_STRAIGHT_UP = 2,
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
    	//if (desiredPosition == DESIRED_POSITION_RETRACTED) {
        arm.PIDSituation(desiredPosition);
//    	} else if (desiredPosition == DESIRED_POSITION_INTAKE) {
//        	arm.PIDSituation(RobotMap.INTAKE_ARM_POSITION);
//    	} else {
//        	arm.PIDSituation(RobotMap.DEFENSE_ARM_POSITION);
//    	}
    }
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (desiredPosition == DESIRED_POSITION_RETRACTED){
    		RobotMap.atIntakePosition = false;
    		return arm.isRetracted(); //written by ria + arushi
    	} else if (desiredPosition == DESIRED_POSITION_DEFENSE) {
    		RobotMap.atIntakePosition = true; //written by ria + arushi
    		return arm.isAtDefense();
    	} else {
    		RobotMap.atIntakePosition = false; //written by ria + arushi
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	arm.gravity();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	arm.gravity();
    }
}
