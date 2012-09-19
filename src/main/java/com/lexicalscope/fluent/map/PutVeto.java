//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map;

public interface PutVeto<K, V>
{
   boolean allow(K key, V value);
}
