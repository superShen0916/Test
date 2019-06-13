package nio;

import java.nio.ByteBuffer;

/**
 * 
 *
 * @date 2018-08-20
 * @author shenpeng
 */
public class Buffer {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        System.out.println("limt:" + byteBuffer.limit() + " capacity:" + byteBuffer.capacity()
                + " position:" + byteBuffer.position());

        for (int i = 0; i < 10; i++) {
            byteBuffer.put((byte) i);
        }
        System.out.println("limt:" + byteBuffer.limit() + " capacity:" + byteBuffer.capacity()
                + " position:" + byteBuffer.position());

        byteBuffer.flip();
        System.out.println("limt:" + byteBuffer.limit() + " capacity:" + byteBuffer.capacity()
                + " position:" + byteBuffer.position());

        byteBuffer.get();
        System.out.println("limt:" + byteBuffer.limit() + " capacity:" + byteBuffer.capacity()
                + " position:" + byteBuffer.position());

        byteBuffer.flip();
        System.out.println("limt:" + byteBuffer.limit() + " capacity:" + byteBuffer.capacity()
                + " position:" + byteBuffer.position());

        byteBuffer.flip();//读写切换时调用
        byteBuffer.clear();//为重新写入Buffer作准备
        byteBuffer.rewind();//为读取Buffer中有效数据作准备
    }

}
