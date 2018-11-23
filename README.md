# FRC Skoll-2019 project
This is Skoll-2019 project using ROS to help navigate in autonomous period. This project only aims to be deployed on roborio and all ros navigation packages(code) is not uploaded yet. In this project, rosjava is used for creating a communication between roborio and ros master(running on raspberry pi or jetson). "ROSFRC.java" in "ROS" folder contains a example ros node and executor. If you want to explore how to use this file, go to "Navigation.java" in "commands/DriveCommand" folder, this will give you a brief example:"

## FRC-ROS Project Structure
The project structure is shown below. This structure diagram mainly shows the structure about autonomous navigation, introducing how ros and roborio cooperate.

![image](https://github.com/eddy20001118/Skoll-2019/blob/master/FRC-ROS-Structure.png)

## FRC-ROS Shuffleboard preview
Shuffleboard is a useful tool in FRC competition used to visualize your code(displaying important information and widgets). In FRC, all data (including widgets in shuffleboard) are pulished on NetworkTable server which can be accessed by clients running on different computers. So, in shuffleboard you can not only view the data pulished in left panel, but also display them as widgets in right panel. The diagram below gives an example

![image](https://github.com/eddy20001118/Skoll-2019/blob/master/FRC-ROS-Shuffleboard.PNG)

## Choosing a way to transmit data
As I mentioned above, there are actually two ways to transmit data between ros master, roborio, driverstation computer. If you want to transmit the data that required high real-time level, such as "drivetrain speed" published in "cmd_vel" topic, you need to subscribe them on roborio by using rosjava subscriber, this method allows roborio and ros master connecting directly through radio, because Networktable server is always running on driverstation computer and may cause delay in the competition. If you just want to transmit data about robot/ros master status data, you can directly send it by using networktable in ros.

### For C++ ROS Node
If you wish to use C++ to programme in ros, please refer [here](https://github.com/eddy20001118/ROS-networktable) to explore an example C++ NetworkTable client in ROS Node.

### For Python ROS Node
If you wish to use python to programme in ros, please refer [pynetworktables](https://github.com/robotpy/pynetworktables). 