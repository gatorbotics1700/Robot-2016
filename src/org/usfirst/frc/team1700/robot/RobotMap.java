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
						    THROTTLE = 5,  // 5
						    TANK_LEFT = 1,
						    TANK_RIGHT = 5,
							SHIFT_HIGH_BUTTON = 6,
							SHIFT_LOW_BUTTON = 5,
							OVERRIDE_BEAMBREAK_BUTTON = 2;
							
	// operating buttons
	public static final int OPERATOR_JOYSTICK_PORT = 1,
							SHOOT_BUTTON = 1,
						//	BACKDRIVE_SHOOT_BUTTON = 3,
							LOW_GOAL_BUTTON = 3,
							START_SHOOT_FAR_BUTTON = 4, //hood up
							START_SHOOT_CLOSE_BUTTON = 5, //hood down
							INTAKE_BUTTON = 6,
							BACKDRIVE_BUTTON = 7,
							INTAKE_ARM_BUTTON = 9,
							DEFENSE_BUTTON = 10,
							RETRACTED_BUTTON = 11,
							MANUAL_ARM_UP_BUTTON = 8,
							MANUAL_ARM_DOWN_BUTTON = 2;
	// pneumatics
	public static final int DRIVE_SOLENOID_ONE_PORT = 6,
							DRIVE_SOLENOID_TWO_PORT = 7,
							SHOOTER_SOLENOID_ONE_PORT = 4,
							SHOOTER_SOLENOID_TWO_PORT = 5;
	
	// talons
	public static final int 
							RIGHT_TALON_ID_1 = 3,
							RIGHT_TALON_ID_2 = 4,
							RIGHT_TALON_ID_3 = 5, // has encoder
							LEFT_TALON__ID_1 = 1,
							LEFT_TALON_ID_2 = 2, // has encoder
							LEFT_TALON_ID_3= 8,
							SHOOTER_TALON_ONE_ID = 6, // has encoder 
							SHOOTER_TALON_TWO_ID = 7,
							ARM_TALON_ID_ONE = 9;
	
	// intake victors
	public static final int ARM_INTAKE_VICTOR = 8,
							FRAME_INTAKE_VICTOR = 7;
	
	// various sensors
	public static final int BEAM_BREAK_FRONT_PORT = 0,
							BEAM_BREAK_BACK_PORT = 1,
							FRONT_LIMIT_SWITCH = 2,
							BACK_LIMIT_SWITCH = 3,
							ULTRASONIC_SENSOR = 4,
							BEAM_BREAK_PORT_NEW = 5;
	
	
	// driving
	public static final double RPM = 1800,
							   TICKS_PER_REV = 2764,
							   CIRCUM_TREAD_WHEEL = 19.05,
							   CIRCUM_PNEUMATIC_WHEEL = 20.05;
	
	//shooter speeds
	public static final double SHOOTER_MOTOR_SPEED_CLOSE = .85,
								SHOOTER_MOTOR_SPEED_FAR = .85,
								SHOOTER_MOTOR_SPEED_BACKDRIVE = -.5;
				
	// deployable arm
	public static final int RETRACTED_ARM_POSITION = 200,
							STRAIGHT_UP_POSITION = 2100,
							INTAKE_ARM_POSITION = 6300, 
							GROUND_ARM_POSITION = 7695,
							DEFENSE_ARM_POSITION = 7000; 
	
	// autonomous
    public static final double AUTO_FORWARD_DISTANCE = 100.0, // change this with more testing
    						   AUTO_DISTANCE_FROM_WALL = 20, // 20 in from wall... about 50 cm ish
    						   ULTRASONIC_CONVERSION_FACTOR = 10, // 10 mv per in for the one on sparkFun, check what Chris's ones are
    						   AUTO_SPEED = 0.3, // change after testing, is this too fast? idk man
    						   LOW_BAR_TURN_ANGLE = 60; // figure out this angle with #trig, do we trust dan's head math?   
	
    //deployable arm speeds
    public static final double MANUAL_ARM_SPEED = 0.4;
    							
			
}
