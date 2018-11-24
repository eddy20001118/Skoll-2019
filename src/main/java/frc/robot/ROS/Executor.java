package frc.robot.ROS;

import frc.robot.ROS.RosTemplate.TP_Node;
import frc.robot.RobotMap;
import org.ros.node.DefaultNodeMainExecutor;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

/*Executor class aims to executor a node by specify a node object*/
public class Executor {
    private NodeMainExecutor executor;
    private TP_Node excutedNode;

    public Executor(TP_Node tp_node) {
        executor = DefaultNodeMainExecutor.newDefault();
        excutedNode = tp_node;
    }

    public void excuteNode() {
        executor.execute(excutedNode, NodeConfiguration.newPublic("localhost", RobotMap.ROSMASTER));
    }

    public void shutdownNode() {
        executor.shutdownNodeMain(excutedNode);
    }
}
