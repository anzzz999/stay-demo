package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 其实现要点是：
 * 服务器端启动ServerSocket，端口0表示自动绑定一个空闲端口。
 * 调用accept方法，阻塞等待客户端连接。
 * 利用Socket模拟了一个简单的客户端，只进行连接、读取、打印。
 * 当连接建立后，启动一个单独线程负责回复客户端请求。
 * 这样，一个简单的Socket服务器就被实现出来了。
 */
public class DemoServer extends Thread {
    static ExecutorService executor = Executors.newFixedThreadPool(10);
    private ServerSocket serverSocket;

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {

            serverSocket = new ServerSocket(0);
            while (true) {
                Socket socket = serverSocket.accept();
                RequesHandler requesHandler = new RequesHandler(socket);
                executor.execute(requesHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DemoServer server = new DemoServer();
        server.start();
        while (true) {
            try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
                BufferedReader buferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                buferedReader.lines().forEach(s -> System.out.println(s));
            }
            Thread.sleep(1000);
        }
    }
}

// 简化实现，不做读取，直接发送字符串
class RequesHandler extends Thread {
    private Socket socket;

    RequesHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream());) {
            out.println("Hello world!");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}