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
							ENABLE_SHIFTING_BUTTON = 6,
							DISABLE_SHIFTING_BUTTON = 5;
	
	// operating
	public static final int OPERATOR_JOYSTICK_PORT = 1,
							INTAKE_BUTTON = 2,
							BACKDRIVE_BUTTON = 3,
							START_SHOOT_CLOSE_BUTTON = 5,
							START_SHOOT_FAR_BUTTON = 4,
							SHOOT_BUTTON = 1;	

	// pneumatics
	public static final int LEFT_DRIVE_SOLENOID_ONE_PORT = 0,
							LEFT_DRIVE_SOLENOID_TWO_PORT = 1,
							RIGHT_DRIVE_SOLENOID_ONE_PORT = 2,
							RIGHT_DRIVE_SOLENOID_TWO_PORT = 3;
	
	// talons
	public static final int //RIGHT_VICTOR_ID_1 = 1,
							//RIGHT_VICTOR_ID_2 = 2,
							RIGHT_TALON_ID_1 = 3,
							RIGHT_TALON_ID_2 = 4,
							RIGHT_TALON_ID_3 = 5, // has encoder
							LEFT_TALON__ID_1 = 1,
							LEFT_TALON_ID_2 = 2, // has encoder
							LEFT_TALON_ID_3= 8,
							SHOOTER_TALON_ONE_ID = 6,
							SHOOTER_TALON_TWO_ID = 7,
							ARM_TALON_ID_ONE = 9;
	
	// intake victors
	public static final int INTAKE_VICTOR_1_ID = 1,
							INTAKE_VICTOR_2_ID = 2;
	
	// various sensors
	public static final int BEAM_BREAK_FRONT_PORT = 0,
							BEAM_BREAK_BACK_PORT = 1,
							FRONT_LIMIT_SWITCH = 2,
							BACK_LIMIT_SWITCH = 3,
							ULTRASONIC_SENSOR = 1;
	
	
	// driving
	public static final double RPM = 1800,
							   TICKS_PER_REV = 2764,
							   CIRCUM_TREAD_WHEEL = 19.05,
							   CIRCUM_PNEUMATIC_WHEEL = 20.05;

	public static final int SHOOTER_MOTOR_SPEED_CLOSE = 10,
							SHOOTER_MOTOR_SPEED_FAR = 20,
							SHOOTER_MOTOR_SPEED_BACKDRIVE = -10;
				
	// deployable arm
	public static final int RETRACTED_ARM_POSITION = 0,
							INTAKE_ARM_POSITION = 0,
							DEFENSE_ARM_POSITION = 0;
	
	// autonomous
    public static final double AUTO_FORWARD_DISTANCE = 100.0, // change this with more testing
    						   AUTO_DISTANCE_FROM_WALL = 20, // 20 in from wall... about 50 cm ish
    						   ULTRASONIC_CONVERSION_FACTOR = 10, // 10 mv per in for the one on sparkFun, check what Chris's ones are
    						   AUTO_SPEED = 0.3, // change after testing, is this too fast? idk man
    						   LOW_BAR_TURN_ANGLE = 60; // figure out this angle with #trig, do we trust dan's head math?
   
    
    							
			
}
