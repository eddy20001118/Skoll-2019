package frc.robot.PID.PIDOutputs;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDOutputImpl implements PIDOutput {

    private double value = 0;

    public double getValue() {
        return value;
    }

    @Override
    public void pidWrite(double output) {
        // TODO Auto-generated method stub
        value = output;
    }

}
