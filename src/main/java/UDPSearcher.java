import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * The class is UDP搜索者，用于搜索服务
 *
 * @author YuCheng
 * @version 2020/3/1 20:22
 */
public class UDPSearcher {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPSearcher Started.");
        // 作为搜索方，无需指定一个端口，可以让系统自动分配端口
        DatagramSocket ds = new DatagramSocket();

        // 构建一份请求数据
        String requestData = "Hello World." ;
        byte[] requestDataBytes = requestData.getBytes();
        // 直接构建Packet
        DatagramPacket requestPacket = new DatagramPacket(
                requestDataBytes,
                requestDataBytes.length);
        // 本机20000端口
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(20000);
        // 发送
        ds.send(requestPacket);


        // 构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
        // 接收
        ds.receive(receivePack);

        // 打印接收到的信息与发送者的信息
        // 发送者的IP地址
        String ip = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int datalen = receivePack.getLength();
        String data = new String(receivePack.getData(), 0, datalen);
        System.out.println("UDPSearcher receive  from ip : "
                + ip + "\tport:" + port+ "\tdata:" + data);


        // 完成
        System.out.println("UDPSearcher Finished.");
        ds.close();
    }
}
