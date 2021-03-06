/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES   OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.metrics.core.timeline.uuid;

import java.security.SecureRandom;

import org.apache.ambari.metrics.core.timeline.aggregators.TimelineClusterMetric;

import com.google.common.primitives.Longs;

public class RandomUuidGenStrategy implements MetricUuidGenStrategy {
  private static SecureRandom randomGenerator;

  public RandomUuidGenStrategy() {
    randomGenerator = new SecureRandom(
      Longs.toByteArray(System.currentTimeMillis()));
  }

  @Override
  public byte[] computeUuid(TimelineClusterMetric timelineClusterMetric, int maxLength) {
    final byte[] bytes = new byte[maxLength];
    randomGenerator.nextBytes(bytes);
    return bytes;
  }

//  @Override
//  public byte[] computeUuid(TimelineMetric timelineMetric, int maxLength) {
//    return new byte[10];
//  }

  @Override
  public byte[] computeUuid(String value, int maxLength) {
    final byte[] bytes = new byte[maxLength];
    randomGenerator.nextBytes(bytes);
    return bytes;
  }
}
