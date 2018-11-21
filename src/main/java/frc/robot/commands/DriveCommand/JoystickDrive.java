package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class JoystickDrive extends Command {

    public JoystickDrive() {
        requires(Robot.m_drivetrain);
    }


    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
