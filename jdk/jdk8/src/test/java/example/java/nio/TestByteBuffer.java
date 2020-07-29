package example.java.nio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * @author changgongzh
 * @description ByteBuffer示例
 * @created 2020-07-29 23:25
 */
public class TestByteBuffer {
    private static Logger logger = LogManager.getLogger(TestByteBuffer.class);

    @Test
    public void test() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        // 获取channel
        FileChannel inChannel = aFile.getChannel();

        // 申请一个Buffer,容量为 48 字节
        ByteBuffer buf = ByteBuffer.allocate(48);
        // 把数据从channel读取出来，写入到buffer中
        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {

            // buffer切换为读模式
            buf.flip();

            while(buf.hasRemaining()){
                // 一次读取 1 字节
                System.out.print((char) buf.get());
            }
            // 清空buffer
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    @Test
    public void testAllocate() {
        // 申请Buffer使用allocate方法，比如分配一个1024字节的buffer
        CharBuffer buf = CharBuffer.allocate(1024);
    }

    @Test
    public void testWrite() throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        // 向buffer写入数据有两种方式
        // 1、从channel读取数据并写入buffer
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        // 获取channel
        FileChannel inChannel = aFile.getChannel();
        int bytesRead = inChannel.read(buf);

        // 2、通过buffer的put方法，put方法有很多重载方法
        buf.put("str".getBytes());
    }
}
