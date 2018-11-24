package frc.robot.PID.PIDSources;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;

public class DriveTrainEncodersInput implements PIDSource {
    PIDSourceType pidSourceType = PIDSourceType.kDisplacement;

    public DriveTrainEncodersInput() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        // TODO Auto-generated method stub
        pidSourceType = pidSource;

    }

    @Override
    public PIDSourceType getPIDSourceType() {
        // TODO Auto-generated method stub
        return pidSourceType;
    }

    @Override
    public double pidGet() {
        return Robot.m_drivetrain.getAverageEncoderPosition();
    }


}
