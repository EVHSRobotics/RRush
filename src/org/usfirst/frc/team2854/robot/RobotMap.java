package org.usfirst.frc.team2854.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	/**
	 * Drive Train Config
	 * @author kevin
	 *
	 */
	public static class Drive {
		/**
		 * Talon Motor IDs
		 * @author kevin
		 *
		 */
		public static class Motor {
			public static final int FL = 1;
			public static final int FR = 2;
			public static final int BR = 3;
			public static final int BL = 4;
		}
		/**
		 * Sensor IDs 
		 * @author kevin
		 *
		 */
		public static class Sensor{
			public static final int GYRO = 2;
			public static class ENCODER {
				public static final int A1 = 0;
				public static final int A2 = 1;
			};
		}
		/**
		 * Drive Speeds 
		 * @author kevin
		 *
		 */
		public static class Speed {
			public static final double MAX = 1;
			public static final double FAST = 0.8;
			public static final double MID = 0.5;
			public static final double SLOW = 0.3;
			public static final double OFF = 0;
		}
		/**
		 * Test Drive Talons
		 * 
		 * 
		 */
		public static class TestMotor{
			public static final int FL = 1;
			public static final int FR = 2;
			public static final int BR = 3;
			public static final int BL = 4;
		}
		/**
		 * PID Gains
		 * 
		 */
		public static class PID{
			public static final double P = 0.002; // low value for safety
			public static final double I = 0.0002;
			public static final double D = 0;
		}
	}

}
