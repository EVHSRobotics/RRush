package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 double yValue = OI.Config.SENSITIVITY * OI.fixDeadBand(Robot.oi.getLeftY(), OI.Config.DEADBAND); 
         double xValue = OI.Config.SENSITIVITY * OI.fixDeadBand(Robot.oi.getLeftX(), OI.Config.DEADBAND);
         
         if (yValue < 0 ){
             yValue *=1.08;//multiplied by 1.08 because joystick forward doesn't send full signal
         }
         if (yValue < -1){
             yValue = -1;
         }
         
         double twist = .7 * OI.Config.SENSITIVITY * OI.fixDeadBand(Robot.oi.getTriggers(), OI.Config.DEADBAND); //double deadband
         double angle = Robot.driveTrain.gyroGetAngle();
         //System.out.println("A: " +angle);

         if (Robot.oi.getBack()) {
             Robot.driveTrain.gyroReset();;
             System.out.println("Gyro Reset");
         }
         Robot.driveTrain.mecDrive(xValue, -yValue, -twist, -angle);
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
