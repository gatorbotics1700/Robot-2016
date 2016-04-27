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
						    TURN_RATE = 0,  // 0
						    THROTTLE = 2, // 5
						    BACKWARDS = 3,
//						    TANK_LEFT = 1,
//						    TANK_RIGHT = 5,
						    SHIFT_HIGH_BUTTON = 6,
						    SHIFT_LOW_BUTTON = 5,
						    LOW_GOAL_BUTTON = 2,
						    AUTO_ALIGN_BUTTON = 3,
						    DEFENSE_BUTTON = 4,
						    RETRACTED_BUTTON = 1,
						    AUTO_ARM_BUTTON = 8,
						    AUTO_INTAKE_BUTTON = 7;

							
	// operating buttons
	public static final int OPERATOR_JOYSTICK_PORT = 1,
							AUTONOMOUS_AXIS = 2,
							SHOOT_BUTTON = 1,
							START_SHOOT_FAR_BUTTON = 5, //hood up
							START_SHOOT_CLOSE_BUTTON = 4, //hood down
							OVERRIDE_BEAMBREAK_BUTTON_INTAKE = 6,
							OVERRIDE_BEAMBREAK_BUTTON_BACKDRIVE = 7,
							OVERRIDE_BEAMBREAK_BUTTON = 8, 
							MANUAL_ARM_BUTTON = 9,						
							MANUAL_ARM_MOVE_BUTTON = 3,
							GRAVITY_BUTTON =   11,
							HOOD_DOWN_BUTTON = 10;

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
							BACK_LIMIT_SWITCH = 8,  
							ULTRASONIC_SENSOR = 4,
							BEAM_BREAK_PORT_NEW = 5;
	
	
	// driving
	public static final double RPM = 1800,
							   TICKS_PER_REV = 2764,
							   CIRCUM_TREAD_WHEEL = 19.05,
							   CIRCUM_PNEUMATIC_WHEEL = 20.05,
							   TURNING_ANGULAR_VELOCITY_DEADBAND = .01,  
							   TURNING_ANGLE_DEADBAND = 2;
	
	//shooter speeds
	public static final double SHOOTER_MOTOR_SPEED_CLOSE = .9,
							   SHOOTER_MOTOR_SPEED_FAR = 1,
							   SHOOTER_MOTOR_SPEED_BACKDRIVE = -.5;

	
	// autonomous
    public static final double AUTO_FORWARD_DISTANCE = 300.0, // 300.0
    						   AUTO_BACKWARDS_DISTANCE = 60, //check // 60
    						   AUTO_SHOOTING_LOW_BAR_DISTANCE = 90, // CHECK PLS
    						   AUTO_SHOOTING_LOW_BAR_ANGLE = 45, //GOT TO CHECK THIS ONE
    						   AUTO_SHOOTING_LOW_BAR_TURN_SPEED = 2, // THIS ALSO MUST be checked
    						   AUTO_DISTANCE_FROM_WALL = 20, // 20 in from wall... about 50 cm ish
    						   ULTRASONIC_CONVERSION_FACTOR = 10, // 10 mv per in for the one on sparkFun, check what Chris's ones are
    						   AUTO_SPEED = 0.5 , // change after testing, is this too fast? idk man // .5 p
    						   LOW_BAR_TURN_ANGLE = 60,
    						   CHEVAL_FRISE_ONE = 45,
    						   CHEVAL_FRISE_TWO = 65,
    						   CHEVAL_FRISE_THREE = 265,
    						   SHOOT = 60.0; // 435  
	
    //deployable arm
    public static final double RETRACTED_TO_STRAIGHT_UP = 1600, // probably change this bc it highkey blocks the shot
    							STRAIGHT_UP_TO_INTAKE = 4900,
    							STRAIGHT_UP_TO_GROUND = 4900,
    							STRAIGHT_UP_TO_DEFENSE = 4900,
    							VERTICAL_OFFSET = 2350,
    							ARM_ERROR_TOLERANCE = 200;
    //deployable arm speeds
    public static final double MANUAL_ARM_SPEED = 0.34;
    
    // is arm at intake position?
    public static boolean atIntakePosition = false;
    
    
    public static boolean ballHeld = false;
    
    public static boolean upToSpeed = false;
    
    public static boolean linedUp = false;
    							
			
}
