package org.usfirst.frc.team1700.robot;

import org.usfirst.frc.team1700.robot.commands.IntakeBallCommand;

import edu.wpi.first.wpilibj.Joystick;


import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// drive Joystick
	public Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK_PORT);
	public Joystick operatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK_PORT);
	Button intakeButton = new JoystickButton(operatorJoystick,RobotMap.INTAKE_BUTTON),
		   enableShiftingButton = new JoystickButton(operatorJoystick,RobotMap.ENABLE_SHIFTING_BUTTON);
	
	
	public OI () {
		intakeButton.whenPressed(new IntakeBallCommand());
	}
	
}

