package com.github.zhenwei.wegoo.network.netty.provider.listener;

import com.github.zhenwei.wegoo.common.enums.NetworkExceptionEnum;
import com.github.zhenwei.wegoo.common.exception.NetworkException;
import io.netty.channel.DefaultChannelPromise;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @description: 绑定端口监听
 * @date: 2021/12/23 22:37
 */
public class NettyProviderBindListener implements GenericFutureListener<DefaultChannelPromise> {

  @Override
  public void operationComplete(DefaultChannelPromise future) throws Exception {
    if (future.isSuccess()){

    }else {
      throw new NetworkException(NetworkExceptionEnum.CLIENT_BIND_ERR);
    }

  }
}