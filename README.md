# wegoo
`类似数据安全网关，可以对服务所有出入的报文进行加解密。`

数据安全的保护力度越来越大,顺应潮流写点自己感兴趣的东西,顺道自我学习.

## one sdk, something about network.
核心为三个模块,① 网络通信模块. ② 数据保护模块. ③综合处理模块.
1. 网络通信模块:负责数据解析和封装,简化和完善网络调用.
2. 数据保护模块:负责数据拆封箱,保证数据的完整性,安全性,防篡改.
3. 综合处理模块:负责各种系统配置及定义标准的使用逻辑.


## basic 
1. 网络通信模块由: [netty](https://github.com/zhenwei1108/netty.git) 实现.
2. 数据保护模块由: [java-bc(bouncycastle)](https://github.com/zhenwei1108/bc-java.git) 实现 



## step
1. 实现网络通信功能.
2. 普遍协议支持. 可以先做http协议的restful接口。以json格式接收处理报文。后续可以考虑xml或其他协议。
3. 使用proto试图抽象调用，完成序列化操作。
4. 处理数据的报文结构可以根据接口进行配置，不同报文的接口样式不一样。同时根据不同的接口请求转发至不同服务。
5.  
6. ...


## 旁路知识
1. 序列化: [proto buffer](https://developers.google.cn/protocol-buffers)