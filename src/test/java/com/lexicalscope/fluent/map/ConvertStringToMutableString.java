//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import ch.lambdaj.function.convert.Converter;

public class ConvertStringToMutableString implements Converter<String, MutableString>
{
   @Override
   public MutableString convert(final String from)
   {
      return new MutableString(from);
   }
}
