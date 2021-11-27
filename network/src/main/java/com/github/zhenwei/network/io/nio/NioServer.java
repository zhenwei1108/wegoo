package com.github.zhenwei.network.io.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioServer {


  public void server(int port) throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    //非阻塞
    serverSocketChannel.configureBlocking(false);
    ServerSocket socket = serverSocketChannel.socket();
    socket.bind(new InetSocketAddress(port));
    //打开Selector 处理channel
    Selector selector = Selector.open();
    //向 selector 注册 ServerSocket
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    ByteBuffer byteBuffer = ByteBuffer.wrap("hello word".getBytes(StandardCharsets.UTF_8));
    while (true) {
      //阻塞, 等待事件进入
      selector.select();
      //获取所有接收的事件
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey selectionKey = iterator.next();
        iterator.remove();
        //判断事件是否就绪
        if (selectionKey.isAcceptable()) {
          ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
          //接收客户端
          SocketChannel client = server.accept();
          client.configureBlocking(false);
          //将客户端注册到selector
          client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ,
              byteBuffer.duplicate());
          System.out.println("received client connect:" + client);

        }
        //检查套接字是否可以被写
        if (selectionKey.isWritable()) {
          SocketChannel client = (SocketChannel) selectionKey.channel();
          ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
          while (buffer.hasRemaining()) {
            if (client.write(buffer) == 0) {
              break;
            }
          }
          client.close();
        }

      }


    }


  }

}