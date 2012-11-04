package com.lexicalscope.fluent.functions.converter;

import ch.lambdaj.function.convert.Converter;

class IntegerToString implements Converter<Integer, String>{
	@Override
	public String convert(final Integer arg) {
		return arg.toString();
	}
}
