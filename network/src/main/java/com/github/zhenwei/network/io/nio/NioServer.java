package com.github.zhenwei.network.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

  public void server(int port) throws Exception {
    //创建服务端 channel
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    //创建 selector
    Selector selector = Selector.open();
    //绑定端口, 设置非阻塞, 并注册至 selector 事件为收到链接
    serverSocketChannel.bind(new InetSocketAddress(port)).configureBlocking(false)
        .register(selector, SelectionKey.OP_ACCEPT);
    //等待客户端链接
    while (true) {
      //此方法阻塞, 或使用select(1000)等待1秒;
      selector.select();
      //获取到有事件发生的的集合
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      System.out.println("哇! 有事件发生:" + selectionKeys.size());
      Set<SelectionKey> keys = selector.keys();
      System.out.println("所有 SelectionKey 的数量:" + keys.size());
      /**
       * 此处必须使用iterator, 后续进行remove当前key
       * 防止多线程并发问题
       */
      Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
      while (selectionKeyIterator.hasNext()) {
        SelectionKey key = selectionKeyIterator.next();
        selectionKeyIterator.remove();
        //不用if-else, 一个channel可读同时也可写.
        //如果是链接 类型
        if (key.isAcceptable()) {
          System.out.println("当前:isAcceptable");
          //获取触发事件的 channel  因是Accept事件,所以此处为:ServerSocketChannel
          ServerSocketChannel channel = (ServerSocketChannel) key.channel();
          System.out.println("channel:" + channel);
          //获取客户端链接
          SocketChannel socketChannel = channel.accept();
          socketChannel.configureBlocking(false);
          //将收到的链接注册到 selector, 绑定事件 Read/write, 填充一个buffer
          socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE,
              ByteBuffer.allocate(1024));
          System.out.println("收到客户端链接:" + socketChannel.getLocalAddress().toString());

        }
        if (key.isReadable()) {
          System.out.println("当前:isReadable");
          //在读操作中,获取到 accept操作中,注册的客户端channel
          SocketChannel channel = (SocketChannel) key.channel();
          // 获取关联的buffer, 在accept操作注册时,填入的buffer
          ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
          int read = channel.read(byteBuffer);
          System.out.println("读取长度:" + read);
          //翻转buffer
          byteBuffer.flip();

          int remaining = byteBuffer.remaining();
          //此长度应同上面一致
          System.out.println("获取长度:" + remaining);
          byte[] data = new byte[remaining];
          byteBuffer.get(data);
          System.out.println("收到消息:" + new String(data));

        }
        if (key.isWritable()) {
          //所有channel随时都可写
          System.out.println("当前:isWritable");
        }
        if (key.isConnectable()) {
          System.out.println("当前:isConnectable");
        }
        if (key.isValid()) {
          System.out.println("当前:isValid");
        }

      }

    }

  }


}