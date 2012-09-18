package com.lexicalscope.fluent.map;

import static ch.lambdaj.Lambda.on;
import static com.lexicalscope.fluent.FluentCollections.$;
import static com.lexicalscope.fluent.MapMatchers.mapHasSize;
import static com.lexicalscope.fluent.StringConverters.reverseString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.lexicalscope.fluent.fixture.ConvertMutableStringToString;
import com.lexicalscope.fluent.fixture.ConvertStringToMutableString;
import com.lexicalscope.fluent.fixture.MutableString;
import com.lexicalscope.fluent.fixture.Person;

import org.hamcrest.StringDescription;
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
   @Rule public final ExpectedException exception = ExpectedException.none();

   @Test
   public void canFilterMapKeys()
   {
      final FluentMap<String, String> filteredMap =
               $.map(String.class, String.class).
                  $put("key1", "value1").
                  $put("key2", "value2").
                  $filterKeys(equalTo("key1"));

      assertThat(filteredMap, mapHasSize(1));
      assertThat(filteredMap, hasKey("key1"));
      assertThat(filteredMap, not(hasKey("key2")));
   }

   @Test
   public void canFilterMapValues()
   {
      final FluentMap<String, String> filteredMap =
               $.map(String.class, String.class).
                  $put("key1", "value1").
                  $put("key2", "value2").
                  $filterValues(equalTo("value1"));

      assertThat(filteredMap, mapHasSize(1));
      assertThat(filteredMap, hasKey("key1"));
      assertThat(filteredMap, not(hasKey("key2")));
   }

   @Test
   public void canConvertMapValues()
   {
      final FluentMap<String, String> convertedMap =
               $.map(String.class, String.class).
                  $put("key1", "value1").
                  $put("key2", "value2").
                  $convertValues(reverseString());

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
      assertThat(convertedMap, hasEntry("newKey", "1eulav"));
   }

   @Test
   public void canAddValuesToABothWaysKeyConvertedMap()
   {
      final FluentMap<String, String> underlyingMap = $.map(String.class, String.class);
      final FluentMap<String, String> convertedMap = underlyingMap.$convertKeys(reverseString(), reverseString());

      convertedMap.put("yek", "value1");

      assertThat(underlyingMap, hasValue("value1"));
      assertThat(underlyingMap, hasKey("key"));
      assertThat(underlyingMap, hasEntry("key", "value1"));

      assertThat(convertedMap, hasValue("value1"));
      assertThat(convertedMap, hasKey("yek"));
      assertThat(convertedMap, hasEntry("yek", "value1"));
   }

   @Test
   public void getAnyReturnsAnyMatchingElement()
   {
      final FluentMap<String, String> map = $.map(String.class, String.class);

      map.$put("key0", "value0").$put("key1", "value1").$put("key2", "value2");

      assertThat(map.$getAny("key0"), equalTo("value0"));
      assertThat(map.$getAny("key0", "key1"), equalTo("value0"));
      assertThat(map.$getAny("key2", "key1"), equalTo("value2"));
   }

   @Test
   public void foreachValue()
   {
      final FluentMap<String, MutableString> map = $.map(String.class, MutableString.class);

      final FluentMap<String, String> stringMap = map.
               $convertValues(new ConvertMutableStringToString(), new ConvertStringToMutableString()).
               $put("key0", "value0").
               $put("key1", "value1").
               $put("key2", "value2");

      map.$foreachValue().reverse();

      assertThat(stringMap.get("key0"), equalTo("0eulav"));
      assertThat(stringMap.get("key1"), equalTo("1eulav"));
      assertThat(stringMap.get("key2"), equalTo("2eulav"));
   }

   @Test
   public void foreachKey()
   {
      final FluentMap<MutableString, String> map = $.map(MutableString.class, String.class);
      map.$convertKeys(new ConvertMutableStringToString(), new ConvertStringToMutableString()).
          $put("key0", "value0").
          $put("key1", "value1").
          $put("key2", "value2");

      final StringDescription description = new StringDescription();
      map.$foreachKey().describe(description);
      assertThat(description.toString(), equalTo("\"key0\"\"key1\"\"key2\""));
   }

   @Test
   public void collectValues()
   {
      final FluentMap<String, Person> map = $.map(String.class, Person.class).
               $put("key0", new Person("tim", 41)).
               $put("key1", new Person("john", 29)).
               $put("key2", new Person("joe", 76));

      assertThat(map.$collectValues(on(Person.class).getAge()), contains(41, 29, 76));
   }
}
