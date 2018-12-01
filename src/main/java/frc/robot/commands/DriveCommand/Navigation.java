package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ROS.MainExecutor;
import frc.robot.Robot;

public class Navigation extends Command {
    private MainExecutor mainExecutor;
    public Navigation() {
        mainExecutor = new MainExecutor();
        requires(Robot.m_drivetrain);
    }


    @Override
    public void initialize() {
        mainExecutor.executeNode();
    }

    @Override
    public void execute() {
        Robot.m_drivetrain.nonProtectArcadeDrive(mainExecutor.subscriberNodes.getLinearX(),mainExecutor.subscriberNodes.getAngularZ(), false);
    }

    @Override
    public boolean isFinished() {
        //TODO
        //subscribe isFinished boolean from ros
        return false;
    }

    @Override
    public void end() {
        mainExecutor.shutdownNode();
        Robot.m_drivetrain.stopMotor();
    }

    @Override
    public void interrupted() {
        end();
    }
}
