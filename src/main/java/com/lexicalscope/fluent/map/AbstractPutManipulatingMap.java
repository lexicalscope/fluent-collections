//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import com.google.common.collect.ForwardingMap;

import java.util.Map;

public abstract class AbstractPutManipulatingMap<K, V> extends ForwardingMap<K, V>
{
   @Override
   public final void putAll(final Map<? extends K, ? extends V> m)
   {
      for (final Entry<? extends K, ? extends V> entry : m.entrySet())
      {
         put(entry.getKey(), entry.getValue());
      }
   }
}
