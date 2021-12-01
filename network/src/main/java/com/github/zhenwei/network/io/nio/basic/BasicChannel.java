package com.github.zhenwei.network.io.nio.basic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * {@link java.nio.channels.Channel}
 */
public class BasicChannel {

  public void fileChannel() throws IOException {

    File file = new File("/opt/data/channelDemo");
    //文件目录, 追加写
    FileOutputStream outputStream = new FileOutputStream(file, true);
    FileChannel outChannel = outputStream.getChannel();

    FileInputStream inputStream = new FileInputStream(file);
    FileChannel inChannel = inputStream.getChannel();

//    byte[] bytes = "this is test".getBytes(StandardCharsets.UTF_8);
//    buffer.put(bytes);
//    //读写转换
//    buffer.flip();

//    outChannel.write(buffer);

//    buffer.clear();
    ByteBuffer buffer = ByteBuffer.allocate(3);
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


  public static void main(String[] args) throws IOException {
    BasicChannel basicChannel = new BasicChannel();
    basicChannel.fileChannel();
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