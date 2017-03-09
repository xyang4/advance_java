package com.xyang.mina.protocal;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 
 * @描述 编解码器工厂方法
 * @date 2017年3月5日-下午6:33:24
 * @author IBM
 *
 */
public class ProtocalFactoryImpl implements ProtocolCodecFactory {
	private ProtocalDecoder decoder;
	private ProtocalEncoder encoder;

	public ProtocalFactoryImpl() {
		this(Charset.defaultCharset());
	}

	public ProtocalFactoryImpl(Charset charset) {
		decoder = new ProtocalDecoder(charset);
		encoder = new ProtocalEncoder(charset);
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
