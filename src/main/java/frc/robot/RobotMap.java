package frc.robot;

import java.net.URI;


public class RobotMap {
    private RobotMap() {
    }

    public final static URI ROSMASTER = URI.create("http://DESKTOP-03QGMNB:11311/");
    public final static int leftfront = 0, leftrear = 1, rightfront = 2, rightrear = 3;
    public final static int xboxchannel = 0, logichannel = 1;
}
