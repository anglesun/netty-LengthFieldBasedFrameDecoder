package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class Client {

    private static final int HEAD_LEN = 2 + 2 + 4 + 4;//short + short + int + int
    private static final int BODY_LEN = 1024;
    private static String serverIp;
    private static int serverPort;


    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        //TODO
        serverIp = args[0];
        serverPort = Integer.parseInt(args[1]);
        int sendThreadNum = Integer.parseInt(args[2]);

        for (int i = 0; i < sendThreadNum; i++) {
            Thread thread = new Thread(new SendThread(), "SendThread_" + i);
            thread.setDaemon(false);
            thread.start();
        }
    }


    public static class SendThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                SocketChannel channel = null;
                ByteBuffer byteBuffer = ByteBuffer.allocate(HEAD_LEN + BODY_LEN);
                byte[] bytes = new byte[BODY_LEN];
                for (int i = 0; i < BODY_LEN; i++) {
                    bytes[i] = (byte) i;
                }
                byteBuffer.putShort((short) 1).putShort((short) 1).putInt(512).putInt(512).put(bytes);
                try {
                    channel = SocketChannel.open();
                    channel.configureBlocking(true);
                    if (channel.connect(new InetSocketAddress(serverIp, serverPort))) {
                        while (true) {
                            byteBuffer.flip();//必须flip
                            channel.write(byteBuffer);
                        }
                    }
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }
    }
}
