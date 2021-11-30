package com.github.zhenwei.network.netty.nio.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;

import java.net.InetSocketAddress;

/**
 * @author zhenwei
 * {@link IpFilterRule}
 * {@link io.netty.handler.ipfilter.RuleBasedIpFilter#accept(ChannelHandlerContext, InetSocketAddress)}
 *
 * matches方法, 若返回true,则进行自定义的规则判断. ruleType()
 */
public class IpWhitelistFilterRule implements IpFilterRule {

    @Override
    public boolean matches(InetSocketAddress remoteAddress) {
        System.out.println("IP-Filter:" + remoteAddress.getHostName());
        return !"127.0.0.1".equalsIgnoreCase(remoteAddress.getAddress().getHostAddress());
    }

    /**
     * 只允许本机访问
     */
    @Override
    public IpFilterRuleType ruleType() {
        return IpFilterRuleType.REJECT;
    }
}