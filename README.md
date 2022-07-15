# wegoo

one sdk, something about network.
实现透明加解密。 
类似网关，对服务所有出入的报文进行加解密。
类似ssl通道。


## basic 
Mainly to learn [netty](https://github.com/zhenwei1108/netty.git) and assist other network knowledge.



## step
1. 实现网络通信功能.
2. 普遍协议支持. 可以先做http协议的restful接口。以json格式接收处理报文。后续可以考虑xml或其他协议。
3. 使用proto试图抽象调用，完成序列化操作。
4. 处理数据的报文结构可以根据接口进行配置，不同报文的接口样式不一样。同时根据不同的接口请求转发至不同服务。
5.  
6. ...


## 旁路知识
1. 序列化: [proto buffer](https://developers.google.cn/protocol-buffers)