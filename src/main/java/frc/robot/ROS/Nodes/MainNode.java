package frc.robot.ROS.Nodes;

import frc.robot.ROS.Publishers.SpeedPublisher;
import frc.robot.ROS.RosTemplate.TP_Node;
import frc.robot.ROS.Subscribers.SpeedSubscriber;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;

/*The node class needs to extend from a template file called TP_Node
 * This process will simplified creating a node with multi-publishers
 * and subscribers as much as possible.
 * Please refer the example below*/
public class MainNode extends TP_Node {
    SpeedPublisher speedPublisher;
    SpeedSubscriber speedSubscriber;

    public MainNode(String nodeName) {
        super("rosjava/" + nodeName);
    }

    @Override
    public void onStart(final ConnectedNode connectedNode) {
        speedPublisher = new SpeedPublisher(connectedNode);
        speedSubscriber = new SpeedSubscriber(connectedNode);
        speedPublisher.executePublisher();
        speedSubscriber.executeSubscriber();
    }

    @Override
    public void onShutdown(final Node node) {
        speedPublisher.shutdownPublisher();
        speedSubscriber.shutdownSubscriber();
    }
}
