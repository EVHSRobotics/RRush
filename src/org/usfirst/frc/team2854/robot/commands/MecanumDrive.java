package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.TestDriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MecanumDrive extends Command {

	public  double drivePercentage = 1;
	double leftValue; 
	double rightValue;
	double turnDirection;
	double rotateValue;
	
    public MecanumDrive() {
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
    		TestDriveTrain.LEFT_CALIBRATION = SmartDashboard.getNumber("Left Cal", TestDriveTrain.LEFT_CALIBRATION);
    		TestDriveTrain.RIGHT_CALIBRATION = SmartDashboard.getNumber("Right Cal", TestDriveTrain.RIGHT_CALIBRATION);
    		
        	drivePercentage = SmartDashboard.getNumber("Teleop Drive Speed", drivePercentage);
        	System.out.println("NEW Drive SPEED: " + drivePercentage);
    	}
    	
    	leftValue = OI.Config.SENSITIVITY * OI.fixDeadBand(Robot.oi.getLeftY(), OI.Config.DEADBAND); 
    	//rightValue =  OI.Config.SENSITIVITY * OI.fixDeadBand(Robot.oi.getRightY(), OI.Config.DEADBAND); 
    	
    	rotateValue = OI.Config.SENSITIVITY * OI.fixDeadBand(Robot.oi.getLeftX(), OI.Config.DEADBAND);
    	
    	adjustFullValues();
    	
    	
    	
    	if(Robot.oi.getLeftTrigger() > 0){
    		turnDirection = OI.Config.TURN_SENSITIVITY* -1 *Robot.oi.getLeftTrigger();
    	}else if(Robot.oi.getRightTrigger() > 0){
    		turnDirection =  OI.Config.TURN_SENSITIVITY * Robot.oi.getRightTrigger(); //multiplied by -1 to reverse direction
    	}else{
    		turnDirection = 0;
    	}

        Robot.testDriveTrain.mecDrive(leftValue * drivePercentage, -rotateValue * drivePercentage, turnDirection, 0);//, rightValue * drivePercentage);

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
