package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class JoystickDrive extends Command {
    public JoystickDrive() {
        SmartDashboard.putBoolean("Drivetrain protect", Robot.m_oi.getDrivetrainProtect());
        requires(Robot.m_drivetrain);
    }


    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        SmartDashboard.putBoolean("Drivetrain protect", Robot.m_oi.getDrivetrainProtect());
        if (Robot.m_oi.getDrivetrainProtect()) {
            Robot.m_drivetrain.protectedArcadeDrive(Robot.m_oi.getXboxA1(), Robot.m_oi.getXboxA2(), Robot.m_oi.getDrivetrainRevert());
        } else {
            Robot.m_drivetrain.nonProtectArcadeDrive(Robot.m_oi.getXboxA1(), Robot.m_oi.getXboxA2(), Robot.m_oi.getDrivetrainRevert());
        }
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
