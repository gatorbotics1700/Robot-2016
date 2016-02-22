 package org.usfirst.frc.team1700.robot;
import org.usfirst.frc.team1700.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * Four buttons for the arm, three buttons for shooter, two buttons for intake,
 * one buttons for the hood, I'm definitely missing something
 */
public class OI {
	public Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK_PORT);
	public Joystick operatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK_PORT);
	Button intakeButton = new JoystickButton(operatorJoystick,RobotMap.INTAKE_BUTTON),
		   backdriveButton = new JoystickButton(operatorJoystick,RobotMap.BACKDRIVE_BUTTON),
		   startShootFarButton = new JoystickButton(operatorJoystick,RobotMap.START_SHOOT_FAR_BUTTON),
		   startShootCloseButton = new JoystickButton(operatorJoystick,RobotMap.START_SHOOT_CLOSE_BUTTON),
		   shootButton = new JoystickButton(operatorJoystick,RobotMap.SHOOT_BUTTON),
		   shootBackdriveButton = new JoystickButton(operatorJoystick,RobotMap.BACKDRIVE_SHOOT_BUTTON),
//		   retractedArmButton = new JoystickButton(operatorJoystick,RobotMap.RETRACTED_BUTTON),
//		   intakeArmButton = new JoystickButton(operatorJoystick,RobotMap.INTAKE_ARM_BUTTON),
//		   defenseButton = new JoystickButton(operatorJoystick,RobotMap.DEFENSE_BUTTON),
		   shiftHighButton = new JoystickButton(driveJoystick,RobotMap.SHIFT_HIGH_BUTTON),
		   shiftLowButton = new JoystickButton(driveJoystick,RobotMap.SHIFT_LOW_BUTTON),
		  // manualArmUpButton = new JoystickButton(operatorJoystick,RobotMap.MANUAL_ARM_UP_BUTTON),
//		   manualArmDownButton = new JoystickButton(operatorJoystick, RobotMap.MANUAL_ARM_DOWN_BUTTON),
		   overrideBeamBreakButton = new JoystickButton(driveJoystick, RobotMap.OVERRIDE_BEAMBREAK_BUTTON);
	
		
	public OI () {
		intakeButton.whileHeld(new IntakeBallCommand());
		backdriveButton.whileHeld(new BackdriveBallCommand());
		startShootFarButton.whileHeld(new StartShootWheelFarCommand());
		startShootCloseButton.whileHeld(new StartShootWheelCloseCommand());
		shootButton.whileHeld(new ShootCommand(ShootCommand.SHOOT));
		shootBackdriveButton.whileHeld(new ShootCommand(ShootCommand.BACKDRIVE));
		//retractedArmButton.whileHeld(new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_RETRACTED));
		//intakeArmButton.whileHeld(new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_INTAKE));
		//defenseButton.whileHeld(new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_DEFENSE));
		shiftHighButton.whenPressed(new DriveShiftingCommand(DriveShiftingCommand.SHIFT_HIGH));
		shiftLowButton.whenPressed(new DriveShiftingCommand(DriveShiftingCommand.SHIFT_LOW));
		overrideBeamBreakButton.whileHeld(new OverrideBeamBreak());
		///manualArmUpButton.whileHeld(new ManualDeployableArmCommand(ManualDeployableArmCommand.UP));
//		manualArmDownButton.whileHeld(new ManualDeployableArmCommand());
	}
	
}

