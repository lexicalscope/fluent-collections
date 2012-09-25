//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map.transforms;

import static com.lexicalscope.fluent.FluentDollar.$;
import ch.lambdaj.function.convert.Converter;

import java.util.Map;

class ConvertKeysPipelineBuilder<KI, VI, KP, KO, VO> extends MapPipelineBuilder<KI, VI, KO, VO>
{
   private final MapPipelineBuilder<KI, VI, KP, VO> previous;
   private final Converter<KO, KP> forward;
   private final Converter<KP, KO> reverse;

   public ConvertKeysPipelineBuilder(
            final MapPipelineBuilder<KI, VI, KP, VO> previous,
            final Converter<KO, KP> forward,
            final Converter<KP, KO> reverse)
   {
      this.previous = previous;
      this.forward = forward;
      this.reverse = reverse;
   }

   @Override
   public Map<KI, VI> transform(final Map<KO, VO> map)
   {
      return previous.transform($(map).$convertKeys(forward, reverse));
   }

}
