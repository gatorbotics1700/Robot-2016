package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.OI;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class StartShootWheelCloseCommand extends Command{

	  private ShooterSubsystem shooter;
	  private IntakeSubsystem intake;
	  private OI oi;
	  
	public StartShootWheelCloseCommand(){
		super();
		this.oi = Robot.oi;
		requires(Subsystems.shooter);
		requires(Subsystems.intake);
        shooter = Subsystems.shooter;
	}
	    
	    
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		//intake.moveBallToWaitingPosition();
		shooter.shootClose();
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
		shooter.setSpeedToZero();
		
	}

}
