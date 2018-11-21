package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveCommand.JoystickDrive;

public class TeleopGroup extends CommandGroup {
    public TeleopGroup(){
        addParallel(new JoystickDrive());
    }
}
