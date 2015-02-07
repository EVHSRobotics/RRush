package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.ElevationControl;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevation extends PIDSubsystem {
	//Talon elevationTalon1;
	//Talon elevationTalon2;
	Talon elevationTalon1;
	Talon elevationTalon2;
	
	Encoder elevationEncoder;
	
	DigitalInput bottomSwitch;
	DigitalInput topSwitch;
	
	
	int limitReached = 0; // -1 is bottom, 1 is top
	boolean pidEnabled = false;

	public Elevation() {
		super("Elevation",RobotMap.Elevation.ePID.P,RobotMap.Elevation.ePID.I,RobotMap.Elevation.ePID.D);
		/*
		elevationTalon1 = new Talon(RobotMap.Elevation.eMotors.EM1);
		elevationTalon2 = new Talon(RobotMap.Elevation.eMotors.EM2);
		*/
		setAbsoluteTolerance(5);
		
		
		elevationTalon1 = new Talon(1);
		elevationTalon2 = elevationTalon1;
		elevationEncoder = new Encoder(RobotMap.Elevation.eSensors.channel1,RobotMap.Elevation.eSensors.channel2, false, EncodingType.k4X);
		topSwitch = new DigitalInput(RobotMap.Elevation.eSensors.topSwitchPort);
		bottomSwitch = new DigitalInput(
				RobotMap.Elevation.eSensors.bottomSwitchPort);
		
		

	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ElevationControl());
	}

	public int checkLimitSwitches() {
		if (bottomSwitch.get()) {
			return -1;
		} else if (topSwitch.get()) {
			return 1;
		} else {
			return 0;
		}
	}

	public void setLimitSwitch(int value) {
		limitReached = value;
	}

	public int checkLimitReached() {
		return limitReached;
	}

	public void setSpeed(double speed) {
		elevationTalon1.set(speed);
		elevationTalon2.set(speed);
	}

	public void safeMove(double speed) {
		if (limitReached == 0) {
			setSpeed(speed);
		} else if (limitReached == -1) {
			if (speed < 0) {
				setSpeed(0);
			} else {
				setSpeed(speed);
				setLimitSwitch(0);
			}
		} else if (limitReached == 1) {
			if(speed > 0){
				setSpeed(0);
			} else{
				setSpeed(speed);
				setLimitSwitch(0);
			}
		}
	}

	public void zeroEncoder() {
		setSpeed(-.5);
		while (bottomSwitch.get()) {
			Timer.delay(10);
		}
		//encoder.reset();
		setSpeed(0);
	}
	
	public void reset(){
		elevationEncoder.reset();
	}
	
	public double eDistance(){
		return elevationEncoder.getDistance();
	}
	
	public void eEnable(){
		enable();
		pidEnabled = true;
	}
	
	public void eDisable(){
		disable();
		pidEnabled = false;
	}
	
	public boolean isEnabled(){
		return pidEnabled;
	}
	
	public void setDistance(double x){
		setSetpoint(x);
	}
	
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return eDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		if(output > 0){
			checkLimitSwitches();
			if(checkLimitReached() != 1){
				elevationTalon1.pidWrite(output);
				elevationTalon2.pidWrite(output);
			}
		}else if(output < 0){
			checkLimitSwitches();
			if(checkLimitReached() != -1){
				elevationTalon1.pidWrite(output);
				elevationTalon2.pidWrite(output);
			}
		}
	}
}
