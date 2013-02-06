//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.list;

import static ch.lambdaj.Lambda.on;
import static com.lexicalscope.fluent.FluentDollar.$;
import static com.lexicalscope.fluent.StringConverters.reverseString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;

import ch.lambdaj.Lambda;

public class TestFluentList
{
   private List<?> originalList;

   private <T> FluentList<T> list(final Class<T> klass) {
      final FluentList<T> list = $.list(klass);
      originalList = list;
      return list;
   }

   @SuppressWarnings("rawtypes")
   private void assertOriginalUntouched(final FluentList<?> modifiedList) {
      assertThat(originalList, notNullValue());
      assertThat("original list should not have been modified", (List) originalList, not(equalTo((List) modifiedList)));
   }

   @Test
   public void canRetainFilterAndCopyList()
   {
      final FluentList<String> filteredList =
               list(String.class).
                  $add("value1").
                  $add("value2").
                  _retain(equalTo("value1"));

      assertOriginalUntouched(filteredList);
      assertThat(filteredList, hasSize(1));
      assertThat(filteredList, contains("value1"));
   }

   @Test
   public void canConvertList()
   {
      final FluentList<String> filteredList =
               list(String.class).
                  $add("value1").
                  $add("value2").
                  $convert(reverseString());

      assertOriginalUntouched(filteredList);
      assertThat(filteredList, hasSize(2));
      assertThat(filteredList, contains("1eulav", "2eulav"));
   }

   @Test
   public void canConvertAndCopyList()
   {
      final FluentList<String> underlyingList =
               list(String.class).
                  $add("value1").
                  $add("value2");

      final FluentList<String> transformedList = underlyingList._convert(reverseString());

      transformedList.add("3eulav");

      assertOriginalUntouched(transformedList);
      assertThat(transformedList, hasSize(3));
      assertThat(transformedList, contains("1eulav", "2eulav", "3eulav"));
      assertThat(underlyingList, contains("value1", "value2"));
   }

   @Test
   public void canDuplexConvertList()
   {
      final FluentList<String> underlyingList =
               list(String.class).
                  $add("value1").
                  $add("value2");

      final FluentList<String> transformedList = underlyingList.$convert(reverseString(), reverseString());

      transformedList.add("3eulav");

      assertOriginalUntouched(transformedList);
      assertThat(transformedList, hasSize(3));
      assertThat(transformedList, contains("1eulav", "2eulav", "3eulav"));
      assertThat(underlyingList, contains("value1", "value2", "value3"));
   }

   @Test
   public void canRemoveFilterAndCopyList()
   {
      final FluentList<String> filteredList =
               list(String.class).
                  $add("value1").
                  $add("value2").
                  _remove(equalTo("value1"));

      assertOriginalUntouched(filteredList);
      assertThat(filteredList, hasSize(1));
      assertThat(filteredList, contains("value2"));
   }

   @Test
   public void canSortAndCopyListUsingLambda()
   {
      final FluentList<Person> filteredList =
              list(Person.class).
                 $add(new Person(31)).
                 $add(new Person(21)).
                 _sort_lj(Lambda.on(Person.class).age());

      assertOriginalUntouched(filteredList);
      assertThat(filteredList, hasSize(2));
      assertThat(filteredList, contains(new Person(21), new Person(31)));
   }

   @Test
   public void canExtractAndCopyListUsingLambda()
   {
      final FluentList<Integer> extractedList =
              list(Person.class).
                 $add(new Person(31)).
                 $add(new Person(21)).
                 _extract_lj(Lambda.on(Person.class).age());

      assertOriginalUntouched(extractedList);
      assertThat(extractedList, hasSize(2));
      assertThat(extractedList, contains(31, 21));
   }

   @Test
   public void canReplaceItemsInCopyOfList()
   {
      final FluentList<String> modifiedList =
               list(String.class).
                  $add("value1").
                  $add("value2").
                  _replace(equalTo("value1"), "value3");

      assertOriginalUntouched(modifiedList);
      assertThat(modifiedList, hasSize(2));
      assertThat(modifiedList, contains("value3", "value2"));
   }

   @Test
   public void canRemoveDuplicateItemsInCopyOfList()
   {
      final FluentList<Person> filteredList =
            list(Person.class).
               $add(new Person(31)).
               $add(new Person(21)).
               $add(new Person(21)).
                  _distinct_lj(on(Person.class).age());

      assertOriginalUntouched(filteredList);
      assertThat(filteredList, hasSize(2));
      assertThat(filteredList, contains(new Person(21), new Person(31)));
   }

   @Test
   public void canProjectItemsInCopyOfList()
   {
      final FluentList<Age> filteredList =
            list(Person.class).
               $add(new Person(31)).
               $add(new Person(21)).
                  _project(Age.class, on(Person.class).age());

      assertOriginalUntouched(filteredList);
      assertThat(filteredList, hasSize(2));
      assertThat(filteredList, contains(new Age(31), new Age(21)));
   }

   @Test
   public void canJoinItemsInList()
   {
      final String filteredList =
            list(Person.class).
               $add(new Person(31)).
               $add(new Person(21)).
                  _joinFrom().toString();
      assertThat(filteredList, equalTo("Person [age=31], Person [age=21]"));
   }

   @Test
   public void canJoinItemsInListWithSeparator()
   {
      final String filteredList =
            list(Person.class).
               $add(new Person(31)).
               $add(new Person(21)).
                  _joinFrom(" - ").toString();
      assertThat(filteredList, equalTo("Person [age=31] - Person [age=21]"));
   }

   @Test
   public void canJoinItemsInCopyOfList()
   {
      final String filteredList =
            list(Person.class).
               $add(new Person(31)).
               $add(new Person(21)).
                  _join();
      assertThat(filteredList, equalTo("Person [age=31], Person [age=21]"));
   }

   static class Person {
      private final int age;

      public Person(final int age) {
         this.age = age;
      }

      int age() {
         return age;
      }

      @Override
      public String toString() {
         return "Person [age=" + age + "]";
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + age;
         return result;
      }

      @Override
      public boolean equals(final Object obj) {
         if (this == obj)
            return true;
         if (obj == null)
            return false;
         if (getClass() != obj.getClass())
            return false;
         final Person other = (Person) obj;
         if (age != other.age)
            return false;
         return true;
      }
   }

   public static class Age {
      private final int age;

      public Age(final int age) {
         this.age = age;
      }

      int age() {
         return age;
      }

      @Override
      public String toString() {
         return "Age [age=" + age + "]";
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + age;
         return result;
      }

      @Override
      public boolean equals(final Object obj) {
         if (this == obj)
            return true;
         if (obj == null)
            return false;
         if (getClass() != obj.getClass())
            return false;
         final Age other = (Age) obj;
         if (age != other.age)
            return false;
         return true;
      }
   }
}
