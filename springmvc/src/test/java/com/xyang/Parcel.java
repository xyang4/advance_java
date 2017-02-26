package com.xyang;

/**
 * @描述 内部类的创建
 * @date 2017年2月17日-上午9:07:09
 * @author IBM
 *
 */
public class Parcel {
	class Contents {
		private int i = 11;

		public int value() {
			return i;
		}
	}

	class Destination {
		private String label;

		public Destination(String label) {
			this.label = label;
		}

		String readLabel() {
			return label;
		}
	}

	public static void main(String[] args) {
		Parcel p = new Parcel();
		Parcel.Contents pc = p.new Contents();
		Destination d = p.new Destination("Tasmania");
	}
}
