package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *'
 */
public class Autonomous extends CommandGroup {
	private OI oi;
	int autoMode = 2;
	
	
    public Autonomous() {
		this.oi = Robot.oi;
//    	
	
    	if (autoMode == 1) { // this is for the moat
        	addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_FORWARD_DISTANCE));
        	addSequential(new AutonomousDriveBackCommand(RobotMap.AUTO_BACKWARDS_DISTANCE));
    	} else if (autoMode == 2) { // this is just straight forward
            addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_FORWARD_DISTANCE));
    	} else if (autoMode == 3) { // this is for the low bar
        	addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_DEFENSE));
        	addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_FORWARD_DISTANCE));
        	addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_RETRACTED));
    	} else if (autoMode == 4) {
    		addSequential (new AutonomousDriveForwardCommand(RobotMap.CHEVAL_FRISE_ONE));
    		addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_DEFENSE));
    		addSequential (new AutonomousDriveForwardCommand(RobotMap.CHEVAL_FRISE_TWO));
    		addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_RETRACTED));
    		addSequential (new AutonomousDriveForwardCommand(RobotMap.CHEVAL_FRISE_THREE));    
    	}
    }
}