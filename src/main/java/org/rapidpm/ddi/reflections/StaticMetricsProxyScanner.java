/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.rapidpm.ddi.reflections;

import org.rapidpm.proxybuilder.staticgenerated.annotations.IsMetricsProxy;

import java.lang.annotation.Annotation;

public class StaticMetricsProxyScanner extends StaticBaseProxyScanner {
  @Override
  protected Class<? extends Annotation> responsibleForAnnotation() {
    return IsMetricsProxy.class;
  }

//  /**
//   * created new SubTypesScanner. will exclude direct Object subtypes
//   */
//  public StaticMetricsProxyScanner() {
//    this(true); //exclude direct Object subtypes by default
//  }
//
//  /**
//   * created new SubTypesScanner.
//   *
//   * @param excludeObjectClass if false, include direct {@link Object} subtypes in results.
//   */
//  public StaticMetricsProxyScanner(boolean excludeObjectClass) {
//    if (excludeObjectClass) {
//      filterResultsBy(new FilterBuilder().exclude(Object.class.getName())); //exclude direct Object subtypes
//    }
//  }
//
//  @SuppressWarnings({"unchecked"})
//  public void scan(final Object cls) {
//    String className = getMetadataAdapter().getClassName(cls);
//    final List<String> interfacesNames = getMetadataAdapter().getInterfacesNames(cls);
//    final String superclassName = getMetadataAdapter().getSuperclassName(cls);
//
//    final Set<String> classAnnotationNames = new HashSet<>(getMetadataAdapter().getClassAnnotationNames(cls));
//    if (classAnnotationNames.contains(IsMetricsProxy.class.getName())) {
//      if (superclassName.isEmpty()) {
//        //
//      } else {
//        getStore().put(superclassName, className);
//      }
//      interfacesNames.forEach(c -> getStore().put(c, className));
//    }
//  }
}

