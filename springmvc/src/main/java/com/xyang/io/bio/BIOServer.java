package com.xyang.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @描述 BIO 同步阻塞IO-一个连接一个线程<br>
 *     一个连接一个线程，即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销， 可通过线程池机制改善
 * @date 2017年3月1日-下午5:38:00
 * @author IBM
 *
 */
public class BIOServer {
	private static final int port = 8080;

	public static void main(String args[]) {
		try {
			// 1.创建一个ServerSocket监听8080端口
			ServerSocket server = new ServerSocket(port);
			// 2.监听请求
			Socket socket = server.accept();
			// 3.接收到请求后使用socket进行通信，创建BufferedReader用于读取数据，
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = is.readLine();
			System.out.println("received from client: " + line);

			// 创建PrintWriter，用于发送数据
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println("received data: " + line);
			pw.flush();
			// 关闭资源

			pw.close();
			is.close();
			socket.close();
			server.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
