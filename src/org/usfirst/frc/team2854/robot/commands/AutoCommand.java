package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCommand extends CommandGroup {
    
    public  AutoCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	
//    	addSequential(new AutonomousDrive(1)); //drive until limit switch pressed
//    	addSequential(new MoveLift(30)); //lift to height (input)
//    	addSequential(new AutonomousDrive(2)); //turn 90 degrees
//    	addSequential(new AutonomousDrive(4)); //drive into auto zone
    	System.out.println("Autonomous run");
    	addSequential(new AutonomousDrive(5)); //test autonomous
    	
    }
}
