package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GravityCommand extends Command {

	private DeployableArmSubsystem arm;

	 public GravityCommand() {
	    	requires(Subsystems.deployableArm);
	    	arm = Subsystems.deployableArm;
	    	
	 }
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		arm.gravity();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
