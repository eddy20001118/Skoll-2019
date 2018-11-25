package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Elevator extends Subsystem {
    private Talon elevator = new Talon(RobotMap.elevator);

    public Elevator() {
        elevator.setSafetyEnabled(true);
        elevator.setInverted(true);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void setElevatorSpeed(double speed) {
        elevator.set(speed);
    }

    public void stopElevator() {
        elevator.stopMotor();
    }

    public void resetEncoder() {
        //TODO
        //replace with your own reset method
    }

    public double getEncoderPosition() {
        //TODO
        //replace here with your encoder reading
        return 100;
    }
}
