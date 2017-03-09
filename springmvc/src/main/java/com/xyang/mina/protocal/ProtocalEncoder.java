package com.xyang.mina.protocal;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ProtocalEncoder extends ProtocolEncoderAdapter {
	private Charset charset;

	public ProtocalEncoder(Charset charset) {
		this.setCharset(charset);
	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		ProtocalPack pp = (ProtocalPack) message;
		IoBuffer ioBuffer = IoBuffer.allocate(pp.getLength());
		ioBuffer.setAutoExpand(true);
		ioBuffer.putInt(pp.getLength());
		ioBuffer.put(pp.getFlag());
		if (null != pp.getContent()) {
			ioBuffer.put(pp.getContent().getBytes());
		}
		ioBuffer.flip();
		out.write(ioBuffer);
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

}
