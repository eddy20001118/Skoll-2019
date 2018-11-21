package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import geometry_msgs.Twist;
import geometry_msgs.Vector3;
import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.*;
import org.ros.node.topic.Publisher;
import org.ros.node.topic.Subscriber;

public class Navigation extends Command {
    private NodeMainExecutor executor = DefaultNodeMainExecutor.newDefault();
    protected double linearX, angularZ = 0;
    private class SpeedPublisher extends AbstractNodeMain {
        private String topic_name;

        public SpeedPublisher() {
            topic_name = "SpeedPublisher";
        }

        public SpeedPublisher(String topic_name)
        {
            this.topic_name = topic_name;
        }

        @Override
        public GraphName getDefaultNodeName() {
            return GraphName.of("rosjava/publisher");
        }

        @Override
        public void onStart(final ConnectedNode connectedNode) {
            final Publisher<Twist> publisher =
                    connectedNode.newPublisher(topic_name, Twist._TYPE);

            connectedNode.executeCancellableLoop(new CancellableLoop() {

                @Override
                protected void setup() {
                    //Do something here, will only run once
                }

                @Override
                protected void loop() throws InterruptedException {
                    Twist msg = publisher.newMessage();
                    Vector3 linear = msg.getLinear();
                    Vector3 angular = msg.getAngular();
                    linear.setX(10);
                    angular.setZ(3);
                    msg.setLinear(linear);
                    msg.setAngular(angular);
                    publisher.publish(msg);
                    Thread.sleep(400);
                }
            });
        }
    }
    private class SpeedSubscriber extends AbstractNodeMain {
        private String topic_name, listened_topic;

        public SpeedSubscriber() {
            topic_name = "DefalutSubscriber";
        }

        public SpeedSubscriber(String topic_name, String listened_topic) {
            this.topic_name = topic_name;
            this.listened_topic = listened_topic;
        }

        @Override
        public GraphName getDefaultNodeName() {
            return GraphName.of("rosjava/subscriber");
        }

        @Override
        public void onStart(final ConnectedNode connectedNode) {
            Subscriber<Twist> subscriber = connectedNode.newSubscriber(listened_topic, Twist._TYPE);
            subscriber.addMessageListener(message -> {
                linearX = message.getLinear().getX();
                angularZ = message.getAngular().getZ();
            });

        }
    }

    private SpeedSubscriber subscriber;
    private SpeedPublisher publisher;
    public Navigation() {
        publisher = new SpeedPublisher("cmd_vel");
        subscriber = new SpeedSubscriber("frcNavigation","cmd_vel");
        requires(Robot.m_drivetrain);
    }


    @Override
    public void initialize() {
        executor.execute(publisher,NodeConfiguration.newPublic("localhost",RobotMap.ROSMASTER));
        executor.execute(subscriber, NodeConfiguration.newPublic("localhost", RobotMap.ROSMASTER));
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("linearX",linearX);
        SmartDashboard.putNumber("AngularZ",angularZ);
        Robot.m_drivetrain.ArcadeDrive(linearX,angularZ,false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        Robot.m_drivetrain.stopMotor();
    }

    @Override
    public void interrupted() {
        Robot.m_drivetrain.stopMotor();
    }
}
