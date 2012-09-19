//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.list;

import ch.lambdaj.function.convert.Converter;

import com.lexicalscope.fluent.map.AbstractConverting;

import java.util.ListIterator;

final class ConvertingListIterator<T, TO> extends AbstractConverting<T, TO> implements ListIterator<T>
{
   private final ListIterator<TO> listIterator;

   public ConvertingListIterator(final ListIterator<TO> listIterator, final Converter<TO, T> forwardConverter, final Converter<T, TO> reverseConverter)
   {
      super(forwardConverter, reverseConverter);
      this.listIterator = listIterator;
   }

   @Override
   public boolean hasNext()
   {
      return listIterator.hasNext();
   }

   @Override
   public T next()
   {
      return forwardConvert(listIterator.next());
   }

   @Override
   public boolean hasPrevious()
   {
      return listIterator.hasPrevious();
   }

   @Override
   public T previous()
   {
      return forwardConvert(listIterator.previous());
   }

   @Override
   public int nextIndex()
   {
      return listIterator.nextIndex();
   }

   @Override
   public int previousIndex()
   {
      return listIterator.previousIndex();
   }

   @Override
   public void remove()
   {
      listIterator.remove();
   }

   @Override
   public void set(final T value)
   {
      listIterator.set(reverseConvert(value));
   }

   @Override
   public void add(final T value)
   {
      listIterator.add(reverseConvert(value));
   }
}