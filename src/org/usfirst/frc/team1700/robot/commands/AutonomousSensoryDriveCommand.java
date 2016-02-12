package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.RobotUtils;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.HalfDriveSubsystem;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;


public class AutonomousSensoryDriveCommand extends Command {
    private DriveSubsystem drive;

//    private HalfDriveSubsystem right;
    double distance;
    double leftSpeed;
    double rightSpeed;
    AnalogInput ultrasonicSensor;

    
	
	public AutonomousSensoryDriveCommand(double autoDistance) {
        requires(Subsystems.drive);
        drive = Subsystems.drive;
        distance = autoDistance;
        ultrasonicSensor = new AnalogInput (RobotMap.ULTRASONIC_SENSOR);
//       frontUltrasonicSensor = new AnalogInput(RobotMap.FRONT_ULTRASONIC_SENSOR);
    }
	
	public void drive(double distance, double leftSpeed, double rightSpeed){
		if (drive.getRightDistance() < distance) {
			drive.driveTank (leftSpeed, rightSpeed);
		}// this is for autonomous
		else {
			drive.driveTank (0,0);
		}
		
	}
	
	public boolean TooClose(){
		if ((ultrasonicSensor.getVoltage() / RobotMap.ULTRASONIC_CONVERSION_FACTOR) < 19.6) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean TooFar(){
		if ((ultrasonicSensor.getVoltage() / RobotMap.ULTRASONIC_CONVERSION_FACTOR) > 20.4 ) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean DriveDistance() {
		if (drive.getRightDistance() < (distance - 10) ) { // change 90 to the actual distance we want to go - 10
			return false;
		} else if (drive.getRightDistance() > (distance + 10) ) {
			return false;
		} else {
			return true;
		}
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rightSpeed = RobotMap.AUTO_SPEED;
    	if (TooClose()) {
    		leftSpeed = (RobotMap.AUTO_SPEED + .05);    		
    	} else if (TooFar()) {
    		leftSpeed = (RobotMap.AUTO_SPEED - .05);
    	} else {
    		leftSpeed = RobotMap.AUTO_SPEED;
    	}
	drive(distance, leftSpeed, rightSpeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return DriveDistance();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive.stop();
    }
}
