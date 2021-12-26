package com.github.zhenwei.wegoo.network.netty.consumer;

import com.github.zhenwei.wegoo.network.netty.NettyChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import java.net.InetSocketAddress;
import java.util.function.Predicate;

public class DefaultConsumerChannelInitialzer extends NettyChannelInitializer {

  @Override
  protected void init(ChannelPipeline pipeline) throws Exception {
    //do nothing
    pipeline.addLast(new RuleBasedIpFilter(new DefaultIpFilterRule()));
  }

  private Predicate<Object> predicate;

  public DefaultConsumerChannelInitialzer predicate(Predicate<Object> predicate) {
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