package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Physics.SmartDifferentialDrive;
import frc.robot.Physics.SmartSpark;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand.JoystickDrive;

public class Drivetrain extends Subsystem {
    private SmartSpark m_leftfront, m_leftrear, m_rightfront, m_rightrear;
    private Encoder m_leftEncoder, m_rightEncoder;
    private SpeedControllerGroup m_leftcontroller;
    private SpeedControllerGroup m_rightcontroller;
    private SmartDifferentialDrive m_drivetrain;
    private ADXRS450_Gyro gyro;
    private BuiltInAccelerometer accelerameter;

    public Drivetrain() {
        m_leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB, false, Encoder.EncodingType.k4X);
        m_rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB, false, Encoder.EncodingType.k4X);

        m_leftfront = new SmartSpark(RobotMap.leftfront, m_leftEncoder);
        m_leftfront.initPID(0.3, 0, 0);

        m_leftrear = new SmartSpark(RobotMap.leftrear, m_leftEncoder);
        m_leftrear.initPID(0.3, 0, 0);

        m_rightfront = new SmartSpark(RobotMap.rightfront, m_rightEncoder);
        m_rightfront.initPID(0.3, 0, 0);

        m_rightrear = new SmartSpark(RobotMap.rightrear, m_rightEncoder);
        m_rightrear.initPID(0.3, 0, 0);

        m_leftcontroller = new SpeedControllerGroup(m_leftfront, m_leftrear);
        m_rightcontroller = new SpeedControllerGroup(m_rightfront, m_rightrear);

        m_drivetrain = new SmartDifferentialDrive(m_leftcontroller, m_rightcontroller);
        m_drivetrain.setSafetyEnabled(false);
        m_drivetrain.setMaxOutput(1);

        gyro = new ADXRS450_Gyro(RobotMap.gyroChannel);
        gyro.calibrate();
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }

    public void protectedArcadeDrive(double linearX, double angularZ, boolean isRevert) {
        m_drivetrain.protectedArcadeDrive(linearX, angularZ, isRevert);
    }

    public void nonProtectArcadeDrive(double linearX, double angularZ, boolean isRevert) {
        m_drivetrain.nonProtectArcadeDrive(linearX, angularZ, isRevert);
    }

    public void stopMotor() {
        m_drivetrain.stopMotor();
    }

    public double getHeading() {
        //TODO Replace with real sensor reading
        //double angle = gyro.getAngle() % 360;
        double angle = SmartDashboard.getNumber("FakeGyro", 10) % 360;
        return angle < 0 ? 360 + angle : angle;
    }

    public void resetHeading() {
        gyro.reset();
    }

    public void resetEncoders() {
        /*Each side only has one encoder, only needs to be reset once*/
        m_leftfront.resetEncoder();
        m_rightfront.resetEncoder();
    }

    public double getAverageDistance() {
        //TODO Replace with real sensor reading
        /*Each side only has one encoder, only needs to be calculated once*/
        double averagePosition = (m_leftfront.getDistance() + m_rightfront.getDistance()) / 2; //unit : meter
        //return averagePosition;
        return SmartDashboard.getNumber("FakeDrivetrainDistance", 0.0);
    }

    public double getAverageSpeed() {
        //TODO Replace with real sensor reading
        //return (m_leftfront.getRate() + m_rightfront.getRate()) / 2;
        return SmartDashboard.getNumber("FakeDrivetrainSpeed", 0.0);
    }
}
