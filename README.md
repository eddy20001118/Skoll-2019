# FRC Skoll-2019 project
This is Skoll-2019 project using ROS to help navigate in autonomous period. This project only aims to be deployed on roborio. All ROS navigation packages(code) are not uploaded yet, I will create a new repository to show them later this year. In this project, rosjava is used for creating a communication between Roborio and ROS(typically running on raspberry pi or jetson). If you want to explore how to use rosjava, go to "Navigation.java" in "commands/DriveCommand" folder, this will give you a brief example. I will add wiki in early 2019.

**This branch is test with 2019 core libraries, may be unstable**
It is important to aware that:
>NOTE: You are using a BETA version of GradleRIO, designed for the 2019 Season!
This release requires the 2019 RoboRIO Image, and may be unstable. Do not use this for the official competition season.

All code are tested by using SnobotSimulatorPlugin 2019.0.0 with Wpi-JNI(Java Native Interface) libs. However, it is not a complete version which is just released. Many bugs may appear when is deployed to roborio. Important versions are shown below: 

**WPILib: 2019.1.1-beta-1 (wpilibVersion)** \
**Shuffleboard: 2019.1.1-beta-1 (shuffleboardVersion)** \
**GradleRIO: 2019.1.1-beta-1 (GradleRIOVersion)** 

## FRC-ROS Project Structure
The project structure is shown below. This structure diagram mainly shows the structure about autonomous navigation, introducing how ROS and Roborio cooperate.

![image](https://github.com/eddy20001118/Skoll-2019/blob/master/images/FRC-ROS-Structure.png)

## FRC-ROS Shuffleboard preview
Shuffleboard is a useful tool in FRC competition used to visualize your code(displaying important information and widgets). In FRC, all data (including widgets in shuffleboard) are pulished on NetworkTable server which can be accessed by clients running on different computers. So, in shuffleboard you can not only view the data pulished in left panel, but also display them as widgets in right panel. You can also display the data from ROS(see next). The diagram below gives an example:

![image](https://github.com/eddy20001118/Skoll-2019/blob/master/images/FRC-ROS-Shuffleboard.PNG)

## Choosing a way to transmit data
There are actually two ways to transmit data between **ROS**, **Roborio**, **Driverstation computer**. If you want to transmit the data between ROS and Roborio that required high real-time level, for example, "drivetrain speed" data published in "cmd_vel" topic in ROS, you need to subscribe them on roborio by using rosjava subscriber, this method allows roborio and ROS to connect directly through radio (OM5P-AC router). Networktable server is usually running on driverstation computer and may cause delay in the competition. So, if you just want to transmit data about Robot/ROS status data, you can directly send it by using networktable in ROS.

<div align="center"> 
    <img src="https://github.com/eddy20001118/Skoll-2019/blob/master/images/Roborio-ROS-NetworkTable.png"/>
</div>

### For ROS-NetworkTable with C++
If you wish to use C++ to programme in ROS, please refer [here](https://github.com/eddy20001118/ROS-networktable) to explore an example C++ NetworkTable client in ROS Node.

### For ROS-NetworkTable with Python
If you wish to use python to programme in ROS, please refer [pynetworktables](https://github.com/robotpy/pynetworktables). I strongly recommand this one as it is easy to install all needed dependencies.

## Other projects you may interested
[Pathfinder](https://github.com/JacisNonsense/Pathfinder): Generate a path and follow it by using encoder. \
[Path Weaver](https://github.com/wpilibsuite/PathWeaver): A GUI tool to generate a path plan and can be loaded by pathfinder. \
[GRIP](https://github.com/WPIRoboticsProjects/GRIP): A desktop app for developing computer vision projects. \
[Lino Robot](https://github.com/linorobot/linorobot): ROS Compatible ground robots (2WD, 4WD, Ackermann Steering, Mecanum Drive) \
[NavxMxp](https://github.com/kauailabs/navxmxp): A powerful robotics navigation sensor designed for roborio & FIRST FRC Robotics. \
[QDriverStation](https://github.com/FRC-Utilities/QDriverStation): An open-source, cross-platform FRC Driver Station.
