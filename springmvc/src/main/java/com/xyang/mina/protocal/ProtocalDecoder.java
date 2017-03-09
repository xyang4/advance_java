package com.xyang.mina.protocal;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocalDecoder implements ProtocolDecoder {
	private static final Logger logger = LoggerFactory.getLogger(ProtocalDecoder.class);

	private final static AttributeKey context = new AttributeKey(ProtocalDecoder.class, "context");
	private Charset charset = Charset.defaultCharset();
	private int maxPackLength = 100;

	public ProtocalDecoder(Charset charset) {
		this.charset = charset;
	}

	public Context getContext(IoSession session) {
		Context ctx = (Context) session.getAttribute(context);
		if (null == ctx) {
			ctx = new Context();
			session.setAttribute(context, ctx);
		}
		return ctx;

	}

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		final int packHeadLength = 5;
		Context ctx = getContext(session);
		ctx.append(in);
		IoBuffer buf = ctx.getIoBuffer();
		buf.flip();
		while (buf.remaining() >= packHeadLength) {// 缓冲区有有效数据
			buf.mark();
			int length = buf.getInt();
			byte flag = buf.get();
			if (length < 0 || length > maxPackLength) { // 无效数据清空
				buf.reset();
				break;
			} else if (length >= packHeadLength && length - packHeadLength <= buf.remaining()) {// 存在完整数据
				int oldLimit = buf.limit();
				buf.limit(buf.position() + length - packHeadLength);
				String content = buf.getString(ctx.getDecoder());
				buf.limit(oldLimit);
				ProtocalPack pakeage = new ProtocalPack(flag, content);
				out.write(pakeage);
			} else { // 数据不完整的就缓存
				buf.clear();
				break;
			}
			if (buf.hasRemaining()) {// 是否还有数据，有就追加
				IoBuffer tmp = IoBuffer.allocate(maxPackLength).setAutoExpand(true);
				tmp.put(buf);
				buf.flip();
				buf.reset();
				buf.put(tmp);
			} else { // 无重置缓存区
				buf.reset();
			}
		}

	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
		logger.info("sessionID[{}] -- > finishDecode!", session.getId());
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		Context ctx = (Context) session.getAttribute(context);
		if (null != ctx) {
			session.removeAttribute(context);
		}
	}

	public int getMaxPackLength() {
		return maxPackLength;
	}

	public void setMaxPackLength(int maxPackLength) {
		if (maxPackLength < 0) {
			throw new IllegalArgumentException("maxPackLength 参数：" + maxPackLength);
		}
		this.maxPackLength = maxPackLength;
	}

	/**
	 * @描述 消息内容体
	 * @date 2017年3月5日-上午11:41:50
	 * @author IBM
	 *
	 */
	private class Context {
		private final CharsetDecoder decoder;
		private IoBuffer ioBuffer;

		public IoBuffer getIoBuffer() {
			return ioBuffer;
		}

		public void append(IoBuffer in) {
			this.ioBuffer.put(in);
		}

		private Context() {
			decoder = charset.newDecoder();
			ioBuffer = IoBuffer.allocate(80).setAutoExpand(true);
		}

		public CharsetDecoder getDecoder() {
			return decoder;
		}

	}
}
