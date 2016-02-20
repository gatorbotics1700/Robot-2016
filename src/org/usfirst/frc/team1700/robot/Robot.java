
package org.usfirst.frc.team1700.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team1700.robot.commands.Autonomous;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private static Subsystems subsystems; // collection of all subsystems	 
	public static OI oi;
	private DriveSubsystem drive;
	private IntakeSubsystem intake;
<<<<<<< HEAD
	private Autonomous autonomous;
=======

	
    Command autonomousCommand;
    SendableChooser chooser;
    private AHRS navX;
    private BuiltInAccelerometer RRA;
>>>>>>> 67a7e0e45604fc2f475db48c5cd9e37e206ddb98

    public Robot () {
		drive = Subsystems.drive;
		intake = Subsystems.intake;
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	drive.zeroEncoders();
    	autonomous = new Autonomous();
    	
    	try {
        	oi = new OI();
    		subsystems = new Subsystems();
    	} catch (Error e){
    		System.out.println(e + " " + e.getStackTrace());
    	}
        // instantiate the command used for the autonomous period
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomous != null) autonomous.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
