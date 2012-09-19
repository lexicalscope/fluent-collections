//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import java.util.Map;

import org.hamcrest.Matcher;

public class KeyVetoingMap<K, V> extends AbstractPutManipulatingMap<K, V>
{
   private final Map<K, V> delegate;
   private final Matcher<K> matcher;

   public KeyVetoingMap(final Map<K, V> delegate, final Matcher<K> matcher)
   {
      this.delegate = delegate;
      this.matcher = matcher;
   }

   @Override
   public V put(final K key, final V value)
   {
      if(matcher.matches(key))
      {
         return super.put(key, value);
      }
      return null;
   }

   @Override
   protected Map<K, V> delegate()
   {
      return delegate;
   }
}
