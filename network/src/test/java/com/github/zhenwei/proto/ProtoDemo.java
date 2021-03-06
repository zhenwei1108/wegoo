package com.github.zhenwei.proto;

import com.github.zhenwei.proto.MineMessage.MyMessage;
import com.github.zhenwei.proto.MineMessage.MyMessage.Type;
import com.github.zhenwei.proto.MineMessage.Person;
import com.github.zhenwei.proto.MineMessage.Worker;
import org.junit.Test;

public class ProtoDemo {

  /**
   * netty中参考
   * @see io.netty.handler.codec.protobuf.ProtobufDecoder
   */
  @Test
  public  void protoTest() {
    Worker worker = Worker.newBuilder().setWorkerTime("now").setHandle("daza").build();
    Person person = Person.newBuilder().setAddr("tiantang").setName("wangwu").setAge(3).build();

    MyMessage myMessage = MyMessage.newBuilder()
        .setType(Type.PERSON_TYPE)
        .setPerson(person)
//        .setWorker(worker) //会被覆盖
        .setMessage("this is test")
        .build();

    System.out.println(myMessage);
    System.out.println(myMessage.getType());
  }

}