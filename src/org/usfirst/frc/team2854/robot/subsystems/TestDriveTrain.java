package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.TestDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestDriveTrain extends Subsystem {

	//DigitalInput touchSensor;
	public static double LEFT_CALIBRATION = 1;
	public static double RIGHT_CALIBRATION = .9;
	
	/*
    public Victor backRight;// = RobotMap.driveTrainBackRight;
    public Victor frontRight;// = RobotMap.driveTrainFrontRight;
    public Victor backLeft;// = RobotMap.driveTrainBackLeft;
    public Victor frontLeft;// = RobotMap.driveTrainFrontLeft;
    */

	Talon leftTalon;
	Talon rightTalon;
	Talon leftBackTalon;
	Talon rightBackTalon;

	
	RobotDrive drive;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public TestDriveTrain() {
		
		leftTalon = new Talon(RobotMap.Drive.TestMotor.FL);
		rightTalon = new Talon(RobotMap.Drive.TestMotor.FR);
		leftBackTalon = new Talon(RobotMap.Drive.TestMotor.BL);
		rightBackTalon = new Talon(RobotMap.Drive.TestMotor.BR);
		/*
		backRight = new Victor(RobotMap.Drive.TestMotor.BR);
        frontRight = new Victor(RobotMap.Drive.TestMotor.FR);
        backLeft = new Victor(RobotMap.Drive.TestMotor.BL);
        frontLeft = new Victor(RobotMap.Drive.TestMotor.FL);
        */
		drive = new RobotDrive(leftTalon, leftBackTalon, rightTalon, rightBackTalon );
		
		
		//touchSensor = new DigitalInput(RobotMap.Drive.Sensor.touchSwitchPort);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new TestDrive()); REENABLE THIS
		
		setDefaultCommand(new TestDrive());
	}
	
	public void rotate(double direction){
		System.out.println("DIR:"+direction);
		leftTalon.set(direction);
		leftBackTalon.set(direction);
		rightTalon.set(direction); // one side is opposite of other
		rightBackTalon.set(direction);
	}
	
	public void rotate(double direction, double time){
		System.out.println("DIR:"+direction);
		leftTalon.set(direction);
		leftBackTalon.set(direction);
		rightTalon.set(direction); // one side is opposite of other
		rightBackTalon.set(direction);
		
		Timer.delay(time);
		leftTalon.set(0);
		leftBackTalon.set(0);
		rightTalon.set(0); // one side is opposite of other
		rightBackTalon.set(0);
	}
	/*

	public void drive(double left, double right) {
		left = OI.fixDeadBand(left, OI.Config.DEADBAND)  * LEFT_CALIBRATION;
		right = OI.fixDeadBand(right, OI.Config.DEADBAND) * RIGHT_CALIBRATION;
		
		//System.out.println("R:"+ right +" L:"+left);

		leftTalon.set(-left);
		leftBackTalon.set(-left);
		rightTalon.set(right); // one side is opposite of other
		rightBackTalon.set(right);
		
	}*/
	
	
	public void drive(double speed, double rotate) {
		speed = OI.fixDeadBand(speed, OI.Config.DEADBAND);
		
		//System.out.println("R:"+ right +" L:"+left);
		
		/*
		leftTalon.set(-speed * LEFT_CALIBRATION);
		leftBackTalon.set(-speed * LEFT_CALIBRATION);
		rightTalon.set(speed * RIGHT_CALIBRATION); // one side is opposite of other
		rightBackTalon.set(speed * RIGHT_CALIBRATION);
		*/
		
		if (speed > 0){
			rotate = -rotate;
		}
		
		drive.arcadeDrive(-speed, -rotate*OI.Config.TURN_SENSITIVITY, true);
		
	}
	
	
	
	
	public void mecDrive(double y, double x, double t, double a){
	    double temp = y*Math.cos(Math.toRadians(a)) - x*Math.sin(Math.toRadians(a));
	    x = y*Math.sin(Math.toRadians(a)) + x*Math.cos(Math.toRadians(a));
	    y = temp;

	    
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
	    if(Math.abs(x+y+t) > 0){
	        System.out.println("DriveX: " +x + " DriveY: " + y);
	        System.out.println("FL:"+front_left+" FR:" + front_right+ " BL:"+back_left+"BR:" + back_right);
	    }
	    /*
	    frontLeft.set(-front_left); //inverts motor
	    frontRight.set(front_right);
	    backRight.set(back_right);
	    backLeft.set(-back_left); //inverts motor
	    */
	}
}
