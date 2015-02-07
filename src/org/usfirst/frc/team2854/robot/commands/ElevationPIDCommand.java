package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevationPIDCommand extends Command {
	int in;
	
    public ElevationPIDCommand(int input) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevationSystem);
        in = input;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevationSystem.eEnable();
    	if(in == 1){
    		Robot.elevationSystem.setDistance(RobotMap.Elevation.setPoint.TOP);
    	}else if(in == -1){
    		Robot.elevationSystem.setDistance(RobotMap.Elevation.setPoint.BOTTOM);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	updateSwitches();
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
    	if(in == 1){
    		if(Robot.elevationSystem.eDistance() >= RobotMap.Elevation.setPoint.TOP || Robot.elevationSystem.checkLimitReached() == 1){
    			return true;
    		}
    	}else if(in == -1){
    		if(Robot.elevationSystem.eDistance() <= RobotMap.Elevation.setPoint.BOTTOM || Robot.elevationSystem.checkLimitReached() == -1){
    			return true;
    		}
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end(){
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
