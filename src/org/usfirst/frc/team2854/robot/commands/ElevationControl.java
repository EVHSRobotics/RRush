package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevationControl extends Command {
public static double elevationSpeed = 0.8;
	
    public ElevationControl() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevationSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevationSpeed = SmartDashboard.getNumber("Elevation Speed", elevationSpeed);
    	//Robot.elevationSystem.moveToBottom(elevationSpeed);
    }

    protected void execute() {
    	//System.out.println("ENCODER VAL: "+ Robot.elevationSystem.returnDistance());
    	if(Robot.oi.getStart()){
    		
        	elevationSpeed = SmartDashboard.getNumber("Elevation Speed", elevationSpeed);
        	System.out.println("NEW SPEED: " + elevationSpeed);
    	}if(Robot.oi.getBack()){
    		//System.out.println("ZERO ENCODER COMMAND CALL");
        	//Scheduler.getInstance().add(new ZeroEncoder());
    		System.out.println("DISABLE SAFETIES");
    		
    	}
    	
    	updateSwitches();
    	
    	if(Robot.oi.getB()){
    		Robot.elevationSystem.moveToBottom(elevationSpeed);
    	}else if(Robot.oi.getY()){
    		Robot.elevationSystem.moveToTop(elevationSpeed);
    	}else if(Math.abs(OI.fixDeadBand(Robot.oi.getRightY(),  OI.Config.DEADBAND)) > 0 ){
    		Robot.elevationSystem.safeMove(elevationSpeed * -Robot.oi.getRightY());
    	}else{
    		Robot.elevationSystem.safeMove(0);
    	}
    	
    	
    }
    
    protected void updateSwitches(){
    	if(Robot.elevationSystem.checkLimitSwitches() != 0){
    		if(Robot.elevationSystem.checkLimitSwitches() == 1){
    			Robot.elevationSystem.setLimitSwitch(1);
    			Robot.elevationSystem.setSpeed(0);
    		}else{
    			Robot.elevationSystem.setLimitSwitch(-1);
    			Robot.elevationSystem.setSpeed(0);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevationSystem.safeMove(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevationSystem.safeMove(0);
    }
}
