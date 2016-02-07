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
						    TURN_RATE = 0, 
						    THROTTLE = 5, 
						    TANK_LEFT = 1,
						    TANK_RIGHT = 5,
							ENABLE_SHIFTING_BUTTON = 2;
	
	// operating
	public static final int OPERATOR_JOYSTICK_PORT = 1,
							INTAKE_BUTTON = 1,
							BACKDRIVE_BUTTON = 2;	

	// pneumatics
	public static final int LEFT_DRIVE_SOLENOID_ONE_PORT = 0,
							LEFT_DRIVE_SOLENOID_TWO_PORT = 1,
							RIGHT_DRIVE_SOLENOID_ONE_PORT = 2,
							RIGHT_DRIVE_SOLENOID_TWO_PORT = 3;
	
	// talons
	public static final int //RIGHT_VICTOR_ID_1 = 1,
							//RIGHT_VICTOR_ID_2 = 2,
							RIGHT_TALON_ID_1 = 1,
							RIGHT_TALON_ID_2 = 2,
							RIGHT_TALON_ID_3 = 3,
							LEFT_TALON__ID_1 = 4,
							LEFT_TALON_ID_2 = 5,
							//LEFT_VICTOR_ID_1 = 4,
							//LEFT_VICTOR_ID_2 = 5,
							LEFT_TALON_ID_3= 6,
							SHOOTER_TALON_ID = 7,
							ARM_TALON_ID = 8;
	
	// intake victors
	public static final int INTAKE_VICTOR_1_ID = 1,
							INTAKE_VICTOR_2_ID = 2;
	
	// various sensors
	public static final int BEAM_BREAK_PORT = 0,
							FRONT_LIMIT_SWITCH = 1,
							BACK_LIMIT_SWITCH = 2;
	
	// driving
	public static final int RPM = 1800;

	public static final int SHOOTER_MOTOR_SPEED_CLOSE = 10,
							SHOOTER_MOTOR_SPEED_FAR = 20,
							SHOOTER_MOTOR_SPEED_BACKDRIVE = -10;
				
	// deployable arm
	public static final int RETRACTED_ARM_POSITION = 0,
							INTAKE_ARM_POSITION = 0,
							DEFENSE_ARM_POSITION = 0;
	
	// autonomous
    public static final double AUTO_FORWARD_DISTANCE = 45.0, // change after testing
    						   AUTO_SPEED = 0.3, // change after testing
    						   AUTO_BACKWARD_DISTANCE = -20.0, //change after testing
    						   AUTO_BACKDRIVE_DISTANCE = 3546.468; // change after testing
			
}
