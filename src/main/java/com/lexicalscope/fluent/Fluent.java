package com.lexicalscope.fluent;

import com.lexicalscope.fluent.map.DefaultMapEntry;
import com.lexicalscope.fluent.map.FluentMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
public class Fluent {
    public static final Fluent $ = new Fluent();

    public static <K, V> FluentMap<K, V> $(final Map<K, V> map){
        return new FluentMap<K, V>(map);
    }

    public <K, V> FluentMap<K, V> map(final Class<K> key, final Class<V> value) {
        return $(new LinkedHashMap<K, V>());
    }

    public <V> List<V> list(final Class<V> value) {
        return new ArrayList<V>();
    }

   public <K, V> Entry<K, V> mapEntry(final K key, final V value)
   {
      return new DefaultMapEntry<K, V>(key, value);
   }
}
