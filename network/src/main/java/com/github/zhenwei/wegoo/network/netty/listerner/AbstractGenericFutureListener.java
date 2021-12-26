package com.github.zhenwei.wegoo.network.netty.listerner;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public abstract class AbstractGenericFutureListener<F extends Future<?>> implements
    GenericFutureListener<F> {

  @Override
  public void operationComplete(F future) throws Exception {
    if (future.isSuccess()) {
      startSuccess();
    } else {
      startFail();
    }
  }

  /**
   * @param []
   * @return void
   * @author zhangzhenwei
   * @description 监听任务成功执行此操作
   * you can override this method
   * @date 2021/12/26 21:23
   */
  public abstract void startSuccess();

  /**
   * @param []
   * @return void
   * @author zhangzhenwei
   * @description 监听任务失败执行此操作
   * you can override this method
   * @date 2021/12/26 21:23
   */
  public abstract void startFail();

}