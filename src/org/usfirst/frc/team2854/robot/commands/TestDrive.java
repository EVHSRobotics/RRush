package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestDrive extends Command {

	public static final double PERCENTAGE = 0.5;
	
    public TestDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.testDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftValue = Robot.oi.getLeftY(); 
    	double rightValue = Robot.oi.getRightY();
    	    	
    	Robot.testDriveTrain.drive(leftValue * PERCENTAGE, rightValue * PERCENTAGE);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.testDriveTrain.drive(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.testDriveTrain.drive(0,0);
    }
}
