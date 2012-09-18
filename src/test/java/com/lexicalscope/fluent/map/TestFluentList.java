//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

import static com.lexicalscope.fluent.FluentCollections.$;
import static com.lexicalscope.fluent.StringConverters.reverseString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.lexicalscope.fluent.list.FluentList;

import org.junit.Test;

public class TestFluentList
{
   @Test
   public void canFilterList()
   {
      final FluentList<String> filteredList =
               $.list(String.class).
                  $add("value1").
                  $add("value2").
                  _filter(equalTo("value1"));

      assertThat(filteredList, hasSize(1));
      assertThat(filteredList, contains("value1"));
   }

   @Test
   public void canTransformList()
   {
      final FluentList<String> filteredList =
               $.list(String.class).
                  $add("value1").
                  $add("value2").
                  $transform(reverseString());

      assertThat(filteredList, hasSize(2));
      assertThat(filteredList, contains("1eulav", "2eulav"));
   }
}
