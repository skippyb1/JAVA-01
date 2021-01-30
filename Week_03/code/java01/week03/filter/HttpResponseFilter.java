package com.guanqp.java01.week03.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpResponseFilter {

    void filter(FullHttpResponse response);

}
