package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand.JoystickDrive;

public class Drivetrain extends PIDSubsystem {
    private SpeedController m_leftfront = new Talon(RobotMap.leftfront),
            m_leftrear = new Talon(RobotMap.leftrear),
            m_rightfront = new Talon(RobotMap.rightfront),
            m_rightrear = new Talon(RobotMap.rightrear);
    private SpeedControllerGroup m_leftcontroller =
            new SpeedControllerGroup(m_leftfront, m_leftrear);
    private SpeedControllerGroup m_rightcontroller =
            new SpeedControllerGroup(m_rightfront, m_rightrear);
    private DifferentialDrive m_drivetrain =
            new DifferentialDrive(m_leftcontroller, m_rightcontroller);
    private ADXRS450_Gyro gyro;

    public Drivetrain() {
        super(1, 0, 0);
        gyro = new ADXRS450_Gyro(RobotMap.gyroChannel);
        m_drivetrain.setSafetyEnabled(false);
        m_drivetrain.setMaxOutput(1);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }

    public void ArcadeDrive(double linearX, double AngularZ, boolean isRevert) {
        //TODO
        //velocity and acceleration need limited here
        //find out the scale of speed output that tankDrive make
        if (isRevert) {
            m_drivetrain.tankDrive(-(linearX + AngularZ), -(linearX - AngularZ));
        } else {
            m_drivetrain.tankDrive(linearX + AngularZ, linearX - AngularZ);
        }
    }

    public void TankDrive(double leftSpeed, double rightSpeed, boolean isRevert) {
        if (isRevert) {
            m_drivetrain.tankDrive(-leftSpeed, -rightSpeed);
        } else {
            m_drivetrain.tankDrive(leftSpeed, rightSpeed);
        }
    }

    public void stopMotor() {
        m_drivetrain.stopMotor();
    }

    @Override
    protected double returnPIDInput() {
        return 0;
    }

    @Override
    protected void usePIDOutput(double output) {
        SmartDashboard.putNumber("output",output);
        TankDrive(-output, output, false);
    }
}
