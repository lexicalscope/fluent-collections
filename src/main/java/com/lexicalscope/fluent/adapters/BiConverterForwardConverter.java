//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.adapters;

import ch.lambdaj.function.convert.Converter;

import com.lexicalscope.fluent.functions.BiConverter;

public class BiConverterForwardConverter<F, T> implements Converter<F, T>
{
   private final BiConverter<F, T> converter;

   public BiConverterForwardConverter(final BiConverter<F, T> converter)
   {
      this.converter = converter;
   }

   @Override
   public T convert(final F from)
   {
      return converter.forward(from);
   }
}
