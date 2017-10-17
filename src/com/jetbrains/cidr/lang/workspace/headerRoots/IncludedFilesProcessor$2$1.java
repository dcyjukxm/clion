// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import java.util.NoSuchElementException;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;

class IncludedFilesProcessor$2$1 implements Iterator<VirtualFile> {
    Iterator<VirtualFile> iterator = null;
    List<VirtualFile> dirs = null;
    boolean processingDirs = false;
    VirtualFile prefetched = null;
    
    @Override
    public boolean hasNext() {
        try {
            this.a();
            if (this.prefetched != null) {
                return true;
            }
        }
        catch (NoSuchElementException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @Override
    public VirtualFile next() {
        try {
            if (this.prefetched == null) {
                this.a();
            }
        }
        catch (NoSuchElementException ex) {
            throw b(ex);
        }
        try {
            if (this.prefetched == null) {
                throw new NoSuchElementException("No more items");
            }
        }
        catch (NoSuchElementException ex2) {
            throw b(ex2);
        }
        final VirtualFile prefetched = this.prefetched;
        this.prefetched = null;
        return prefetched;
    }
    
    private void a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.prefetched:Lcom/intellij/openapi/vfs/VirtualFile;
        //     4: ifnull          12
        //     7: return         
        //     8: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //    11: athrow         
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
        //    16: ifnonnull       42
        //    19: aload_0        
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.this$0:Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2;
        //    24: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2.val$files:Ljava/lang/Iterable;
        //    27: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //    32: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //    41: athrow         
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
        //    46: invokeinterface java/util/Iterator.hasNext:()Z
        //    51: ifeq            165
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
        //    58: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    63: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //    66: astore_1       
        //    67: aload_1        
        //    68: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
        //    71: istore_2       
        //    72: iload_2        
        //    73: aload_0        
        //    74: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.processingDirs:Z
        //    77: if_icmpne       90
        //    80: aload_0        
        //    81: aload_1        
        //    82: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.prefetched:Lcom/intellij/openapi/vfs/VirtualFile;
        //    85: return         
        //    86: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //    89: athrow         
        //    90: aload_0        
        //    91: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.processingDirs:Z
        //    94: ifne            162
        //    97: iload_2        
        //    98: ifeq            140
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //   107: athrow         
        //   108: aload_0        
        //   109: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
        //   112: ifnonnull       140
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //   121: athrow         
        //   122: aload_0        
        //   123: new             Ljava/util/ArrayList;
        //   126: dup            
        //   127: invokespecial   java/util/ArrayList.<init>:()V
        //   130: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //   139: athrow         
        //   140: iload_2        
        //   141: ifeq            162
        //   144: aload_0        
        //   145: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
        //   148: aload_1        
        //   149: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   154: pop            
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //   161: athrow         
        //   162: goto            42
        //   165: aload_0        
        //   166: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.prefetched:Lcom/intellij/openapi/vfs/VirtualFile;
        //   169: ifnonnull       229
        //   172: aload_0        
        //   173: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.processingDirs:Z
        //   176: ifne            229
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //   185: athrow         
        //   186: aload_0        
        //   187: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
        //   190: ifnull          229
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //   199: athrow         
        //   200: aload_0        
        //   201: iconst_1       
        //   202: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.processingDirs:Z
        //   205: aload_0        
        //   206: aload_0        
        //   207: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
        //   210: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   215: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
        //   218: aload_0        
        //   219: invokespecial   com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.a:()V
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
        //   228: athrow         
        //   229: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  0      8      8      12     Ljava/util/NoSuchElementException;
        //  12     35     38     42     Ljava/util/NoSuchElementException;
        //  72     86     86     90     Ljava/util/NoSuchElementException;
        //  90     101    104    108    Ljava/util/NoSuchElementException;
        //  97     115    118    122    Ljava/util/NoSuchElementException;
        //  108    133    136    140    Ljava/util/NoSuchElementException;
        //  140    155    158    162    Ljava/util/NoSuchElementException;
        //  165    179    182    186    Ljava/util/NoSuchElementException;
        //  172    193    196    200    Ljava/util/NoSuchElementException;
        //  186    222    225    229    Ljava/util/NoSuchElementException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
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
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    private static NoSuchElementException b(final NoSuchElementException ex) {
        return ex;
    }
}