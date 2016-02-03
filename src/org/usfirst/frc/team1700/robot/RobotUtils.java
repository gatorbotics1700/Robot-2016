package org.usfirst.frc.team1700.robot;

public class RobotUtils {

	public static boolean checkDeadband(double desiredValue, double actualValue, double deadband) {
		return (actualValue < desiredValue + deadband && 
				actualValue > desiredValue - deadband);
	}
}
