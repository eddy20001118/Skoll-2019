package frc.robot.ROS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import geometry_msgs.Twist;
import geometry_msgs.Vector3;
import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.*;
import org.ros.node.topic.Publisher;
import org.ros.node.topic.Subscriber;

/*This class aims to simplify action for creating, executing and shutdown an node*/
public class ROSFRC {
    private double linearX = 0, angularZ = 0;
    //User can create another node as below
    private class FRCNode extends AbstractNodeMain {
        private String topic_name;
        private Twist msg;
        private Vector3 linear, angular;
        private Publisher<Twist> publisher;
        private Subscriber<Twist> subscriber;

        public FRCNode() {
            topic_name = "cmd_vel";
        }

        public FRCNode(String topic_name) {
            this.topic_name = topic_name;
        }

        @Override
        public GraphName getDefaultNodeName() {
            return GraphName.of("rosjava/frcnode");
        }

        @Override
        public void onStart(final ConnectedNode connectedNode) {
            // add a publisher to publish message
            publisher = connectedNode.newPublisher(topic_name, Twist._TYPE);

            // add a subscriber to listen a topic
            subscriber = connectedNode.newSubscriber("cmd_vel", Twist._TYPE);

            // publishing the topic in an cancellable loop
            connectedNode.executeCancellableLoop(new CancellableLoop() {

                @Override
                protected void setup() {
                    //Do something here, will only run once
                }

                @Override
                protected void loop() {
                    msg = publisher.newMessage();
                    linear = msg.getLinear();
                    angular = msg.getAngular();
                    linear.setX(0.8);
                    angular.setZ(0.2);
                    msg.setLinear(linear);
                    msg.setAngular(angular);
                    publisher.publish(msg);
                    Timer.delay(0.02);
                }

            });

            // start listening
            subscriber.addMessageListener(message -> {
                linearX = message.getLinear().getX();
                angularZ = message.getAngular().getZ();
            });
        }

        @Override
        public void onShutdown(final Node node) {
            linearX = 0;
            angularZ = 0;
            linear.setX(0);
            angular.setZ(0);
            msg.setLinear(linear);
            msg.setAngular(angular);
            publisher.publish(msg);
        }
    }
    private NodeMainExecutor executor;
    private FRCNode frcNode;

    public ROSFRC() {
        executor = DefaultNodeMainExecutor.newDefault();
        frcNode = new FRCNode("cmd_vel");
    }

    public void executeNode() {
        try {
        executor.execute(frcNode, NodeConfiguration.newPublic("localhost", RobotMap.ROSMASTER));
        } catch (Exception e){
            System.out.println("Cannot connect with ros master");
            System.out.println(e);
        }
    }

    public double getLinearX() {
        SmartDashboard.putNumber("linearX", linearX);
        return linearX;
    }

    public double getAngularZ() {
        SmartDashboard.putNumber("AngularZ", angularZ);
        return angularZ;
    }

    public void shutdownNode() {
        try{
        executor.shutdownNodeMain(frcNode); // shutdown the node will run onShutdown()
        } catch (Exception e){
            System.out.println("Cannot shutdown ros node");
            System.out.println(e);
        }
        SmartDashboard.putNumber("linearX", linearX);
        SmartDashboard.putNumber("AngularZ", angularZ);
    }
}
