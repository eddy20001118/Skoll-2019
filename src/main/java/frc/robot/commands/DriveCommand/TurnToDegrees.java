package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PID.PIDOutputs.PIDOutputImpl;
import frc.robot.PID.PIDSources.GyroInput;
import frc.robot.Robot;

public class TurnToDegrees extends Command {
    private double TargetAngle;
    private PIDOutputImpl rotationPIDOutput = new PIDOutputImpl();
    private PIDController anglePIDController = null;
    private GyroInput gyroInput = new GyroInput();
    private PIDController createAnglePIDController() {
        PIDController newPIDController = new PIDController(.03, .01, .01, gyroInput, rotationPIDOutput);

        newPIDController.setInputRange(0,360);
        newPIDController.setOutputRange(-0.5, 0.5);
        newPIDController.setSetpoint(TargetAngle);
        newPIDController.setAbsoluteTolerance(0.01);
        newPIDController.setContinuous();
        newPIDController.enable();
        SmartDashboard.putData("Gyro2", newPIDController);
        return newPIDController;
    }

    public TurnToDegrees(double TargetAngle) {
        requires(Robot.m_drivetrain);
        this.TargetAngle = TargetAngle;
    }

    @Override
    protected void initialize() {
        anglePIDController = createAnglePIDController();
    }

    @Override
    protected void execute() {
        Robot.m_drivetrain.nonProtectArcadeDrive(0.0, rotationPIDOutput.getValue(), false);
    }

    @Override
    protected boolean isFinished() {
        return anglePIDController.onTarget() && anglePIDController.isEnabled();
    }

    @Override
    protected void end() {
        anglePIDController.disable();
        anglePIDController.reset();
        Robot.m_drivetrain.stopMotor();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
