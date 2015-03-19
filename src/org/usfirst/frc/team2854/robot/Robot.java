
package org.usfirst.frc.team2854.robot;

import org.usfirst.frc.team2854.robot.commands.FullAutonomous;
import org.usfirst.frc.team2854.robot.commands.MoveForward;
import org.usfirst.frc.team2854.robot.commands.NoAction;
import org.usfirst.frc.team2854.robot.subsystems.Elevation;
import org.usfirst.frc.team2854.robot.subsystems.Intake;
import org.usfirst.frc.team2854.robot.subsystems.TestDriveTrain;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem()
	public static OI oi;
	
	//public static final DriveTrain driveTrain = new DriveTrain();
	
	public static final TestDriveTrain testDriveTrain = new TestDriveTrain();
	public static final Elevation elevationSystem = new Elevation();
	public static final Intake intakeSystem = new Intake();
	
	//options for autonomous 
	SendableChooser autoChooser;
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("INIT BEGIN");
		oi = new OI();
		
		dashboard();
		
		CameraServer server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");
        // instantiate the command used for the autonomous period
        
    }
	
	private void dashboard() {
		SmartDashboard.putNumber("Intake Speed", .8);
		SmartDashboard.putNumber("Elevation Speed", .8);
		SmartDashboard.putNumber("Auto Elevation Speed", .7);
		SmartDashboard.putNumber("Teleop Drive Speed", 1);
		SmartDashboard.putNumber("Autonomous Drive Speed", 1);
		SmartDashboard.putNumber("Left Cal", TestDriveTrain.LEFT_CALIBRATION);
		SmartDashboard.putNumber("Right Cal", TestDriveTrain.RIGHT_CALIBRATION);
		SmartDashboard.putNumber("Forward Time", .5);
		SmartDashboard.putNumber("Auto Rotate Time", .5);
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Do Nothing", new NoAction());
		autoChooser.addObject("Experimental Auto", new FullAutonomous());
		autoChooser.addObject("Forward", new MoveForward());
		SmartDashboard.putData("Autonomous Mode Choooser", autoChooser);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command)autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
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
