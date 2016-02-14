package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
//import org.usfirst.frc.team1700.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command{
	private OI oi;

	
	public DriveCommand(){
		super();
		this.oi = Robot.oi;
		requires(Subsystems.drive);

	}
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double turnRate = (oi.driveJoystick.getRawAxis(RobotMap.TURN_RATE));
		double throttle = (oi.driveJoystick.getRawAxis(RobotMap.THROTTLE));	
		Subsystems.drive.driveArcade(throttle, turnRate);

	}

	@Override
	protected boolean isFinished() { // lol this should never be finished amirite lmao
		return false;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}
}
