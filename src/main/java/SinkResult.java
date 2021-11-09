import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.Transformer;

enum SinkResultID {
    RuntimeExec, TemplatesImplNewTransformer, TemplatesImplTrAXFilter;
}

class DefaultConfig {
    public static String command = "calc.exe";
    public static String code = "java.lang.Runtime.getRuntime().exec(\"calc.exe\");";
}

public class SinkResult {
    public SinkResultID id;
    public org.apache.commons.collections.Transformer transformer;
    public org.apache.commons.collections4.Transformer transformer4;
    public TemplatesImpl templates;

    public SinkResult(SinkResultID id , Transformer transformer, org.apache.commons.collections4.Transformer transformer4, TemplatesImpl templates) {
        this.id = id;
        this.transformer = transformer;
        this.transformer4 = transformer4;
        this.templates = templates;
    }

}
