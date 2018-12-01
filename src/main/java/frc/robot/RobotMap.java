package frc.robot;

import edu.wpi.first.wpilibj.SPI;

import java.net.URI;


public class RobotMap {
    private RobotMap() {
    }

    public final static URI ROSMASTER = URI.create("http://DESKTOP-03QGMNB:11311/");
    public final static int leftfront = 0, leftrear = 1, rightfront = 2, rightrear = 3;
    public final static int elevator = 4;
    public final static int xboxchannel = 0, logichannel = 1;
    public final static int xboxA1 = 1, xboxA2 = 2, xboxX1 = 3, xboxY1 = 4, xboxLeft1 = 5,
            xboxRight1 = 6, xboxBack1 = 7, xboxStart1 = 8;
    public final static SPI.Port gyroChannel = SPI.Port.kOnboardCS0;
    public final static int leftEncoderA = 0, leftEncoderB = 1;
    public final static int rightEncoderA = 2, rightEncoderB = 3;

}
