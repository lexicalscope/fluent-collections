//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.fixture;

import ch.lambdaj.function.convert.Converter;

public class ConvertMutableStringToString implements Converter<MutableString, String>
{
   @Override
   public String convert(final MutableString from)
   {
      return from.toString();
   }
}
