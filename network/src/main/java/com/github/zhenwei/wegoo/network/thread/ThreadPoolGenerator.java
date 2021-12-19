package com.github.zhenwei.wegoo.network.thread;

import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolGenerator {

  public static ThreadPoolExecutor generate(){
    return new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,new LinkedBlockingQueue<>(1),new DefaultThreadFactory("default thread pool factory"));
  }

}