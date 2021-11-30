# 本目录主要描述IO相关知识点.
IO: input/output
## 推荐书籍(排名无先后)
1. Java网络编程
2. TCP-IP详解


## BIO (Blocking IO: 同步阻塞IO. 适用于低并发,短平快的应用程序)
* 同步阻塞式IO,数据的读写必须阻塞在一个线程内完成. 
* 服务端(server)阻塞接收客户端链接,每接收一个客户端(client)链接,server都需完成处理后才能接受下一个链接(可以使用多线程解决,注意线程数量控制和回收).


## NIO(Non-Blocking IO: 同步非阻塞IO)
从Java1.4开始支持. 面向缓冲区.
* Channel: 通道. 可以进行读写,与buffer进行数据交互
* ByteBuffer: 缓冲区. 负责数据的存储.
* Selector: 选择器. 监听 channel 进行数据处理. 由事件(event)驱动

一个线程处理一个selector, 一个selector可以管理多个channel,一个channel对应一个buffer.
相当于使用一个线程处理多个客户端连接(区别于BIO).
Http2.0实现了多路复用.

## AIO