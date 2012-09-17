//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

public class Person
{
   private final Integer age;
   private final String name;

   public Person(final String name, final Integer age)
   {
      this.name = name;
      this.age = age;
   }

   public String getName()
   {
      return name;
   }

   public Integer getAge()
   {
      return age;
   }
}
