//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map.transforms;

import static com.lexicalscope.fluent.FluentDollar.$;

import com.lexicalscope.fluent.map.PutVeto;

import java.util.Map;

class VetoPutPipelineBuilder<KI, VI, KO, VO> extends MapPipelineBuilder<KI, VI, KO, VO>
{
   private final MapPipelineBuilder<KI, VI, KO, VO> previous;
   private final PutVeto<? super KO, ? super VO> veto;

   public VetoPutPipelineBuilder(
            final MapPipelineBuilder<KI, VI, KO, VO> previous,
            final PutVeto<? super KO, ? super VO> veto)
   {
      this.previous = previous;
      this.veto = veto;
   }

   @Override
   public Map<KI, VI> outputTo(final Map<KO, VO> map)
   {
      return previous.outputTo($(map).$vetoPuts(veto));
   }
}
