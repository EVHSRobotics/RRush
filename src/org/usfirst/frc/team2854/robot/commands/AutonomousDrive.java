package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDrive extends Command {
	
	int choice; //moves differently depending on the input
	/*
	 * 1 - Go forward until LimitSwitch pressed
	 * 2 - Turn right 90 degrees
	 * 3 - Turn left 90 degrees
	 * 4 - Go forward until robot is in auto zone
	 * 
	 */
	boolean finished = false;
	
    public AutonomousDrive(int input) {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.testDriveTrain);
    	choice = input;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.testDriveTrain.drive(0,0);
    	Robot.testDriveTrain.gyroReset();
    	
    	if(choice == 1){ //go forward
    		Robot.testDriveTrain.drive(RobotMap.Drive.Speed.MID, RobotMap.Drive.Speed.MID);	
    	}else if(choice == 2){ //turn right
    		Robot.testDriveTrain.drive(RobotMap.Drive.Speed.SLOW,-RobotMap.Drive.Speed.SLOW);
    	}else if(choice == 3){ //turn left
    		Robot.testDriveTrain.drive(-RobotMap.Drive.Speed.SLOW,RobotMap.Drive.Speed.SLOW);
    	}else if(choice == 4){ //go into auto zone
    		Robot.testDriveTrain.drive(RobotMap.Drive.Speed.FAST, RobotMap.Drive.Speed.FAST);
    	}
    }	

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(choice == 1){
    		if(Robot.testDriveTrain.switchPressed()){
    			Robot.testDriveTrain.drive(0,0);
    			finished = true;
    		}
    	}else if(choice == 2){
    		if((Robot.testDriveTrain.angle()%360 - 90)*(Robot.testDriveTrain.angle()%360 - 90) < 3 ){ //if angle within three degrees of 90
    			Robot.testDriveTrain.drive(0,0);
    			finished = true;
    		}
    	}else if(choice == 3){
    		if((Robot.testDriveTrain.angle()%360 - (-90) )*(Robot.testDriveTrain.angle()%360 - (-90) ) < 3 ){ //don't know if cw or ccw is positive, should be fixed
    			Robot.testDriveTrain.drive(0,0);
    			finished = true;
    		}
    	}else if(choice == 4){
    		if(Robot.testDriveTrain.encoderDistance() > 50){ //keep going until distance is reached, 50 should be changed 
    			Robot.testDriveTrain.drive(0,0);
    			finished = true;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
