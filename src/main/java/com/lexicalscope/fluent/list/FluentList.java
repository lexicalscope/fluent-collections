//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.list;

import static com.lexicalscope.fluent.FluentCollections.$;
import ch.lambdaj.function.convert.Converter;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.lexicalscope.fluent.adapters.ConverterFunction;
import com.lexicalscope.fluent.adapters.MatcherPredicate;

import java.util.List;

import org.hamcrest.Matcher;

public class FluentList<T> extends ForwardingList<T>
{
   private final List<T> list;

   public FluentList(final List<T> list)
   {
      this.list = list;
   }

   @Override
   protected List<T> delegate()
   {
      return list;
   }

   public FluentList<T> $add(final T value)
   {
      add(value);
      return this;
   }

   public FluentList<T> _filter(final Matcher<T> matcher)
   {
      return $(Lists.newArrayList(Iterables.filter(list, new MatcherPredicate<T>(matcher))));
   }

   public <TN> FluentList<TN> $transform(final Converter<T, TN> forwardConverter)
   {
      return $(Lists.transform(list, new ConverterFunction<T, TN>(forwardConverter)));
   }
}
