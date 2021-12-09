package com.github.zhenwei.network.netty.nio.client.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientFutureHolder {

  private static Map<Object, ClienMessageFuture> map = new ConcurrentHashMap<>(10);


  public static void add(Object key, ClienMessageFuture future) {
    System.out.println("缓存处理:" + key);
    map.put(key, future);
  }

  public static void success(Object key, String message) {
    if (map.containsKey(key)) {
      ClienMessageFuture future = map.get(key);
      future.setSuccess(message);
    }
  }

  public static void fail(Object key,Throwable throwable){
    if (map.containsKey(key)) {
      map.get(key).setFailure(throwable);
      map.remove(key);
    }
  }

}