package frc.robot.ROS.Subscribers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import geometry_msgs.Twist;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;

/*The subscriber class does not really need to extend from a template class
 * as publisher*/
public class SpeedSubscriber {
    private Subscriber<Twist> subscriber;
    private double linearX = 0, angularZ = 0;

    public SpeedSubscriber(ConnectedNode connectedNode) {
        subscriber = connectedNode.newSubscriber("cmd_vel", Twist._TYPE);
    }

    public void executeSubscriber() {
        subscriber.addMessageListener(message -> {
            linearX = message.getLinear().getX();
            angularZ = message.getAngular().getZ();
            SmartDashboard.putNumber("linearX", linearX);
            SmartDashboard.putNumber("AngularZ", angularZ);
        });
    }

    public void shutdownSubscriber() {
        linearX = 0;
        angularZ = 0;
        SmartDashboard.putNumber("linearX", linearX);
        SmartDashboard.putNumber("AngularZ", angularZ);
    }
}
