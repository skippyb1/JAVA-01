# 使用netty实现后端访问

1. Http后台结果分两次进入ChannelInboundHandlerAdapter，一次HttpResponse 一次HttpContent
2. 后端服务返回ByteBuf 需要使用ByteBuf内部API复制比特数组，否则抛出异常
3. 浏览器请求->Netty服务器->Netty客户端->Http后台 Netty客户端代码结构有欠缺 整体逻辑较完整