//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.functions;

public interface BiConverter<F, T>
{
   T forward(F from);
   F reverse(T from);
}
