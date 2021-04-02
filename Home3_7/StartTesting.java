package homework3_7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StartTesting {

    public static void startTest(Class cl) {
        List<Method> methods = new ArrayList<>();
        Method[] classMethods = cl.getDeclaredMethods();

        for (Method meth : classMethods) {
            if (meth.isAnnotationPresent(Test.class)) {
                methods.add(meth);
            }
        }

        methods.sort(Comparator.comparingInt((Method m) -> m.getAnnotation(Test.class).priority()).reversed());

        for (Method meth : classMethods) {
            if (meth.isAnnotationPresent(BeforeSuite.class)) {
                if (methods.size() > 0 && methods.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException("@BeforeSuite annotation method > 1");
                }
                methods.add(0, meth);
            }
        }

        for (Method meth : classMethods) {
            if (meth.isAnnotationPresent(AfterSuite.class)) {
                if (methods.size() > 0 && methods.get(methods.size() - 1).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("@AfterSuite annotation method > 1");
                }
                methods.add (meth);
            }
            
        }

        for (Method meth : methods) {
            try {
                meth.invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


    }
}
