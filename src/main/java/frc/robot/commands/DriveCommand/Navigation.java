package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Navigation extends Command {
    //private NodeMainExecutor executor = DefaultNodeMainExecutor.newDefault();

    public Navigation() {
        requires(Robot.m_drivetrain);
    }


    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end() {
    }

    @Override
    public void interrupted() {
    }
}
