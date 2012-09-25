//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.functions;

import ch.lambdaj.function.convert.Converter;

import org.hamcrest.Matcher;

public class PredicatedConverter<V> implements Converter<V, V>
{
   private final Matcher<V> predicate;
   private final Converter<V, V> converter;

   public PredicatedConverter(
            final Matcher<V> predicate,
            final Converter<V, V> converter)
   {
      this.predicate = predicate;
      this.converter = converter;
   }

   @Override
   public V convert(final V from)
   {
      if(predicate.matches(from)) {
         return converter.convert(from);
      }
      return from;
   }
}
