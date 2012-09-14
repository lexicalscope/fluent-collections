//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import ch.lambdaj.function.convert.Converter;

import java.util.Map.Entry;

public class EntryKeyConvertor<K, KO, V> extends AbstractConverting<K, KO> implements Converter<java.util.Map.Entry<KO, V>, java.util.Map.Entry<K, V>>
{
   public EntryKeyConvertor(final Converter<KO, K> forwardConverter, final Converter<K, KO> reverseConverter)
   {
      super(forwardConverter, reverseConverter);
   }

   @Override
   public Entry<K, V> convert(final Entry<KO, V> from)
   {
      return new AbstractMapEntry<K, V>()
      {
         @Override
         public K getKey()
         {
            return forwardConvert(from.getKey());
         }

         @Override
         public V getValue()
         {
            return from.getValue();
         }

         @Override
         public V setValue(final V value)
         {
            return from.setValue(value);
         }
      };
   }
}
