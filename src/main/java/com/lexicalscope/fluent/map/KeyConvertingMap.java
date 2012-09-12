package com.lexicalscope.fluent.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import ch.lambdaj.function.convert.Converter;

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
public class KeyConvertingMap<K, L, V> implements Map<K, V> {
    private final Map<L, V> map;
    private final Converter<L, K> forward;
    private final Converter<K, L> reverse;

    public KeyConvertingMap(
            final Map<L, V> map,
            final Converter<L, K> forward,
            final Converter<K, L> reverse) {
                this.map = map;
                this.forward = forward;
                this.reverse = reverse;
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(final Object key) {
        return map.containsKey(convertFromKey(key));
    }

    public boolean containsValue(final Object value) {
        return map.containsValue(value);
    }

    public V get(final Object key) {
        return map.get(convertFromKey(key));
    }

    public V put(final K key, final V value) {
        return map.put(convertFromKey(key), value);
    }

    public V remove(final Object key) {
        return map.remove(convertFromKey(key));
    }

    public void putAll(final Map<? extends K, ? extends V> m) {
        for (final java.util.Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        map.clear();
    }

    public Set<K> keySet() {
        return new ConvertingSet<K, L>(map.keySet(), forward, reverse);
    }

    public Collection<V> values() {
        // TODO Auto-generated method stub
        return null;
    }

    public Set<java.util.Map.Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    private L convertFromKey(final Object key) {
        return reverse.convert((K) key);
    }
}
