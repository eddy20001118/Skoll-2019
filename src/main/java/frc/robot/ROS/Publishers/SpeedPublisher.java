package frc.robot.ROS.Publishers;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.ROS.RosTemplate.TP_Publisher;
import geometry_msgs.Twist;
import geometry_msgs.Vector3;
import org.ros.node.ConnectedNode;

/*The publisher class needs to extend from a template class called TP_Publisher.
 * This process will simplified publishing data as much as possible.
 * Please refer  tothe example below*/
public class SpeedPublisher extends TP_Publisher {
    private Twist twist;
    private Vector3 linear, angular;

    public SpeedPublisher(ConnectedNode connectedNode) {
        super("cmd_vel", Twist._TYPE, connectedNode);
    }

    @Override
    public Twist setMessage() {
        twist = (Twist) getMessage();
        linear = twist.getLinear();
        angular = twist.getAngular();
        linear.setX(0.5);
        angular.setZ(0.2);
        twist.setLinear(linear);
        twist.setAngular(angular);
        Timer.delay(0.5);
        return twist;
    }

}
