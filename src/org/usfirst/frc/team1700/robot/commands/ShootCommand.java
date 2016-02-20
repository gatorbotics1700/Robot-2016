package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCommand extends Command {
	public static final int SHOOT = 1,
							BACKDRIVE = 2;
	private IntakeSubsystem intake;
	private ShooterSubsystem shooter;
	private int desiredAction;
	private OI oi;
	 
    public ShootCommand(int desiredAction) {
    	super();
 		this.oi = Robot.oi;
 		requires(Subsystems.intake);
 		requires(Subsystems.shooter);
        intake = Subsystems.intake;
        shooter = Subsystems.shooter;
        this.desiredAction = desiredAction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		shooter.shootClose();
		if (desiredAction == SHOOT) {
	    	if (shooter.readyToShoot()) {
				intake.moveBallToShootingPosition();
			} else {
				intake.stopMotors();
			}
		} else if (desiredAction == BACKDRIVE) {
			shooter.backdrive();
			intake.backDrive();
		} else {
			intake.stopMotors();
		}
    }    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.stopMotors();
    	shooter.setSpeedToZero();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		intake.stopMotors();
    	shooter.setSpeedToZero();
    }
}
