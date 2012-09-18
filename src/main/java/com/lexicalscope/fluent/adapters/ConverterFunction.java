//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.adapters;

import ch.lambdaj.function.convert.Converter;

import com.google.common.base.Function;

public class ConverterFunction<F, T> implements Function<F, T>
{
   private final Converter<F, T> converter;

   public ConverterFunction(final Converter<F, T> converter)
   {
      this.converter = converter;
   }

   @Override
   public T apply(final F input)
   {
      return converter.convert(input);
   }
}
