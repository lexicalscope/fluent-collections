//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.adapters;

import ch.lambdaj.function.convert.Converter;

public class PreventConversion
{
   public static <F, T> Converter<F, T> preventConversion()
   {
      return new Converter<F, T>()
      {
         @Override
         public T convert(@SuppressWarnings("unused") final F from)
         {
            throw new IllegalStateException("conversion is not allowed");
         }
      };
   }

   public static <F, T> Converter<F, T> preventConversion(
		   @SuppressWarnings("unused") final Class<F> from,
		   @SuppressWarnings("unused") final Class<T> to)
   {
      return preventConversion();
   }
}
