package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
    Joystick xbox;
    Joystick logi;

    public OI() {
        xbox = new Joystick(RobotMap.xboxchannel);
        logi = new Joystick(RobotMap.logichannel);
    }

    public double getXboxA1() {
        //TODO
        //Change axis channel to match real situation
        //velocity limit here if possible
        return xbox.getRawAxis(3) - xbox.getRawAxis(2);
    }

    public double getXboxA2() {
        //TODO
        //Change axis channel to match real situation
        //velocity limit here if possible
        return xbox.getRawAxis(0);
    }

    public boolean getXboxBtnAStatus() {
        return xbox.getButtonCount() % 2 != 0;
    }
}
