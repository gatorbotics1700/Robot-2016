package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousArm extends Command {

	public final static int DESIRED_POSITION_RETRACTED = 1,
			DESIRED_POSITION_STRAIGHT_UP = 2,
			 DESIRED_POSITION_DEFENSE = 3;
		private DeployableArmSubsystem arm;
		private int desiredPosition;

		
		 public AutonomousArm(int position) {
		        // Use requires() here to declare subsystem dependencies
		        // eg. requires(chassis);
		    	requires(Subsystems.deployableArm);
		    	this.desiredPosition = position;
		    	arm = Subsystems.deployableArm;
		    }

	@Override
	protected void initialize() {
    	arm.enable();
		
	}

	@Override
	protected void execute() {
		 arm.PIDSituation(desiredPosition);	
	}

	@Override
	protected boolean isFinished() {
		if (desiredPosition == 3 && (arm.getEncPos() > 4900)) {
			return true;
		} else if (desiredPosition == 1 && arm.getEncPos() < 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		arm.gravity();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
