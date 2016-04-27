package org.usfirst.frc.team1700.robot;

import org.usfirst.frc.team1700.robot.subsystems.*;

public class Subsystems {
	public static DriveSubsystem drive = new DriveSubsystem(); //these are going to change will change when our drive train is finalized
	public static ShooterSubsystem shooter = new ShooterSubsystem();
	public static IntakeSubsystem intake = new IntakeSubsystem();
	public static DeployableArmSubsystem deployableArm = new DeployableArmSubsystem();
	public static Robot robot;
	public static VisionTracking vision = new VisionTracking();
}
