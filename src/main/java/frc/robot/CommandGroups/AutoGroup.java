package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveCommand.Navigation;
import frc.robot.commands.DriveCommand.TurnToDegrees;

public class AutoGroup extends CommandGroup {
    public AutoGroup(){
        addSequential(new TurnToDegrees(90));
//        addSequential(new Navigation());
    }
}
