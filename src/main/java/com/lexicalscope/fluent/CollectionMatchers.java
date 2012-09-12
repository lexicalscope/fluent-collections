package com.lexicalscope.fluent;

import java.util.Collection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

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
public class CollectionMatchers {
    public static <V> Matcher<Collection<V>> empty(final Class<V> klass) {
        return new TypeSafeMatcher<Collection<V>>(Collection.class) {
            @Override public boolean matchesSafely(final Collection<V> item) {
                return item.isEmpty();
            }
            @Override public void describeMismatchSafely(final Collection<V> item, final Description mismatchDescription) {
                mismatchDescription.appendValue(item);
            }
            public void describeTo(final Description description) {
                description.appendText("an empty collection");
            }
        };
    }

    public static <V> Matcher<? super Collection<V>> containsItem(final V expected) {
        return new TypeSafeMatcher<Collection<V>>(Collection.class) {
            @Override public boolean matchesSafely(final Collection<V> item) {
                return item.contains(expected);
            }
            @Override public void describeMismatchSafely(final Collection<V> item, final Description mismatchDescription) {
                mismatchDescription.appendValue(item);
            }
            public void describeTo(final Description description) {
                description.appendText("collection containing ").appendValue(expected);
            }
        };
    }
}
