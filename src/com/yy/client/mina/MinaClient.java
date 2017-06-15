package com.yy.client.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * Created by yy on 17-6-16.
 * Mina客户端
 */
public class MinaClient {
    public static void main(String[] args) throws IOException {
        NioSocketConnector connector = new NioSocketConnector();
        connector.setHandler(new MyClientHanader());  //消息管理器
        connector.getFilterChain().addLast("codec_client",new ProtocolCodecFilter(new TextLineCodecFactory()));
        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1",9898));
        future.awaitUninterruptibly();

        IoSession session = future.getSession();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String inputContent;
        while(!(inputContent = inputReader.readLine()).equals("bye")){
            session.write(inputContent);
        }
    }
}
