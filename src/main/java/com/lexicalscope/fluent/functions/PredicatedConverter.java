//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.functions;

import ch.lambdaj.function.convert.Converter;

import org.hamcrest.Matcher;

public interface PredicatedConverter<F, T> extends Matcher<F>, Converter<F, T>
{
   // only converts if the matcher matches
}
