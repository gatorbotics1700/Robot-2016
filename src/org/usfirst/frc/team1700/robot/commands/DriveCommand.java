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
		this.oi = Robot.oi;
		requires(Subsystems.drive);

	}
	    protected void initialize() {

	    }

		@Override
		protected void execute() {
			// TODO Auto-generated method stub
			double turnRate = (oi.driveJoystick.getRawAxis(RobotMap.TURN_RATE));
			double throttle = (oi.driveJoystick.getRawAxis(RobotMap.THROTTLE));		
	    	Subsystems.drive.driveCheesy(throttle, turnRate);
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
