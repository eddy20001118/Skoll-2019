package frc.robot.subsystems;

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

    public Drivetrain(){
        m_drivetrain.setSafetyEnabled(false);
        m_drivetrain.setMaxOutput(0.8);
    }
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }

    public void ArcadeDrive(double linearX, double AngularZ, boolean isRevert) {
        //TODO
        //velocity and acceleration need limited here

        if (isRevert)
            m_drivetrain.tankDrive(-(linearX+AngularZ), -(linearX-AngularZ));
        else
            m_drivetrain.tankDrive(linearX+AngularZ, linearX-AngularZ);
    }

    public void stopMotor() {
        m_drivetrain.stopMotor();
    }

}
