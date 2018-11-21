package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.CommandGroups.AutoGroup;
import frc.robot.CommandGroups.TeleopGroup;
import frc.robot.subsystems.Drivetrain;


public class Robot extends TimedRobot {
    public static Drivetrain m_drivetrain = new Drivetrain();
    public static OI m_oi = new OI();

    private CommandGroup m_teleopgroup = new TeleopGroup();
    private CommandGroup m_autogroup = new AutoGroup();

    @Override
    public void robotInit() {
        SmartDashboard.putData(m_autogroup);
        SmartDashboard.putData(m_teleopgroup);
    }

    @Override
    public void robotPeriodic() {

    }

    @Override
    public void autonomousInit() {
        m_autogroup.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (m_autogroup != null)
            m_autogroup.cancel();
        m_teleopgroup.start();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

}
