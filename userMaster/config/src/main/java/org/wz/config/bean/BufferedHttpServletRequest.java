package org.wz.config.bean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @project: userMaster
 * @package: org.wz.config.bean
 * @author: Administrator
 * @crDate: 2019/3/25 11:54
 * @editor: IntelliJ IDEA
 * @role:
 **/
public class BufferedHttpServletRequest extends HttpServletRequestWrapper {

    private ByteBuf buffer;

    private final AtomicBoolean isCached = new AtomicBoolean();

    public BufferedHttpServletRequest(HttpServletRequest request, int initialCapacity) {
        super(request);
        int contentLength = request.getContentLength();
        int min = Math.min(initialCapacity, contentLength);
        if (min < 0) {
            buffer = Unpooled.buffer(0);
        } else {
            buffer = Unpooled.buffer(min, contentLength);
        }
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        //Only returning data from buffer if it is readonly, which means the underlying stream is EOF or closed.
        if (isCached.get()) {
            return new NettyServletInputStream(buffer);
        }
        return new ContentCachingInputStream(super.getInputStream());
    }

    public void release() {
        buffer.release();
    }

    private class ContentCachingInputStream extends ServletInputStream {

        private final ServletInputStream is;

        public ContentCachingInputStream(ServletInputStream is) {
            this.is = is;
        }

        @Override
        public int read() throws IOException {
            int ch = this.is.read();
            if (ch != -1) {
                //Stream is EOF, set this buffer to readonly state
                buffer.writeByte(ch);
            } else {
                isCached.compareAndSet(true, false);
            }
            return ch;
        }

        @Override
        public void close() throws IOException {
            //Stream is closed, set this buffer to readonly state
            try {
                is.close();
            } finally {
                isCached.compareAndSet(false, true);
            }
        }

        @Override
        public boolean isFinished() {
            throw new UnsupportedOperationException("Not yet implemented!");
        }

        @Override
        public boolean isReady() {
            throw new UnsupportedOperationException("Not yet implemented!");
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new UnsupportedOperationException("Not yet implemented!");
        }
    }
}
