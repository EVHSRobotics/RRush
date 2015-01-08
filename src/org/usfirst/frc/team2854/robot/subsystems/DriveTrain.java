package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.Drive;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	Talon DriveFLMotor;
	Talon DriveFRMotor;
	Talon DriveBRMotor;
	Talon DriveBLMotor;
	
	Gyro driveGyro;
	
	public DriveTrain() {
		DriveFLMotor = new Talon(RobotMap.Drive.Motor.FL);
		DriveFRMotor = new Talon(RobotMap.Drive.Motor.FR);
		DriveBRMotor = new Talon(RobotMap.Drive.Motor.BR);
		DriveBLMotor = new Talon(RobotMap.Drive.Motor.BL);
		
		driveGyro = new Gyro(RobotMap.Drive.Sensor.GYRO);
	}
	
	public void mecDrive(double x, double y, double t, double a){
	    double temp = y*Math.cos(Math.toRadians(a)) - x*Math.sin(Math.toRadians(a));
	    x = y*Math.sin(Math.toRadians(a)) + x*Math.cos(Math.toRadians(a));
	    y = temp;
	    if(Math.abs(x+y+t) > 0){
	        System.out.println("DriveX: " +x + " DriveY: " + y);
	    }
	    
	    double front_left = y + t + x;
	    double front_right = y - t - x;
	    double back_left = y + t - x;
	    double back_right = y - t + x;
	    
	    double max = Math.abs(front_left);
	    if (Math.abs(front_right)>max) {
	        max = Math.abs(front_right);
	    }
	    if (Math.abs(back_left)>max){
	        max=Math.abs(back_left);
	    }
	    if (Math.abs(back_right)>max) {
	        max=Math.abs(back_right);
	    }
	    if (max>1){
	      front_left/=max; front_right/=max; back_left/=max; back_right/=max;

	    }
	    DriveFLMotor.set(-front_left); //inverts motor
	    DriveFRMotor.set(front_right);
	    DriveBRMotor.set(back_right);
	    DriveBLMotor.set(-back_left); //inverts motor
	}
	
	public double gyroGetAngle() {
		return driveGyro.getAngle();
	}
	
	public void gyroReset() {
		driveGyro.reset();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
}

