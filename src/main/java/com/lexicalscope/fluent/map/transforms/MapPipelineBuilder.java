//
//Author       : t.wood
//Copyright    : (c) Resilient Networks plc 2012 - All Rights Reserved
//
package com.lexicalscope.fluent.map.transforms;

import ch.lambdaj.function.convert.Converter;

import com.lexicalscope.fluent.adapters.BiConverterForwardConverter;
import com.lexicalscope.fluent.adapters.BiConverterReverseConverter;
import com.lexicalscope.fluent.functions.BiConverter;
import com.lexicalscope.fluent.map.PutVeto;

import java.util.Map.Entry;

import org.hamcrest.Matcher;


public abstract class MapPipelineBuilder<KI, VI, KO, VO> implements MapPipelineBuilderStage<KI, VI, KO, VO>
{
   public MapPipelineBuilder<KI, VI, KO, VO> allowKeys(final Matcher<KO> matcher)
   {
      return new AllowKeysPipelineBuilder<KI, VI, KO, VO>(this, matcher);
   }

   public MapPipelineBuilder<KI, VI, KO, VO> processPuts(final Converter<Entry<KO, VO>, Entry<KO, VO>> converter)
   {
      return new ProcessPutsPipelineBuilder<KI, VI, KO, VO>(this, converter);
   }

   public <KT> MapPipelineBuilder<KI, VI, KT, VO> convertKeys(final Converter<KT, KO> forward,
                                                              final Converter<KO, KT> reverse)
   {
      return new ConvertKeysPipelineBuilder<KI, VI, KO, KT, VO>(this, forward, reverse);
   }

   public <KT> MapPipelineBuilder<KI, VI, KT, VO> convertKeys(final BiConverter<KT, KO> converter)
   {
      return convertKeys(
               new BiConverterForwardConverter<KT, KO>(converter),
               new BiConverterReverseConverter<KT, KO>(converter));
   }

   public MapPipelineBuilder<KI, VI, KO, VO> vetoPuts(final PutVeto<? super KO, ? super VO> veto)
   {
      return new VetoPutPipelineBuilder<KI, VI, KO, VO>(this, veto);
   }
}
