package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.TestDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestDriveTrain extends Subsystem {

	//DigitalInput touchSensor;
	public static double LEFT_CALIBRATION = 1;
	public static double RIGHT_CALIBRATION = .9;
	
	
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
		
		drive = new RobotDrive(leftTalon, leftBackTalon, rightTalon, rightBackTalon );
		
		
		//touchSensor = new DigitalInput(RobotMap.Drive.Sensor.touchSwitchPort);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TestDrive());
	}
	
	public void rotate(double direction){
		System.out.println("DIR:"+direction);
		leftTalon.set(direction);
		leftBackTalon.set(direction);
		rightTalon.set(direction); // one side is opposite of other
		rightBackTalon.set(direction);
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
		rightBackTalon.set(speed * RIGHT_CALIBRATION);*/
		if (speed > 0){
			rotate = -rotate;
		}
		
		drive.arcadeDrive(-speed, -rotate*OI.Config.TURN_SENSITIVITY, true);
		
	}
}
