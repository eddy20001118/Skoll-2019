package frc.robot.PID.PIDSources;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;

public class ElevatorEncoderInput implements PIDSource {
    private PIDSourceType pidSourceType = PIDSourceType.kDisplacement;

    public ElevatorEncoderInput() {
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        pidSourceType = pidSource;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return pidSourceType;
    }

    @Override
    public double pidGet() {
        return Robot.m_elevator.getEncoderPosition();
    }
}
