//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.list;

import static com.lexicalscope.fluent.FluentCollections.$;
import ch.lambdaj.collection.LambdaCollections;
import ch.lambdaj.collection.LambdaGroup;
import ch.lambdaj.collection.LambdaList;
import ch.lambdaj.collection.LambdaMap;
import ch.lambdaj.collection.LambdaSet;
import ch.lambdaj.function.aggregate.Aggregator;
import ch.lambdaj.function.convert.Converter;
import ch.lambdaj.group.GroupCondition;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;
import com.lexicalscope.fluent.adapters.ConverterFunction;
import com.lexicalscope.fluent.collection.FluentCollection;
import com.lexicalscope.fluent.map.FluentMap;

import java.util.List;

import org.hamcrest.Matcher;

public class FluentList<T> extends ForwardingList<T>
{
   private final List<T> list;
   private final LambdaList<T> lambdaList;

   public FluentList(final List<T> list)
   {
      this.list = list;
      if(list instanceof LambdaList)
      {
         this.lambdaList = (LambdaList<T>) list;
      }
      else
      {
         this.lambdaList = LambdaCollections.with(list);
      }
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

   public <TN> FluentList<TN> $convert(final Converter<T, TN> forwardConverter)
   {
      return $(Lists.transform(list, new ConverterFunction<T, TN>(forwardConverter)));
   }

   public <TN> FluentList<TN> $convert(final Converter<T, TN> forwardConverter, final Converter<TN, T> reverseConverter)
   {
      return $(new ValueConvertingList<TN, T>(delegate(), forwardConverter, reverseConverter));
   }

   public FluentList<T> _retain(final Matcher<?> matcher) {
       return $(lambdaList.retain(matcher));
   }

   public FluentList<T> _remove(final Matcher<?> matcher) {
      return $(lambdaList.remove(matcher));
   }

   public FluentList<T> _sort(final Object argument) {
       return $(lambdaList.sort(argument));
   }

   public <TN> FluentList<TN> _convert(final Converter<T, TN> converter) {
      return $(lambdaList.convert(converter));
   }

   public <V> FluentList<V> _extract(final V argument) {
      return $(lambdaList.extract(argument));
   }

   public FluentList<T> _replace(final Matcher<?> matcher, final T replacer) {
      return $(lambdaList.replace(matcher, replacer));
   }

   public FluentList<T> _distinct(final Object argument) {
      return $(lambdaList.distinct(argument));
   }

   public <V> FluentList<V> _project(final Class<V> targetClass, final Object... arguments) {
      return $(lambdaList.project(targetClass, arguments));
   }

   public T _joinFrom()
   {
      return lambdaList.joinFrom();
   }

   public T _joinFrom(final String separator)
   {
      return lambdaList.joinFrom(separator);
   }

   public T _aggregate(final Aggregator<T> aggregator)
   {
      return lambdaList.aggregate(aggregator);
   }

   public <A> A _aggregate(final A argument, final Aggregator<A> aggregator)
   {
      return lambdaList.aggregate(argument, aggregator);
   }

   public T _sumFrom()
   {
      return lambdaList.sumFrom();
   }

   public FluentCollection<T> _append(final T... newValues)
   {
      return $(lambdaList.append(newValues));
   }

   public <A> A _min(final A argument)
   {
      return lambdaList.min(argument);
   }

   public T _maxFrom()
   {
      return lambdaList.maxFrom();
   }

   public <A> A _max(final A argument)
   {
      return lambdaList.max(argument);
   }

   public T _minFrom()
   {
      return lambdaList.minFrom();
   }

   public <A> A _sum(final A argument)
   {
      return lambdaList.sum(argument);
   }

   public <A> T _selectMin(final A argument)
   {
      return lambdaList.selectMin(argument);
   }

   public <A> T _selectMax(final A argument)
   {
      return lambdaList.selectMax(argument);
   }

   public String _join()
   {
      return lambdaList.join();
   }

   public String _join(final String separator)
   {
      return lambdaList.join(separator);
   }

   public T _forEach()
   {
      return lambdaList.forEach();
   }

   public T _forEach(final Matcher<?> matcher)
   {
      return lambdaList.forEach(matcher);
   }

   public <K> LambdaMap<K, T> _map(final Converter<T, K> converter)
   {
      return lambdaList.map(converter);
   }

   public LambdaSet<T> _distinct()
   {
      return lambdaList.distinct();
   }

   public <K> FluentMap<K, T> _map(final K argument)
   {
      return $(lambdaList.map(argument));
   }

   public <K> FluentMap<K, T> _map(final Matcher<T> matcher, final Converter<T, K> converter)
   {
      return $(lambdaList.map(matcher, converter));
   }

   public <K> FluentMap<K, T> _map(final Matcher<T> matcher, final K argument)
   {
      return $(lambdaList.map(matcher, argument));
   }

   public <A> FluentMap<A, T> _index(final A argument)
   {
      return $(lambdaList.index(argument));
   }

   public LambdaGroup<T> _group(final GroupCondition<?>... conditions)
   {
      return lambdaList.group(conditions);
   }

   public boolean _exists(final Matcher<?> matcher)
   {
      return lambdaList.exists(matcher);
   }

   public boolean _all(final Matcher<?> matcher)
   {
      return lambdaList.all(matcher);
   }

   public T _first(final Matcher<?> matcher)
   {
      return lambdaList.first(matcher);
   }

   public T _unique(final Matcher<?> matcher)
   {
      return lambdaList.unique(matcher);
   }
}
