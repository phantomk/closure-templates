/*
 * Copyright 2008 Google Inc.
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

package com.google.template.soy.tofu;

import com.google.template.soy.data.SoyDataException;
import com.google.template.soy.sharedpasses.render.RenderException;


/**
 * Exception thrown when an error occurs during template rendering.
 *
 */
public class SoyTofuException extends RuntimeException {


  /**
   * @param message A detailed description of the error.
   */
  public SoyTofuException(String message) {
    super(message);
  }

  /**
   * Creates an instance by copying a RenderException.
   * @param re The RenderException to copy.
   */
  public SoyTofuException(RenderException re) {
    super(re.getMessage(), getRootCause(re));
    // At this point, the stack trace aggregation logic in RenderException can be considered done.
    // Set the stack trace of both the current SoyTofuException class as well as the
    // RenderException class.
    re.finalizeStackTrace();
    re.finalizeStackTrace(this);
  }

  private static Throwable getRootCause(Throwable e) {
    while (e.getCause() != null) {
      if (e instanceof SoyDataException) {
        // Don't unwrap SoyDataException as they are handled differently, which could change the
        // server error status
        return e;
      }
      e = e.getCause();
    }
    return e;
  }
}