package com.lexicalscope.fluent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hamcrest.Matcher;

import ch.lambdaj.function.convert.Converter;

import com.google.common.collect.Lists;
import com.lexicalscope.fluent.collection.FluentCollection;
import com.lexicalscope.fluent.functions.PredicatedConverter;
import com.lexicalscope.fluent.iterator.FluentIterator;
import com.lexicalscope.fluent.list.FluentList;
import com.lexicalscope.fluent.map.DefaultMapEntry;
import com.lexicalscope.fluent.map.FluentMap;
import com.lexicalscope.fluent.map.transforms.MapPipelineBuilder;

/*
 * Copyright 2012 Tim Wood
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Method staring with a $ are going return a wrapper around the collection but methods starting with _ are going to copy the collection.
 *
 * @author t.wood
 */
public class FluentDollar {
    public static final FluentDollar $ = new FluentDollar();

    public static <K, V> FluentMap<K, V> $(final Map<K, V> map){
        return new FluentMap<K, V>(map);
    }

    public static <V> FluentList<V> $(final List<V> list){
       return new FluentList<V>(list);
    }

    public static <V> FluentList<V> _(final List<V> list){
       return $(Lists.newArrayList(list));
    }

    public static <V> FluentCollection<V> $(final Collection<V> collection){
       return new FluentCollection<V>(collection);
    }

    public static <V> FluentIterator<V> $(final Iterator<V> iterator){
       return new FluentIterator<V>(iterator);
    }

    public <K, V> FluentMap<K, V> map(
    		@SuppressWarnings("unused") final Class<K> key,
    		@SuppressWarnings("unused") final Class<V> value) {
        return $(new LinkedHashMap<K, V>());
    }

    public <K, V> FluentMap<K, V> map() {
       return $(new LinkedHashMap<K, V>());
    }

    public <V> FluentList<V> list(@SuppressWarnings("unused") final Class<V> value) {
        return $(new ArrayList<V>());
    }

    public <V> FluentList<V> asList(@SuppressWarnings("unchecked") final V ... values)
    {
       return $(Arrays.asList(values));
    }

   public <K, V> Entry<K, V> mapEntry(final K key, final V value)
   {
      return new DefaultMapEntry<K, V>(key, value);
   }

   public <K, V> MapPipelineBuilder<K, V, K, V> mapPipeline()
   {
      return new MapPipelineBuilder<K, V, K, V>(){
         @Override
         public Map<K, V> outputTo(final Map<K, V> map)
         {
            return map;
         }};
   }

   public <V> Converter<V, V> predicatedConverter(
            final Matcher<V> matcher,
            final Converter<V, V> converter)
   {
      return new PredicatedConverter<V>(matcher, converter);
   }
}
