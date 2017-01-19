package com.EasyUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anoopd on 1/13/17.
 */
public class Ru {
    public static Thread meanwhile(String fullyQualifiedMethodName) {
        return meanwhile(fullyQualifiedMethodName,
                new ArrayList<Class>(),
                new ArrayList<Object>(),
                new ArrayList<Class>(),
                new ArrayList<Object>());
    }

    public static Thread meanwhile(String fullyQualifiedMethodName, List<Class> typeList, List<Object> valueList) {
        return meanwhile(fullyQualifiedMethodName,
                typeList,
                valueList,
                new ArrayList<Class>(),
                new ArrayList<Object>());
    }

    public static Thread meanwhile(String fullyQualifiedMethodName,
                                 List<Class> typeList,
                                 List<Object> valueList,
                                 List<Class> constructorType,
                                 List<Object> constructorValue) {
        try {
            String[] parts = fullyQualifiedMethodName.split("\\.");
            String methodName = parts[parts.length - 1];
            String className = parts[0];
            for(int i=1; i < parts.length - 1; i++)
                className = className + "." + parts[i];

            Class objectClass = Class.forName(className);
            Class[] types = (typeList.toArray(new Class[typeList.size()]));
            Method runnableMethod = objectClass.getDeclaredMethod(methodName, types);
            // For static methods
            if (Modifier.isStatic(runnableMethod.getModifiers())) {
                return createThread(objectClass, runnableMethod, valueList);
            } else {
                Class[] consTypes = (constructorType.toArray(new Class[constructorType.size()]));
                Constructor<?> cons = objectClass.getConstructor(consTypes);
                Object objectNonStatic = cons.newInstance(constructorValue.toArray());
                return createThread(objectNonStatic, runnableMethod, valueList);
            }
        } catch(ClassNotFoundException cNF){
            cNF.printStackTrace();
        } catch(NoSuchMethodException nM){
            nM.printStackTrace();
        } catch(IllegalAccessException iA){
            iA.printStackTrace();
        } catch(InvocationTargetException iT){
            iT.printStackTrace();
        } catch(InstantiationException iE){
            iE.printStackTrace();
        }
        //Should never come here.
        return null;
    }

    private static Thread createThread(Object objectClass,
                                     Method runnableMethod,
                                     List<Object> valueList) {
        Thread t = new Thread(() -> {
            try {
                Object[] paramValues = valueList.toArray();
                runnableMethod.invoke(objectClass, paramValues);
            } catch(IllegalAccessException iAE) {
                iAE.printStackTrace();
            } catch(InvocationTargetException iTE) {
                iTE.printStackTrace();
            }
        }
        );
        t.start();
        return t;
    }
}
