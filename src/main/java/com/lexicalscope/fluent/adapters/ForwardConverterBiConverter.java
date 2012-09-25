//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.adapters;

import ch.lambdaj.function.convert.Converter;

public class ForwardConverterBiConverter<F, T> extends ConvertersBiConverter<F, T>
{
   public ForwardConverterBiConverter(final Converter<F, T> forward)
   {
      super(forward, PreventConversion.<T, F>preventConversion());
   }
}
