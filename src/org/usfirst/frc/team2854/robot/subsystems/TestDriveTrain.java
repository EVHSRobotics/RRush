package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.TestDrive;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestDriveTrain extends Subsystem {
    
	Talon leftTalon;
	Talon rightTalon;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public TestDriveTrain(){
		leftTalon = new Talon(RobotMap.Drive.TestMotor.left);
		rightTalon = new Talon(RobotMap.Drive.TestMotor.right);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TestDrive());
    }
    
    public void drive(double left, double right){
    	leftTalon.set(left);
    	rightTalon.set(right);
    }
}

