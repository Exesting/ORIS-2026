package reflection;

import jdk.jfr.MetadataDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("reflection.Human");

        System.out.println(aClass.getName());

        Field fields[] = aClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        Constructor constructor = aClass.getDeclaredConstructor(double.class);
        constructor.setAccessible(true);
        Human human = (Human) constructor.newInstance(1.9);
        System.out.println(human.getHeight());

//        Method methods[] = aClass.getMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName() + " " +
//                    method.getParameterCount() + " " +
//                    method.getReturnType());
//        }

        Method methodsprivate[] = aClass.getDeclaredMethods();
        for (Method method : methodsprivate) {
            System.out.println(method.getName() + " " +
                    method.getParameterCount() + " " +
                    method.getReturnType());
        }

        Method method = aClass.getMethod("grow", Double.TYPE);
        method.invoke(human, 0.2);
        System.out.println(human.getHeight());

        Method methodWithReturn = aClass.getMethod("getHeight");
        Double result = (Double) methodWithReturn.invoke(human);
        System.out.println(result.byteValue());
    }
}
