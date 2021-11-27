package com.github.zhenwei.network.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioClient {

  public void client(String ip, int port) throws Exception {
    Selector selector = Selector.open();
    SocketChannel client = SocketChannel.open();
    client.configureBlocking(false);
    boolean connect = client.connect(new InetSocketAddress(ip, port));
    if (connect) {
      client.register(selector, SelectionKey.OP_READ);
    }
    byte[] data = "hello word".getBytes(StandardCharsets.UTF_8);
    ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);
    byteBuffer.put(data);
    client.write(byteBuffer);
    byteBuffer.clear();

    //阻塞接收应答
    selector.select();
    Set<SelectionKey> selectionKeys = selector.selectedKeys();
    Iterator<SelectionKey> iterator = selectionKeys.iterator();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    while (iterator.hasNext()) {
      SelectionKey selectionKey = iterator.next();
      if (selectionKey.isValid()) {

        if (selectionKey.isReadable()) {
          SocketChannel channel = (SocketChannel) selectionKey.channel();
          channel.read(buffer);
          byteBuffer.flip();
          data = new byte[buffer.remaining()];
          byteBuffer.get(data);
          System.out.println("接收应答为:" + new String(data));
          byteBuffer.clear();
        }

      }
    }
  }


}