package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.RobotUtils;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.VisionTracking;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class VisionTrackingTurnToAngleCommand extends Command {
	
	private DriveSubsystem drive;
	private VisionTracking vision;
	private NetworkTable table;
	private double targetAngle;
	private double thetaInitial;
	private double cameraAngle;
	private static final double TIME_DELAY = 0.2; // fix
	private static final double P = .01; // fix
	private static final double D = 0; // fix
	private double turnEffort;
	private static final double MINIMUM_EFFORT = .05; // fix
	
    public VisionTrackingTurnToAngleCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.drive);
    	requires(Subsystems.vision);
    	this.drive = Subsystems.drive;
    	table = NetworkTable.getTable("TowerTrack");
    	thetaInitial = Subsystems.robot.theta;
    	cameraAngle = table.getNumber("azimuth");
    	targetAngle = cameraAngle;
    	turnEffort = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// If this is a new camera angle from vision, read it in, apply lookahead
    	// and update target angle
    	if(cameraAngle != table.getNumber("azimuth")){
    		cameraAngle = table.getNumber("azimuth") + TIME_DELAY*Subsystems.robot.omega;
    		targetAngle = cameraAngle - (Subsystems.robot.theta - thetaInitial);
    	}
    	
    	// p * how far away you are
    	turnEffort = P * (targetAngle + (Subsystems.robot.theta - thetaInitial)) // negative = left
    				 + D * Subsystems.robot.omega;
    	turnEffort += Math.signum(turnEffort) * MINIMUM_EFFORT;
    	drive.driveTank(-turnEffort, turnEffort);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Check if camera angle is < 2 and omega < 0.1
    	 return (Math.abs(cameraAngle) < RobotMap.TURNING_ANGLE_DEADBAND 
    			 && Math.abs(Subsystems.robot.omega) < RobotMap.TURNING_ANGULAR_VELOCITY_DEADBAND);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
