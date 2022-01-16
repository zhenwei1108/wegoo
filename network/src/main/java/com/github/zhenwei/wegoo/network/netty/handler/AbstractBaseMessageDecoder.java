package com.github.zhenwei.wegoo.network.netty.handler;

import com.github.zhenwei.wegoo.network.netty.serialize.NettySerializer;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.Data;

@Data
public abstract class AbstractBaseMessageDecoder<T> extends ReplayingDecoder<Void> {

  private NettySerializer<T> serializer;

}