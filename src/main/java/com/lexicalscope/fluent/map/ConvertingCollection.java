//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import ch.lambdaj.function.convert.Converter;

import java.util.Collection;
import java.util.Iterator;

public class ConvertingCollection<V, VO> implements Collection<V>
{
   private final Collection<VO> collection;
   private final Converter<VO, V> forwardConverter;
   private final Converter<V, VO> reverseConverter;

   public ConvertingCollection(final Collection<VO> collection, final Converter<VO, V> forwardConverter, final Converter<V, VO> reverseConverter)
   {
      this.collection = collection;
      this.forwardConverter = forwardConverter;
      this.reverseConverter = reverseConverter;
   }

   public int size()
   {
      return 0;
   }

   public boolean isEmpty()
   {
      return false;
   }

   public boolean contains(final Object o)
   {
      return false;
   }

   public Iterator<V> iterator()
   {
      return null;
   }

   public Object[] toArray()
   {
      return null;
   }

   public <T> T[] toArray(final T[] a)
   {
      return null;
   }

   public boolean add(final V e)
   {
      return false;
   }

   public boolean remove(final Object o)
   {
      return false;
   }

   public boolean containsAll(final Collection<?> c)
   {
      return false;
   }

   public boolean addAll(final Collection<? extends V> c)
   {
      return false;
   }

   public boolean removeAll(final Collection<?> c)
   {
      return false;
   }

   public boolean retainAll(final Collection<?> c)
   {
      return false;
   }

   public void clear()
   {
   }
}
