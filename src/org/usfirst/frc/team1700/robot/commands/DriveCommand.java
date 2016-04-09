package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
//import org.usfirst.frc.team1700.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command{
	private OI oi;
    private DriveSubsystem drive;
	double turnRatePrev = 0;

	
	public DriveCommand(){
		super();
		this.oi = Robot.oi;
		requires(Subsystems.drive);
		drive = Subsystems.drive;
		drive.ShiftLow();

		
	}
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		System.out.println(drive.getRightDistance());
		double throttle = (oi.driveJoystick.getRawAxis(RobotMap.THROTTLE) - oi.driveJoystick.getRawAxis(RobotMap.BACKWARDS));	
		throttle = throttle*Math.abs(throttle);
		double turnRate;
		if (Math.abs(oi.driveJoystick.getRawAxis(RobotMap.TURN_RATE)) < 0.1) {
			turnRate = 0;
		} else if (Math.abs(oi.driveJoystick.getRawAxis(RobotMap.TURN_RATE)) < 0.8){
			 turnRate = (.5*(1-.8*throttle)*(oi.driveJoystick.getRawAxis(RobotMap.TURN_RATE)))-.2*turnRatePrev;
		} else {
			 turnRate = ((0.4 + (oi.driveJoystick.getRawAxis(RobotMap.TURN_RATE)- 0.8)*3)*(1-.8*throttle) -.2*turnRatePrev);
		}
		turnRatePrev = turnRate;
		
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
		drive.stop();

	}
}
