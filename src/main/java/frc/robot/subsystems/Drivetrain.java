package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand.JoystickDrive;

public class Drivetrain extends Subsystem {
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
        if (isRevert) {
            m_drivetrain.tankDrive(-(linearX + AngularZ), -(linearX - AngularZ), false);
        } else {
            m_drivetrain.tankDrive(linearX + AngularZ, linearX - AngularZ, false);
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

    public double getHeading() {
        double angle = gyro.getAngle() % 360;
        return angle < 0 ? 360 + angle : angle;
    }

    public void resetHeading() {
        gyro.reset();
    }

    public void resetEncoders()
    {
        //TODO
        //Complete this method to reset encoders positions
    }

    public static double ConvertInchestoEncoderCount(double inches)
    {
        //TODO
        //Indicate the correct coefficient to inches
        return inches * 508;
    }


    public double getAverageEncoderPosition(){
        //TODO
        //Complete this return method with reading from encoders
        return 0.0;
    }
}
