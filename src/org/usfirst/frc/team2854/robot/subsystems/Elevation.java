package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.ElevationControl;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevation extends Subsystem {
	//Talon elevationTalon1;
	//Talon elevationTalon2;
	Talon elevationTalon1;
	Talon elevationTalon2;
	Encoder encoder;
	DigitalInput bottomSwitch;
	DigitalInput topSwitch;
	int limitReached = 0; // -1 is bottom, 1 is top
	
	/*800 - height to pick up 1 tote
	2000 - height to stack 1 tote
	3000  max height
	*/
	int topEncoderValue = 3050;

	public Elevation() {
		
		elevationTalon1 = new Talon(RobotMap.Elevation.eMotors.EM1);
		elevationTalon2 = new Talon(RobotMap.Elevation.eMotors.EM2);
		
		//elevationTalon1 = new Talon(1);
		//elevationTalon2 = elevationTalon1;
		encoder = new Encoder(2,3, true, EncodingType.k4X);
		topSwitch = new DigitalInput(RobotMap.Elevation.eSensors.topSwitchPort);
		bottomSwitch = new DigitalInput(
				RobotMap.Elevation.eSensors.bottomSwitchPort);
		
		
		

	}
	
	public double returnDistance(){
		return encoder.get();
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
		/*
		limitReached = value;
		System.out.println("LIMIT SWITCH HIT:"+ value);
		*/
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
				System.out.println("BOTTOM LIMIT REACHED");
			} else if (speed > 0) {
				setSpeed(speed);
				setLimitSwitch(0);
			}
		} else if (limitReached == 1) {
			if(speed > 0){
				setSpeed(0);
				System.out.println("TOP LIMIT REACHED");
			} else if (speed < 0){
				setSpeed(speed);
				setLimitSwitch(0);
			}
		}
	}

	public void zeroEncoder() {
		setSpeed(-.5);
		while (!bottomSwitch.get()) {
			Timer.delay(.01);
		}
		encoder.reset();
		setSpeed(0);
	}
}
