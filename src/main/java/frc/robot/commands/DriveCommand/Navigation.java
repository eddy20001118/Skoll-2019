package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ROS.Executor;
import frc.robot.ROS.Nodes.MainNode;
import frc.robot.Robot;

public class Navigation extends Command {
    private Executor executor;
    public Navigation() {
        executor = new Executor(new MainNode("mainNode"));
        requires(Robot.m_drivetrain);
    }


    @Override
    public void initialize() {
        executor.excuteNode();
    }

    @Override
    public void execute() {
//        Robot.m_drivetrain.ArcadeDrive(rosfrc.getLinearX(), rosfrc.getAngularZ(), false);
    }

    @Override
    public boolean isFinished() {
        //TODO
        //subscribe isFinished boolean from ros
        return false;
    }

    @Override
    public void end() {
        executor.shutdownNode();
        Robot.m_drivetrain.stopMotor();
    }

    @Override
    public void interrupted() {
        end();
    }
}
