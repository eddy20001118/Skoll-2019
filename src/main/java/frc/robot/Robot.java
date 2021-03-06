package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.CommandGroups.AutoGroup;
import frc.robot.CommandGroups.TeleopGroup;
import frc.robot.commands.DriveCommand.DriveToDistance;
import frc.robot.commands.DriveCommand.JoystickDrive;
import frc.robot.commands.DriveCommand.Navigation;
import frc.robot.commands.DriveCommand.TurnToDegrees;
import frc.robot.commands.UpperCommand.ElevatorCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;


public class Robot extends TimedRobot {
    public static Drivetrain m_drivetrain = new Drivetrain();
    public static Elevator m_elevator = new Elevator();
    public static OI m_oi = new OI();

    public enum AutonomousMode {Case1, Case2, Case3}

    private CommandGroup m_teleopgroup = new TeleopGroup();
    private CommandGroup m_autogroup;

    /* test command individually*/
    private Command m_navigation = new Navigation();
    private Command m_turntodegrees = new TurnToDegrees(90);
    private Command m_drivetodistance = new DriveToDistance(40);
    private Command m_elevatorcommand = new ElevatorCommand();
    private Command m_joystickdrive = new JoystickDrive();
    /*Delete these when deploy to roborio*/

    private SendableChooser<AutonomousMode> sendableChooser = new SendableChooser<>();

    @Override
    public void robotInit() {
        /*Remember to delete these when deploy to roborio*/
        m_teleopgroup.setRunWhenDisabled(true);
        m_navigation.setRunWhenDisabled(true);
        m_turntodegrees.setRunWhenDisabled(true);
        m_drivetodistance.setRunWhenDisabled(true);
        m_elevatorcommand.setRunWhenDisabled(true);
        m_joystickdrive.setRunWhenDisabled(true);
        /*Delete these when deploy to roborio*/

        sendableChooser.setDefaultOption("Case1", AutonomousMode.Case1);
        sendableChooser.addOption("Case2", AutonomousMode.Case2);
        sendableChooser.addOption("Case3", AutonomousMode.Case3);

        /*Init fake sensors reading for simulation test*/
        SmartDashboard.putNumber("FakeBuiltInAcceleratorX", 0.0);
        SmartDashboard.putNumber("FakeGyro", 0.0);
        SmartDashboard.putNumber("FakeDrivetrainDistance", 0.0);
        SmartDashboard.putNumber("FakeDrivetrainSpeed", 0.0);
        SmartDashboard.putNumber("FakeElevatorEncoder", 0.0);
        /*Delete when real sensors is avavilable*/

        /*Add Sendable widgets*/
        SmartDashboard.putData("Chooser", sendableChooser);
        SmartDashboard.putData(m_teleopgroup);
        SmartDashboard.putData(m_navigation);
        SmartDashboard.putData(m_turntodegrees);
        SmartDashboard.putData(m_drivetodistance);
        SmartDashboard.putData(m_elevatorcommand);
        SmartDashboard.putData(m_joystickdrive);

    }

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        m_autogroup = new AutoGroup(sendableChooser.getSelected());
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
        Scheduler.getInstance().removeAll();
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

}
