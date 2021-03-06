package com.lexicalscope.fluent.map;

import java.util.Map;

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
public class MapMatchers {
    public static Matcher<Map<?, ?>> mapHasSize(final int size) {
        return new TypeSafeMatcher<Map<?,?>>() {
            public void describeTo(final Description description) {
                description.appendText("Map of length ").appendValue(size);
            }

            @Override protected boolean matchesSafely(final Map<?, ?> item) {
                return item.size() == size;
            }};
    }
}
