package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous() {
    	addSequential(new AutonomousDriveCommand(RobotMap.AUTO_FORWARD_DISTANCE));
    	addSequential(new AutonomousShootHighGoalCommand());
    	addSequential(new AutonomousDriveCommand(RobotMap.AUTO_BACKWARD_DISTANCE));
    }
}
