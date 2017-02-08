package com.xyang.pattern.behavioral.visitor.demo.impl;

import com.xyang.pattern.behavioral.visitor.demo.Visitor;

public class FloatElement {
	private Float fe;

	public FloatElement(Float fe) {
		this.fe = fe;
	}

	public Float getFe() {
		return this.fe;
	}

	public void accept(Visitor visitor) {
		visitor.visitFloat(this);
	}
}
