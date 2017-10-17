// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Pair;
import java.util.Iterator;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.io.IOException;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.io.FileUtil;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.io.StreamTokenizer;
import java.io.File;

public class OCClangMessagesParser
{
    private File myClangDir;
    private String myFileName;
    private StringBuilder myBuilder;
    private StreamTokenizer myTokenizer;
    private Stack<String> myCategories;
    private Set<String> mySupportedIDs;
    private Map<String, String> myDiagnosticGroups;
    private boolean myVerifyIDsMode;
    protected static final String SUPPORTED_IDS_FILE = "supported-ids.txt";
    
    private void a(final String s) throws IOException {
        if (this.myVerifyIDsMode) {
            this.mySupportedIDs = new HashSet<String>();
            for (final String s2 : StringUtil.split(FileUtil.loadFile(new File(this.myClangDir, "supported-ids.txt")), "\n")) {
                Label_0092: {
                    try {
                        if (s2.startsWith("//")) {
                            continue;
                        }
                        final String s3 = s2;
                        final boolean b = s3.isEmpty();
                        if (!b) {
                            break Label_0092;
                        }
                        continue;
                    }
                    catch (IOException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s3 = s2;
                        final boolean b = s3.isEmpty();
                        if (b) {
                            continue;
                        }
                        this.mySupportedIDs.add(s2.trim());
                    }
                    catch (IOException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        try {
            if (this.myDiagnosticGroups == null) {
                this.myDiagnosticGroups = new HashMap<String, String>();
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        (this.myTokenizer = new StreamTokenizer(new StringReader(s))).wordChars(95, 95);
        (this.myCategories = new Stack<String>()).push("Empty Category");
        this.myTokenizer.nextToken();
        while (true) {
            try {
                Block_19: {
                    while (this.myTokenizer.ttype != -1) {
                        if (!this.d()) {
                            break Block_19;
                        }
                    }
                    break;
                }
            }
            catch (IOException ex4) {
                throw a(ex4);
            }
            if (!this.a()) {
                this.b("Let or def expected");
            }
        }
    }
    
    private boolean d() throws IOException {
        try {
            if (!"let".equals(this.b())) {
                return false;
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        this.e();
        String s = this.e();
        this.a('=');
        String s2 = this.c();
        while (this.myTokenizer.ttype == 44) {
            this.a(',');
            s = this.e();
            this.a('=');
            s2 = this.c();
        }
        try {
            this.c("in");
            this.a('{');
            if ("CategoryName".equals(s)) {
                this.myCategories.push(s2);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        Label_0141: {
            while (true) {
                Label_0134: {
                    try {
                        while (this.d()) {}
                        final OCClangMessagesParser ocClangMessagesParser = this;
                        final boolean b = ocClangMessagesParser.a();
                        if (b) {
                            break Label_0134;
                        }
                        break Label_0141;
                    }
                    catch (IOException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final OCClangMessagesParser ocClangMessagesParser = this;
                        final boolean b = ocClangMessagesParser.a();
                        if (b) {
                            continue;
                        }
                    }
                    catch (IOException ex4) {
                        throw a(ex4);
                    }
                }
                break;
            }
            try {
                if ("CategoryName".equals(s)) {
                    this.myCategories.pop();
                }
            }
            catch (IOException ex5) {
                throw a(ex5);
            }
        }
        this.a('}');
        return true;
    }
    
    private boolean a() throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: aload_0        
        //     3: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myVerifyIDsMode:Z
        //     6: ifeq            70
        //     9: aload_0        
        //    10: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //    13: getfield        java/io/StreamTokenizer.ttype:I
        //    16: bipush          42
        //    18: if_icmpne       40
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //    27: athrow         
        //    28: bipush          42
        //    30: istore_1       
        //    31: aload_0        
        //    32: bipush          42
        //    34: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //    37: goto            70
        //    40: aload_0        
        //    41: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //    44: getfield        java/io/StreamTokenizer.ttype:I
        //    47: bipush          45
        //    49: if_icmpne       64
        //    52: bipush          45
        //    54: istore_1       
        //    55: aload_0        
        //    56: bipush          45
        //    58: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //    61: goto            70
        //    64: aload_0        
        //    65: ldc             "Def is not prefixed"
        //    67: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.b:(Ljava/lang/String;)V
        //    70: ldc             "def"
        //    72: aload_0        
        //    73: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.b:()Ljava/lang/String;
        //    76: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    79: ifne            88
        //    82: iconst_0       
        //    83: ireturn        
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.e:()Ljava/lang/String;
        //    92: pop            
        //    93: aconst_null    
        //    94: astore_2       
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //    99: getfield        java/io/StreamTokenizer.ttype:I
        //   102: bipush          58
        //   104: if_icmpeq       112
        //   107: aload_0        
        //   108: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.e:()Ljava/lang/String;
        //   111: astore_2       
        //   112: aload_0        
        //   113: bipush          58
        //   115: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //   118: aload_0        
        //   119: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.e:()Ljava/lang/String;
        //   122: astore_3       
        //   123: aload_0        
        //   124: bipush          60
        //   126: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //   129: aload_0        
        //   130: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myVerifyIDsMode:Z
        //   133: ifeq            279
        //   136: aload_2        
        //   137: ifnull          279
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   146: athrow         
        //   147: iload_1        
        //   148: bipush          42
        //   150: if_icmpne       211
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   159: athrow         
        //   160: aload_0        
        //   161: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.mySupportedIDs:Ljava/util/Set;
        //   164: aload_2        
        //   165: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   170: ifne            211
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   179: athrow         
        //   180: new             Ljava/io/IOException;
        //   183: dup            
        //   184: new             Ljava/lang/StringBuilder;
        //   187: dup            
        //   188: invokespecial   java/lang/StringBuilder.<init>:()V
        //   191: aload_2        
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: ldc             " is wrongly marked as *"
        //   197: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   200: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   203: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   206: athrow         
        //   207: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   210: athrow         
        //   211: iload_1        
        //   212: bipush          45
        //   214: if_icmpeq       228
        //   217: iload_1        
        //   218: ifne            279
        //   221: goto            228
        //   224: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   227: athrow         
        //   228: aload_0        
        //   229: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.mySupportedIDs:Ljava/util/Set;
        //   232: aload_2        
        //   233: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   238: ifeq            279
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   247: athrow         
        //   248: new             Ljava/io/IOException;
        //   251: dup            
        //   252: new             Ljava/lang/StringBuilder;
        //   255: dup            
        //   256: invokespecial   java/lang/StringBuilder.<init>:()V
        //   259: aload_2        
        //   260: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   263: ldc             " is not marked as *"
        //   265: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   268: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   271: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   274: athrow         
        //   275: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   278: athrow         
        //   279: new             Ljava/lang/StringBuilder;
        //   282: dup            
        //   283: invokespecial   java/lang/StringBuilder.<init>:()V
        //   286: astore          4
        //   288: aload_0        
        //   289: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //   292: getfield        java/io/StreamTokenizer.ttype:I
        //   295: bipush          34
        //   297: if_icmpeq       319
        //   300: aload_0        
        //   301: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //   304: getfield        java/io/StreamTokenizer.ttype:I
        //   307: bipush          -3
        //   309: if_icmpne       347
        //   312: goto            319
        //   315: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   318: athrow         
        //   319: aload           4
        //   321: aload_0        
        //   322: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //   325: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
        //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: pop            
        //   332: aload_0        
        //   333: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //   336: invokevirtual   java/io/StreamTokenizer.nextToken:()I
        //   339: pop            
        //   340: goto            288
        //   343: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   346: athrow         
        //   347: aload_0        
        //   348: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //   351: getfield        java/io/StreamTokenizer.ttype:I
        //   354: bipush          44
        //   356: if_icmpne       419
        //   359: aload_0        
        //   360: bipush          44
        //   362: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //   365: aload_0        
        //   366: bipush          91
        //   368: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //   371: aload_0        
        //   372: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.e:()Ljava/lang/String;
        //   375: pop            
        //   376: goto            383
        //   379: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   382: athrow         
        //   383: aload_0        
        //   384: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //   387: getfield        java/io/StreamTokenizer.ttype:I
        //   390: bipush          44
        //   392: if_icmpne       413
        //   395: aload_0        
        //   396: bipush          44
        //   398: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //   401: aload_0        
        //   402: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.e:()Ljava/lang/String;
        //   405: pop            
        //   406: goto            383
        //   409: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   412: athrow         
        //   413: aload_0        
        //   414: bipush          93
        //   416: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //   419: aload_0        
        //   420: bipush          62
        //   422: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //   425: ldc             "Empty Group"
        //   427: astore          5
        //   429: aload_0        
        //   430: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myTokenizer:Ljava/io/StreamTokenizer;
        //   433: getfield        java/io/StreamTokenizer.ttype:I
        //   436: bipush          44
        //   438: if_icmpne       652
        //   441: aload_0        
        //   442: bipush          44
        //   444: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //   447: aload_0        
        //   448: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.f:()Ljava/lang/Object;
        //   451: astore          6
        //   453: aload           6
        //   455: instanceof      Lcom/intellij/openapi/util/Pair;
        //   458: ifeq            649
        //   461: ldc             "InGroup"
        //   463: aload           6
        //   465: checkcast       Lcom/intellij/openapi/util/Pair;
        //   468: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   471: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   474: ifeq            649
        //   477: goto            484
        //   480: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   483: athrow         
        //   484: aload           6
        //   486: checkcast       Lcom/intellij/openapi/util/Pair;
        //   489: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   492: astore          7
        //   494: aload           7
        //   496: instanceof      Ljava/lang/String;
        //   499: ifeq            575
        //   502: aload_0        
        //   503: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myDiagnosticGroups:Ljava/util/Map;
        //   506: aload           7
        //   508: checkcast       Ljava/lang/String;
        //   511: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   516: ifeq            548
        //   519: goto            526
        //   522: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   525: athrow         
        //   526: aload_0        
        //   527: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myDiagnosticGroups:Ljava/util/Map;
        //   530: aload           7
        //   532: checkcast       Ljava/lang/String;
        //   535: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   540: checkcast       Ljava/lang/String;
        //   543: astore          5
        //   545: goto            649
        //   548: aload_0        
        //   549: new             Ljava/lang/StringBuilder;
        //   552: dup            
        //   553: invokespecial   java/lang/StringBuilder.<init>:()V
        //   556: ldc             "No value for diagnostic group "
        //   558: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   561: aload           7
        //   563: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   566: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   569: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.b:(Ljava/lang/String;)V
        //   572: goto            649
        //   575: aload           7
        //   577: instanceof      Lcom/intellij/openapi/util/Pair;
        //   580: ifeq            643
        //   583: ldc             "DiagGroup"
        //   585: aload           7
        //   587: checkcast       Lcom/intellij/openapi/util/Pair;
        //   590: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   593: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   596: ifeq            643
        //   599: goto            606
        //   602: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   605: athrow         
        //   606: aload           7
        //   608: checkcast       Lcom/intellij/openapi/util/Pair;
        //   611: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   614: astore          7
        //   616: aload           7
        //   618: instanceof      Ljava/lang/String;
        //   621: ifeq            634
        //   624: aload           7
        //   626: checkcast       Ljava/lang/String;
        //   629: astore          5
        //   631: goto            649
        //   634: aload_0        
        //   635: ldc             "No value for option InGroup"
        //   637: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.b:(Ljava/lang/String;)V
        //   640: goto            649
        //   643: aload_0        
        //   644: ldc             "No value for option InGroup"
        //   646: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.b:(Ljava/lang/String;)V
        //   649: goto            429
        //   652: aload_0        
        //   653: bipush          59
        //   655: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(C)V
        //   658: aload_2        
        //   659: ifnull          721
        //   662: ldc             "DiagGroup"
        //   664: aload_3        
        //   665: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   668: ifeq            701
        //   671: goto            678
        //   674: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   677: athrow         
        //   678: aload_0        
        //   679: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myDiagnosticGroups:Ljava/util/Map;
        //   682: aload_2        
        //   683: aload           4
        //   685: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   688: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   693: pop            
        //   694: goto            721
        //   697: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/io/IOException;)Ljava/io/IOException;
        //   700: athrow         
        //   701: aload_0        
        //   702: aload_2        
        //   703: aload_3        
        //   704: aload_0        
        //   705: getfield        com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.myCategories:Ljava/util/Stack;
        //   708: invokevirtual   java/util/Stack.peek:()Ljava/lang/Object;
        //   711: checkcast       Ljava/lang/String;
        //   714: aload           5
        //   716: aload           4
        //   718: invokespecial   com/jetbrains/cidr/lang/daemon/clang/OCClangMessagesParser.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/StringBuilder;)V
        //   721: iconst_1       
        //   722: ireturn        
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      21     24     28     Ljava/io/IOException;
        //  70     84     84     88     Ljava/io/IOException;
        //  123    140    143    147    Ljava/io/IOException;
        //  136    153    156    160    Ljava/io/IOException;
        //  147    173    176    180    Ljava/io/IOException;
        //  160    207    207    211    Ljava/io/IOException;
        //  211    221    224    228    Ljava/io/IOException;
        //  217    241    244    248    Ljava/io/IOException;
        //  228    275    275    279    Ljava/io/IOException;
        //  288    312    315    319    Ljava/io/IOException;
        //  300    343    343    347    Ljava/io/IOException;
        //  347    376    379    383    Ljava/io/IOException;
        //  383    409    409    413    Ljava/io/IOException;
        //  453    477    480    484    Ljava/io/IOException;
        //  494    519    522    526    Ljava/io/IOException;
        //  575    599    602    606    Ljava/io/IOException;
        //  652    671    674    678    Ljava/io/IOException;
        //  662    697    697    701    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0147:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Nullable
    private Object f() throws IOException {
        Object f = null;
        String s;
        if (this.myTokenizer.ttype == 34) {
            s = this.c();
        }
        else {
            s = this.e();
        }
        if (this.myTokenizer.ttype == 60) {
            this.a('<');
            f = this.f();
            this.a('>');
        }
        try {
            if (f != null) {
                return Pair.create((Object)s, f);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private void a(final String s, final String s2, final String s3, final String s4, final StringBuilder sb) {
        this.myBuilder.append(s).append("\n").append(s2).append("\n");
        this.myBuilder.append(s3).append("\n").append(s4).append("\n").append(convertPattern(sb.toString())).append("\n");
    }
    
    public static String convertPattern(final String s) {
        return new Parser(s).parse();
    }
    
    private String b() throws IOException {
        try {
            if (this.myTokenizer.ttype == -3) {
                return this.myTokenizer.sval;
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        return "";
    }
    
    private String e() throws IOException {
        if (this.myTokenizer.ttype == -3) {
            final String sval = this.myTokenizer.sval;
            this.myTokenizer.nextToken();
            return sval;
        }
        throw new IOException("Expected word at " + this.myFileName + ":" + this.myTokenizer.lineno());
    }
    
    private String c() throws IOException {
        if (this.myTokenizer.ttype == 34) {
            final String sval = this.myTokenizer.sval;
            this.myTokenizer.nextToken();
            return sval;
        }
        throw new IOException("Expected quoted word at " + this.myFileName + ":" + this.myTokenizer.lineno());
    }
    
    private void c(final String s) throws IOException {
        Label_0033: {
            try {
                if (this.myTokenizer.ttype != -3) {
                    throw new IOException("Expected '" + s + "' at " + this.myFileName + ":" + this.myTokenizer.lineno());
                }
                final String s2 = s;
                final OCClangMessagesParser ocClangMessagesParser = this;
                final StreamTokenizer streamTokenizer = ocClangMessagesParser.myTokenizer;
                final String s3 = streamTokenizer.sval;
                final boolean b = s2.equals(s3);
                if (b) {
                    break Label_0033;
                }
                throw new IOException("Expected '" + s + "' at " + this.myFileName + ":" + this.myTokenizer.lineno());
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                final String s2 = s;
                final OCClangMessagesParser ocClangMessagesParser = this;
                final StreamTokenizer streamTokenizer = ocClangMessagesParser.myTokenizer;
                final String s3 = streamTokenizer.sval;
                final boolean b = s2.equals(s3);
                if (b) {
                    this.myTokenizer.nextToken();
                    return;
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
        }
        throw new IOException("Expected '" + s + "' at " + this.myFileName + ":" + this.myTokenizer.lineno());
    }
    
    private void a(final char c) throws IOException {
        try {
            if (this.myTokenizer.ttype == c) {
                this.myTokenizer.nextToken();
                return;
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        throw new IOException("Expected '" + c + "' at " + this.myFileName + ":" + this.myTokenizer.lineno());
    }
    
    private void b(final String s) throws IOException {
        throw new IOException(s + "' at " + this.myFileName + ":" + this.myTokenizer.lineno());
    }
    
    private void a(final File myClangDir, final boolean myVerifyIDsMode) throws IOException {
        this.myVerifyIDsMode = myVerifyIDsMode;
        this.myBuilder = new StringBuilder();
        if (myClangDir.isDirectory()) {
            this.myClangDir = myClangDir;
            final File file = new File(myClangDir, "DiagnosticGroups.td");
            if (file.exists()) {
                final String loadFile = FileUtil.loadFile(file);
                this.myFileName = file.getName();
                this.a(loadFile);
            }
            for (final File file2 : myClangDir.listFiles()) {
                if (file2.getName().endsWith("Kinds.td")) {
                    final String loadFile2 = FileUtil.loadFile(file2);
                    this.myFileName = file2.getName();
                    this.a(loadFile2);
                }
            }
        }
        else if (myClangDir.getName().endsWith("td")) {
            this.myClangDir = myClangDir.getParentFile();
            final String loadFile3 = FileUtil.loadFile(myClangDir);
            this.myFileName = myClangDir.getName();
            this.a(loadFile3);
        }
        FileUtil.writeToFile(new File(this.myClangDir, "all-messages.txt"), this.myBuilder.toString());
    }
    
    public static void main(final String[] array) throws IOException {
        if (array.length == 1) {
            final File file = new File(array[0]);
            try {
                if (!file.exists()) {
                    System.err.println(file.getPath() + " does not exist");
                    return;
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            new OCClangMessagesParser().a(file, false);
        }
        else {
            System.err.println("Usage: <path to .td file or directory with .td files>");
        }
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    private static class Parser
    {
        private final String myMessage;
        private final int myLength;
        private int myIndex;
        private final StringBuilder myResult;
        
        public Parser(final String myMessage) {
            this.myResult = new StringBuilder();
            this.myMessage = myMessage;
            this.myLength = myMessage.length();
        }
        
        public String parse() {
            while (this.myIndex < this.myLength) {
                final char char1 = this.myMessage.charAt(this.myIndex++);
                if (char1 == '%') {
                    this.b();
                }
                else {
                    this.a(char1);
                }
            }
            return this.myResult.toString();
        }
        
        private void b() {
            if (this.myIndex < this.myLength && this.myMessage.charAt(this.myIndex) == '%') {
                ++this.myIndex;
                this.myResult.append("%");
                return;
            }
            final StringBuilder sb = new StringBuilder();
            while (this.myIndex < this.myLength) {
                final char char1 = this.myMessage.charAt(this.myIndex);
                if (!Character.isDigit(char1) && !Character.isLetter(char1)) {
                    break;
                }
                sb.append(char1);
                ++this.myIndex;
            }
            final String string = sb.toString();
            if (string.equals("select") || string.equals("diff") || string.equals("plural")) {
                assert this.myIndex < this.myLength;
                assert this.myMessage.charAt(this.myIndex) == '{';
                ++this.myIndex;
                this.myResult.append("(");
                this.a(string.equals("plural"));
            }
            else {
                this.myResult.append(".*");
            }
        }
        
        private void a(final boolean b) {
            int n = b ? 1 : 0;
            while (this.myIndex < this.myLength) {
                final char char1 = this.myMessage.charAt(this.myIndex++);
                if (n != 0) {
                    if (Character.isDigit(char1)) {
                        continue;
                    }
                    if (char1 == ':') {
                        continue;
                    }
                    n = 0;
                }
                if (char1 == '%') {
                    this.b();
                }
                else {
                    if (char1 == '}') {
                        this.a();
                        return;
                    }
                    if (char1 == '|') {
                        this.myResult.append("|");
                        n = (b ? 1 : 0);
                    }
                    else {
                        this.a(char1);
                    }
                }
            }
            assert false;
        }
        
        private void a() {
            while (this.myIndex < this.myLength) {
                final char char1 = this.myMessage.charAt(this.myIndex);
                if (!Character.isDigit(char1) && char1 != ',') {
                    break;
                }
                ++this.myIndex;
            }
            this.myResult.append(")");
        }
        
        private void a(final char c) {
            if (c == '$') {
                this.myResult.append(".*");
            }
            else if (c == '(' || c == ')' || c == '+' || c == '*' || c == '?' || c == '.' || c == '[' || c == ']' || c == '{' || c == '}' || c == '|' || c == '\\' || c == '\"') {
                this.myResult.append("\\").append(c);
            }
            else {
                this.myResult.append(c);
            }
        }
    }
}
