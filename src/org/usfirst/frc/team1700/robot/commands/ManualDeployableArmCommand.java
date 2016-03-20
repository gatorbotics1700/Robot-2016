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
	public static final int UP = 1,
							DOWN = 2;
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
    	arm.zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (desiredAction == UP){
    		arm.moveUp();
    	} else if (desiredAction == DOWN) {
    		arm.moveDown();
    	} else {
    		arm.gravity();
    	}
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }
    // Called once after isFinished returns true
    protected void end() {
    	//be wary when changing mode to percent vbus mode
    	arm.gravity();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	arm.gravity();
    }
}
