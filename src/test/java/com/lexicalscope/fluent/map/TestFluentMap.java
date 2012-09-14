package com.lexicalscope.fluent.map;

import static com.lexicalscope.fluent.Fluent.$;
import static com.lexicalscope.fluent.MapMatchers.mapHasSize;
import static com.lexicalscope.fluent.StringConverters.reverseString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
public class TestFluentMap
{
   @Rule
   public final ExpectedException exception = ExpectedException.none();

   @Test
   public void canFilterMapKeys()
   {
      final FluentMap<String, String> filteredMap = $.map(String.class, String.class).$put("key1", "value1").$put("key2", "value2").$filter(equalTo("key1"));

      assertThat(filteredMap, mapHasSize(1));
      assertThat(filteredMap, hasKey("key1"));
      assertThat(filteredMap, not(hasKey("key2")));
   }

   @Test
   public void canConvertMapValues()
   {
      final FluentMap<String, String> convertedMap = $.map(String.class, String.class).$put("key1", "value1").$put("key2", "value2")
               .$convertValues(reverseString());

      assertThat(convertedMap, mapHasSize(2));
      assertThat(convertedMap, hasValue("2eulav"));
      assertThat(convertedMap, hasValue("1eulav"));
   }

   @Test
   public void cannotAddValuesToAOneWayConvertedMap()
   {
      final FluentMap<String, String> convertedMap = $.map(String.class, String.class).$convertValues(reverseString());

      exception.expect(UnsupportedOperationException.class);
      convertedMap.put("newKey", "newValue");
   }

   @Test
   public void canAddValuesToABothWaysConvertedMap()
   {
      final FluentMap<String, String> underlyingMap = $.map(String.class, String.class);
      final FluentMap<String, String> convertedMap = underlyingMap.$convertValues(reverseString(), reverseString());

      convertedMap.put("newKey", "1eulav");

      assertThat(underlyingMap, hasValue("value1"));
      assertThat(convertedMap, hasValue("1eulav"));

      convertedMap.entrySet().add(new Map.Entry<String, String>()
      {

         public String setValue(final String value)
         {
            return null;
         }

         public String getValue()
         {
            return null;
         }

         public String getKey()
         {
            return null;
         }
      })
   }
}
