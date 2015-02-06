package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TestDrive extends Command {

	public  double drivePercentage = 1;
	double leftValue; 
	double rightValue;
	
    public TestDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.testDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    protected void adjustFullValues(){
    	if(leftValue < -.9){ //full forward doesn't send -1
    		leftValue = -1;
    	}else if(leftValue > .9){
    		leftValue = 1;
    	}
    	if(rightValue < -.9){
    		rightValue = -1;
    	}else if(rightValue > .9){
    		rightValue = 1;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getStart()){
    		
        	drivePercentage = SmartDashboard.getNumber("Teleop Drive Speed", drivePercentage);
        	System.out.println("NEW Drive SPEED: " + drivePercentage);
    	}
    	
    	leftValue = Robot.oi.getLeftY(); 
    	rightValue = Robot.oi.getRightY();
    	
    	adjustFullValues();
    	
    	    	
    	Robot.testDriveTrain.drive(leftValue * drivePercentage, rightValue * drivePercentage);
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
