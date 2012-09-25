//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.adapters;

import ch.lambdaj.function.convert.Converter;

public class ReverseConverterBiConverter<F, T> extends ConvertersBiConverter<F, T>
{
   public ReverseConverterBiConverter(final Converter<T, F> reverse)
   {
      super(PreventConversion.<F, T>preventConversion(), reverse);
   }
}
