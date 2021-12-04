# 本目录主要描述IO相关知识点.
IO: input/output

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



## 零拷贝(无CPU拷贝)
可以大量减少上下文切换,CPU的校验和计算等.
* DMA: directory memory access.直接内存拷贝 不使用CPU.
* buffer 间拷贝需要用到CPU.
* NIO提供 transferTo 支持零拷贝.

常见文件操作:
1. 从磁盘 拷贝到 内核. DMA
2. 从内核 拷贝到 用户缓冲区(buffer).
3. 从用户缓冲区 拷贝到 网络缓冲区(socket).
4. 从网络缓冲区 拷贝到 协议栈. DMA

### mmap 内存映射优化
将文件映射到内存缓冲区, 用户空间可以共享此内存. 
### sendFile 
* linux 2.1 提供底层支持.提供sendFile函数.可以直接从内核拷贝到网络缓冲区.(CPU拷贝)
* 在linux 2.4 进行升级,可以从内核直接DMA拷贝到协议栈.
* 只有很少部分数据(偏移量,长度等),依旧需要从内核拷贝到网络缓冲区.可忽略.