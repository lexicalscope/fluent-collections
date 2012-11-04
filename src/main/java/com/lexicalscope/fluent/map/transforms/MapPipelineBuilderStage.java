//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map.transforms;

import java.util.Map;

public interface MapPipelineBuilderStage<KI, VI, KO, VO>
{
   Map<KI, VI> outputTo(Map<KO, VO> map);
}
