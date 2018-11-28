package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.DriveCommand.DriveToDistance;
import frc.robot.commands.DriveCommand.Navigation;
import frc.robot.commands.DriveCommand.TurnToDegrees;

public class AutoGroup extends CommandGroup {

    public AutoGroup(Robot.AutonomousMode caseMode) {
        switch (caseMode) {
            case Case1:
                case1();
                break;
            case Case2:
                case2();
                break;
            case Case3:
                case3();
                break;
        }
    }


    public void case1() {
        SmartDashboard.putString("CurrentCase", "case1");
        addSequential(new TurnToDegrees(90));
        addSequential(new DriveToDistance(50));
        addSequential(new Navigation());
    }

    public void case2() {
        SmartDashboard.putString("CurrentCase", "case2");
    }

    public void case3() {
        SmartDashboard.putString("CurrentCase", "case3");
    }
}
