package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveCommand.Navigation;

public class AutoGroup extends CommandGroup {
    public AutoGroup(){
        addSequential(new Navigation());
    }
}
