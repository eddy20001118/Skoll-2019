package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.CommandGroups.AutoGroup;
import frc.robot.CommandGroups.TeleopGroup;
import frc.robot.subsystems.Drivetrain;


public class Robot extends TimedRobot {
    public static Drivetrain m_drivetrain = new Drivetrain();
    public static OI m_oi = new OI();

    private CommandGroup m_teleopgroup = new TeleopGroup();
    private CommandGroup m_autogroup = new AutoGroup();
    private enum AutonomousMode {Case1, Case2, Case3}
    private SendableChooser<AutonomousMode> sendableChooser = new SendableChooser<>();
    @Override
    public void robotInit() {
        m_autogroup.setRunWhenDisabled(true);
        m_teleopgroup.setRunWhenDisabled(true);
        sendableChooser.addDefault("Case1", AutonomousMode.Case1);
        sendableChooser.addObject("Case2", AutonomousMode.Case2);
        sendableChooser.addObject("Case3",AutonomousMode.Case3);
        SmartDashboard.putData("Chooser",sendableChooser);
        SmartDashboard.putData(m_autogroup);
        SmartDashboard.putData(m_teleopgroup);
    }

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        //TODO
        //update selection code
        //e.g AutonomousMode selectedMode = sendableChooser.getSelected();
        //if(selectedMode == xxx){ //run which command }
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
