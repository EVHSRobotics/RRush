package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.LimitSwitchTest;
import org.usfirst.frc.team2854.robot.commands.PIDTestCommand;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDTest extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Talon PIDTestTalon;
	Encoder PIDTestEncoder;
	DigitalInput PIDLimitSwitch;
	
	public PIDTest(){
		super("PIDTest",RobotMap.Drive.PID.P,RobotMap.Drive.PID.I,RobotMap.Drive.PID.D);
		
		setAbsoluteTolerance(5);
		
		PIDTestTalon = new Talon(4);
		PIDTestEncoder = new Encoder(RobotMap.Drive.Sensor.ENCODER.A1, RobotMap.Drive.Sensor.ENCODER.A2, false, CounterBase.EncodingType.k4X);
		PIDLimitSwitch = new DigitalInput(RobotMap.Drive.Sensor.LIMIT_SWITCH);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LimitSwitchTest());
    }
    
    public void drive(double power){
    	PIDTestTalon.set(power);
    }
    
    public void enablePid() {
    	enable();
    }
    
    public void disablePid() {
    	disable();
    }
    
    public void reset() {
    	PIDTestEncoder.reset();
    }
    
    public double getDistance(){
    	return PIDTestEncoder.getDistance();
    }
    
    public double encoderGet(){
    	return PIDTestEncoder.getRaw();
    }
    
    public boolean isSwitchOn(){
    	return PIDLimitSwitch.get();
    }
    
    public void setDistance(double distance){
    	setSetpoint(distance);
    }

	@Override
	protected double returnPIDInput() {
		return getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		//System.out.println("encoder distance: " + getDistance());
		PIDTestTalon.pidWrite(-output);
	}
}

