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

package junit.org.rapidpm.ddi.producerresolver.v006;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.Produces;
import org.rapidpm.ddi.ResponsibleFor;
import org.rapidpm.ddi.implresolver.ClassResolver;
import org.rapidpm.ddi.producer.Producer;
import org.rapidpm.ddi.producerresolver.ProducerResolver;

public class ProducerResolverTest006 {


  public static Boolean toggle = true;
  //build a Toggle ProducerResolver
  public static Boolean toggleProducer = true;

  @Test
  public void test001() throws Exception {

    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    DI.activatePackages(ProducerResolverTest006.class);

    Assert.assertEquals(ServiceB.class, DI.activateDI(Service.class).getClass());
    Assert.assertEquals(false, toggle);
    Assert.assertEquals(true, toggleProducer);
    Assert.assertEquals(ServiceA.class, DI.activateDI(Service.class).getClass());
    Assert.assertEquals(true, toggle);
    Assert.assertEquals(false, toggleProducer);

    Assert.assertEquals(ServiceB.class, DI.activateDI(Service.class).getClass());
    Assert.assertEquals(false, toggle);
    Assert.assertEquals(false, toggleProducer);

    Assert.assertEquals(ServiceA.class, DI.activateDI(Service.class).getClass());
    Assert.assertEquals(true, toggle);
    Assert.assertEquals(true, toggleProducer);

    DI.clearReflectionModel();

  }

  public interface Service {
    String doWork(String txt);
  }

  public static class ServiceA implements Service {
    private String postVAlue;

    public String doWork(String txt) {
      return txt + "A - " + postVAlue;
    }
  }

  public static class ServiceB implements Service {
    public String doWork(String txt) {
      return txt + "B";
    }
  }

  @ResponsibleFor(Service.class)
  public static class ServiceClassResolver implements ClassResolver<Service> {

    @Override
    public Class<? extends Service> resolve(final Class<Service> interf) {
      toggle = !toggle;
      System.out.println("toggle = " + toggle);
      return (toggle) ? ServiceA.class : ServiceB.class;
    }
  }

  @ResponsibleFor(ServiceA.class)
  public static class ServiceAProducerResolver implements ProducerResolver<Service, Producer<Service>> {
    @Override
    public Class<? extends Producer<Service>> resolve(final Class<? extends Service> interf) {
      toggleProducer = !toggleProducer;
      System.out.println("toggleProducer = " + toggleProducer);
      return (toggleProducer) ? ServiceAProducerA.class : ServiceAProducerB.class;

    }
  }

  @Produces(ServiceA.class)
  public static class ServiceAProducerA implements Producer<Service> {

    @Override
    public Service create() {
      final ServiceA serviceA = new ServiceA();
      serviceA.postVAlue = "Producer A";
      return serviceA;
    }
  }

  @Produces(ServiceA.class)
  public static class ServiceAProducerB implements Producer<Service> {

    @Override
    public Service create() {
      final ServiceA serviceA = new ServiceA();
      serviceA.postVAlue = "Producer B";
      return serviceA;
    }
  }


}
