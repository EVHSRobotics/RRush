package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDTestCommand extends Command {

    public PIDTestCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.testPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.testPID.reset();
    	//Robot.testPID.enablePid();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Robot.oi.getA()) {
//    		Robot.testPID.setDistance(200);
//    	}
//    	
//    	if(Robot.oi.getB()) {
//    		Robot.testPID.setDistance(-200);
//    	}
    	
    	Robot.testPID.regularDrive(Robot.oi.getRightY() * 0.3);
    	System.out.println("encoder distance: " + Robot.testPID.getDistance() + "    encoder raw: " + Robot.testPID.encoderGet());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.testPID.disablePid();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.testPID.disablePid();
    	Robot.testPID.reset(); // allows us to enable and disable code without having to re-deploy to update setDistance
    	Robot.testPID.regularDrive(0);
    }
}
