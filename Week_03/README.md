# 使用netty实现后端访问

1. Http后台结果分两次进入ChannelInboundHandlerAdapter，一次HttpResponse 一次HttpContent
2. 后端服务返回ByteBuf 需要使用ByteBuf内部API复制比特数组，否则抛出异常
3. Netty服务器->Netty客户端->Http后台 InBound OutBound 顺序不清晰，最后还是没实现