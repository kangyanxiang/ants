package com.awifi.toe.dbservice.head;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.DefaultChannelPromise;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.awifi.toe.dbservice.service.DbService;

/**   
 * @Description:  
 * @Title: EchoServerHandler.java 
 * @Package com.awifi.toe.dbservice 
 * @author kangyanxiang 
 * @date 2016年12月5日 上午11:08:22
 * @version V1.0   
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

    /** 日志 */
    private Log logger = LogFactory.getLog(EchoServerHandler.class);
    
    //数据标识
    private static String DATA_IDENTIFICATION = "6771";
    
    //数据成功包尾
    private static String DATA_SUCCESSFUL_TAIL = "800A01AA55";
    
    //数据失败包尾
//  private static String DATA_FAIL_TAIL = "800AFFAA55";
    
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException { 
        ByteBuf buf = (ByteBuf) msg; 
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        if(body.length() == 24){
            heartPing(ctx,body);
        } else if (body.length() > 24){
            dataPing(ctx,body);
            new DbService(body);
        }
    } 

    /**
     * 心跳机制
     * @param ctx
     * @param body
     * @author kangyanxiang 
     * @date Dec 2, 2016 11:02:42 AM
     */
    private void heartPing(ChannelHandlerContext ctx, String body) {
        String data = body.substring(0,8)+DATA_IDENTIFICATION+body.substring(12,body.length());
        ping(ctx, data, "心跳机制:");
    }

    /**
     * 数据确认
     * @param ctx
     * @param body
     * @author kangyanxiang 
     * @date Dec 2, 2016 10:57:57 AM
     */
    private void dataPing(ChannelHandlerContext ctx, String body) {
        String data = body.substring(0,20)+DATA_SUCCESSFUL_TAIL;
        ping(ctx, data, "数据心跳机制:");
    }

    /**
     * 返回数据--私有方法
     * @param ctx
     * @param body
     * @author kangyanxiang 
     * @date Dec 2, 2016 10:57:40 AM
     */
    private void ping(ChannelHandlerContext ctx,String data,final String message){
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        logger.debug("提示："+message+data);
        byteBuf.writeBytes(data.getBytes()); 
        ctx.writeAndFlush(byteBuf,new DefaultChannelPromise(ctx.channel()){
            @Override
            public boolean trySuccess() {
                logger.debug("提示："+message+"返回到设备成功...");
                return super.trySuccess();
            }
        });
    }
    
    /**
     * 保存到数据库
     * @param body 
     * @author kangyanxiang 
     * @date Dec 2, 2016 11:18:19 AM
     */
    private void saveDb(String body) {
        // TODO Auto-generated method stub
        
    }
    
    public void channelReadComplete(ChannelHandlerContext ctx) { 
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);  //flush掉所有写回的数据
//      .addListener(ChannelFutureListener.CLOSE); //当flush完成后关闭channel
    } 
    
    
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) { 
        cause.printStackTrace();//捕捉异常信息
        ctx.close();            //出现异常时关闭channel 
    }
    
}
