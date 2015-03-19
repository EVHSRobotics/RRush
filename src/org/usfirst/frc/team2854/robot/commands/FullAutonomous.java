package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FullAutonomous extends Command {
	public static double elevationSpeed = 0.8;
	double spinTime = .5;
	
    public FullAutonomous() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevationSpeed = SmartDashboard.getNumber("Auto Elevation Speed", elevationSpeed);
    	spinTime = SmartDashboard.getNumber("Auto Rotate Time", spinTime);
    	
    	//double speed = SmartDashboard.getNumber("Autonomous Drive Speed");
    	//Robot.testDriveTrain.drive(speed, speed);
    	Robot.elevationSystem.moveToBottom(elevationSpeed);
    	Robot.elevationSystem.moveToTop(elevationSpeed);
    	
    	Robot.testDriveTrain.rotate(.8, spinTime);
    	
    	//double speed = SmartDashboard.getNumber("Autonomous Drive Speed");
    	//Robot.testDriveTrain.drive(speed, speed);
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
