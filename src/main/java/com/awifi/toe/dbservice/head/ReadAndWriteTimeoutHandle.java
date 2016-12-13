package com.awifi.toe.dbservice.head;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutException;

/**
 * 读写期间在一定时间内空闲
 * 则关闭链接
 * @author kangyanxiang
 * Dec 2, 2016 11:25:10 AM
 */
public class ReadAndWriteTimeoutHandle extends IdleStateHandler{

	boolean wirteTimeOut = false;
	boolean readTimeOut = false;
	
	private boolean closed;

	public ReadAndWriteTimeoutHandle(int  timeout){
		super(timeout, timeout, timeout);
	}
	 
	public ReadAndWriteTimeoutHandle(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
		super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
	}

	@Override
	protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
		
		super.channelIdle(ctx, evt);
		boolean closeit = false;
		switch (evt.state()) {
			case READER_IDLE:
				readTimeOut = true;
				closeit = wirteTimeOut;
				break;
			case WRITER_IDLE:
				wirteTimeOut=true;
				closeit=readTimeOut;
				break;
			default:
				break;
		}
		
		if(closeit && !closed){
			ctx.fireExceptionCaught(ReadTimeoutException.INSTANCE);
			ctx.close();
			closed = true;
		}
	}

}
