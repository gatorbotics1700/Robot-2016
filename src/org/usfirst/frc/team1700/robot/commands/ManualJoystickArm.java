package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;


/**
 *
 */
public class ManualJoystickArm extends Command {
	private DeployableArmSubsystem arm;
	private OI oi;
	double armDeadband;

    public ManualJoystickArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.deployableArm);
    	arm = Subsystems.deployableArm;
    	armDeadband = 0.4;
    	oi = Robot.oi;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(arm.readEncoder());
    	if(Robot.oi.operatorJoystick.getY() > armDeadband) {
    		arm.moveDown();
    		System.out.println("Moving up");
    	} else if (Robot.oi.operatorJoystick.getY() < -armDeadband) {
    		arm.moveUp();
    		System.out.println("Moving down");
    	} else { 
    		if(this.oi.driveJoystick.getRawButton(RobotMap.NO_GRAVITY_BUTTON)) {
    			arm.stopMotors();
    			System.out.println("Not moving and not using gravity");
    		} else {
    			arm.gravity();
    			System.out.println("not moving");
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
