package com.lexicalscope.fluent.map;

import static com.lexicalscope.fluent.FluentDollar.$;
import static com.lexicalscope.fluent.functions.biConvertor.BiConverters.integerToFromString;
import static com.lexicalscope.fluent.functions.converter.Converters.integerToString;
import static com.lexicalscope.fluent.functions.converter.Converters.stringToInteger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class TestMapPipeline {
	@Test public void canFilterKeys() {
		final Map<String, String> out = new HashMap<String, String>();

		final Map<String, String> in =
				$.<String, String>mapPipeline().
					allowKeys(lessThan("f")).
					outputTo(out);

		in.put("e", "value");
		in.put("f", "value");

		assertThat(in, hasKey("e"));
		assertThat(in, not(hasKey("f")));

		assertThat(out, hasKey("e"));
		assertThat(out, not(hasKey("f")));
	}

	@Test public void canConvertKeysInBothDirectionsWithBiConvertor() {
		final Map<Integer, String> out = new HashMap<Integer, String>();

		final Map<String, String> in =
				$.<String, String>mapPipeline().convertKeys(integerToFromString()).outputTo(out);

		in.put("1", "value");

		assertThat(in, hasKey("1"));
		assertThat(out, hasKey(1));
	}

	@Test public void canConvertKeysInBothDirectionsWithConvertorPair() {
		final Map<Integer, String> out = new HashMap<Integer, String>();

		final Map<String, String> in =
				$.<String, String>mapPipeline().
				  convertKeys(integerToString(), stringToInteger()).
				  outputTo(out);

		in.put("1", "value");

		assertThat(in, hasKey("1"));
		assertThat(out, hasKey(1));
	}
}
