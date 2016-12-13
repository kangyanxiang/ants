package com.awifi.toe.dbservice.head;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**   
 * @Description:  
 * @Title: PollService.java 
 * @Package com.awifi.toe.dbservice 
 * @author kangyanxiang 
 * @date 2016年12月5日 上午10:16:36
 * @version V1.0   
 */
public class PollService extends HttpServlet{
 
    /** 端口  */
    private static final int port = 8090;
    
    /** 日志 */
    private Log logger = LogFactory.getLog(PollService.class);
    
    public PollService() {
        
        ServerBootstrap bootstrap = new ServerBootstrap();// 引导辅助程序
        //通过nio方式来接收连接和处理连接
        NioEventLoopGroup group = new NioEventLoopGroup(4);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(8);
        try {
            bootstrap.group(group);
            bootstrap.channel(NioServerSocketChannel.class);        //设置nio类型的channel
            bootstrap.localAddress(new InetSocketAddress(port));    //设置监听端口
            
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {//有连接到达时会创建一个channel
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // pipeline管理channel中的Handler，在channel队列中添加一个handler来处理业务
                            ch.pipeline().addLast("myHandler", new EchoServerHandler());
                        }
                    });
            //配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture f = bootstrap.bind().sync();
            logger.debug("提示：服务已经启动，监听端口= " + f.channel().localAddress());
            //应用程序会一直等待，直到channel关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            try {
                group.shutdownGracefully().sync();
                workGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}
