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
	int autoMode = 6;
	int startingPos = 1; 
	
    public Autonomous() {
		this.oi = Robot.oi;
	
    	if (autoMode == 1) { // this is for the moat
    		addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_FORWARD_DISTANCE, false));
        	addSequential(new AutonomousDriveBackCommand(RobotMap.AUTO_BACKWARDS_DISTANCE));
    	} else if (autoMode == 2) { // this is just straight forward
        	addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_FORWARD_DISTANCE, false));
    	} else if (autoMode == 3) { // this is for the low bar
        	addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_DEFENSE));
        	addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_FORWARD_DISTANCE, false));
        	addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_RETRACTED));
    	} else if (autoMode == 4) { // you're welcome Chris
    		addSequential (new AutonomousDriveForwardCommand(RobotMap.CHEVAL_FRISE_ONE, false));
    		addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_DEFENSE));
    		addSequential (new AutonomousDriveForwardCommand(RobotMap.CHEVAL_FRISE_TWO, false));
    		addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_RETRACTED));
    		addSequential (new AutonomousDriveForwardCommand(RobotMap.CHEVAL_FRISE_THREE, false));    
    	} else if (autoMode == 5) { //you're welcome Natalie
    		addSequential (new AutonomousDriveForwardCommand(RobotMap.SHOOT, false));
    		addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_DEFENSE));
    		addSequential (new AutonomousShootHighGoalCommand());
    		addSequential (new AutonomousArm(AutonomousArm.DESIRED_POSITION_RETRACTED));
    		addSequential (new AutonomousDriveBackCommand(RobotMap.AUTO_BACKWARDS_DISTANCE));
    	} else if (autoMode == 6) {
    		addSequential (new AutonomousTurnToAngle(.2, 30));
    	} else if (autoMode == 7) { //vision tracking autonomous command
    		addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_FORWARD_DISTANCE/3, false)); //the value should be the distance from starting position to front of defense... is probably not correct
    		addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_FORWARD_DISTANCE*2/3, true));
    	} else { // don't move bro
    		new AutonomousDriveForwardCommand(0, false);
    	}
    }
}