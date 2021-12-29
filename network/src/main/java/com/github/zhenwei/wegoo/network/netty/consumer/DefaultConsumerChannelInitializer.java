package com.github.zhenwei.wegoo.network.netty.consumer;

import com.github.zhenwei.wegoo.common.util.ArraysUtil;
import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import com.github.zhenwei.wegoo.network.netty.handler.AbstractDecoder;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * @author zhangzhenwei
 */
public class DefaultConsumerChannelInitializer extends NettyChannelInitializer {

  ChannelInboundHandler[] handlers;

  /**
   * @param [pipeline]
   * @return void
   * @author zhangzhenwei
   * @description 初始化操作, 有客户端连接时触发
   * @date 2021/12/28 23:03
   */
  @Override
  protected void init(ChannelPipeline pipeline) throws Exception {
    //do nothing
    if (predicate != null) {
      pipeline.addLast(new RuleBasedIpFilter(new DefaultIpFilterRule()));
    }
    if (handlers != null) {
      Iterator<ChannelInboundHandler> handlerIterator = Arrays.stream(handlers).iterator();
      while (handlerIterator.hasNext()) {
        ChannelInboundHandler handler = handlerIterator.next();
        if (handler instanceof AbstractDecoder) {
          pipeline.addLast(handler);
//          handlerIterator.remove();
        }
      }
      pipeline.addLast(handlers);
    }
  }

  public DefaultConsumerChannelInitializer addHandler(ChannelInboundHandler... handlers) {
    if (ArraysUtil.isEmpty(handlers)) {
      return this;
    }
    if (this.handlers != null) {
      ChannelInboundHandler[] inboundHandlers = new ChannelInboundHandler[
          this.handlers.length + handlers.length];
      System.arraycopy(this.handlers, 0, inboundHandlers, 0, this.handlers.length);
      System.arraycopy(handlers, 0, inboundHandlers, this.handlers.length, handlers.length);
      this.handlers = inboundHandlers;
      return this;
    }
    this.handlers = handlers;

    return this;
  }

  private Predicate<Object> predicate;

  public DefaultConsumerChannelInitializer predicate(Predicate<Object> predicate) {
    this.predicate = predicate;
    return this;
  }

  class DefaultIpFilterRule implements IpFilterRule {

    /**
     * @param [remoteAddress]
     * @return boolean
     * @author zhangzhenwei
     * @description if return "true" , will {@link IpFilterRuleType.REJECT} client connect.
     * @date 2021/12/26 22:00
     */
    @Override
    public boolean matches(InetSocketAddress remoteAddress) {
      if (predicate != null) {
        return predicate.test(remoteAddress.getHostName());
      }
      return false;
    }

    @Override
    public IpFilterRuleType ruleType() {
      return IpFilterRuleType.REJECT;
    }
  }
}