package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ROS.ROSFRC;
import frc.robot.Robot;

public class Navigation extends Command {
    private ROSFRC rosfrc;

    public Navigation() {
        rosfrc = new ROSFRC();
        requires(Robot.m_drivetrain);
    }


    @Override
    public void initialize() {
        //excuteNode() will run a loop, remember to put it here
        rosfrc.executeNode();
    }

    @Override
    public void execute() {
        Robot.m_drivetrain.ArcadeDrive(rosfrc.getLinearX(), rosfrc.getAngularZ(), false);
    }

    @Override
    public boolean isFinished() {
        //TODO
        //subscribe isFinished boolean from ros
        return false;
    }

    @Override
    public void end() {
        rosfrc.shutdownNode();
        Robot.m_drivetrain.stopMotor();
    }

    @Override
    public void interrupted() {
        end();
    }
}
