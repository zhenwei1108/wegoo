package com.github.zhenwei.network.netty.nio.server;

import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;
import java.net.InetSocketAddress;

public class IpWhitelistFilterRule implements IpFilterRule {

  @Override
  public boolean matches(InetSocketAddress remoteAddress) {

    return !"127.0.0.1".equalsIgnoreCase(remoteAddress.getAddress().getHostAddress());
  }

  /**
   * 只允许本机访问
   *
   */
  @Override
  public IpFilterRuleType ruleType() {
    return IpFilterRuleType.REJECT;
  }
}