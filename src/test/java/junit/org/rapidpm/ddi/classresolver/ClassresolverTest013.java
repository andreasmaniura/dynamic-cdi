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

package junit.org.rapidpm.ddi.classresolver;

import org.junit.Test;
import org.rapidpm.ddi.DDIModelException;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.ResponsibleFor;
import org.rapidpm.ddi.implresolver.ClassResolver;

import javax.inject.Inject;

public class ClassresolverTest013 {

  @Test(expected = DDIModelException.class)
  public void test001() throws Exception {
    Service service = new Service();
    DI.activateDI(service);
  }

  public interface SubService {
  }

  public static class Service {
    @Inject SubService subService;
  }

  @ResponsibleFor(SubService.class)
  public static class SubServiceResolver implements ClassResolver<SubService> {

    public SubServiceResolver(String txt) {
    }

    @Override
    public Class<? extends SubService> resolve(Class<SubService> interf) {
      return SubServiceImplA.class;
    }
  }

  public static class SubServiceImplA implements SubService {
    public SubServiceImplA() {
      throw new RuntimeException("should never be be..");
    }
  }

  public static class SubServiceImplB implements SubService {
    public SubServiceImplB() {
      throw new RuntimeException("should never be be..");
    }
  }

}
