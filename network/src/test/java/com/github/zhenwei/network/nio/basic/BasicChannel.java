package com.github.zhenwei.network.nio.basic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.val;

/**
 * {@link java.nio.channels.Channel}
 */
public class BasicChannel {

  public void fileChannel(boolean useBuffer) throws IOException {

    File file = new File("/opt/data/channelDemo");
    //文件目录, 追加写
    FileOutputStream outputStream = new FileOutputStream(file, true);
    FileChannel outChannel = outputStream.getChannel();

    FileInputStream inputStream = new FileInputStream(file);
    FileChannel inChannel = inputStream.getChannel();

    /**
     * 直接在内存中修改
     * 模式, 起始位置, 长度
     */
    MappedByteBuffer map = inChannel.map(MapMode.READ_WRITE, 0, 1);
    map.put(0, (byte) 1);

//    byte[] bytes = "this is test".getBytes(StandardCharsets.UTF_8);
//    buffer.put(bytes);
//    //读写转换
//    buffer.flip();

//    outChannel.write(buffer);

//    buffer.clear();
    ByteBuffer buffer = ByteBuffer.allocate(3);

    if (!useBuffer) {
      inChannel.transferFrom(outChannel, 0, inChannel.size());
    } else {

      while (inChannel.read(buffer) != -1) {
        //转换
        buffer.flip();
        if (buffer.remaining() > 0) {
          System.out.println("开始写");
          outChannel.write(buffer);
          //复位
          buffer.clear();
        }
      }

    }
    inputStream.close();
    outputStream.close();

  }

  /**
   * 基于 {@link java.nio.channels.NetworkChannel} 实现端口绑定 具体实现者为:
   *
   * @throws IOException
   * @see sun.nio.ch.ServerSocketChannelImpl
   * @see sun.nio.ch.SocketChannelImpl
   */
  public void SocketChannel(int port) throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    SocketAddress socketAddress = new InetSocketAddress(port);
    serverSocketChannel.bind(socketAddress);
    System.out.println("启动端口:" + port);
    //可以使用telnet发送数据
    SocketChannel channel = serverSocketChannel.accept();
    val buffers = new ArrayList<ByteBuffer>();
    buffers.add(ByteBuffer.allocate(10));
    buffers.add(ByteBuffer.allocate(20));
    buffers.add(ByteBuffer.allocate(30));
    val byteBuffers = buffers.toArray(new ByteBuffer[0]);
    channel.read(byteBuffers);

    Arrays.stream(byteBuffers).forEach(System.out::println);

    Arrays.stream(byteBuffers).forEach(buf -> {
      buf.flip();
      byte[] bytes = new byte[buf.remaining()];
      buf.get(bytes);
      System.out.println(new String(bytes));
    });

//    SocketChannel socketChannel = SocketChannel.open();
//    socketAddress = new InetSocketAddress("localhost",18088);
//    socketChannel.connect(socketAddress);

  }


  public static void main(String[] args) throws Exception {
    BasicChannel basicChannel = new BasicChannel();
//    basicChannel.fileChannel(false);
    basicChannel.SocketChannel(18088);
  }


  class MineChannel implements ReadableByteChannel, WritableByteChannel {

    @Override
    public int read(ByteBuffer dst) throws IOException {
      return 0;
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
      return 0;
    }

    @Override
    public boolean isOpen() {
      return false;
    }

    @Override
    public void close() throws IOException {

    }
  }

}