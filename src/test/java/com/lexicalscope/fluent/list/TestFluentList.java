//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.list;

import static com.lexicalscope.fluent.FluentDollar.$;
import static com.lexicalscope.fluent.StringConverters.reverseString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.lexicalscope.fluent.list.FluentList;

import org.junit.Test;

public class TestFluentList
{
   @Test
   public void canFilterAndCopyList()
   {
      final FluentList<String> filteredList =
               $.list(String.class).
                  $add("value1").
                  $add("value2").
                  _retain(equalTo("value1"));

      assertThat(filteredList, hasSize(1));
      assertThat(filteredList, contains("value1"));
   }

   @Test
   public void canConvertList()
   {
      final FluentList<String> filteredList =
               $.list(String.class).
                  $add("value1").
                  $add("value2").
                  $convert(reverseString());

      assertThat(filteredList, hasSize(2));
      assertThat(filteredList, contains("1eulav", "2eulav"));
   }

   @Test
   public void canConvertAndCopyList()
   {
      final FluentList<String> list = $.
               list(String.class).
                  $add("value1").
                  $add("value2");

      final FluentList<String> transformedList = list._convert(reverseString());

      transformedList.add("3eulav");

      assertThat(transformedList, hasSize(3));
      assertThat(transformedList, contains("1eulav", "2eulav", "3eulav"));
      assertThat(list, contains("value1", "value2"));
   }

   @Test
   public void canDuplexConvertList()
   {
      final FluentList<String> list = $.
               list(String.class).
                  $add("value1").
                  $add("value2");

      final FluentList<String> transformedList = list.$convert(reverseString(), reverseString());

      transformedList.add("3eulav");

      assertThat(transformedList, hasSize(3));
      assertThat(transformedList, contains("1eulav", "2eulav", "3eulav"));
      assertThat(list, contains("value1", "value2", "value3"));
   }
}
