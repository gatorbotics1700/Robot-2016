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
	Button // drive buttons
		   lowGoalButton = new JoystickButton(driveJoystick,RobotMap.LOW_GOAL_BUTTON),
		   retractedArmButton = new JoystickButton(driveJoystick,RobotMap.RETRACTED_BUTTON),
		   straightUpArmButton = new JoystickButton(driveJoystick,RobotMap.STRAIGHT_UP_ARM_BUTTON),
		   defenseButton = new JoystickButton(driveJoystick,RobotMap.DEFENSE_BUTTON),
		   shiftHighButton = new JoystickButton(driveJoystick,RobotMap.SHIFT_HIGH_BUTTON),
		   shiftLowButton = new JoystickButton(driveJoystick,RobotMap.SHIFT_LOW_BUTTON),
		   autoArmButton = new JoystickButton(driveJoystick, RobotMap.AUTO_ARM_BUTTON);

		
	Button // manual operator buttons
		   startShootFarButton = new JoystickButton(operatorJoystick,RobotMap.START_SHOOT_FAR_BUTTON),
		   startShootCloseButton = new JoystickButton(operatorJoystick,RobotMap.START_SHOOT_CLOSE_BUTTON),
		   shootButton = new JoystickButton(operatorJoystick,RobotMap.SHOOT_BUTTON),
		   manualArmButton = new JoystickButton(operatorJoystick, RobotMap.MANUAL_ARM_BUTTON),
		   manualArmUpButton = new JoystickButton(operatorJoystick,RobotMap.MANUAL_ARM_UP_BUTTON),
		   manualArmDownButton = new JoystickButton(operatorJoystick, RobotMap.MANUAL_ARM_DOWN_BUTTON),
		   overrideBeamBreakIntakeButton = new JoystickButton(operatorJoystick, RobotMap.OVERRIDE_BEAMBREAK_BUTTON_INTAKE),
		   overrideBeamBreakStopButton = new JoystickButton(operatorJoystick, RobotMap.OVERRIDE_BEAMBREAK_BUTTON_STOP),
		   overrideBeamBreakBackdriveButton = new JoystickButton(operatorJoystick, RobotMap.OVERRIDE_BEAMBREAK_BUTTON_BACKDRIVE);

	
		
	public OI () {

		lowGoalButton.whenPressed(new LowGoal());
		retractedArmButton.whileHeld(new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_RETRACTED));
		straightUpArmButton.whileHeld(new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_STRAIGHT_UP));
		defenseButton.whileHeld(new DeployableArmCommand(DeployableArmCommand.DESIRED_POSITION_DEFENSE));
		shiftHighButton.whenPressed(new DriveShiftingCommand(DriveShiftingCommand.SHIFT_HIGH));
		shiftLowButton.whenPressed(new DriveShiftingCommand(DriveShiftingCommand.SHIFT_LOW));
		autoArmButton.whenPressed(new AutoOverrideArm());
		
		startShootFarButton.whileHeld(new StartShootWheelFarCommand());
		startShootCloseButton.whileHeld(new StartShootWheelCloseCommand());
		shootButton.whileHeld(new ShootCommand());
		manualArmButton.whenPressed(new ManualOverrideArm());
		manualArmUpButton.whileHeld(new ManualDeployableArmCommand(ManualDeployableArmCommand.UP));
		manualArmDownButton.whileHeld(new ManualDeployableArmCommand(ManualDeployableArmCommand.DOWN));
//		overrideBeamBreakIntakeButton.whileHeld(new OverrideBeamBreakIntake());
//		overrideBeamBreakStopButton.whileHeld(new OverrideBeamBreakStop());
//		overrideBeamBreakBackdriveButton.whileHeld(new OverrideBeamBreakBackdrive());
		
	}
	
}

