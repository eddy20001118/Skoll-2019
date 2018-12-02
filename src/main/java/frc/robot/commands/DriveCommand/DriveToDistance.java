package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PID.PIDOutputs.PIDOutputImpl;
import frc.robot.PID.PIDSources.DriveTrainEncodersInput;
import frc.robot.PID.PIDSources.GyroInput;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveToDistance extends Command {
    private double TargetDistance; //unit : meter

    private PIDOutputImpl rotationPIDOutput = new PIDOutputImpl();
    private PIDOutputImpl speedPIDOutput = new PIDOutputImpl();

    private PIDController anglePIDController = null;
    private PIDController encoderPIDController = null;

    private PIDController createEncoderPIDController() {
        PIDController newPIDController = new PIDController(
                RobotMap.PREFERENCES.getDouble("DrivetrainEncoderKp",0.3),
                RobotMap.PREFERENCES.getDouble("DrivetrainEncoderKi",0.1),
                RobotMap.PREFERENCES.getDouble("DrivetrainEncoderKd",0.1),
                new DriveTrainEncodersInput(),
                speedPIDOutput);

        newPIDController.setInputRange(0,200);
        newPIDController.setOutputRange(-0.5, 0.5);
        newPIDController.setSetpoint(TargetDistance);
        newPIDController.setAbsoluteTolerance(1); //Max tolerance is 1 meter
        newPIDController.setContinuous(false);
        newPIDController.enable();
        SmartDashboard.putData("DrivetrainEncoder", newPIDController);
        return newPIDController;
    }

    private PIDController createAnglePIDController() {
        PIDController newPIDController = new PIDController(
                RobotMap.PREFERENCES.getDouble("Gyro1Kp",0.3),
                RobotMap.PREFERENCES.getDouble("Gyro1Ki",0.1),
                RobotMap.PREFERENCES.getDouble("Gyro1Kd",0.1),
                new GyroInput(),
                rotationPIDOutput);

        newPIDController.setInputRange(0, 360);
        newPIDController.setOutputRange(-0.5, 0.5);
        newPIDController.setSetpoint(Robot.m_drivetrain.getHeading());
        newPIDController.setAbsoluteTolerance(0.01);
        newPIDController.setContinuous();
        newPIDController.enable();
        SmartDashboard.putData("Gyro1", newPIDController);
        return newPIDController;
    }

    public DriveToDistance(double TargetDistance) {

        requires(Robot.m_drivetrain);
        this.TargetDistance = TargetDistance;
    }

    @Override
    protected void initialize() {
        anglePIDController = createAnglePIDController();
        encoderPIDController = createEncoderPIDController();
    }

    @Override
    protected void execute() {
        Robot.m_drivetrain.nonProtectArcadeDrive(speedPIDOutput.getValue(), rotationPIDOutput.getValue(), false);
    }

    @Override
    protected boolean isFinished() {
        return encoderPIDController.onTarget() && encoderPIDController.isEnabled();
    }

    @Override
    protected void end() {
        encoderPIDController.disable();
        anglePIDController.disable();
        encoderPIDController.reset();
        anglePIDController.reset();
        Robot.m_drivetrain.stopMotor();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
