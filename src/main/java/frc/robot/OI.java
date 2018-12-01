package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {
    private Joystick xbox;
    private Joystick logi;
    private Button buttonA1, buttonB1, buttonX1, buttonY1, buttonLeft1, buttonRight1, buttonBack1, buttonStart1;
    private int A1ClickCount = 0;
    private int Y1ClickCount = 0;

    OI() {
        xbox = new Joystick(RobotMap.xboxchannel);
        logi = new Joystick(RobotMap.logichannel);
        initXboxButtons();
    }

    private void initXboxButtons() {
        {
            buttonA1 = new JoystickButton(xbox, RobotMap.xboxA1);
            buttonB1 = new JoystickButton(xbox, RobotMap.xboxA2);
            buttonX1 = new JoystickButton(xbox, RobotMap.xboxX1);
            buttonY1 = new JoystickButton(xbox, RobotMap.xboxY1);
            buttonLeft1 = new JoystickButton(xbox, RobotMap.xboxLeft1);
            buttonRight1 = new JoystickButton(xbox, RobotMap.xboxRight1);
            buttonBack1 = new JoystickButton(xbox, RobotMap.xboxBack1);
            buttonStart1 = new JoystickButton(xbox, RobotMap.xboxStart1);
        }
    }

    public double getXboxA1() {
        return xbox.getRawAxis(3) - xbox.getRawAxis(2);
    }

    public double getXboxA2() {
        return xbox.getRawAxis(0);
    }

    public double getXboxA3() {
        return 100 - xbox.getRawAxis(4) * 100;
    }

    /*return the value to enable(disable) reverted drivertrain*/
    public boolean getDrivetrainRevert() {
        if (xbox.getRawButton(RobotMap.xboxA1)) {
            Timer.delay(0.02);
            if (!xbox.getRawButton(RobotMap.xboxA1)) {
                A1ClickCount++;
            }
        }
        return A1ClickCount % 2 != 0;
    }

    /*return the value to enable(disable) acceleration protect*/
    public boolean getDrivetrainProtect() {
        if (xbox.getRawButton(RobotMap.xboxY1)) {
            Timer.delay(0.02);
            if (!xbox.getRawButton(RobotMap.xboxY1)) {
                Y1ClickCount++;
            }
        }
        return Y1ClickCount % 2 != 0;
    }
}
