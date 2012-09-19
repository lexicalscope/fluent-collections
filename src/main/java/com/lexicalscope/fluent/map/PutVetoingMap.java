//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import java.util.Map;

public class PutVetoingMap<K, V> extends AbstractPutManipulatingMap<K, V>
{
   private final Map<K, V> delegate;
   private final PutVeto<? super K, ? super V> veto;

   public PutVetoingMap(final Map<K, V> delegate, final PutVeto<? super K, ? super V> veto)
   {
      this.delegate = delegate;
      this.veto = veto;
   }

   @Override
   public V put(final K key, final V value)
   {
      if(veto.allow(key, value))
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
