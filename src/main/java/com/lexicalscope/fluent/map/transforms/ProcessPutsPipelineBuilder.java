//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map.transforms;

import static com.lexicalscope.fluent.FluentDollar.$;
import ch.lambdaj.function.convert.Converter;

import java.util.Map;
import java.util.Map.Entry;

class ProcessPutsPipelineBuilder<KI, VI, KO, VO> extends MapPipelineBuilder<KI, VI, KO, VO>
{
   private final MapPipelineBuilder<KI, VI, KO, VO> previous;
   private final Converter<Entry<KO, VO>, Entry<KO, VO>> converter;

   public ProcessPutsPipelineBuilder(
            final MapPipelineBuilder<KI, VI, KO, VO> previous,
            final Converter<Entry<KO, VO>, Entry<KO, VO>> converter)
   {
      this.previous = previous;
      this.converter = converter;
   }

   @Override
   public Map<KI, VI> outputTo(final Map<KO, VO> map)
   {
      return previous.outputTo($(map).$processPuts(converter));
   }
}
