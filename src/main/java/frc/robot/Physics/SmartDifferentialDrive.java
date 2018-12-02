package frc.robot.Physics;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class SmartDifferentialDrive extends DifferentialDrive {
    private BuiltInAccelerometer accelerameter;
    private double curSpeed = 0;
    private final double LimitAcceleration = 5;
    private final double MaxAcceleration = 8;


    public SmartDifferentialDrive(SpeedController leftMotor, SpeedController rightMotor) {
        super(leftMotor, rightMotor);
        accelerameter = new BuiltInAccelerometer();
        accelerameter.setRange(Accelerometer.Range.k8G);
    }


    public void protectedArcadeDrive(double linearX, double angularZ, boolean isRevert) {
        SmartDashboard.putNumber("linearX", linearX);
        SmartDashboard.putNumber("AngularZ", angularZ);
        SmartDashboard.putBoolean("Drivetrain reverted", isRevert);
        curSpeed = linearX;
        if (Math.abs(accelerameter.getX()) > MaxAcceleration) {
            /*acceleration protect trigger only works when joystick
             * is input source*/
            accelerationTrigger(linearX, angularZ);
        } else {
            if (isRevert) {
                super.arcadeDrive(-linearX, -angularZ, false);
            } else {
                super.arcadeDrive(linearX, angularZ, false);
            }
        }
    }

    public double getAcceleratorX() {
        //TODO Replace with real sensor reading
        //return accelerameter.getX();
        return SmartDashboard.getNumber("FakeBuiltInAcceleratorX", 0.0);
    }

    public void nonProtectArcadeDrive(double linearX, double angularZ, boolean isRevert) {
        SmartDashboard.putNumber("linearX", linearX);
        SmartDashboard.putNumber("AngularZ", angularZ);
        SmartDashboard.putBoolean("Drivetrain reverted", isRevert);

        if (isRevert) {
            super.arcadeDrive(-linearX, -angularZ, false);
        } else {
            super.arcadeDrive(linearX, angularZ, false);
        }
    }

    private void accelerationTrigger(double linearX, double angularZ) {
        double limitStartSpeed = linearX - Math.copySign(0.4, linearX);

        /*if the value of the joystick is changed but in range, we still
         * consider that robot need control by accelerator protector. Otherwise
         * we will let human player take over the robot*/

        while (Math.abs(limitStartSpeed) < Math.abs(linearX) && isInRange(Robot.m_oi.getXboxA1())) {
            limitStartSpeed += Math.copySign(0.02 * LimitAcceleration, linearX);
            Timer.delay(0.02);
            super.arcadeDrive(limitStartSpeed, angularZ, false);
        }
    }

    private boolean isInRange(double Speed) {
        return Speed > curSpeed - 0.15 && Speed < curSpeed + 0.15;
    }


}
