package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
    public Autonomous() {
    	//addSequential (new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_DEFENSE));
    	addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_FORWARD_DISTANCE));
    	addSequential(new AutonomousDriveForwardCommand(RobotMap.AUTO_BACKWARDS_DISTANCE));
    	//addSequential (new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_RETRACTED));
    }
}
