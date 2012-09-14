//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import ch.lambdaj.function.convert.Converter;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ValueConvertingMap<K, V, VO> extends AbstractConverting<V, VO> implements Map<K, V>
{
   public static class EntryValueConvertor<K, V, VO> extends AbstractConverting<V, VO> implements Converter<Entry<K, VO>, Entry<K, V>>
   {
      public EntryValueConvertor(final Converter<VO, V> forwardConverter, final Converter<V, VO> reverseConverter)
      {
         super(forwardConverter, reverseConverter);
      }

      @Override public java.util.Map.Entry<K, V> convert(final java.util.Map.Entry<K, VO> from)
      {
         return new AbstractMapEntry<K, V>()
         {
            @Override public K getKey()
            {
               return from.getKey();
            }

            @Override public V getValue()
            {
               return forwardConvert(from.getValue());
            }

            @Override public V setValue(final V value)
            {
               return forwardConvert(from.setValue(reverseConvert(value)));
            }
         };
      }
   }

   private final Map<K, VO> map;

   public ValueConvertingMap(final Map<K, VO> map, final Converter<VO, V> forwardConverter, final Converter<V, VO> reverseConverter)
   {
      super(forwardConverter, reverseConverter);
      this.map = map;
   }

   public int size()
   {
      return map.size();
   }

   public boolean isEmpty()
   {
      return map.isEmpty();
   }

   public boolean containsKey(final Object key)
   {
      return map.containsKey(key);
   }

   public boolean containsValue(final Object value)
   {
      return map.containsValue(reverseConvert((V) value));
   }

   public V get(final Object key)
   {
      return forwardConvert(map.get(key));
   }

   public V put(final K key, final V value)
   {
      return forwardConvert(map.put(key, reverseConvert(value)));
   }

   public V remove(final Object key)
   {
      return forwardConvert(map.remove(key));
   }

   public void putAll(final Map<? extends K, ? extends V> m)
   {
      for (final java.util.Map.Entry<? extends K, ? extends V> entry : m.entrySet())
      {
         put(entry.getKey(), entry.getValue());
      }
   }

   public void clear()
   {
      map.clear();
   }

   public Set<K> keySet()
   {
      return map.keySet();
   }

   public Collection<V> values()
   {
      return new ConvertingCollection<V, VO>(map.values(), forwardConverter(), reverseConverter());
   }

   public Set<java.util.Map.Entry<K, V>> entrySet()
   {
      return new ConvertingSet<java.util.Map.Entry<K, V>, java.util.Map.Entry<K, VO>>(map.entrySet(), new EntryValueConvertor<K, V, VO>(forwardConverter(), reverseConverter()), new EntryValueConvertor<K, VO, V>(reverseConverter(), forwardConverter()));
   }
}
