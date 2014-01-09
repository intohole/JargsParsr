package com.liran.zero.opinion;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 * 
 * @author lixuze
 * 
 * @param <T>
 */
public class Opinion<T> {

	public static enum Parsers {

		INT {
			@Override
			public Object parser(String value) {
				return Integer.parseInt(value);
			}
		},
		String {
			@Override
			public Object parser(String value) {
				return value;
			}
		},
		Long {
			@Override
			public Object parser(String value) {
				return Long.parser(value);
			}
		},
		Boolean {
			@Override
			public Object parser(String value) {
				return Boolean.parser(value);
			}
		},
		Double {
			@Override
			public Object parser(String value) {
				return Double.parser(value);
			}
		};

		public abstract Object parser(String value);

	}

	private T value; // 取值
	private Set<String> opinionSet = new HashSet<String>(); // 选择选项
	private String descMsg = "";
	private Parsers parser = null;

	public Opinion(Parsers parser, String opinionName, T defaultValue) {
		this(parser, new String[] { opinionName }, defaultValue, "");
	}

	public Opinion(Parsers parser, String opinionName, T defaultValue,
			String descMsg) {
		this(parser, new String[] { opinionName }, defaultValue, descMsg);
	}

	public Opinion(Parsers parser, String opinions[], T defaultValue,
			String descMsg) {
		this.parser = parser;
		if (opinions.length == 0) {
			throw new IllegalArgumentException("opinion 列表不能为空");
		}
		this.value = defaultValue;
		for (String opinion : opinions) {
			opinionSet.add(opinion);
		}
		this.descMsg = descMsg;
	}

	public Opinion(Parsers parser, String opinions[], T defaultValue) {
		this(parser, opinions, defaultValue, "");
	}

	public Set<String> getOpinions() {
		return new HashSet<String>(opinionSet);
	}

	public boolean assertExcute(String key) {
		for (String opinionName : opinionSet) {
			if (key.equals(opinionName)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void setValue(Object value) {
		this.value = (T) value;
	}

	public T getValue() {
		return this.value;
	}

	public Object parserValue(String value) {
		return this.parser.parser(value);
	}

	public String getDescription() {
		return this.descMsg;
	}

}