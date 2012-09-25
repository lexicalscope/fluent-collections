package com.lexicalscope.fluent.map;

import static com.google.common.collect.Maps.*;
import static com.lexicalscope.fluent.FluentDollar.$;
import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;
import com.lexicalscope.fluent.adapters.MatcherPredicate;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;

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
public class FluentMap<K, V> extends ForwardingMap<K, V>{
    private final Map<K, V> map;

    public FluentMap(final Map<K, V> map) {
        this.map = map;
    }

    public FluentMap<K,V> $retainKeys(final Matcher<K> matcher) {
        return $(filterKeys(delegate(), new MatcherPredicate<K>(matcher)));
    }

    public FluentMap<K,V> $retainValues(final Matcher<V> matcher) {
       return $(filterValues(delegate(), new MatcherPredicate<V>(matcher)));
   }

    public <W> FluentMap<K, W> $convert(final Converter<Entry<K, V>, W> converter) {
        return $(Maps.transformEntries(delegate(), new Maps.EntryTransformer<K, V, W>(){
            @Override public W transformEntry(final K key, final V value){
                return converter.convert(new Map.Entry<K, V>() {
                   @Override public K getKey() { return key; }
                   @Override public V getValue() { return value;}
                   @Override public V setValue(final V value) { throw new UnsupportedOperationException(); }
                });
            }
        }));
    }

    public <W> FluentMap<K, W> $convertValues(final Converter<V, W> converter) {
        return $(Maps.transformEntries(delegate(), new Maps.EntryTransformer<K, V, W>(){
            @Override public W transformEntry(final K key, final V value){
                return converter.convert(value);
            }
        }));
    }

    public <W> FluentMap<K, W> $convertValues(final Converter<V, W> forwardConverter, final Converter<W, V> reverseConverter) {
       return $(new ValueConvertingMap<K,W,V>(delegate(), forwardConverter, reverseConverter));
    }

    public <L> FluentMap<L, V> $convertKeys(
            final Converter<K, L> forward,
            final Converter<L, K> reverse) {
        return $(new KeyConvertingMap<L,K,V>(delegate(), forward, reverse));
    }

    public FluentMap<K, V> $allowKeys(final Matcher<K> matcher) {
         return $(new KeyVetoingMap<K,V>(delegate(), matcher));
    }

    public FluentMap<K, V> $vetoPuts(final PutVeto<? super K, ? super V> veto) {
       return $(new PutVetoingMap<K,V>(delegate(), veto));
    }

    public FluentMap<K, V> $processPuts(final Matcher<Entry<K, V>> matcher, final Converter<Entry<K, V>, Entry<K, V>> converter) {
       return $(new PutConvertingMap<K,V>(delegate(), matcher, converter));
    }

    public V $getAny(final Object ... keys ) {
        for (final Object key : keys) {
            final V value = get(key);
            if(value != null || containsKey(key))
            {
                return value;
            }
        }
        return null;
    }

    public FluentMap<K, V> $put(final K key, final V value) {
        put(key, value);
        return this;
    }

    public FluentMap<K, V> $remove(final Object key) {
        remove(key);
        return this;
    }

    public FluentMap<K, V> $putAll(final Map<? extends K, ? extends V> m) {
        putAll(m);
        return this;
    }

    public FluentMap<K, V> $clear() {
        clear();
        return this;
    }

    @Override protected Map<K, V> delegate() {
        return map;
    }

   public V $foreachValue()
   {
      return Lambda.forEach(values());
   }

   public K $foreachKey()
   {
      return Lambda.forEach(keySet());
   }

   public <T> List<T> $collectValues(final T argument)
   {
      return Lambda.collect(this, argument);
   }
}
