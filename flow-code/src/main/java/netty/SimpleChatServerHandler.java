package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleChatServerHandler extends SimpleChannelInboundHandler<Object> {
    private static AtomicLong count = new AtomicLong(0);
    private static long start = System.currentTimeMillis();

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();//打印异常的堆栈跟踪信息
        ctx.close();//关闭这个channel
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        if (!(msg instanceof ByteBuf))
            return;
        ByteBuf byteBuf = (ByteBuf) msg;
        short type1 = byteBuf.readShort();
        short type2 = byteBuf.readShort();
        int len1 = byteBuf.readInt();
        int len2 = byteBuf.readInt();
        byte[] data1 = new byte[len1];
        byte[] data2 = new byte[len2];
        byteBuf.readBytes(data1);
        byteBuf.readBytes(data2);



        CustomMsg customMsg = new CustomMsg(type1, type2, len1, len2, data1, data2);

        if ((count.incrementAndGet() % 1000000) == 0) {
            System.out.println((1000000 / (System.currentTimeMillis() - start) * 1000));
            start = System.currentTimeMillis();
        }
        //System.out.println("total news is "+(counter++)+ "  " +customMsg.toString());
    }
}
