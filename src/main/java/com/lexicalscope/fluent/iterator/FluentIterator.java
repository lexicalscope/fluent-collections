//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.iterator;

import static com.lexicalscope.fluent.FluentCollections.$;
import ch.lambdaj.function.convert.Converter;

import com.google.common.collect.ForwardingIterator;

import java.util.Iterator;

public class FluentIterator<T> extends ForwardingIterator<T>
{
   private final Iterator<T> iterator;

   public FluentIterator(final Iterator<T> iterator)
   {
      this.iterator = iterator;
   }

   @Override
   protected Iterator<T> delegate()
   {
      return iterator;
   }

   public <TN> FluentIterator<TN> $transform(final Converter<T, TN> forwardConverter)
   {
      return $(new ConvertingIterator<TN, T>(delegate(), forwardConverter));
   }
}
