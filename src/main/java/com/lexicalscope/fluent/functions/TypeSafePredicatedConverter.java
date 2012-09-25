//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.functions;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public abstract class TypeSafePredicatedConverter<F, T> extends TypeSafeMatcher<F> implements PredicatedConverter<F, T>
{
   @Override
   public void describeTo(@SuppressWarnings("unused") final Description description)
   {
      // optional
   }
}
