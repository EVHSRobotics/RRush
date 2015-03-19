package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.IntakeControl;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	Talon leftIntake;
	Talon rightIntake;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Intake(){
		leftIntake = new Talon(RobotMap.Intake.left);
		rightIntake = new Talon(RobotMap.Intake.right);
	}
	
	public void move(double speed){
		leftIntake.set(speed);
		rightIntake.set(-speed); //inverted to make them spin in opposite directions
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeControl());
    }
}

