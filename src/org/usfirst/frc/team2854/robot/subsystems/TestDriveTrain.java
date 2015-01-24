package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestDriveTrain extends Subsystem {
    
	Talon leftTalon;
	Talon rightTalon;
	Talon leftBackTalon;
	Talon rightBackTalon;
	
	DigitalInput driveLimitSwitch;
	
	Gyro driveGyro;
	Encoder driveEncoder;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public TestDriveTrain(){
		leftTalon = new Talon(RobotMap.Drive.TestMotor.FL);
		rightTalon = new Talon(RobotMap.Drive.TestMotor.FR);
		leftBackTalon = new Talon(RobotMap.Drive.TestMotor.BL);
		rightBackTalon = new Talon(RobotMap.Drive.TestMotor.BR);
		
		driveLimitSwitch = new DigitalInput(RobotMap.Drive.Sensor.LIMIT_SWITCH);
		driveGyro = new Gyro(RobotMap.Drive.Sensor.GYRO);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new TestDrive());
    }
    
    public void drive(double left, double right){
    	leftTalon.set(left);
    	leftBackTalon.set(left);
    	rightTalon.set(right);
    	rightBackTalon.set(right);
    }
    
    public boolean switchPressed(){
    	return driveLimitSwitch.get();
    }
    
    public void gyroReset(){
    	driveGyro.reset();
    }
    
    public double angle(){
    	return driveGyro.getAngle();
    }
    
    public void encoderReset(){
    	driveEncoder.reset();
    }
    
    public double encoderDistance(){
    	return driveEncoder.getDistance();
    }
}

