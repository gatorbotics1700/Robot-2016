package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
//import org.usfirst.frc.team1700.robot.subsystems.*;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command{
	private OI oi;
    private DriveSubsystem drive;


	
	public DriveCommand(){
		super();
		this.oi = Robot.oi;
		requires(Subsystems.drive);
		drive = new DriveSubsystem();

		
	}
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double turnRate = (oi.driveJoystick.getRawAxis(RobotMap.TURN_RATE));
		double throttle = (oi.driveJoystick.getRawAxis(RobotMap.THROTTLE));	
		drive.driveArcade(throttle, turnRate);
		drive.navX();
		

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
