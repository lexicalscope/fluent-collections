//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.fixture;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.StringDescription;

public class MutableString
{
   private String string;

   public MutableString(final String string)
   {
      this.string = string;
   }

   @Override
   public String toString()
   {
      return string;
   }

   public void reverse()
   {
      string = StringUtils.reverse(string);
   }

   public void describe(final StringDescription stringDescription)
   {
      stringDescription.appendValue(string);
   }
}
