package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ManualOverrideIntake extends Command{
	private IntakeSubsystem intake;
	
	
	public ManualOverrideIntake () {
		requires(Subsystems.intake);
    	intake = Subsystems.intake;
    	
	}
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		intake.manual();
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
