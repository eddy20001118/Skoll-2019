package frc.robot.commands.UpperCommand;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PID.PIDOutputs.PIDOutputImpl;
import frc.robot.PID.PIDSources.ElevatorEncoderInput;
import frc.robot.Robot;

public class ElevatorCommand extends Command {
    private PIDOutputImpl speedPIDOutput = new PIDOutputImpl();
    private PIDController encoderPIDController = null;

    private PIDController createEncoderPIDController(double setPoint) {
        PIDController newPIDController = new PIDController(.3, .01, .01, new ElevatorEncoderInput(), speedPIDOutput);
        newPIDController.setInputRange(0.0, 200.0);
        newPIDController.setOutputRange(-0.5, 0.5);
        newPIDController.setSetpoint(setPoint);
        newPIDController.setAbsoluteTolerance(10);
        newPIDController.setContinuous(false);
        newPIDController.enable();
        SmartDashboard.putData("Elevator", newPIDController);
        return newPIDController;
    }

    public ElevatorCommand() {
        requires(Robot.m_elevator);
    }

    @Override
    protected void initialize() {
        encoderPIDController = createEncoderPIDController(Robot.m_oi.getXboxA3());
    }

    @Override
    protected void execute() {
        encoderPIDController.setSetpoint(Robot.m_oi.getXboxA3());
        encoderPIDController.enable();
        SmartDashboard.putBoolean("Elevator On target",encoderPIDController.onTarget());
        if (encoderPIDController.onTarget()) {
            Robot.m_elevator.stopElevator();
        } else {
            Robot.m_elevator.setElevatorSpeed(speedPIDOutput.getValue());
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        encoderPIDController.disable();
        encoderPIDController.reset();
        Robot.m_elevator.stopElevator();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
