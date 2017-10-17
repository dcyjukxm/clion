// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.openapi.roots.ExcludeFolder;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.roots.ContentEntry;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.openapi.roots.OrderRootType;
import org.jetbrains.annotations.NotNull;
import java.util.Set;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.impl.libraries.LibraryEx;
import java.util.Stack;

static final class CidrRootsSynchronizer$1 implements RootTree.Visitor {
    final Stack<State> stack = new Stack<State>();
    State state;
    final /* synthetic */ LibraryEx.ModifiableModelEx val$libraryModel;
    final /* synthetic */ ModifiableRootModel val$model;
    
    @Override
    public void enter() {
        State state = null;
        Label_0047: {
            try {
                this.stack.push(this.state);
                if (this.state == null) {
                    state = new State();
                    break Label_0047;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            state = new State(this.state);
        }
        this.state = state;
    }
    
    @Override
    public boolean visit(@NotNull final Set<RootItem> p0) {
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
        //    18: ldc             "items"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/project/CidrRootsSynchronizer$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "visit"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface java/util/Set.isEmpty:()Z
        //    50: ifeq            59
        //    53: iconst_1       
        //    54: ireturn        
        //    55: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: aload_0        
        //    60: aload_1        
        //    61: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.EXPLICIT_EXCLUDE:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
        //    64: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
        //    67: astore_2       
        //    68: aload_2        
        //    69: ifnull          153
        //    72: aload_0        
        //    73: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //    76: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
        //    79: ifnull          113
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload_0        
        //    90: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //    93: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
        //    96: aload_2        
        //    97: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
        //   100: invokeinterface com/intellij/openapi/roots/ContentEntry.addExcludeFolder:(Ljava/lang/String;)Lcom/intellij/openapi/roots/ExcludeFolder;
        //   105: pop            
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   117: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
        //   120: ifeq            151
        //   123: aload_0        
        //   124: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$libraryModel:Lcom/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx;
        //   127: aload_2        
        //   128: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
        //   131: invokeinterface com/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx.addExcludedRoot:(Ljava/lang/String;)V
        //   136: aload_0        
        //   137: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   140: iconst_0       
        //   141: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: iconst_0       
        //   152: ireturn        
        //   153: aload_0        
        //   154: aload_1        
        //   155: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.CONTENT:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
        //   158: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
        //   161: astore_3       
        //   162: aload_3        
        //   163: ifnull          248
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   170: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
        //   173: ifnonnull       248
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: aload_0        
        //   184: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   187: aload_0        
        //   188: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$model:Lcom/intellij/openapi/roots/ModifiableRootModel;
        //   191: aload_3        
        //   192: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
        //   195: invokeinterface com/intellij/openapi/roots/ModifiableRootModel.addContentEntry:(Ljava/lang/String;)Lcom/intellij/openapi/roots/ContentEntry;
        //   200: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
        //   203: aload_0        
        //   204: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   207: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
        //   210: ifeq            248
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: aload_0        
        //   221: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$libraryModel:Lcom/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx;
        //   224: aload_3        
        //   225: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
        //   228: invokeinterface com/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx.addExcludedRoot:(Ljava/lang/String;)V
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   237: iconst_0       
        //   238: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aload_0        
        //   249: aload_1        
        //   250: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.EXPLICIT_SOURCE:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
        //   253: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
        //   256: astore          4
        //   258: aload           4
        //   260: ifnull          283
        //   263: aload_0        
        //   264: aload           4
        //   266: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;)V
        //   269: aload_0        
        //   270: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   273: iconst_1       
        //   274: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isExplicitRoot:Z
        //   277: iconst_1       
        //   278: ireturn        
        //   279: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   282: athrow         
        //   283: aload_0        
        //   284: aload_1        
        //   285: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.EXPLICIT_LIBRARY:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
        //   288: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
        //   291: astore          5
        //   293: aload           5
        //   295: ifnull          318
        //   298: aload_0        
        //   299: aload           5
        //   301: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.b:(Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;)V
        //   304: aload_0        
        //   305: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   308: iconst_1       
        //   309: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isExplicitRoot:Z
        //   312: iconst_1       
        //   313: ireturn        
        //   314: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   317: athrow         
        //   318: aload_0        
        //   319: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   322: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isExplicitRoot:Z
        //   325: ifeq            334
        //   328: iconst_1       
        //   329: ireturn        
        //   330: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: aload_0        
        //   335: aload_1        
        //   336: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.SOURCE:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
        //   339: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
        //   342: astore          4
        //   344: aload           4
        //   346: ifnull          361
        //   349: aload_0        
        //   350: aload           4
        //   352: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;)V
        //   355: iconst_1       
        //   356: ireturn        
        //   357: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   360: athrow         
        //   361: aload_0        
        //   362: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$info:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootsInfo;
        //   365: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$RootsInfo.registerSystemHeaderRootUnderContentRootAsLibraries:Z
        //   368: ifne            388
        //   371: aload_0        
        //   372: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   375: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
        //   378: ifnonnull       481
        //   381: goto            388
        //   384: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   387: athrow         
        //   388: aload_0        
        //   389: aload_1        
        //   390: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.LIBRARY_EXCLUDE:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
        //   393: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
        //   396: astore          6
        //   398: aload           6
        //   400: ifnull          449
        //   403: aload_0        
        //   404: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   407: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
        //   410: ifeq            449
        //   413: goto            420
        //   416: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: aload_0        
        //   421: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$libraryModel:Lcom/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx;
        //   424: aload           6
        //   426: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
        //   429: invokeinterface com/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx.addExcludedRoot:(Ljava/lang/String;)V
        //   434: aload_0        
        //   435: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
        //   438: iconst_0       
        //   439: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
        //   442: goto            481
        //   445: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   448: athrow         
        //   449: aload_3        
        //   450: ifnonnull       481
        //   453: aload_0        
        //   454: aload_1        
        //   455: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.LIBRARY:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
        //   458: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
        //   461: astore          5
        //   463: aload           5
        //   465: ifnull          481
        //   468: aload_0        
        //   469: aload           5
        //   471: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.b:(Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;)V
        //   474: goto            481
        //   477: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   480: athrow         
        //   481: iconst_1       
        //   482: ireturn        
        //    Signature:
        //  (Ljava/util/Set<Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     55     55     59     Ljava/lang/IllegalArgumentException;
        //  68     82     85     89     Ljava/lang/IllegalArgumentException;
        //  72     106    109    113    Ljava/lang/IllegalArgumentException;
        //  113    144    147    151    Ljava/lang/IllegalArgumentException;
        //  162    176    179    183    Ljava/lang/IllegalArgumentException;
        //  166    213    216    220    Ljava/lang/IllegalArgumentException;
        //  183    241    244    248    Ljava/lang/IllegalArgumentException;
        //  258    279    279    283    Ljava/lang/IllegalArgumentException;
        //  293    314    314    318    Ljava/lang/IllegalArgumentException;
        //  318    330    330    334    Ljava/lang/IllegalArgumentException;
        //  344    357    357    361    Ljava/lang/IllegalArgumentException;
        //  361    381    384    388    Ljava/lang/IllegalArgumentException;
        //  398    413    416    420    Ljava/lang/IllegalArgumentException;
        //  403    445    445    449    Ljava/lang/IllegalArgumentException;
        //  463    474    477    481    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0183:
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
    
    private void a(@NotNull final RootItem rootItem) {
        try {
            if (rootItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/project/CidrRootsSynchronizer$1", "addSourceFolder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.state.contentEntry == null) {
                this.state.contentEntry = this.val$model.addContentEntry(rootItem.getUrl());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (this.state.sourceFolder == null) {
                this.state.sourceFolder = this.state.contentEntry.addSourceFolder(rootItem.getUrl(), false);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (this.state.isLibraryRoot) {
                this.val$libraryModel.addExcludedRoot(rootItem.getUrl());
                this.state.isLibraryRoot = false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    private void b(@NotNull final RootItem rootItem) {
        try {
            if (rootItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/project/CidrRootsSynchronizer$1", "addLibraryRoot"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (!this.state.isLibraryRoot) {
            final String url = rootItem.getUrl();
            this.val$libraryModel.addRoot(url, OrderRootType.CLASSES);
            this.val$libraryModel.addRoot(url, OrderRootType.SOURCES);
            this.state.isLibraryRoot = true;
            this.state.sourceFolder = null;
        }
    }
    
    @Nullable
    private RootItem a(final Set<RootItem> set, final RootKind rootKind) {
        for (final RootItem rootItem : set) {
            try {
                if (rootItem.kind == rootKind) {
                    return rootItem;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    @Override
    public void exit() {
        this.state = this.stack.pop();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    class State
    {
        @Nullable
        ContentEntry contentEntry;
        @Nullable
        SourceFolder sourceFolder;
        @Nullable
        ExcludeFolder excludeFolder;
        boolean isLibraryRoot;
        boolean isExplicitRoot;
        
        public State() {
        }
        
        public State(final State state) {
            this.contentEntry = state.contentEntry;
            this.sourceFolder = state.sourceFolder;
            this.excludeFolder = state.excludeFolder;
            this.isLibraryRoot = state.isLibraryRoot;
            this.isExplicitRoot = state.isExplicitRoot;
        }
    }
}