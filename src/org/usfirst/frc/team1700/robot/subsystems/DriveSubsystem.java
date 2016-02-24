package org.usfirst.frc.team1700.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;

public class DriveSubsystem extends Subsystem {
	
	private HalfDriveSubsystem left;
	private HalfDriveSubsystem right;
	private static final double JOY_DEADBAND = 0.15;
	private OI oi;
	DoubleSolenoid solenoid;
	private AHRS navX;


	
	public DriveSubsystem() {	
		super();
		navX = new AHRS(SPI.Port.kMXP); 
		left = new HalfDriveSubsystem(RobotMap.LEFT_TALON_ID_2, RobotMap.LEFT_TALON__ID_1, RobotMap.LEFT_TALON_ID_3);
		right = new HalfDriveSubsystem(RobotMap.RIGHT_TALON_ID_3, RobotMap.RIGHT_TALON_ID_1, RobotMap.RIGHT_TALON_ID_2);
		solenoid = new DoubleSolenoid(RobotMap.DRIVE_SOLENOID_ONE_PORT, RobotMap.DRIVE_SOLENOID_TWO_PORT);

	}
	
	public void navX () {
		//System.out.println(navX.getAngle());
	}


//various ways we can drive -- auto is tank, teleop is cheesy or arcade
	public void driveTank (double speedLeft, double speedRight) { // tank drive
			if(speedLeft > JOY_DEADBAND || speedLeft < -JOY_DEADBAND) { // maybe take out the deadband later in life
				left.setSpeed(speedLeft);
				//System.out.println(speedLeft);
			} else {
				left.setSpeed(0);
			}
			if(speedRight > JOY_DEADBAND || speedRight < -JOY_DEADBAND){ 
				right.setSpeed(-speedRight);
				//System.out.println(speedRight);
			} else {
				right.setSpeed(0);
			}
			navX();
		}
	
	public void driveCheesy (double throttle, double turnRate){ // cheesy drive, see 254 website for more info
		double leftOutput, rightOutput, angularPower, linearPower;
		// Linear power controls forward motion.
		linearPower = throttle;
		// Angular powers controls the ratio between the wheels. 
		angularPower = Math.abs(throttle) * turnRate;
		
		leftOutput = rightOutput = linearPower;
		leftOutput += angularPower;
		rightOutput -= angularPower;
		
		driveTank (leftOutput, rightOutput);		
 	}
	
	public void driveArcade (double throttle, double turnRate) {
		double leftOutput, rightOutput;
		leftOutput = throttle - turnRate;
		rightOutput = throttle + turnRate;
		
		driveTank (leftOutput, rightOutput);
		//System.out.println("The left drive enc is:" + left.getEncReading());
		
		
	}

//	public void EnableAutoShifting (boolean on) {
//			boolean AUTOSHIFTING = true;
//	}

//	public void autoShiftHigh() {
//		if (left.getEncVelocity() > RobotMap.RPM)
//			solenoid.set(DoubleSolenoid.Value.kForward);
//	}
//	
//	// If the motors are slow enough, shift down a gear. 
//	public void autoShiftLow() {
//		if (left.getEncVelocity() < RobotMap.RPM)
//			solenoid.set(DoubleSolenoid.Value.kReverse);
//	}
	
	public void ShiftHigh() { // shift into high gear
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void ShiftLow() { // shift into low gear, call the shiftlow fxn from the half drive subsystem for ease of use
		solenoid.set(DoubleSolenoid.Value.kForward);

	}
	
	public void zeroEncoders() { // this is self explanatory
		left.zeroEncoderHalfDrive();
		right.zeroEncoderHalfDrive();
	}

	public double ticksPerInch () {
		return (RobotMap.TICKS_PER_REV / RobotMap.CIRCUM_PNEUMATIC_WHEEL);
	}
	
	public double getLeftDistance() { // changing encoder reading into inches traveled for usefulness 
		return left.getEncReading() / ticksPerInch();
	}
	
	public double getRightDistance() { // same as above
		return right.getEncReading() / ticksPerInch();
	}
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand()); // drive command is always active
    }
	

	public void stop() { // this is obvious
		left.setSpeed(0);
		right.setSpeed(0);
	}
}
