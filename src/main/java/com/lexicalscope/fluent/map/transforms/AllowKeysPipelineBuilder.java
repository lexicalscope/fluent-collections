//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map.transforms;

import static com.lexicalscope.fluent.FluentDollar.$;

import java.util.Map;

import org.hamcrest.Matcher;

class AllowKeysPipelineBuilder<KI, VI, KO, VO> extends MapPipelineBuilder<KI, VI, KO, VO>
{
   private final MapPipelineBuilder<KI, VI, KO, VO> previous;
   private final Matcher<KO> matcher;

   public AllowKeysPipelineBuilder(final MapPipelineBuilder<KI, VI, KO, VO> previous, final Matcher<KO> matcher)
   {
      this.previous = previous;
      this.matcher = matcher;
   }

   @Override
   public Map<KI, VI> outputTo(final Map<KO, VO> map)
   {
      return previous.outputTo($(map).$allowKeys(matcher));
   }
}
