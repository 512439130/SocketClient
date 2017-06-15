package com.yy.client.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by yy on 17-6-14.
 * 会话管理和消息处理(处理消息收到和发出)
 */

public class MyClientHanader extends IoHandlerAdapter {
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        //会话创建时
        System.out.println("sessionCreated");
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        //会话打开时
        System.out.println("sessionOpened");
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        //客户端和服务器的会话关闭时
        System.out.println("sessionClosed");
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        //会话进入空闲状态时(服务器定义空闲状态位)
        System.out.println("sessionIdle");
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        //网络连接出现异常时
        System.out.println("exceptionCaught");
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        //收到消息时
        String str = (String) message;
        System.out.println("messageReceived:" + str);  //使用自定义解码器后ln导致多打印一行
        
        super.messageReceived(session, message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        //发出一条消息时
        System.out.println("messageSent");
        super.messageSent(session, message);
    }
}
