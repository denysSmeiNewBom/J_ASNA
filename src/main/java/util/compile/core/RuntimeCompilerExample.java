package util.compile.core;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * An example showing how to use the RuntimeCompiler utility class
 */
public class RuntimeCompilerExample
{/*
    *//**
     * Simple example: Shows how to add and compile a class, and then
     * invoke a static method on the loaded class.
     *//*
    private static void simpleExample()
    {
        String classNameA = "ExampleClass";
        String codeA =
            "public class ExampleClass {" + "\n" +
            "    public static void exampleMethod(String name) {" + "\n" +
            "        System.out.println(\"Hello, \"+name);" + "\n" +
            "    }" + "\n" +
            "}" + "\n";

        RuntimeCompiler r = new RuntimeCompiler();
        r.addClass(classNameA, codeA);
        r.compile();

        MethodInvocationUtils.invokeStaticMethod(
            r.getCompiledClass(classNameA),
            "exampleMethod", "exampleParameter");
    }

    *//**
     * An example showing how to add two classes (where one refers to the
     * other), compile them, and invoke a static method on one of them
     *//*
    private static void twoClassExample()
    {
        String classNameA = "ExampleClassA";
        String codeA =
            "public class ExampleClassA {" + "\n" +
            "    public static void exampleMethodA(String name) {" + "\n" +
            "        System.out.println(\"Hello, \"+name);" + "\n" +
            "    }" + "\n" +
            "}" + "\n";

        String classNameB = "ExampleClassB";
        String codeB =
            "public class ExampleClassB {" + "\n" +
            "    public static void exampleMethodB(String name) {" + "\n" +
            "        System.out.println(\"Passing to other class\");" + "\n" +
            "        ExampleClassA.exampleMethodA(name);" + "\n" +
            "    }" + "\n" +
            "}" + "\n";

        RuntimeCompiler r = new RuntimeCompiler();
        r.addClass(classNameA, codeA);
        r.addClass(classNameB, codeB);
        r.compile();

        MethodInvocationUtils.invokeStaticMethod(
            r.getCompiledClass(classNameB),
            "exampleMethodB", "exampleParameter");
    }

    *//**
     * An example that compiles and loads a class, and then uses an
     * instance of this class
     *//*
    private static void useLoadedClassExample() throws Exception
    {
        String classNameA = "ExampleComparator";
        String codeA =
            "import java.util.Comparator;" + "\n" +
            "public class ExampleComparator " + "\n" +
            "    implements Comparator<Integer> {" + "\n" +
            "    @Override" + "\n" +
            "    public int compare(Integer i0, Integer i1) {" + "\n" +
            "        System.out.println(i0+\" and \"+i1);" + "\n" +
            "        return Integer.compare(i0, i1);" + "\n" +
            "    }" + "\n" +
            "}" + "\n";

        RuntimeCompiler r = new RuntimeCompiler();
        r.addClass(classNameA, codeA);
        r.compile();

        Class<?> c = r.getCompiledClass("ExampleComparator");
        Comparator<Integer> comparator = (Comparator<Integer>) c.newInstance();

        System.out.println("Sorting...");
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(3,1,2));
        Collections.sort(list, comparator);
        System.out.println("Result: "+list);
    }
*/
}


/**
 * Utility methods not directly related to the RuntimeCompiler
 */
class MethodInvocationUtils
{/*
    *//**
     * Utility method to invoke the first static method in the given 
     * class that can accept the given parameters.
     *  
     * @param c The class
     * @param methodName The method name
     * @param args The arguments for the method call
     * @return The return value of the method call
     * @throws RuntimeException If either the class or a matching method
     * could not be found
     *//*
    public static Object invokeStaticMethod(
        Class<?> c, String methodName, Object... args)
    {
        Method m = findFirstMatchingStaticMethod(c, methodName, args);
        if (m == null)
        {
            throw new RuntimeException("No matching method found");
        }
        try
        {
            return m.invoke(null, args);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
        catch (IllegalArgumentException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
        catch (SecurityException e)
        {
            throw new RuntimeException(e);
        }
    }

    *//**
     * Utility method to find the first static method in the given
     * class that has the given name and can accept the given 
     * arguments. Returns <code>null</code> if no such method 
     * can be found.
     * 
     * @param c The class
     * @param methodName The name of the method 
     * @param args The arguments
     * @return The first matching static method.
     *//*
    private static Method findFirstMatchingStaticMethod(
        Class<?> c, String methodName, Object ... args)
    {
        Method methods[] = c.getDeclaredMethods();
        for (Method m : methods)
        {
            if (m.getName().equals(methodName) &&
                Modifier.isStatic(m.getModifiers()))
            {
                Class<?>[] parameterTypes = m.getParameterTypes();
                if (areAssignable(parameterTypes, args))
                {
                    return m;
                }
            }
        }
        return null;
    }

    *//**
     * Returns whether the given arguments are assignable to the
     * respective types
     * 
     * @param types The types
     * @param args The arguments
     * @return Whether the arguments are assignable
     *//*
    private static boolean areAssignable(Class<?> types[], Object ...args)
    {
        if (types.length != args.length)
        {
            return false;
        }
        for (int i=0; i<types.length; i++)
        {
            Object arg = args[i];
            Class<?> type = types[i];
            if (arg != null && !type.isAssignableFrom(arg.getClass()))
            {
                return false;
            }
        }
        return true;
    }*/
}
