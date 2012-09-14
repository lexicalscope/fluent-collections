package com.lexicalscope.fluent.map;

import com.google.common.base.Objects;

import java.util.Map.Entry;

abstract class AbstractMapEntry<K, V> implements Entry<K, V>
{
   @Override
   public boolean equals(final Object object)
   {
      if (object instanceof Entry)
      {
         final Entry<?, ?> that = (Entry<?, ?>) object;
         return Objects.equal(this.getKey(), that.getKey()) && Objects.equal(this.getValue(), that.getValue());
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      final K k = getKey();
      final V v = getValue();
      return (k == null ? 0 : k.hashCode()) ^ (v == null ? 0 : v.hashCode());
   }

   @Override
   public String toString()
   {
      return getKey() + "=" + getValue();
   }
}