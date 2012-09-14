//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import ch.lambdaj.function.convert.Converter;

public class AbstractConverting<V, VO>
{
   private final Converter<VO, V> forwardConverter;
   private final Converter<V, VO> reverseConverter;

   public AbstractConverting(final Converter<VO, V> forwardConverter, final Converter<V, VO> reverseConverter)
   {
      this.forwardConverter = forwardConverter;
      this.reverseConverter = reverseConverter;
   }

   protected final VO reverseConvert(final V value)
   {
      if(value == null) {return null;}
      return reverseConverter().convert(value);
   }

   protected final V forwardConvert(final VO value)
   {
      if(value == null) {return null;}
      return forwardConverter().convert(value);
   }

   protected final Converter<VO, V> forwardConverter()
   {
      return forwardConverter;
   }

   protected final Converter<V, VO> reverseConverter()
   {
      return reverseConverter;
   }
}
