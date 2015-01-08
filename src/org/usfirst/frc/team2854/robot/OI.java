package org.usfirst.frc.team2854.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static class Config {
		public static final double DEADBAND = .3;
	    public static final double SENSITIVITY = 1;
	}
	
	
	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public Joystick controller;

	public OI() {
		controller = new Joystick(1);
	}

	public Joystick getJoystick() {
		return controller;
	}

	public double getLeftX() {
		return controller.getRawAxis(1);
	}

	public double getLeftY() {
		return controller.getRawAxis(2);
	}

	public double getRightX() {
		return controller.getRawAxis(4);
	}

	public double getRightY() {
		return controller.getRawAxis(5);
	}

	public double getTriggers() {
		return controller.getRawAxis(3);
	}

	public boolean getA() {
		return controller.getRawButton(1);
	}

	public boolean getB() {
		return controller.getRawButton(2);
	}

	public boolean getX() {
		return controller.getRawButton(3);
	}

	public boolean getY() {
		return controller.getRawButton(4);
	}

	public boolean getLB() {
		return controller.getRawButton(5);
	}

	public boolean getRB() {
		return controller.getRawButton(6);
	}

	public boolean getBack() {
		return controller.getRawButton(7);
	}

	public boolean getStart() {
		return controller.getRawButton(8);
	}

	// Axis indexes:
	// 1 - LeftX
	// 2 - LeftY
	// 3 - Triggers (Each trigger = 0 to 1, axis value = right - left)
	// 4 - RightX
	// 5 - RightY
	// 6 - DPad Left/Right
	// double axisValue = mXboxController.getRawAxis(2); // Where "2" is the
	// index of the Y axis on the left stick (see above)

	// Button indexes:
	// 1 A
	// 2 B
	// 3 X
	// 4 Y
	// 5 LB
	// 6 RB
	// 7 back
	// 8 start
	// 9 left stick press
	// 10 right stick press
	//
	// Move Robot
	// X-Movement - Axis 1
	// Y-Movement - Axis 2
	// Twist - Axis 3
	//
	// Shooter & Arm
	// Trigger - RB (6)
	//
	// Move Arm
	// Picking up - A (1)
	// Passing - B (2)
	// Shooting - X (3)
	// 2nd angle shooting? - Y (4)
	//
	// Safety
	// Reset gyro (or disable) - Back
	// Start Position - Start
	
	public static double fixDeadBand(double speed, double deadBand) {
        return (Math.abs(speed) > deadBand ? speed : 0.0);
    }
}
