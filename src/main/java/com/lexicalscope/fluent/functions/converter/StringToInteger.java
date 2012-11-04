package com.lexicalscope.fluent.functions.converter;

import ch.lambdaj.function.convert.Converter;

class StringToInteger implements Converter<String, Integer>{
	@Override
	public Integer convert(final String arg) {
		return Integer.valueOf(arg);
	}
}
