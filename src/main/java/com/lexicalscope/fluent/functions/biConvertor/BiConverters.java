package com.lexicalscope.fluent.functions.biConvertor;

import com.lexicalscope.fluent.functions.BiConverter;

public class BiConverters {
	public static BiConverter<Integer, String> integerToFromString() {
		return new IntegerToString();
	}
}
