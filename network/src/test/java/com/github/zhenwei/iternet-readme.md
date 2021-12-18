# network
## 推荐书籍(排名无先后)
1. Java网络编程
2. TCP-IP详解
3. Scalable IO in Java
## 分类
* BIO/NIO/AIO. [点我,看详细介绍](network/io/io-readme.md)
* Netty主要基于NIO实现.
## 网络分层

* 应用层
* 传输层
* 网络层
* 物理层

## IP和域名
* IPV4网络中的计算机,都是由4个字节的数字标识,一般分四段,即每段占用一个字节(八位),最大为255.
* IPV6使用16字节标识地址,通常写为冒号分割的8个区块,每个区块2个字节,常用4个16进制数字表示.最大为 0xFFFF .
* 有时IPV6的后4个字节使用IPV4进行标识. 从JDK1.4开始支持IPV6
* 域名(DNS),用来便于人类记忆的主机名,其为计算机IP的映射.

## 端口
计算机为了同时可以进行多线程工作,对外的IP无法映射到每个线程,所以使用端口进行区分.
每个传输层协议有 65535 个端口(linux的文件句柄数). 1~1023端口一般给设备自己使用.