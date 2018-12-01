package frc.robot.PID.PIDSources;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;

public class DriveTrainEncodersInput implements PIDSource {
    private PIDSourceType pidSourceType = PIDSourceType.kDisplacement;

    public DriveTrainEncodersInput() {
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
        return Robot.m_drivetrain.getAverageDistance();
    }


}
