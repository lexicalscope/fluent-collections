package com.lexicalscope.fluent.iterator;

import java.util.Iterator;

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
public class ConvertingIterator<T, TO> implements Iterator<T> {
    private final Iterator<TO> iterator;
    private final Converter<TO, T> convertor;

    public ConvertingIterator(
            final Iterator<TO> iterator,
            final Converter<TO, T> convertor) {
                this.iterator = iterator;
                this.convertor = convertor;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public T next() {
        return convertor.convert(iterator.next());
    }

    public void remove() {
        iterator.remove();
    }
}
