import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class X10D {

    private static int loaderCompatibility = 1;

    public static X10Sion getX10SionObject(String jarPath, String className, Object ... initArgs) throws Exception {
        ArrayList<Class> classes = loadJarFile(jarPath);
        X10Sion xtend = null;
        for(Class c : classes) {
            if (c.toString().equals("class " + className)) {
                xtend = (X10Sion) c.getDeclaredConstructor().newInstance(initArgs);
                break;
            }
        }
        if (xtend == null) throw new Exception("Unable to find class: " + className);
        if (xtend.X10SionCompatibility() > loaderCompatibility) throw new Exception("Program is too new to load.");
        if (xtend.X10SionCompatibility() < loaderCompatibility) System.out.println("[Warning] Program is older than loader. Program may crash.");
        return xtend;
    }

    public static ArrayList<Class> loadJarFile(String filePath) throws Exception {
        ArrayList<Class> availableClasses = new ArrayList<>();
        ArrayList<String> classNames = getClassNamesFromJar(filePath);
        File file = new File(filePath);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
        for (String className : classNames) {
            Class cc = classLoader.loadClass(className);
            availableClasses.add(cc);
        }
        return availableClasses;
    }

    public static ArrayList<String> getClassNamesFromJar(JarInputStream jarFile) throws Exception {
        ArrayList<String> classNames = new ArrayList<>();
        try {
            JarEntry jar;
            while (true) {
                jar = jarFile.getNextJarEntry();
                if (jar == null) {
                    break;
                }
                //Pick file that has the extension of .class
                if ((jar.getName().endsWith(".class"))) {
                    String className = jar.getName().replaceAll("/", "\\.");
                    String myClass = className.substring(0, className.lastIndexOf('.'));
                    classNames.add(myClass);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while getting class names from jar", e);
        }
        return classNames;
    }

    public static ArrayList<String> getClassNamesFromJar(String jarPath) throws Exception {
        return getClassNamesFromJar(new JarInputStream(new FileInputStream(jarPath)));
    }
}
