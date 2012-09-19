//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.list;

import ch.lambdaj.function.convert.Converter;

import com.lexicalscope.fluent.FluentDollar;
import com.lexicalscope.fluent.map.AbstractConverting;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ValueConvertingList<T, TO> extends AbstractConverting<T, TO> implements List<T>
{
   private final List<TO> delegate;

   public ValueConvertingList(final List<TO> delegate, final Converter<TO, T> forwardConverter, final Converter<T, TO> reverseConverter)
   {
      super(forwardConverter, reverseConverter);
      this.delegate = delegate;
   }

   @Override
   public int size()
   {
      return delegate.size();
   }

   @Override
   public boolean isEmpty()
   {
      return delegate.isEmpty();
   }

   @Override
   public boolean contains(final Object o)
   {
      return delegate.contains(reverseConvert((T) o));
   }

   @Override
   public Iterator<T> iterator()
   {
      return FluentDollar.$(delegate.iterator()).$transform(forwardConverter());
   }

   @Override
   public Object[] toArray()
   {
      final Object[] raw = delegate.toArray();
      final Object[] result = new Object[raw.length];
      for (int i = 0; i < result.length; i++) {
          result[i] = forwardConvert((TO) raw[i]);
      }
      return result;
   }

   @Override
   public <T> T[] toArray(final T[] a)
   {
      final Object[] raw = delegate.toArray();

      final T[] result = a.length >= raw.length ? a :
         (T[])java.lang.reflect.Array
         .newInstance(a.getClass().getComponentType(), raw.length);

      for (int i = 0; i < raw.length; i++) {
         result[i] = (T) forwardConvert((TO) raw[i]);
      }
      return result;
   }

   @Override
   public boolean add(final T value)
   {
      return delegate.add(reverseConvert(value));
   }

   @Override
   public boolean remove(final Object value)
   {
      return delegate.remove(reverseConvert((T) value));
   }

   @Override
   public boolean containsAll(final Collection<?> collection)
   {
      for (final Object value : collection)
      {
         if (!contains(value))
         {
            return false;
         }
      }
     return true;
   }

   @Override
   public boolean addAll(final Collection<? extends T> collection)
   {
      boolean modified = false;
      for (final T value : collection)
      {
         if (add(value))
         {
            modified = true;
         }
      }
      return modified;
   }

   @Override
   public boolean addAll(int index, final Collection<? extends T> collection)
   {
      boolean modified = false;
      for (final T value : collection) {
          add(index++, value);
          modified = true;
      }
      return modified;
   }

   @Override
   public boolean removeAll(final Collection<?> collection)
   {
      boolean modified = false;
      for (final Iterator<?> it = iterator(); it.hasNext();) {
          if (collection.contains(it.next())) {
              it.remove();
              modified = true;
          }
      }
      return modified;
   }

   @Override
   public boolean retainAll(final Collection<?> collection)
   {
      boolean modified = false;
      for (final Iterator<T> it = iterator(); it.hasNext();) {
          if (!collection.contains(it.next())) {
              it.remove();
              modified = true;
          }
      }
      return modified;
   }

   @Override
   public void clear()
   {
      delegate.clear();
   }

   @Override
   public T get(final int index)
   {
      return forwardConvert(delegate.get(index));
   }

   @Override
   public T set(final int index, final T element)
   {
      return forwardConvert(delegate.set(index, reverseConvert(element)));
   }

   @Override
   public void add(final int index, final T element)
   {
      delegate.add(index, reverseConvert(element));
   }

   @Override
   public T remove(final int index)
   {
      return forwardConvert(delegate.remove(index));
   }

   @Override
   public int indexOf(final Object value)
   {
      return delegate.indexOf(reverseConvert((T) value));
   }

   @Override
   public int lastIndexOf(final Object value)
   {
      return delegate.lastIndexOf(reverseConvert((T) value));
   }

   @Override
   public ListIterator<T> listIterator()
   {
      return new ConvertingListIterator<T, TO>(delegate.listIterator(), forwardConverter(), reverseConverter());
   }

   @Override
   public ListIterator<T> listIterator(final int index)
   {
      return new ConvertingListIterator<T, TO>(delegate.listIterator(index), forwardConverter(), reverseConverter());
   }

   @Override
   public List<T> subList(final int fromIndex, final int toIndex)
   {
      return FluentDollar.$(delegate.subList(fromIndex, toIndex)).$convert(forwardConverter(), reverseConverter());
   }
}
