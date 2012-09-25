//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.adapters;

import ch.lambdaj.function.convert.Converter;

import com.lexicalscope.fluent.functions.BiConverter;

public class ConvertersBiConverter<F, T> implements BiConverter<F, T>
{
   private final Converter<F, T> forward;
   private final Converter<T, F> reverse;

   public ConvertersBiConverter(
            final Converter<F, T> forward,
            final Converter<T, F> reverse)
   {
      this.forward = forward;
      this.reverse = reverse;
   }

   @Override
   public T forward(final F from)
   {
      return forward.convert(from);
   }

   @Override
   public F reverse(final T from)
   {
      return reverse.convert(from);
   }
}
