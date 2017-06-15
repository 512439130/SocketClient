package com.yy.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by yy on 17-6-12.
 */
public class SocketClient {
    public static void main(String[] args) {
        SocketClient socketClient = new SocketClient();
        socketClient.start();
    }

    public void start() {
        //控制台输入
        BufferedReader inputReader = null;
        //从Socket输入
        BufferedReader reader = null;
        BufferedWriter writer = null;
        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1", 9898);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inputReader = new BufferedReader(new InputStreamReader(System.in));  //控制台输入
            startServerReplyListener(reader);
            String inputContent;
            int count = 0;
            //当输入结果不等于“bye”时，打印内容
            while (!(inputContent = inputReader.readLine()).equals("bye")) {
                writer.write(inputContent);  //将控制台数据写入输出流
                if (count % 2 == 0) {
                    writer.write("\n");
                }
                count++;
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {//使用结束后关闭
            try {
                inputReader.close();
                reader.close();
                writer.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 监听器(监听服务器时刻发送的消息)
     */
    public void startServerReplyListener(final BufferedReader reader) {
        new Thread(new Runnable() {  //开启一个子线程
            @Override
            public void run() {
                try {
                    String response;
                    while ((response = reader.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
