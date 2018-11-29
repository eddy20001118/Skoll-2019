package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand.JoystickDrive;

public class Drivetrain extends Subsystem {
    private SpeedController m_leftfront, m_leftrear, m_rightfront, m_rightrear;
    private SpeedControllerGroup m_leftcontroller;
    private SpeedControllerGroup m_rightcontroller;
    private DifferentialDrive m_drivetrain;
    private ADXRS450_Gyro gyro;
    private BuiltInAccelerometer accelerometer;

    public Drivetrain() {
        m_leftfront = new Talon(RobotMap.leftfront);
        m_leftrear = new Talon(RobotMap.leftrear);
        m_rightfront = new Talon(RobotMap.rightfront);
        m_rightrear = new Talon(RobotMap.rightrear);
        m_leftcontroller = new SpeedControllerGroup(m_leftfront, m_leftrear);
        m_rightcontroller = new SpeedControllerGroup(m_rightfront, m_rightrear);
        m_drivetrain = new DifferentialDrive(m_leftcontroller, m_rightcontroller);
        gyro = new ADXRS450_Gyro(RobotMap.gyroChannel);
        gyro.calibrate();
        accelerometer = new BuiltInAccelerometer();

        m_drivetrain.setSafetyEnabled(false);
        m_drivetrain.setMaxOutput(1);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }

    public void ArcadeDrive(double linearX, double AngularZ, boolean isRevert) {
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
        //TODO
        //Replace with real sensor reading
//        double angle = gyro.getAngle() % 360;
        double angle = SmartDashboard.getNumber("FakeGyro", 10) % 360;
        return angle < 0 ? 360 + angle : angle;
    }

    public void resetHeading() {
        gyro.reset();
    }

    public void resetEncoders() {
        //TODO
        //Complete this method to reset encoders positions
    }

    public static double ConvertInchestoEncoderCount(double inches) {
        //TODO
        //Indicate the correct coefficient to inches
        return inches * 508;
    }

    public double getAverageEncoderPosition() {
        //TODO
        //Replace with real sensor reading
        return SmartDashboard.getNumber("FakeDrivetrainEncoder", 0.0);
    }

    public double getAccX(){
        return accelerometer.getX();
    }

    public double getAccY(){
        return accelerometer.getY();
    }

    public double getAccZ(){
        return accelerometer.getZ();
    }

    public double getResultantAcceleration(){
        double accSquare = getAccX()*getAccX() + getAccY()*getAccY() + getAccZ()*getAccZ();
        return  Math.sqrt(accSquare);
    }

}
