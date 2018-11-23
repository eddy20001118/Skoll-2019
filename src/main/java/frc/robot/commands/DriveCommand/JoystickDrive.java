package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        SmartDashboard.putBoolean("Button Status", Robot.m_oi.getXboxBtnAStatus());
        Robot.m_drivetrain.ArcadeDrive(Robot.m_oi.getXboxA1(), Robot.m_oi.getXboxA2(), Robot.m_oi.getXboxBtnAStatus());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.m_drivetrain.stopMotor();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
