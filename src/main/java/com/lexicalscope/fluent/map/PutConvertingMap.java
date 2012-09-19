//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import static com.lexicalscope.fluent.FluentDollar.$;
import ch.lambdaj.function.convert.Converter;

import java.util.Map;

import org.hamcrest.Matcher;


public class PutConvertingMap<K, V> extends AbstractPutManipulatingMap<K, V>
{
   private final Map<K, V> delegate;
   private final Matcher<K> matcher;
   private final Converter<java.util.Map.Entry<K, V>, java.util.Map.Entry<K, V>> converter;

   public PutConvertingMap(final Map<K, V> delegate, final Matcher<K> matcher, final Converter<Entry<K, V>, Entry<K, V>> converter)
   {
      this.delegate = delegate;
      this.matcher = matcher;
      this.converter = converter;
   }

   @Override
   public V put(final K key, final V value)
   {
      if(matcher.matches(key))
      {
         final java.util.Map.Entry<K, V> converted = converter.convert($.mapEntry(key, value));
         return super.put(converted.getKey(), converted.getValue());
      }
      return super.put(key, value);
   }

   @Override
   protected Map<K, V> delegate()
   {
      return delegate;
   }
}
