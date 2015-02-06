package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.TestDrive;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestDriveTrain extends Subsystem {

	//DigitalInput touchSensor;
	Talon leftTalon;
	Talon rightTalon;
	Talon leftBackTalon;
	Talon rightBackTalon;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public TestDriveTrain() {
		/*
		leftTalon = new Talon(RobotMap.Drive.TestMotor.FL);
		rightTalon = new Talon(RobotMap.Drive.TestMotor.FR);
		leftBackTalon = new Talon(RobotMap.Drive.TestMotor.BL);
		rightBackTalon = new Talon(RobotMap.Drive.TestMotor.BR);
		*/
		//touchSensor = new DigitalInput(RobotMap.Drive.Sensor.touchSwitchPort);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TestDrive());
	}

	public void drive(double left, double right) {
		left = Robot.oi.fixDeadBand(left, 0.1);
		right = Robot.oi.fixDeadBand(right, 0.1);
		
		System.out.println("R:"+ right +" L:"+left);
		/*
		leftTalon.set(-left);
		leftBackTalon.set(-left);
		rightTalon.set(right); // one side is opposite of other
		rightBackTalon.set(right);
		*/
	}
}
