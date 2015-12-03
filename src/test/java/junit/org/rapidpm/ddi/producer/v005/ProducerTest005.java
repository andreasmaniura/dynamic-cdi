package junit.org.rapidpm.ddi.producer.v005;

import junit.org.rapidpm.ddi.DDIBaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.ddi.DDIModelException;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.Produces;
import org.rapidpm.ddi.producer.Producer;

import javax.inject.Inject;

/**
 * Created by svenruppert on 12.10.15.
 */
public class ProducerTest005 extends DDIBaseTest {

  @Inject Service service;

  @Test(expected = DDIModelException.class)
  public void test001() throws Exception {
    try {
      DI.activateDI(this);
    } catch (Exception e) {
      Assert.assertTrue(e instanceof DDIModelException);
      final String message = e.getMessage();
      System.out.println("message = " + message);
      Assert.assertTrue(message.contains("interface with multiple implementations and no ClassResolver and n Producers f the interface"));
      throw e;
    }
  }

  public interface Service{}

  public static class ServiceImpl_A implements Service{}
  public static class ServiceImpl_B implements Service{}

  @Produces(Service.class)
  public static class ServiceProducer_A implements Producer<Service> {
    @Override
    public Service create() {
      return new ServiceImpl_A();
    }
  }

  @Produces(Service.class)
  public static class ServiceProducer_B implements Producer<Service> {
    @Override
    public Service create() {
      return new ServiceImpl_A();
    }
  }

  @Produces(ServiceImpl_A.class)
  public static class ServiceImpl_A_Producer implements Producer<Service>{
    @Override
    public Service create() {
      throw new RuntimeException("wrong producer activated");
    }
  }

  @Produces(ServiceImpl_B.class)
  public static class ServiceImpl_B_Producer implements Producer<Service>{
    @Override
    public Service create() {
      throw new RuntimeException("wrong producer activated");
    }
  }
}
