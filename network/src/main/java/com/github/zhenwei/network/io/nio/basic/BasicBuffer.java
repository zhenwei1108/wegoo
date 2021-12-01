package com.github.zhenwei.network.io.nio.basic;

import lombok.val;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Buffer学习
 * {@link java.nio.Buffer}
 * 关键参数  mark(标记), position(当前位置), limit(当前缓冲容量,可读或可写到到到到的数量), capacity(总容量)
 * 内涵是个数组
 */
public class BasicBuffer {


    public ByteBuffer getByteBuffer(int length) {
        //设置 buffer 大小
        val buffer = ByteBuffer.allocate(length);
        //是否有剩余
        boolean hasRemaining = buffer.hasRemaining();
        printBufferParam(buffer);
        System.out.println("hasRemaining:" + hasRemaining);
        buffer.put((byte) 1);
        buffer.put((byte) 1);

        /**
         * 调用mark, 会记录当前位置 position
         */
        buffer.mark();
        /**
         * 将 mark 返还给 position
         */
        buffer.reset();


        printBufferParam(buffer);
        /**
         * 读写切换,翻转缓冲区
         * 每次调用 flip, 会将 position 赋值给 limit
         * 然后 position = 0
         */
        buffer.flip();
        System.out.println("调用:flip");

        printBufferParam(buffer);

        buffer.flip();
        //读消息
        buffer.get();

        //重置
        buffer.clear();
        System.out.println("调用:flip");
        printBufferParam(buffer);
        return buffer;
    }


    private void printBufferParam(Buffer buffer) {
        int limit = buffer.limit();
        System.out.println("buffer-limit:" + limit);
        int position = buffer.position();
        System.out.println("buffer-position:" + position);
        int capacity = buffer.capacity();
        System.out.println("buffer-capacity:" + capacity);

    }

    public static void main(String[] args) {
        BasicBuffer basicBuffer = new BasicBuffer();
        //不会自动扩容
        basicBuffer.getByteBuffer(1024);
    }


}