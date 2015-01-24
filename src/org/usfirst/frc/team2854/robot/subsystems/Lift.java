package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
	
	Talon liftTalon;
	Encoder liftEncoder;
	
	
	public Lift(){
		liftTalon = new Talon(RobotMap.Lift.TALON);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void move(double speed){
		liftTalon.set(speed);
	}
	
	public void encoderReset(){
		liftEncoder.reset();
	}
	
	public double encoderDistance(){
		return liftEncoder.getDistance();
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

