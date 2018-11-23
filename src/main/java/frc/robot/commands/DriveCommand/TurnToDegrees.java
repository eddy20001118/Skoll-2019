package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TurnToDegrees extends Command {
    private double setpoint;
    public TurnToDegrees(double TargetAngle) {
        requires(Robot.m_drivetrain);
        setpoint = TargetAngle;
    }

    @Override
    protected void initialize() {
        Robot.m_drivetrain.setSetpoint(setpoint);
        Robot.m_drivetrain.setAbsoluteTolerance(2.0);
        Robot.m_drivetrain.setInputRange(-180.0,180.0);
        Robot.m_drivetrain.setOutputRange(-0.5,0.5);
        Robot.m_drivetrain.enable();
        SmartDashboard.putData("DriveTrainPID",Robot.m_drivetrain.getPIDController());
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.m_drivetrain.getPosition() - setpoint) < 2.0;
    }

    @Override
    protected void end() {
        Robot.m_drivetrain.disable();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
