package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Our autonomous drive command
 * A distance is passed in from the autonomous command (can be a positive or negative distance)
 * The robot drives forward until the encoder reading is greater than the auto distance passed in
 */
public class AutonomousDriveForwardCommand extends Command {
	DriveSubsystem drive;
	double autoDistance;
	boolean useGyro;
	private AHRS navX;
	int counter;
	double flatIntervalDeadband;
	boolean goingOverDefense;
	// Value obtained by dividing anticipated delay time by RoboRio loop delay time
	private static final double DELAY_TIME_VALUE = 10;
	
	public AutonomousDriveForwardCommand(double autoDistance, boolean useGyro) {
		requires(Subsystems.drive);
	    drive = Subsystems.drive;
		this.autoDistance = autoDistance;
		this.useGyro = useGyro; 
		counter = 0; 
		flatIntervalDeadband = 2/100;
		goingOverDefense = false;
	}

	protected void initialize() {
		drive.zeroEncoders();
		drive.ShiftLow();
	}

	@Override
	protected void execute() {
		if (useGyro) accelDrive();
		else regularDrive();		
	} 

	private void accelDrive() {
		//navXValue = 9.81 (gravity) + rawAccelZ (nominally, -9.81)
		double navXValue = 9.81+navX.getRawAccelZ();
		regularDrive();
		if (Math.abs(navXValue) > flatIntervalDeadband*9.81) {
			counter = 0;
		}else{
			counter++;
		}
	}
	
	private void regularDrive() {
		drive.ShiftLow();
		drive.driveTank(-RobotMap.AUTO_SPEED, -RobotMap.AUTO_SPEED);
	}
	
	@Override
	protected boolean isFinished() {
		if ((counter < DELAY_TIME_VALUE) && (Math.abs(drive.getRightDistance()) < autoDistance || Math.abs(drive.getLeftDistance()) < autoDistance)) {
			return false;
		} else {
			return true;
		}
	} 
	

	@Override
	protected void end() {
		System.out.println("ending the forward");
		drive.driveTank(0,0);
		
	}

	@Override
	protected void interrupted() {
		drive.driveTank(0,0);
		
	}	
}