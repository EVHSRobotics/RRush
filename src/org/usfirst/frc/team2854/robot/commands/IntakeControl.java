package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeControl extends Command {
public static double intakeSpeed = 1;
	
    public IntakeControl() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intakeSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intakeSpeed = SmartDashboard.getNumber("Intake Speed", intakeSpeed);
    	//Robot.elevationSystem.moveToBottom(elevationSpeed);
    }

    protected void execute() {
    	if(Robot.oi.getStart()){
    		
    		intakeSpeed = SmartDashboard.getNumber("Intake Speed", intakeSpeed);
        	System.out.println("NEW SPEED: " + intakeSpeed);
    	}
    	
    	if(Robot.oi.getX()){
    		Robot.intakeSystem.move(intakeSpeed);
    	}else if(Robot.oi.getA()){
    		Robot.intakeSystem.move(-intakeSpeed);
    	}else{
    		Robot.intakeSystem.move(0);
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSystem.move(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSystem.move(0);
    }
}
