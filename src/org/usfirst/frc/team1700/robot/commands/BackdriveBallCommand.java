package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class BackdriveBallCommand extends Command {
	
	 private IntakeSubsystem intake;
	 private ShooterSubsystem shooter;
	    private OI oi;

	public BackdriveBallCommand (){
		super();
		this.oi = Robot.oi;
		requires(Subsystems.intake);
        intake = Subsystems.intake;
        requires(Subsystems.shooter);
        shooter = Subsystems.shooter;
	}
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		shooter.backdrive();
		intake.backDrive();
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
