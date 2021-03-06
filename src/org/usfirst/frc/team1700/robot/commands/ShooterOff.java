package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterOff extends Command {
    private ShooterSubsystem shooter;
    private OI oi;
	
    
    public ShooterOff() {
    	super();
    	this.oi = Robot.oi;
    	requires(Subsystems.shooter);
    	shooter = Subsystems.shooter;
    }
	@Override
	
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		shooter.setSpeedToZero();
		shooter.retractHood();
		// TODO Auto-generated method stub
		
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
