# netty
## 模型
1. 主要有两种 EventLoopGroup: worker(负责接收客户端链接)和 boss(负责网络读写).
2. EventLoopGroup中包含一定数量的NioEventLoop. 具体实现参见源码:
```java
/**
 * {@link io.netty.channel.nio.NioEventLoopGroup#newChild(java.util.concurrent.Executor, java.lang.Object...)} }
 * 生成一定数量的 NioEventLoop
 */
```
3. 每个 NioEventLoop 对应一个Selector,用于监听绑定的 Channel发生的事件.
4. Boss 维护的 Selector,负责接收(Accept)事件.
5. 当Boss接收到事件,获取到 SocketChannel,并进行NIOSocketChannel封装.
6. 将 NIOSocketChannel 注册到 Worker的Selector中.由Worker关注后续IO操作 如:Read/Write.
7. Worker 将事件分配给 ChannelPipeline 的 Handler 队列 进行顺序处理.
8. Handler进行具体业务处理.