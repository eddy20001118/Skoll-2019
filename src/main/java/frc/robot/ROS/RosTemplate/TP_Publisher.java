package frc.robot.ROS.RosTemplate;

import org.ros.concurrent.CancellableLoop;
import org.ros.internal.message.Message;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;

public abstract class TP_Publisher {
    private String topic_name;
    private String msgType;
    private ConnectedNode connectedNode;
    private Publisher<Message> publisher;
    private Message message;

    public TP_Publisher() {
    }

    public TP_Publisher(String topic_name, String msgType, ConnectedNode connectedNode) {
        this.topic_name = topic_name;
        this.connectedNode = connectedNode;
        this.msgType = msgType;
        publisher = connectedNode.newPublisher(topic_name, msgType);
        message = publisher.newMessage();
    }

    public Message getMessage() {
        return publisher.newMessage();
    }

    //Please override this method to set your own message
    public Message setMessage() {
        return null;
    }

    //Please override this method to set

    //Please override this method with your own shutdown method
    public boolean onShutdown() {
        return false;
    }

    public void executePublisher() {
        connectedNode.executeCancellableLoop(new CancellableLoop() {
            @Override
            protected void loop() throws InterruptedException {
                publisher.publish(setMessage());
            }
        });
    }


    public void shutdownPublisher() {
        onShutdown();
        publisher.shutdown();
    }

}
