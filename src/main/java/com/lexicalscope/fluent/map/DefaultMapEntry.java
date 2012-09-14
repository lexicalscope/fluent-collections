//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

public final class DefaultMapEntry<K, V> extends AbstractMapEntry<K, V>
{
   private final K key;
   private final V value;

   public DefaultMapEntry(final K key, final V value)
   {
      this.key = key;
      this.value = value;
   }

   @Override
   public K getKey()
   {
      return key;
   }

   @Override
   public V getValue()
   {
      return value;
   }

   @Override
   public V setValue(@SuppressWarnings("unused") final V value)
   {
      throw new UnsupportedOperationException();
   }
}