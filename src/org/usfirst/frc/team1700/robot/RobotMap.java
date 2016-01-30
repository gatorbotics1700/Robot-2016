package org.usfirst.frc.team1700.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
   // driving
	public static final int DRIVE_JOYSTICK_PORT = 0,
							// axes
						    TURN_RATE = 4, 
						    THROTTLE = 5, 
							ENABLE_SHIFTING_BUTTON = 2;

			
	
	// talons
	public static final int RIGHT_TALON_ID_1 = 1,
							RIGHT_TALON_ID_2 = 2,
							RIGHT_TALON_ID_3 = 3,
							LEFT_TALON_ID_1 = 4,
							LEFT_TALON_ID_2 = 5,
							LEFT_TALON_ID_3 = 6,
							SHOOTER_TALON_ID = 7,
							ARM_TALON_ID = 8;
	
	// intake victors
	public static final int INTAKE_VICTOR_1_ID = 0,
							INTAKE_VICTOR_2_ID = 1;
	
	// various sensors
	public static final int BEAM_BREAK_PORT = 0,
							FRONT_LIMIT_SWITCH = 1,
							BACK_LIMIT_SWITCH = 2;
	
	// driving
	public static final int RPM = 1800;

	public static final int SHOOTER_MOTOR_SPEED_CLOSE = 10,
							SHOOTER_MOTOR_SPEED_FAR = 20,
							SHOOTER_MOTOR_SPEED_BACKDRIVE = -10;
				
			
}
