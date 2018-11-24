package frc.robot.ROS.RosTemplate;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;

public abstract class TP_Node extends AbstractNodeMain {
    String NodeName;

    public TP_Node(){}
    public TP_Node(String NodeName){
        this.NodeName = NodeName;
    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of(NodeName);
    }

    //Please override this method again to add your publisher/subscriber
    @Override
    public void onStart(final ConnectedNode connectedNode) {

    }

    //Please override this method again to shutdown your publisher/subscriber
    @Override
    public void onShutdown(final Node node) {

    }
}

