/*
 * Copyright 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.template.soy.bidifunctions;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;
import com.google.template.soy.data.Dir;
import com.google.template.soy.data.SanitizedContents;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.restricted.StringData;
import com.google.template.soy.jssrc.restricted.JsExpr;
import com.google.template.soy.shared.SharedRestrictedTestUtils;

import junit.framework.TestCase;

/**
 * Unit tests for BidiMarkAfterFunction.
 *
 */
public class BidiMarkAfterFunctionTest extends TestCase {


  private static final BidiMarkAfterFunction BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR =
      new BidiMarkAfterFunction(SharedRestrictedTestUtils.BIDI_GLOBAL_DIR_FOR_STATIC_LTR_PROVIDER);

  private static final BidiMarkAfterFunction BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL =
      new BidiMarkAfterFunction(SharedRestrictedTestUtils.BIDI_GLOBAL_DIR_FOR_STATIC_RTL_PROVIDER);

  private static final BidiMarkAfterFunction BIDI_MARK_AFTER_FUNCTION_FOR_ISRTL_CODE_SNIPPET =
      new BidiMarkAfterFunction(
          SharedRestrictedTestUtils.BIDI_GLOBAL_DIR_FOR_ISRTL_CODE_SNIPPET_PROVIDER);


  public void testComputeForJava() {

    SoyValue text = StringData.EMPTY_STRING;
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = StringData.forValue("a");
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = StringData.forValue("\u05E0");
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200E"));

    text = SanitizedContents.unsanitizedText("a");
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = SanitizedContents.unsanitizedText("a", Dir.LTR);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = SanitizedContents.unsanitizedText("a", Dir.NEUTRAL);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = SanitizedContents.unsanitizedText("a", Dir.RTL);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200E"));
    text = SanitizedContents.unsanitizedText("\u05E0");
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200E"));
    text = SanitizedContents.unsanitizedText("\u05E0", Dir.RTL);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200E"));
    text = SanitizedContents.unsanitizedText("\u05E0", Dir.NEUTRAL);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200E"));
    text = SanitizedContents.unsanitizedText("\u05E0", Dir.LTR);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200E"));

    text = StringData.EMPTY_STRING;
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = StringData.forValue("\u05E0");
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = StringData.forValue("a");
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200F"));

    text = SanitizedContents.unsanitizedText("\u05E0");
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = SanitizedContents.unsanitizedText("\u05E0", Dir.RTL);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = SanitizedContents.unsanitizedText("\u05E0", Dir.NEUTRAL);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.EMPTY_STRING);
    text = SanitizedContents.unsanitizedText("\u05E0", Dir.LTR);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200F"));
    text = SanitizedContents.unsanitizedText("a");
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200F"));
    text = SanitizedContents.unsanitizedText("a", Dir.LTR);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200F"));
    text = SanitizedContents.unsanitizedText("a", Dir.NEUTRAL);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200F"));
    text = SanitizedContents.unsanitizedText("a", Dir.RTL);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJava(ImmutableList.of(text)))
        .isEqualTo(StringData.forValue("\u200F"));
  }


  public void testComputeForJsSrc() {

    JsExpr textExpr = new JsExpr("TEXT_JS_CODE", Integer.MAX_VALUE);
    assertThat(BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_LTR.computeForJsSrc(ImmutableList.of(textExpr)))
        .isEqualTo(new JsExpr("soy.$$bidiMarkAfter(1, TEXT_JS_CODE)", Integer.MAX_VALUE));
    assertThat(
        BIDI_MARK_AFTER_FUNCTION_FOR_ISRTL_CODE_SNIPPET.computeForJsSrc(ImmutableList.of(textExpr)))
        .isEqualTo(new JsExpr("soy.$$bidiMarkAfter(IS_RTL?-1:1, TEXT_JS_CODE)", Integer.MAX_VALUE));

    JsExpr isHtmlExpr = new JsExpr("IS_HTML_JS_CODE", Integer.MAX_VALUE);
    assertThat(
        BIDI_MARK_AFTER_FUNCTION_FOR_STATIC_RTL.computeForJsSrc(
            ImmutableList.of(textExpr, isHtmlExpr)))
        .isEqualTo(new JsExpr(
            "soy.$$bidiMarkAfter(-1, TEXT_JS_CODE, IS_HTML_JS_CODE)", Integer.MAX_VALUE));
    assertThat(
        BIDI_MARK_AFTER_FUNCTION_FOR_ISRTL_CODE_SNIPPET.computeForJsSrc(
            ImmutableList.of(textExpr, isHtmlExpr)))
        .isEqualTo(new JsExpr(
            "soy.$$bidiMarkAfter(IS_RTL?-1:1, TEXT_JS_CODE, IS_HTML_JS_CODE)", Integer.MAX_VALUE));
  }

}
