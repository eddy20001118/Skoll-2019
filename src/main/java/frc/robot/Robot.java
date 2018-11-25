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

    private CommandGroup m_teleopgroup = new TeleopGroup();
    private CommandGroup m_autogroup = new AutoGroup();
    private Command m_navigation = new Navigation();
    private Command m_turntodegrees = new TurnToDegrees(90);
    private Command m_drivetodistance = new DriveToDistance(40);
    private Command m_elevatorcommand = new ElevatorCommand();
    private Command m_joystickdrive = new JoystickDrive();

    private enum AutonomousMode {Case1, Case2, Case3}

    private SendableChooser<AutonomousMode> sendableChooser = new SendableChooser<>();

    @Override
    public void robotInit() {
        /*Remember to delete these when deploy to roborio*/
        m_autogroup.setRunWhenDisabled(true);
        m_teleopgroup.setRunWhenDisabled(true);
        m_navigation.setRunWhenDisabled(true);
        m_turntodegrees.setRunWhenDisabled(true);
        m_drivetodistance.setRunWhenDisabled(true);
        m_elevatorcommand.setRunWhenDisabled(true);
        m_joystickdrive.setRunWhenDisabled(true);
        /*Remember to delete these when deploy to roborio*/

        sendableChooser.addDefault("Case1", AutonomousMode.Case1);
        sendableChooser.addObject("Case2", AutonomousMode.Case2);
        sendableChooser.addObject("Case3", AutonomousMode.Case3);

        /*Add Sendable widgets*/
        SmartDashboard.putData("Chooser", sendableChooser);
        SmartDashboard.putData(m_autogroup);
        SmartDashboard.putData(m_teleopgroup);
        SmartDashboard.putData(m_navigation);
        SmartDashboard.putData(m_turntodegrees);
        SmartDashboard.putData(m_drivetodistance);
        SmartDashboard.putData(m_elevatorcommand);
        SmartDashboard.putData(m_joystickdrive);

        /*Add values, will be replace when running*/
        SmartDashboard.putNumber("linearX", 0.0);
        SmartDashboard.putNumber("AngularZ", 0.0);
        SmartDashboard.putBoolean("Drivetrain reverted", false);
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
