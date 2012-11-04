package com.lexicalscope.fluent.functions.converter;

import ch.lambdaj.function.convert.Converter;


public class Converters {
	public static Converter<Integer, String> integerToString() {
		return new IntegerToString();
	}

	public static Converter<String, Integer> stringToInteger() {
		return new StringToInteger();
	}
}
