package frc.robot.Physics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.PID.PIDOutputs.PIDOutputImpl;

public class SmartSpark extends Spark {
    private double kP = 0;
    private double kI = 0;
    private double kD = 0;
    private Encoder encoder;
    private PIDOutputImpl pidOutput;
    private PIDController pidController;
    private final double kDistancePerPulse = 0.01; //0.01m displacement per pulse

    public SmartSpark(int channel, Encoder encoder) {
        super(channel);
        this.encoder = encoder;
        this.encoder.setDistancePerPulse(kDistancePerPulse);
        this.encoder.setSamplesToAverage(7);
        pidOutput = new PIDOutputImpl();
        pidController = new PIDController(kP,kI,kD,this.encoder,pidOutput);
    }

    public void initPID(double kP, double kI, double kD){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        pidController.setPID(kP,kI,kD);
        pidController.setOutputRange(-1,1);
        pidController.setContinuous(false);
    }

    @Override
    public void set(double setSpeed){
        pidController.setSetpoint(setSpeed);
        pidController.enable();
        super.set(pidOutput.getValue());
    }

    public double getDistance(){
        return this.encoder.getDistance();
    }

    public double getRate(){
        return this.encoder.getRate();
    }

    public void resetEncoder(){
        this.encoder.reset();
    }
}
