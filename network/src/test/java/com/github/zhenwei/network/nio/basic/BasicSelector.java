package com.github.zhenwei.network.nio.basic;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import lombok.val;

/**
 * Selector 可以检测多个注册到通道上是否有事件发生.
 * 多个channel可以注册到一个Selector中
 * {@link java.nio.channels.Selector}
 * @see java.nio.channels.SelectionKey 其中可以获取到channel
 *
 *
 */
public class BasicSelector {


  public void selector() throws IOException {

    Selector selector = Selector.open();


    SocketChannel socketChannel = SocketChannel.open();
    //将channel注册到selector上, 并设置感兴趣的事件.
    SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT);

    //阻塞方法,
    val select = selector.select();
    Set<SelectionKey> selectionKeys = selector.selectedKeys();

    selectionKeys.forEach(key->{
      /**
       *
       * 获取到有链接的channel 等同
       * {@linkplain ServerSocketChannel#accept()}
       */
      SelectableChannel channel = key.channel();

    });


  }



}