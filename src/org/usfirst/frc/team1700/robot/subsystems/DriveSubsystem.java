package org.usfirst.frc.team1700.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	
	private List<Talon> rightTalons;
	private List<Talon> leftTalons;




//	private static final double JOY_DEADBAND = 0.05;
	
	
	/** actual driving stuff happens now */
	
	public DriveSubsystem() {
		this.rightTalons = new ArrayList<Talon>();
		this.leftTalons = new ArrayList<Talon>();
		this.rightTalons.add(new Talon(RobotMap.RIGHT_TALON_ID_1));
		this.rightTalons.add(new Talon(RobotMap.RIGHT_TALON_ID_2));
		this.rightTalons.add(new Talon(RobotMap.RIGHT_TALON_ID_3));
		this.leftTalons.add(new Talon(RobotMap.LEFT_TALON_ID_1));
		this.leftTalons.add(new Talon(RobotMap.LEFT_TALON_ID_2));
		this.leftTalons.add(new Talon(RobotMap.LEFT_TALON_ID_3));



	}
	
	public void DriveTank (double speedLeft, double speedRight) {
		for (Talon leftTalon: this.leftTalons) {
			leftTalon.set(speedLeft);	
		}
		for (Talon rightTalon: this.rightTalons){
			rightTalon.set(speedRight);
		}
		
		
	}
	public void DriveCheesy (double throttle, double turnRate){
		double leftOutput, rightOutput, angularPower, linearPower;
		linearPower = throttle;
		angularPower = Math.abs(throttle) * turnRate;
		
		leftOutput = rightOutput = linearPower;
		leftOutput += angularPower;
		rightOutput -= angularPower;
		
		DriveTank (leftOutput, rightOutput);
		
	}
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand()); // drive command is always active
    }
	
	
}
