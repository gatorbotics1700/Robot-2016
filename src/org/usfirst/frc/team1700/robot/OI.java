package org.usfirst.frc.team1700.robot;

import org.usfirst.frc.team1700.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK_PORT);
	public Joystick operatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK_PORT);
	Button enableShiftingButton = new JoystickButton(driveJoystick,RobotMap.ENABLE_SHIFTING_BUTTON),
		   disableShiftingButton = new JoystickButton(driveJoystick,RobotMap.DISABLE_SHIFTING_BUTTON),
		   intakeButton = new JoystickButton(operatorJoystick,RobotMap.INTAKE_BUTTON),
		   backdriveButton = new JoystickButton(operatorJoystick,RobotMap.BACKDRIVE_BUTTON),
		   startShootFarButton = new JoystickButton(operatorJoystick,RobotMap.START_SHOOT_FAR_BUTTON),
		   startShootCloseButton = new JoystickButton(operatorJoystick,RobotMap.START_SHOOT_CLOSE_BUTTON),
		   shootButton = new JoystickButton(operatorJoystick,RobotMap.SHOOT_BUTTON);
	
	
	public OI () {
		intakeButton.whenPressed(new IntakeBallCommand());
		backdriveButton.whenPressed(new BackdriveBallCommand());
		startShootFarButton.whenPressed(new StartShootWheelFarCommand());
		startShootCloseButton.whenPressed(new StartShootWheelCloseCommand());
		shootButton.whileHeld(new ShootCommand());
	}
	
}

