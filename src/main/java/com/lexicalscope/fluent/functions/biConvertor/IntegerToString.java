package com.lexicalscope.fluent.functions.biConvertor;

import com.lexicalscope.fluent.functions.BiConverter;

final class IntegerToString implements BiConverter<Integer, String> {
	@Override public String forward(final Integer from) {
		return from.toString();
	}

	@Override public Integer reverse(final String from) {
		return Integer.valueOf(from);
	}
}