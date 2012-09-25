//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.adapters;

import ch.lambdaj.function.convert.Converter;

import com.lexicalscope.fluent.functions.BiConverter;

public class BiConverterReverseConverter<F, T> implements Converter<T, F>
{
   private final BiConverter<F, T> converter;

   public BiConverterReverseConverter(final BiConverter<F, T> converter)
   {
      this.converter = converter;
   }

   @Override
   public F convert(final T from)
   {
      return converter.reverse(from);
   }
}
