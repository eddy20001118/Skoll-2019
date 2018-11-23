package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {
    private Joystick xbox;
    private Joystick logi;
    private Button buttonA1, buttonB1, buttonX1, buttonY1, buttonLeft1, buttonRight1, buttonBack1, buttonStart1;

    OI() {
        xbox = new Joystick(RobotMap.xboxchannel);
        logi = new Joystick(RobotMap.logichannel);
        initXboxButtons();
    }

    private void initXboxButtons(){
        {
            buttonA1 = new JoystickButton(xbox, RobotMap.xboxA1);
            buttonB1 = new JoystickButton(xbox, RobotMap.xboxA2);
            buttonX1 = new JoystickButton(xbox, RobotMap.xboxX1);
            buttonLeft1 = new JoystickButton(xbox, RobotMap.xboxLeft1);
            buttonRight1 = new JoystickButton(xbox, RobotMap.xboxRight1);
            buttonBack1 = new JoystickButton(xbox, RobotMap.xboxBack1);
            buttonStart1 = new JoystickButton(xbox, RobotMap.xboxStart1);
        }
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
        //TODO
        //needs to return button status
        int ClickCount = 0;
        if(xbox.getRawButtonPressed(RobotMap.xboxA1)){
            ClickCount++;
        }
        return ClickCount % 2 != 0;
    }
}
