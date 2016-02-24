package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveShiftingCommand extends Command {
	public final static int SHIFT_HIGH = 1,
							SHIFT_LOW= 2;
	private DriveSubsystem drive;
	private int desiredShift;
	private boolean shifted;
	
	public DriveShiftingCommand (int state) {
		requires(Subsystems.drive);
    	this.desiredShift = state;
    	drive = Subsystems.drive;
	}
	@Override
	
	protected void initialize() {
		shifted=false;
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void execute() {
		if (desiredShift == SHIFT_HIGH) {
    		drive.ShiftHigh();
//    		shifted=true;
		} else if (desiredShift == SHIFT_LOW) {
    		drive.ShiftLow();
//    		shifted=true;
    	}
		shifted= true;
		
    }
		
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return shifted;
	}
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void interrupted() {
		
	}
}

