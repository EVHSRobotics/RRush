package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.TestDrive;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestDriveTrain extends PIDSubsystem {

	Talon leftTalon;
	Talon rightTalon;
	Talon leftBackTalon;
	Talon rightBackTalon;
	
	Encoder driveEncoder;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public TestDriveTrain() {
		super("TestDriveTrain",RobotMap.Drive.PID.P,RobotMap.Drive.PID.I,RobotMap.Drive.PID.D);
		
		setAbsoluteTolerance(5);
		
		leftTalon = new Talon(RobotMap.Drive.TestMotor.FL);
		rightTalon = new Talon(RobotMap.Drive.TestMotor.FR);
		leftBackTalon = new Talon(RobotMap.Drive.TestMotor.BL);
		rightBackTalon = new Talon(RobotMap.Drive.TestMotor.BR);
		
		driveEncoder = new Encoder(RobotMap.Drive.Sensor.ENCODER.A1, RobotMap.Drive.Sensor.ENCODER.A2, false, CounterBase.EncodingType.k4X);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TestDrive());
	}

	public void drive(double left, double right) {
		left = Robot.oi.fixDeadBand(left, 0.1);
		right = Robot.oi.fixDeadBand(right, 0.1);
		leftTalon.set(left);
		leftBackTalon.set(left);
		rightTalon.set(-right); // one side is opposite of other
		rightBackTalon.set(-right);
	}
    
    public void enablePid() {
    	enable();
    }
    
    public void disablePid() {
    	disable();
    }
    
    public void reset() {
    	driveEncoder.reset();
    }
    
    public void setDistance(double distance){
    	setSetpoint(distance);
    }
    
    public double getDistance(){
    	return driveEncoder.getDistance();
    }
    
    public double encoderGet(){
    	return driveEncoder.getRaw();
    }
    
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		leftTalon.pidWrite(output);
		leftBackTalon.pidWrite(output);
		rightTalon.pidWrite(-output); // one side is opposite of other
		rightBackTalon.pidWrite(-output);
	}
}
