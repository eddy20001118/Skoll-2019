package frc.robot.ROS.Nodes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import geometry_msgs.Twist;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.topic.Subscriber;

public class SubscriberNodes extends AbstractNodeMain {
    private Subscriber<Twist> speedSubscriber;
    private double linearX = 0, angularZ = 0;

    public SubscriberNodes() {

    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("rosjava/frcSubscribers");
    }

    @Override
    public void onStart(final ConnectedNode connectedNode) {
        // add a subscriber to listen a topic
        speedSubscriber= connectedNode.newSubscriber("cmd_vel", Twist._TYPE);

        // start listening
        speedSubscriber.addMessageListener(message -> {
            linearX = message.getLinear().getX();
            angularZ = message.getAngular().getZ();
        });
    }

    @Override
    public void onShutdown(final Node node) {
        linearX = 0;
        angularZ = 0;
    }

    public double getLinearX() {
        SmartDashboard.putNumber("linearX", linearX);
        return linearX;
    }

    public double getAngularZ() {
        SmartDashboard.putNumber("AngularZ", angularZ);
        return angularZ;
    }
}