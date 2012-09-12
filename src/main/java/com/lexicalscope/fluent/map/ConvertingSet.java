package com.lexicalscope.fluent.map;

import java.util.Collection;
import java.util.Iterator;
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
public class ConvertingSet<V, W> implements Set<V> {
    private final Set<W> set;
    private final Converter<W, V> forward;
    private final Converter<V, W> reverse;

    public ConvertingSet(
            final Set<W> set,
            final Converter<W, V> forward,
            final Converter<V, W> reverse) {
        this.set = set;
        this.forward = forward;
        this.reverse = reverse;
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public boolean contains(final Object o) {
        return set.contains(forward.convert((W) o));
    }

    public Iterator<V> iterator() {
        return new ConvertingIterator<V, W>(set.iterator(), forward);
    }

    public Object[] toArray() {
        final Object[] raw = set.toArray();
        final Object[] result = new Object[raw.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = forward.convert((W) raw[i]);
        }
        return result;
    }

    public <T> T[] toArray(final T[] a) {
        final Object[] raw = set.toArray();

        final T[] result = a.length >= raw.length ? a :
            (T[])java.lang.reflect.Array
            .newInstance(a.getClass().getComponentType(), raw.length);

        for (int i = 0; i < raw.length; i++) {
            result[i] = (T) forward.convert((W) raw[i]);
        }
        return result;
    }

    public boolean add(final V e) {
        return set.add(reverse.convert(e));
    }

    public boolean remove(final Object o) {
        return set.add(reverse.convert((V) o));
    }

    public boolean containsAll(final Collection<?> c) {
//        return Collections.;
        return false;
    }

    public boolean addAll(final Collection<? extends V> c) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean retainAll(final Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean removeAll(final Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    public void clear() {
        // TODO Auto-generated method stub

    }
}
