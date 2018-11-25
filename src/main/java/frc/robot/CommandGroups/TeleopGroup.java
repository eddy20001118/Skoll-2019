package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveCommand.JoystickDrive;
import frc.robot.commands.UpperCommand.ElevatorCommand;

public class TeleopGroup extends CommandGroup {
    public TeleopGroup(){
        addParallel(new JoystickDrive());
        addParallel(new ElevatorCommand());
    }
}
