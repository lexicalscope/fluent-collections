//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map.transforms;

import java.util.Map;

public interface MapTransform<KI, VI, KO, VO>
{
   Map<KI, VI> transform(Map<KO, VO> map);
}
