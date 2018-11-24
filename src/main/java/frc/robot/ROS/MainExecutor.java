package frc.robot.ROS;

import frc.robot.ROS.Nodes.PublisherNodes;
import frc.robot.ROS.Nodes.SubscriberNodes;
import frc.robot.RobotMap;
import org.ros.node.DefaultNodeMainExecutor;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

/*This class aims to simplify action for creating, executing and shutdown an node*/
public class MainExecutor {


    //User can create another node as below

    private NodeMainExecutor executor;
    public SubscriberNodes subscriberNodes;
    public PublisherNodes publisherNodes;

    public MainExecutor() {
        executor = DefaultNodeMainExecutor.newDefault();
        subscriberNodes = new SubscriberNodes();
        publisherNodes = new PublisherNodes();
    }

    public void executeNode() {
        executor.execute(publisherNodes, NodeConfiguration.newPublic("localhost", RobotMap.ROSMASTER));
        executor.execute(subscriberNodes, NodeConfiguration.newPublic("localhost", RobotMap.ROSMASTER));

    }

    public void shutdownNode() {
        executor.shutdownNodeMain(subscriberNodes);
    }
}
