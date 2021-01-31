package com.guanqp.java01.week03.outbound.netty4;


import com.guanqp.java01.week03.filter.HeaderHttpResponseFilter;
import com.guanqp.java01.week03.filter.HttpResponseFilter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpResponse;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyHttpClientOutboundHandler  extends ChannelInboundHandlerAdapter {

    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    ChannelHandlerContext originCtx;
    public NettyHttpClientOutboundHandler(ChannelHandlerContext originCtx){
        this.originCtx = originCtx;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {


        if (msg instanceof HttpResponse)
        {
            HttpResponse response=(HttpResponse) msg;
        }
        if (msg instanceof HttpContent)
        {
            ctx.handler();
            HttpContent content = (HttpContent)msg;
            ByteBuf buf = content.content();
            FullHttpResponse response = null;
            try {
//            String value = "hello,kimmking";
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", response.content().readableBytes());


//            System.out.println(new String(body));
//            System.out.println(body.length);
                byte[] body = new byte[buf.readableBytes()];
                buf.readBytes(body);
                buf.release();

                response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

                response.headers().set("Content-Type", "text/plain");
                response.headers().setInt("Content-Length",body.length);
                filter.filter(response);
//            for (Header e : endpointResponse.getAllHeaders()) {
//                //response.headers().set(e.getName(),e.getValue());
//                System.out.println(e.getName() + " => " + e.getValue());
//            }

            } catch (Exception e) {
                e.printStackTrace();
                response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
                exceptionCaught(ctx, e);
            } finally {
                originCtx.writeAndFlush(response);
                //ctx.close();
            }
        }
    }


}
