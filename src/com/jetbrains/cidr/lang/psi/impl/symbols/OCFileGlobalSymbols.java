// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;
import com.intellij.openapi.util.Comparing;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCForeignSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.HashSet;
import java.util.HashMap;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Set;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Map;

public class OCFileGlobalSymbols extends OCFileSymbolsContainerBase
{
    private Map<OCSymbol, VirtualFile> mySymbolToImport;
    private Map<String, Pair<OCSymbol, VirtualFile>> myUndefinedProtocols;
    private Map<String, Pair<OCSymbol, VirtualFile>> myUndefinedClasses;
    private Set<VirtualFile> myRequiredImports;
    
    public OCFileGlobalSymbols(final OCFile ocFile) {
        super(ocFile);
        this.mySymbolToImport = new HashMap<OCSymbol, VirtualFile>();
        this.myUndefinedProtocols = new HashMap<String, Pair<OCSymbol, VirtualFile>>();
        this.myUndefinedClasses = new HashMap<String, Pair<OCSymbol, VirtualFile>>();
        this.myRequiredImports = new HashSet<VirtualFile>();
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCClassSymbol) {
            final VirtualFile virtualFile = this.mySymbolToImport.get(ocSymbol);
            final OCClassSymbol ocClassSymbol = (OCClassSymbol)ocSymbol;
            try {
                if (!(ocClassSymbol instanceof OCForeignSymbol)) {
                    this.a(ocClassSymbol, virtualFile);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myNameToSymbol.add((Object)ocSymbol.getName(), (Object)ocSymbol);
        }
        else {
            try {
                if (ocSymbol.getKind() == OCSymbolKind.NAMESPACE_USING_SYMBOL) {
                    this.myUsingSymbols.add((OCUsingSymbol)ocSymbol);
                    this.myAllUsingSymbolsToIndex.put((OCUsingSymbol)ocSymbol, this.myCurrentUsingIndex++);
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            Label_0165: {
                try {
                    this.myNameToSymbol.add((Object)ocSymbol.getName(), (Object)ocSymbol);
                    if (ocSymbol.getKind() != OCSymbolKind.NAMESPACE) {
                        return true;
                    }
                    final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                    final OCNamespaceSymbol ocNamespaceSymbol2 = ocNamespaceSymbol;
                    final boolean b = ocNamespaceSymbol2.isInlineNamespace();
                    if (b) {
                        break Label_0165;
                    }
                    break Label_0165;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                    final OCNamespaceSymbol ocNamespaceSymbol2 = ocNamespaceSymbol;
                    final boolean b = ocNamespaceSymbol2.isInlineNamespace();
                    if (b) {
                        this.myInlineNamespaces.add((OCNamespaceSymbol)ocSymbol);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            this.fillUsingsIndex((OCNamespaceSymbol)ocSymbol);
        }
        return true;
    }
    
    private void a(@NotNull final OCClassSymbol p0, @Nullable final VirtualFile p1) {
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
        //    18: ldc             "classSymbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkSuperAndProtocolsDefined"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getProtocolNames:()Ljava/util/List;
        //    50: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    55: astore_3       
        //    56: aload_3        
        //    57: invokeinterface java/util/Iterator.hasNext:()Z
        //    62: ifeq            137
        //    65: aload_3        
        //    66: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    71: checkcast       Ljava/lang/String;
        //    74: astore          4
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.myUndefinedProtocols:Ljava/util/Map;
        //    80: aload           4
        //    82: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //    87: ifne            134
        //    90: aload_0        
        //    91: aload           4
        //    93: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    96: aload_2        
        //    97: invokespecial   com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //   100: ifne            134
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_0        
        //   111: getfield        com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.myUndefinedProtocols:Ljava/util/Map;
        //   114: aload           4
        //   116: aload_1        
        //   117: aload_2        
        //   118: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   121: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   126: pop            
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: goto            56
        //   137: aload_1        
        //   138: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   141: ifeq            220
        //   144: aload_1        
        //   145: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getSuperClassName:()Ljava/lang/String;
        //   150: astore_3       
        //   151: aload_3        
        //   152: invokevirtual   java/lang/String.isEmpty:()Z
        //   155: ifne            220
        //   158: aload_0        
        //   159: getfield        com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.myUndefinedClasses:Ljava/util/Map;
        //   162: aload_3        
        //   163: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   168: ifne            220
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload_0        
        //   179: aload_3        
        //   180: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INTERFACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   183: aload_2        
        //   184: invokespecial   com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //   187: ifne            220
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload_0        
        //   198: getfield        com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.myUndefinedClasses:Ljava/util/Map;
        //   201: aload_3        
        //   202: aload_1        
        //   203: aload_2        
        //   204: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   207: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   212: pop            
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  76     103    106    110    Ljava/lang/IllegalArgumentException;
        //  90     127    130    134    Ljava/lang/IllegalArgumentException;
        //  151    171    174    178    Ljava/lang/IllegalArgumentException;
        //  158    190    193    197    Ljava/lang/IllegalArgumentException;
        //  178    213    216    220    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0178:
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
    
    private boolean a(final String s, final OCSymbolKind ocSymbolKind, final VirtualFile virtualFile) {
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return ocSymbol.getKind() == ocSymbolKind && !ocSymbol.isPredeclaration();
            }
        };
        this.myNameToSymbol.processForKey((Object)s, (Processor)findFirstProcessor);
        final OCSymbol ocSymbol = (OCSymbol)findFirstProcessor.getFoundValue();
        if (ocSymbol != null) {
            final VirtualFile virtualFile2 = this.mySymbolToImport.get(ocSymbol);
            try {
                if (!Comparing.equal((Object)virtualFile, (Object)virtualFile2)) {
                    this.myRequiredImports.add(virtualFile2);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return findFirstProcessor.isFound();
    }
    
    public Map<OCSymbol, VirtualFile> getSymbolToImport() {
        return this.mySymbolToImport;
    }
    
    public Map<String, Pair<OCSymbol, VirtualFile>> getUndefinedProtocols() {
        return this.myUndefinedProtocols;
    }
    
    public Map<String, Pair<OCSymbol, VirtualFile>> getUndefinedClasses() {
        return this.myUndefinedClasses;
    }
    
    public Set<VirtualFile> getRequiredImports() {
        return this.myRequiredImports;
    }
    
    public static OCFileGlobalSymbols buildSymbols(final OCFileImpl ocFileImpl) {
        final OCFileGlobalSymbols ocFileGlobalSymbols = new OCFileGlobalSymbols(ocFileImpl);
        OCFileGlobalSymbolsCache.processFile(ocFileImpl, (Processor<OCSymbol>)ocFileGlobalSymbols, ocFileGlobalSymbols.mySymbolToImport);
        return ocFileGlobalSymbols;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
