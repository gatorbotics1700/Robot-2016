package org.usfirst.frc.team1700.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
   // driving
	// recheck w/ new joystick bc maybe everything is wrong??
	public static final int DRIVE_JOYSTICK_PORT = 0,
							// axes
						    TURN_RATE = 0, 
						    THROTTLE = 5, // 5
						    TANK_LEFT = 1,
						    TANK_RIGHT = 5,
						    SHIFT_HIGH_BUTTON = 6,
						    SHIFT_LOW_BUTTON = 5,
						    LOW_GOAL_BUTTON = 2,
						    STRAIGHT_UP_ARM_BUTTON = 3,
						    DEFENSE_BUTTON = 1,
						    RETRACTED_BUTTON = 4,
						    AUTO_ARM_BUTTON = 7,
						    AUTO_INTAKE_BUTTON = 8;

							
	// operating buttons
	public static final int OPERATOR_JOYSTICK_PORT = 1,
							SHOOT_BUTTON = 1,
							START_SHOOT_FAR_BUTTON = 5, //hood up
							START_SHOOT_CLOSE_BUTTON = 4, //hood down
							OVERRIDE_BEAMBREAK_BUTTON_INTAKE = 6,
							OVERRIDE_BEAMBREAK_BUTTON_BACKDRIVE = 7,
							OVERRIDE_BEAMBREAK_BUTTON_STOP = 8, 
							MANUAL_ARM_BUTTON = 8,						
							MANUAL_ARM_UP_BUTTON = 3,
							MANUAL_ARM_DOWN_BUTTON = 2;

	// pneumatics
	public static final int DRIVE_SOLENOID_ONE_PORT = 6,
							DRIVE_SOLENOID_TWO_PORT = 7,
							SHOOTER_SOLENOID_ONE_PORT = 4,
							SHOOTER_SOLENOID_TWO_PORT = 5;
	
	// talons
	public static final int 
							RIGHT_TALON_ID_1 = 5, // 3
							RIGHT_TALON_ID_2 = 9, // 4
							RIGHT_TALON_ID_3 = 6, // has encoder 5
							LEFT_TALON__ID_1 = 1,
							LEFT_TALON_ID_2 = 2, // has encoder
							LEFT_TALON_ID_3= 8,
							SHOOTER_TALON_ONE_ID = 3, // has encoder  // 6
							SHOOTER_TALON_TWO_ID = 4, // 7
							ARM_TALON_ID_ONE = 9; // 9
	
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
				

	
	// autonomous
    public static final double AUTO_FORWARD_DISTANCE = 01.0, // change this with more testing
    						   AUTO_DISTANCE_FROM_WALL = 20, // 20 in from wall... about 50 cm ish
    						   ULTRASONIC_CONVERSION_FACTOR = 10, // 10 mv per in for the one on sparkFun, check what Chris's ones are
    						   AUTO_SPEED = 0.3, // change after testing, is this too fast? idk man
    						   LOW_BAR_TURN_ANGLE = 60; // figure out this angle with #trig, do we trust dan's head math?   
	
    //deployable arm
    public static final double RETRACTED_TO_STRAIGHT_UP = 4200,
    							STRAIGHT_UP_TO_INTAKE = 4200,
    							STRAIGHT_UP_TO_GROUND = 4200,
    							STRAIGHT_UP_TO_DEFENSE = 4200;
    //deployable arm speeds
    public static final double MANUAL_ARM_SPEED = 0.4;
    
    // is arm at intake position?
    public static boolean atIntakePosition = false;
			
}
