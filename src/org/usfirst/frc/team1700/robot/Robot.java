
package org.usfirst.frc.team1700.robot;
import java.lang.Math;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;

import org.usfirst.frc.team1700.robot.commands.Autonomous;
import org.usfirst.frc.team1700.robot.subsystems.DeployableArmSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.IntakeSubsystem;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;


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
	private DeployableArmSubsystem arm;
	private Autonomous autonomous;

	
    Command autonomousCommand;
    SendableChooser chooser;
    private AHRS navX;
//    public double X_initial, Y_initial, theta_initial, dispX_field, dispY_field, theta, loopTime,
//    accX_robot_navX_meas, accX_robot_RRA_meas, accY_robot_navX_meas, accY_robot_RRA_meas,
//    alpha_navX_meas, motorCmdLeft, motorCmdRight, RRA_weight, navX_acc_weight, accX_robot_prev,
//    velX_robot_prev, accX_robot, velX_robot, dispX_robot, accY_robot_prev, velY_robot_prev, accY_robot,
//    velY_robot, dispY_robot, alpha_prev, omega_prev, alpha, omega, velX_field, accX_field,
//    velY_field, accY_field, wheelRPM_robot_left_enc_meas, wheelRPM_robot_right_enc_meas, 
//    dispY_robot_left_enc_meas_prev, dispY_robot_right_enc_meas_prev, dispY_robot_left_enc_meas, 
//    dispY_robot_right_enc_meas, slip_ratio_left, slip_ratio_right, wheel_diameter;
    
    public double theta, omega;

    public Robot () {
		drive = Subsystems.drive;
		arm = Subsystems.deployableArm;
		Subsystems.robot = this;
		navX = new AHRS(SPI.Port.kMXP); 
		//what do I assign RRA and navX to?
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	drive.zeroEncoders();
    	autonomous = new Autonomous();
    	
        oi = new OI();
    	subsystems = new Subsystems();
    	
    	arm.enable();
    	arm.calibrate();

    	//System.out.println(e + " " + e.getStackTrace());
    	
        // instantiate the command used for the autonomous period
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
         // schedule the autonomous command (example)
        if (autonomous != null) autonomous.start();
        arm.calibrate();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
//    	arm.calibrate();
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
    
//    Notes:
//    	dispX, velX, and accX should all be approximately 0 (except during turns/collisions)
//    	Need to check if angled surfaces causes additional errors
//    	Need to check in navX/RRA buffer sensor readings and average over time
//    	What do we need for the roborio accelerometer in robotInit
//    	Use raw data for navX and ? for RR
//
//    	To implement:
//    	Moving average filter, Long-Short Term Filter, Encoder/Motor as sanity check, AccX as sanity check
//    	TO DO items for Christine
    public void localizationFilter() {
    	theta = navX.getAngle();
    }
}
