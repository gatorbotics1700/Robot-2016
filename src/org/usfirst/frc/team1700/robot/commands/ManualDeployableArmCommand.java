package org.usfirst.frc.team1700.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;

/**
 *
 */
public class ManualDeployableArmCommand extends Command {
	public static final int MOVE = 1,
							STOP = 2;
	private DeployableArmSubsystem arm;
	double armDeadband;
	int desiredAction;
	private OI oi;
	
    public ManualDeployableArmCommand(int desiredAction) {
    	requires(Subsystems.deployableArm);
    	arm = Subsystems.deployableArm;
    	this.desiredAction = desiredAction;
    	this.oi = Robot.oi;
    }

    public void getPosition() {
    
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (desiredAction == MOVE){
    		arm.moveAnalog(Robot.oi.operatorJoystick.getRawAxis(1));
    	} else {
    		arm.stopMotors();
    	}
    }
   
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }
    // Called once after isFinished returns true
    protected void end() {
    	arm.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	arm.stopMotors();
    }
}
