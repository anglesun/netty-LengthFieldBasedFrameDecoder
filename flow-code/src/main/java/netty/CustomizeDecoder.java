package netty;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import java.nio.ByteOrder;

public class CustomizeDecoder extends LengthFieldBasedFrameDecoder {

    public CustomizeDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                            int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength,
                lengthAdjustment, initialBytesToStrip, failFast);
    }

    @Override
    protected long getUnadjustedFrameLength(ByteBuf buf, int offset, int length, ByteOrder order) {
        buf = buf.order(order);
        long frameLength;
        frameLength = buf.getUnsignedInt(offset) + buf.getUnsignedInt(offset + 4);
        return frameLength;
    }

    /**
     *         发送数据格式                                         接收数据的格式
     * +---2----+--2--+---4--+--4--+----512---+---512-----+-----+-------+-------+-------+---------+
     *  type1  type2    len1   len2  data1[]   data2[] |  type1  type2 len1 len2  data1[]  data2[]
     *  short  short    int    int   byte[]    byte[]  |   type1  type2 len1 len2 data1    data2
     *+-------+---------+-----------+---------+----------+------------+-------------------------+
     */
}
