//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map.transforms;

import static com.lexicalscope.fluent.FluentDollar.$;
import ch.lambdaj.function.convert.Converter;

import java.util.Map;
import java.util.Map.Entry;

import org.hamcrest.Matcher;

class ProcessPutsPipelineBuilder<KI, VI, KO, VO> extends MapPipelineBuilder<KI, VI, KO, VO>
{
   private final MapPipelineBuilder<KI, VI, KO, VO> previous;
   private final Matcher<KO> matcher;
   private final Converter<Entry<KO, VO>, Entry<KO, VO>> converter;

   public ProcessPutsPipelineBuilder(
            final MapPipelineBuilder<KI, VI, KO, VO> previous,
            final Matcher<KO> matcher,
            final Converter<Entry<KO, VO>, Entry<KO, VO>> converter)
   {
      this.previous = previous;
      this.matcher = matcher;
      this.converter = converter;
   }

   @Override
   public Map<KI, VI> transform(final Map<KO, VO> map)
   {
      return previous.transform($(map).$processPuts(matcher, converter));
   }
}
