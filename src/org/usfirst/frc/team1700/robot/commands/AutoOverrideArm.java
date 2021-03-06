package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class AutoOverrideArm extends Command{
	private DeployableArmSubsystem arm;
	
	
	public AutoOverrideArm () {
		requires(Subsystems.deployableArm);
    	arm = Subsystems.deployableArm;
    	
	}
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		arm.auto();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
