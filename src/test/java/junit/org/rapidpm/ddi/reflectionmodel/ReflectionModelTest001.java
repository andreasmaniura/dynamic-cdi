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

package junit.org.rapidpm.ddi.reflectionmodel;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.ddi.DDIModelException;
import org.rapidpm.ddi.DI;

import javax.inject.Inject;

public class ReflectionModelTest001 {

  @Test(expected = DDIModelException.class)
  public void test001() throws Exception {
    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    try {
      DI.activateDI(new BusinessModule());
    } catch (Exception e) {
      if (e instanceof DDIModelException) {
        final String message = e.getMessage();
        Assert.assertTrue(message.contains("only interfaces found for interface"));
        throw e;
      } else {
        Assert.fail();
      }
    }
  }


  @Test
  public void test002() throws Exception {
    DI.clearReflectionModel();
    DI.activatePackages("junit.org.rapidpm");
    final BusinessModule businessModule = DI.activateDI(new BusinessModule());
  }


  interface Service {
  }

  public static class ServiceImpl implements Service {
  }

  public static class BusinessModule {
    @Inject Service service;
  }


}
