//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.collection;

import com.google.common.collect.ForwardingCollection;

import java.util.Collection;

public class FluentCollection<T> extends ForwardingCollection<T>
{
   private final Collection<T> collection;

   public FluentCollection(final Collection<T> collection)
   {
      this.collection = collection;
   }

   @Override
   protected Collection<T> delegate()
   {
      return collection;
   }
}
