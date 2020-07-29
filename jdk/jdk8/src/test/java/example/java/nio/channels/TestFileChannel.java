package example.java.nio.channels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author changgongzh
 * @description FileChannel示例
 * @created 2020-07-29 23:14
 */
public class TestFileChannel {
    private static Logger logger = LogManager.getLogger(TestFileChannel.class);

    @Test
    public void test() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        // 获取channel
        FileChannel inChannel = aFile.getChannel();
        // 申请一个Buffer，空间为48字节
        ByteBuffer buf = ByteBuffer.allocate(48);
        // 把数据从channel读取出来，写入到buffer中
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            // buffer切换为读模式
            buf.flip();
            // 还没读取完
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
}
