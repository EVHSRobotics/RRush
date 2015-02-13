package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveForward extends Command {
	double forwardTime;

    public MoveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double speed = SmartDashboard.getNumber("Autonomous Drive Speed");
    	forwardTime = SmartDashboard.getNumber("Forward Time", forwardTime);
    	Robot.testDriveTrain.drive(-speed, -speed);//reversed because forward on robot is - values
    	Timer.delay(forwardTime); 
    	Robot.testDriveTrain.drive(0,0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
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
