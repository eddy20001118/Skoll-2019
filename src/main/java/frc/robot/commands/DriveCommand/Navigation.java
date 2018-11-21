package frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import geometry_msgs.Twist;
import org.ros.namespace.GraphName;
import org.ros.node.*;
import org.ros.node.topic.Subscriber;

public class Navigation extends Command {
    private NodeMainExecutor executor = DefaultNodeMainExecutor.newDefault();
    protected double linerX, angularZ = 0;
    private class SpeedSubscriber extends AbstractNodeMain {
        private String topic_name;

        public SpeedSubscriber() {
            topic_name = "DefalutSuber";
        }

        public SpeedSubscriber(java.lang.String topic_name) {
            this.topic_name = topic_name;
        }

        @Override
        public GraphName getDefaultNodeName() {
            return GraphName.of("rosjava/subscriber");
        }

        @Override
        public void onStart(final ConnectedNode connectedNode) {
            Subscriber<Twist> subscriber = connectedNode.newSubscriber("cmd_vel", Twist._TYPE);
            subscriber.addMessageListener(message -> {
                linerX = message.getLinear().getX();
                angularZ = message.getAngular().getZ();
            });

        }
    }
    private SpeedSubscriber subscriber;

    public Navigation() {
        subscriber = new SpeedSubscriber("frcNavigation");
        requires(Robot.m_drivetrain);
    }


    @Override
    public void initialize() {
        executor.execute(subscriber, NodeConfiguration.newPublic("localhost", RobotMap.ROSMASTER));
    }

    @Override
    public void execute() {
        Robot.m_drivetrain.ArcadeDrive(linerX,angularZ,false);
    }

    @Override
    public boolean isFinished() {
        return true;
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
