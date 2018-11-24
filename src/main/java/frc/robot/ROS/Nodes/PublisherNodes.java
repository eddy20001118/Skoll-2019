package frc.robot.ROS.Nodes;

import frc.robot.ROS.Publishers.SpeedPublisher;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;

public class PublisherNodes extends AbstractNodeMain {
    private SpeedPublisher speedPublisher;

    public PublisherNodes(){
    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("rosjava/frcPublishers");
    }

    @Override
    public void onStart(final ConnectedNode connectedNode){
        speedPublisher = new SpeedPublisher(connectedNode);
        speedPublisher.executePublisher();
    }

    @Override
    public void onShutdown(final Node node) {
        speedPublisher.shutdownPublisher();
    }
}
