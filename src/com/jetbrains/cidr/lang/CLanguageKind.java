// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.util.PlatformUtils;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Key;

public enum CLanguageKind implements OCLanguageKind
{
    C("C", "c"), 
    OBJ_C("Objective-C", "m"), 
    CPP("C++", "cpp"), 
    OBJ_CPP("Objective-C++", "mm");
    
    public static final Key<CLanguageKind> MAX_LANGUAGE_KIND_KEY;
    private final String myDefaultSourceExtension;
    private final String myDisplayName;
    
    private CLanguageKind(final String myDisplayName, final String myDefaultSourceExtension) {
        if (myDisplayName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/CLanguageKind", "<init>"));
        }
        if (myDefaultSourceExtension == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "extension", "com/jetbrains/cidr/lang/CLanguageKind", "<init>"));
        }
        super(s, n);
        this.myDefaultSourceExtension = myDefaultSourceExtension;
        this.myDisplayName = myDisplayName;
    }
    
    @NotNull
    public static CLanguageKind maxLanguage(@Nullable final Project project) {
        CLanguageKind cLanguageKind = null;
        Label_0024: {
            try {
                if (project != null) {
                    cLanguageKind = (CLanguageKind)project.getUserData((Key)CLanguageKind.MAX_LANGUAGE_KIND_KEY);
                    break Label_0024;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            cLanguageKind = null;
        }
        final CLanguageKind cLanguageKind2 = cLanguageKind;
        CLanguageKind cLanguageKind4 = null;
        Label_0095: {
            Label_0076: {
                CLanguageKind cLanguageKind3 = null;
                Label_0041: {
                    try {
                        if (cLanguageKind2 == null) {
                            break Label_0076;
                        }
                        cLanguageKind3 = cLanguageKind2;
                        if (cLanguageKind3 == null) {
                            break Label_0041;
                        }
                        return cLanguageKind3;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        cLanguageKind3 = cLanguageKind2;
                        if (cLanguageKind3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/CLanguageKind", "maxLanguage"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return cLanguageKind3;
                try {
                    if (PlatformUtils.isAppCode()) {
                        final CLanguageKind cLanguageKind5;
                        cLanguageKind4 = (cLanguageKind5 = CLanguageKind.OBJ_CPP);
                        break Label_0095;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            CLanguageKind cLanguageKind5;
            cLanguageKind4 = (cLanguageKind5 = CLanguageKind.CPP);
            try {
                if (cLanguageKind5 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/CLanguageKind", "maxLanguage"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return cLanguageKind4;
    }
    
    @NotNull
    public static CLanguageKind min(@NotNull final CLanguageKind p0, @NotNull final CLanguageKind p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "a"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "min"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "b"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "min"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_1        
        //    90: if_acmpne       140
        //    93: aload_0        
        //    94: dup            
        //    95: ifnonnull       139
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: new             Ljava/lang/IllegalStateException;
        //   108: dup            
        //   109: ldc             "@NotNull method %s.%s must not return null"
        //   111: ldc             2
        //   113: anewarray       Ljava/lang/Object;
        //   116: dup            
        //   117: ldc             0
        //   119: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //   121: aastore        
        //   122: dup            
        //   123: ldc             1
        //   125: ldc             "min"
        //   127: aastore        
        //   128: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   131: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   134: athrow         
        //   135: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: areturn        
        //   140: aload_0        
        //   141: getstatic       com/jetbrains/cidr/lang/CLanguageKind.C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   144: if_acmpeq       161
        //   147: aload_1        
        //   148: getstatic       com/jetbrains/cidr/lang/CLanguageKind.C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   151: if_acmpne       210
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: getstatic       com/jetbrains/cidr/lang/CLanguageKind.C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   164: dup            
        //   165: ifnonnull       209
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: new             Ljava/lang/IllegalStateException;
        //   178: dup            
        //   179: ldc             "@NotNull method %s.%s must not return null"
        //   181: ldc             2
        //   183: anewarray       Ljava/lang/Object;
        //   186: dup            
        //   187: ldc             0
        //   189: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //   191: aastore        
        //   192: dup            
        //   193: ldc             1
        //   195: ldc             "min"
        //   197: aastore        
        //   198: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   201: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   204: athrow         
        //   205: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: areturn        
        //   210: aload_0        
        //   211: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   214: if_acmpne       280
        //   217: aload_1        
        //   218: getstatic       com/jetbrains/cidr/lang/CLanguageKind.CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   221: if_acmpne       280
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: getstatic       com/jetbrains/cidr/lang/CLanguageKind.C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   234: dup            
        //   235: ifnonnull       279
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: new             Ljava/lang/IllegalStateException;
        //   248: dup            
        //   249: ldc             "@NotNull method %s.%s must not return null"
        //   251: ldc             2
        //   253: anewarray       Ljava/lang/Object;
        //   256: dup            
        //   257: ldc             0
        //   259: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //   261: aastore        
        //   262: dup            
        //   263: ldc             1
        //   265: ldc             "min"
        //   267: aastore        
        //   268: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   271: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   274: athrow         
        //   275: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   278: athrow         
        //   279: areturn        
        //   280: aload_1        
        //   281: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   284: if_acmpne       350
        //   287: aload_0        
        //   288: getstatic       com/jetbrains/cidr/lang/CLanguageKind.CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   291: if_acmpne       350
        //   294: goto            301
        //   297: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   300: athrow         
        //   301: getstatic       com/jetbrains/cidr/lang/CLanguageKind.C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   304: dup            
        //   305: ifnonnull       349
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: new             Ljava/lang/IllegalStateException;
        //   318: dup            
        //   319: ldc             "@NotNull method %s.%s must not return null"
        //   321: ldc             2
        //   323: anewarray       Ljava/lang/Object;
        //   326: dup            
        //   327: ldc             0
        //   329: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //   331: aastore        
        //   332: dup            
        //   333: ldc             1
        //   335: ldc             "min"
        //   337: aastore        
        //   338: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   341: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   344: athrow         
        //   345: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   348: athrow         
        //   349: areturn        
        //   350: aload_0        
        //   351: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   354: if_acmpne       420
        //   357: aload_1        
        //   358: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   361: if_acmpne       420
        //   364: goto            371
        //   367: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   370: athrow         
        //   371: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   374: dup            
        //   375: ifnonnull       419
        //   378: goto            385
        //   381: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   384: athrow         
        //   385: new             Ljava/lang/IllegalStateException;
        //   388: dup            
        //   389: ldc             "@NotNull method %s.%s must not return null"
        //   391: ldc             2
        //   393: anewarray       Ljava/lang/Object;
        //   396: dup            
        //   397: ldc             0
        //   399: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //   401: aastore        
        //   402: dup            
        //   403: ldc             1
        //   405: ldc             "min"
        //   407: aastore        
        //   408: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   411: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   414: athrow         
        //   415: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: areturn        
        //   420: aload_1        
        //   421: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   424: if_acmpne       490
        //   427: aload_0        
        //   428: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   431: if_acmpne       490
        //   434: goto            441
        //   437: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   440: athrow         
        //   441: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_C:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   444: dup            
        //   445: ifnonnull       489
        //   448: goto            455
        //   451: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   454: athrow         
        //   455: new             Ljava/lang/IllegalStateException;
        //   458: dup            
        //   459: ldc             "@NotNull method %s.%s must not return null"
        //   461: ldc             2
        //   463: anewarray       Ljava/lang/Object;
        //   466: dup            
        //   467: ldc             0
        //   469: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //   471: aastore        
        //   472: dup            
        //   473: ldc             1
        //   475: ldc             "min"
        //   477: aastore        
        //   478: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   481: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   484: athrow         
        //   485: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: areturn        
        //   490: aload_0        
        //   491: getstatic       com/jetbrains/cidr/lang/CLanguageKind.CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   494: if_acmpne       560
        //   497: aload_1        
        //   498: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   501: if_acmpne       560
        //   504: goto            511
        //   507: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   510: athrow         
        //   511: getstatic       com/jetbrains/cidr/lang/CLanguageKind.CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   514: dup            
        //   515: ifnonnull       559
        //   518: goto            525
        //   521: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   524: athrow         
        //   525: new             Ljava/lang/IllegalStateException;
        //   528: dup            
        //   529: ldc             "@NotNull method %s.%s must not return null"
        //   531: ldc             2
        //   533: anewarray       Ljava/lang/Object;
        //   536: dup            
        //   537: ldc             0
        //   539: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //   541: aastore        
        //   542: dup            
        //   543: ldc             1
        //   545: ldc             "min"
        //   547: aastore        
        //   548: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   551: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   554: athrow         
        //   555: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   558: athrow         
        //   559: areturn        
        //   560: aload_1        
        //   561: getstatic       com/jetbrains/cidr/lang/CLanguageKind.CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   564: if_acmpne       630
        //   567: aload_0        
        //   568: getstatic       com/jetbrains/cidr/lang/CLanguageKind.OBJ_CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   571: if_acmpne       630
        //   574: goto            581
        //   577: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   580: athrow         
        //   581: getstatic       com/jetbrains/cidr/lang/CLanguageKind.CPP:Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   584: dup            
        //   585: ifnonnull       629
        //   588: goto            595
        //   591: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   594: athrow         
        //   595: new             Ljava/lang/IllegalStateException;
        //   598: dup            
        //   599: ldc             "@NotNull method %s.%s must not return null"
        //   601: ldc             2
        //   603: anewarray       Ljava/lang/Object;
        //   606: dup            
        //   607: ldc             0
        //   609: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //   611: aastore        
        //   612: dup            
        //   613: ldc             1
        //   615: ldc             "min"
        //   617: aastore        
        //   618: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   621: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   624: athrow         
        //   625: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   628: athrow         
        //   629: areturn        
        //   630: aload_0        
        //   631: dup            
        //   632: ifnonnull       669
        //   635: new             Ljava/lang/IllegalStateException;
        //   638: dup            
        //   639: ldc             "@NotNull method %s.%s must not return null"
        //   641: ldc             2
        //   643: anewarray       Ljava/lang/Object;
        //   646: dup            
        //   647: ldc             0
        //   649: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //   651: aastore        
        //   652: dup            
        //   653: ldc             1
        //   655: ldc             "min"
        //   657: aastore        
        //   658: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   661: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   664: athrow         
        //   665: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   668: athrow         
        //   669: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     98     101    105    Ljava/lang/IllegalArgumentException;
        //  93     135    135    139    Ljava/lang/IllegalArgumentException;
        //  140    154    157    161    Ljava/lang/IllegalArgumentException;
        //  147    168    171    175    Ljava/lang/IllegalArgumentException;
        //  161    205    205    209    Ljava/lang/IllegalArgumentException;
        //  210    224    227    231    Ljava/lang/IllegalArgumentException;
        //  217    238    241    245    Ljava/lang/IllegalArgumentException;
        //  231    275    275    279    Ljava/lang/IllegalArgumentException;
        //  280    294    297    301    Ljava/lang/IllegalArgumentException;
        //  287    308    311    315    Ljava/lang/IllegalArgumentException;
        //  301    345    345    349    Ljava/lang/IllegalArgumentException;
        //  350    364    367    371    Ljava/lang/IllegalArgumentException;
        //  357    378    381    385    Ljava/lang/IllegalArgumentException;
        //  371    415    415    419    Ljava/lang/IllegalArgumentException;
        //  420    434    437    441    Ljava/lang/IllegalArgumentException;
        //  427    448    451    455    Ljava/lang/IllegalArgumentException;
        //  441    485    485    489    Ljava/lang/IllegalArgumentException;
        //  490    504    507    511    Ljava/lang/IllegalArgumentException;
        //  497    518    521    525    Ljava/lang/IllegalArgumentException;
        //  511    555    555    559    Ljava/lang/IllegalArgumentException;
        //  560    574    577    581    Ljava/lang/IllegalArgumentException;
        //  567    588    591    595    Ljava/lang/IllegalArgumentException;
        //  581    625    625    629    Ljava/lang/IllegalArgumentException;
        //  630    665    665    669    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0161:
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
    public static CLanguageKind fromCompilerSetting(@Nullable final String s) {
        try {
            if ("objective-c".equals(s)) {
                return CLanguageKind.OBJ_C;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if ("objective-c++".equals(s)) {
                return CLanguageKind.OBJ_CPP;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if ("c".equals(s)) {
                return CLanguageKind.C;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if ("c++".equals(s)) {
                return CLanguageKind.CPP;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    @Override
    public boolean isCpp() {
        Label_0021: {
            try {
                if (this == CLanguageKind.CPP) {
                    break Label_0021;
                }
                final CLanguageKind cLanguageKind = this;
                final CLanguageKind cLanguageKind2 = CLanguageKind.OBJ_CPP;
                if (cLanguageKind == cLanguageKind2) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CLanguageKind cLanguageKind = this;
                final CLanguageKind cLanguageKind2 = CLanguageKind.OBJ_CPP;
                if (cLanguageKind == cLanguageKind2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isObjC() {
        Label_0021: {
            try {
                if (this == CLanguageKind.OBJ_C) {
                    break Label_0021;
                }
                final CLanguageKind cLanguageKind = this;
                final CLanguageKind cLanguageKind2 = CLanguageKind.OBJ_CPP;
                if (cLanguageKind == cLanguageKind2) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CLanguageKind cLanguageKind = this;
                final CLanguageKind cLanguageKind2 = CLanguageKind.OBJ_CPP;
                if (cLanguageKind == cLanguageKind2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean conforms(@NotNull final OCLanguageKind p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "requiredKind"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/CLanguageKind"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "conforms"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/CLanguageKind.isObjC:()Z
        //    48: ifne            67
        //    51: aload_1        
        //    52: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //    57: ifne            105
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_0        
        //    68: invokevirtual   com/jetbrains/cidr/lang/CLanguageKind.isCpp:()Z
        //    71: ifne            97
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aload_1        
        //    82: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //    87: ifne            105
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: iconst_1       
        //    98: goto            106
        //   101: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: iconst_0       
        //   106: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     60     63     67     Ljava/lang/IllegalArgumentException;
        //  51     74     77     81     Ljava/lang/IllegalArgumentException;
        //  67     90     93     97     Ljava/lang/IllegalArgumentException;
        //  81     101    101    105    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
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
    
    @NotNull
    @Override
    public String getDefaultSourceExtension() {
        String myDefaultSourceExtension;
        try {
            myDefaultSourceExtension = this.myDefaultSourceExtension;
            if (myDefaultSourceExtension == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/CLanguageKind", "getDefaultSourceExtension"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDefaultSourceExtension;
    }
    
    @NotNull
    @Override
    public String getDisplayName() {
        String myDisplayName;
        try {
            myDisplayName = this.myDisplayName;
            if (myDisplayName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/CLanguageKind", "getDisplayName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDisplayName;
    }
    
    @Override
    public boolean supportsPrecompiledHeaders() {
        return true;
    }
    
    static {
        MAX_LANGUAGE_KIND_KEY = Key.create("MAX_LANGUAGE_KIND_KEY");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
