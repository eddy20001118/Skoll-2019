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

    // add a ros node class contains topic publisher and subscriber
    private class FRCNode extends AbstractNodeMain {
        private String topic_name;

        public FRCNode() {
            topic_name = "cmd_vel";
        }

        public FRCNode(String topic_name)
        {
            this.topic_name = topic_name;
        }

        @Override
        public GraphName getDefaultNodeName() {
            return GraphName.of("rosjava/frcnode");
        }

        @Override
        public void onStart(final ConnectedNode connectedNode) {
            // add a publisher to publish message
            final Publisher<Twist> publisher =
                    connectedNode.newPublisher(topic_name, Twist._TYPE);

            // add a subscriber to subscribe message
            final Subscriber<Twist> subscriber =
                    connectedNode.newSubscriber("cmd_vel",Twist._TYPE);

            // publishing the topic in an cancellable loop
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
                    linear.setX(8);
                    angular.setZ(2);
                    msg.setLinear(linear);
                    msg.setAngular(angular);
                    publisher.publish(msg);
                    Thread.sleep(400);
                }
            });

            // listen to the topic you want
            subscriber.addMessageListener(message -> {
                linearX = message.getLinear().getX();
                angularZ = message.getAngular().getZ();
            });
        }
    }

    private FRCNode RosFRCHandler;
    public Navigation() {
        RosFRCHandler = new FRCNode("cmd_vel");
        requires(Robot.m_drivetrain);
    }


    @Override
    public void initialize() {
        //ros node will be executed here
        executor.execute(
                RosFRCHandler,NodeConfiguration.newPublic("localhost",RobotMap.ROSMASTER));
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("linearX",linearX);
        SmartDashboard.putNumber("AngularZ",angularZ);
        Robot.m_drivetrain.ArcadeDrive(linearX,angularZ,false);
    }

    @Override
    public boolean isFinished() {
        //TODO
        //subscribe isFinished from ros
        return false;
    }

    @Override
    public void end() {
        linearX=0;
        angularZ=0;
        Robot.m_drivetrain.stopMotor();
    }

    @Override
    public void interrupted() {
        end();
    }
}
