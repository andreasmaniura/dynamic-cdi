package org.rapidpm.ddi.bootstrap;

import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.implresolver.ClassResolver;
import org.rapidpm.ddi.DDIModelException;
import org.rapidpm.ddi.implresolver.ImplementingClassResolver;
import org.rapidpm.ddi.ResponsibleFor;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by svenruppert on 06.08.15.
 */
public class ClassResolverCheck001 {


  public void execute() {
    final Set<Class<? extends ClassResolver>> subTypesOfClassResolver = DI.getSubTypesOf(ClassResolver.class);
    final boolean remove = subTypesOfClassResolver.remove(ImplementingClassResolver.class);

//        ClassResolver responsible for interface
    final Iterator<Class<? extends ClassResolver>> iterator = subTypesOfClassResolver.iterator();
    while (iterator.hasNext()) {
      Class<? extends ClassResolver> aClassResolver = iterator.next();
      if (aClassResolver.isAnnotationPresent(ResponsibleFor.class)) {
        //ok
      } else {
        throw new DDIModelException("Found ClassResolver without @ResponsibleForInterface annotation= " + aClassResolver);
      }
    }
  }


}
