// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import com.intellij.codeInsight.completion.CodeCompletionHandlerBase;
import com.intellij.codeInsight.completion.CompletionType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.openapi.util.TextRange;
import com.intellij.codeInsight.completion.InsertionContext;
import java.util.regex.Matcher;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.codeInsight.completion.PrefixMatcher;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.Contract;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.Processor;
import java.util.Set;
import com.intellij.util.CommonProcessors;
import java.util.HashSet;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.jetbrains.cidr.lang.ui.OCFieldAdapter;
import com.jetbrains.cidr.lang.ui.OCFieldAdapterForSymbolName;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.ui.OCTextFieldCompletionProvider;
import com.intellij.ui.TextFieldWithAutoCompletionListProvider;
import com.jetbrains.cidr.lang.ui.OCTextFieldWithSymbolAutoCompletion;
import com.intellij.ui.TextFieldWithAutoCompletion;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import java.util.regex.Pattern;
import com.jetbrains.cidr.execution.debugger.breakpoints.CidrSymbolicBreakpointPropertiesPanel;

public class OCSymbolicBreakpointPropertiesPanel extends CidrSymbolicBreakpointPropertiesPanel
{
    private static final Pattern OBJC_METHOD_PATTERN;
    
    public OCSymbolicBreakpointPropertiesPanel(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel", "<init>"));
        }
        super(project);
    }
    
    @NotNull
    @Override
    protected TextFieldWithAutoCompletion createSymbolNameField() {
        OCTextFieldWithSymbolAutoCompletion ocTextFieldWithSymbolAutoCompletion;
        try {
            ocTextFieldWithSymbolAutoCompletion = new OCTextFieldWithSymbolAutoCompletion(this.myProject, new ClassSymbolsProvider(this.myProject));
            if (ocTextFieldWithSymbolAutoCompletion == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel", "createSymbolNameField"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocTextFieldWithSymbolAutoCompletion;
    }
    
    static {
        OBJC_METHOD_PATTERN = Pattern.compile("[-+]?(\\[(\\w*)( ([\\w:]*))?]?)");
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class ClassSymbolsProvider extends OCTextFieldCompletionProvider<OCSymbol>
    {
        @NotNull
        private final Project myProject;
        
        private ClassSymbolsProvider(@NotNull final Project myProject) {
            if (myProject == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "<init>"));
            }
            super(new OCFieldAdapterForSymbolName() {
                @NotNull
                @Override
                public String getReadableName(@NotNull final OCSymbol p0) {
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
                    //    18: ldc             "symbol"
                    //    20: aastore        
                    //    21: dup            
                    //    22: ldc             1
                    //    24: ldc             "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1"
                    //    26: aastore        
                    //    27: dup            
                    //    28: ldc             2
                    //    30: ldc             "getReadableName"
                    //    32: aastore        
                    //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                    //    39: athrow         
                    //    40: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    43: athrow         
                    //    44: aload_1        
                    //    45: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                    //    48: ifeq            123
                    //    51: aload_1        
                    //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                    //    55: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
                    //    58: ifnull          123
                    //    61: goto            68
                    //    64: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    67: athrow         
                    //    68: aload_1        
                    //    69: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                    //    72: iconst_1       
                    //    73: iconst_0       
                    //    74: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getSignatureWithoutParamNames:(ZZ)Ljava/lang/String;
                    //    77: dup            
                    //    78: ifnonnull       122
                    //    81: goto            88
                    //    84: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    87: athrow         
                    //    88: new             Ljava/lang/IllegalStateException;
                    //    91: dup            
                    //    92: ldc             "@NotNull method %s.%s must not return null"
                    //    94: ldc             2
                    //    96: anewarray       Ljava/lang/Object;
                    //    99: dup            
                    //   100: ldc             0
                    //   102: ldc             "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1"
                    //   104: aastore        
                    //   105: dup            
                    //   106: ldc             1
                    //   108: ldc             "getReadableName"
                    //   110: aastore        
                    //   111: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //   114: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                    //   117: athrow         
                    //   118: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   121: athrow         
                    //   122: areturn        
                    //   123: aload_1        
                    //   124: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getPresentableName:()Ljava/lang/String;
                    //   129: dup            
                    //   130: ifnonnull       167
                    //   133: new             Ljava/lang/IllegalStateException;
                    //   136: dup            
                    //   137: ldc             "@NotNull method %s.%s must not return null"
                    //   139: ldc             2
                    //   141: anewarray       Ljava/lang/Object;
                    //   144: dup            
                    //   145: ldc             0
                    //   147: ldc             "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1"
                    //   149: aastore        
                    //   150: dup            
                    //   151: ldc             1
                    //   153: ldc             "getReadableName"
                    //   155: aastore        
                    //   156: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //   159: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                    //   162: athrow         
                    //   163: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   166: athrow         
                    //   167: areturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                
                    //  -----  -----  -----  -----  ------------------------------------
                    //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                    //  44     61     64     68     Ljava/lang/IllegalArgumentException;
                    //  51     81     84     88     Ljava/lang/IllegalArgumentException;
                    //  68     118    118    122    Ljava/lang/IllegalArgumentException;
                    //  123    163    163    167    Ljava/lang/IllegalArgumentException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
                
                private static IllegalArgumentException b(final IllegalArgumentException ex) {
                    return ex;
                }
            });
            this.myProject = myProject;
        }
        
        @NotNull
        @Override
        public Collection<OCSymbol> getItems(final String p0, final boolean p1, final CompletionParameters p2) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_3        
            //     1: invokevirtual   com/intellij/codeInsight/completion/CompletionParameters.getOriginalFile:()Lcom/intellij/psi/PsiFile;
            //     4: invokeinterface com/intellij/psi/PsiFile.getText:()Ljava/lang/String;
            //     9: astore          4
            //    11: iload_2        
            //    12: ifne            41
            //    15: aload_1        
            //    16: ifnull          41
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    25: athrow         
            //    26: aload           4
            //    28: invokevirtual   java/lang/String.isEmpty:()Z
            //    31: ifeq            90
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    40: athrow         
            //    41: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
            //    44: dup            
            //    45: ifnonnull       89
            //    48: goto            55
            //    51: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    54: athrow         
            //    55: new             Ljava/lang/IllegalStateException;
            //    58: dup            
            //    59: ldc             "@NotNull method %s.%s must not return null"
            //    61: ldc             2
            //    63: anewarray       Ljava/lang/Object;
            //    66: dup            
            //    67: ldc             0
            //    69: ldc             "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider"
            //    71: aastore        
            //    72: dup            
            //    73: ldc             1
            //    75: ldc             "getItems"
            //    77: aastore        
            //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    81: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //    84: athrow         
            //    85: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    88: athrow         
            //    89: areturn        
            //    90: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel.access$100:()Ljava/util/regex/Pattern;
            //    93: aload           4
            //    95: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
            //    98: astore          6
            //   100: aload           6
            //   102: invokevirtual   java/util/regex/Matcher.matches:()Z
            //   105: ifeq            148
            //   108: aload           6
            //   110: iconst_4       
            //   111: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
            //   114: ifnull          139
            //   117: goto            124
            //   120: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   123: athrow         
            //   124: aload_0        
            //   125: aload           6
            //   127: iconst_2       
            //   128: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
            //   131: invokespecial   com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.a:(Ljava/lang/String;)Ljava/util/Collection;
            //   134: astore          5
            //   136: goto            208
            //   139: aload_0        
            //   140: invokespecial   com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.a:()Ljava/util/Collection;
            //   143: astore          5
            //   145: goto            208
            //   148: aload_0        
            //   149: aload_1        
            //   150: invokevirtual   com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.createPrefixMatcher:(Ljava/lang/String;)Lcom/intellij/codeInsight/completion/PrefixMatcher;
            //   153: astore          7
            //   155: aload_0        
            //   156: aload           4
            //   158: aload           7
            //   160: invokespecial   com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.a:(Ljava/lang/String;Lcom/intellij/codeInsight/completion/PrefixMatcher;)Ljava/util/Collection;
            //   163: astore          5
            //   165: aload           4
            //   167: ldc             "::"
            //   169: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
            //   172: ifne            208
            //   175: aload           5
            //   177: aload_0        
            //   178: aload           7
            //   180: invokespecial   com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.a:(Lcom/intellij/codeInsight/completion/PrefixMatcher;)Ljava/util/Collection;
            //   183: invokeinterface java/util/Collection.addAll:(Ljava/util/Collection;)Z
            //   188: pop            
            //   189: aload           5
            //   191: aload_0        
            //   192: invokespecial   com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.a:()Ljava/util/Collection;
            //   195: invokeinterface java/util/Collection.addAll:(Ljava/util/Collection;)Z
            //   200: pop            
            //   201: goto            208
            //   204: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   207: athrow         
            //   208: new             Ljava/util/ArrayList;
            //   211: dup            
            //   212: aload           5
            //   214: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
            //   217: astore          7
            //   219: aload           7
            //   221: aload_0        
            //   222: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
            //   225: aload           7
            //   227: dup            
            //   228: ifnonnull       265
            //   231: new             Ljava/lang/IllegalStateException;
            //   234: dup            
            //   235: ldc             "@NotNull method %s.%s must not return null"
            //   237: ldc             2
            //   239: anewarray       Ljava/lang/Object;
            //   242: dup            
            //   243: ldc             0
            //   245: ldc             "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider"
            //   247: aastore        
            //   248: dup            
            //   249: ldc             1
            //   251: ldc             "getItems"
            //   253: aastore        
            //   254: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   257: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   260: athrow         
            //   261: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   264: athrow         
            //   265: areturn        
            //    Signature:
            //  (Ljava/lang/String;ZLcom/intellij/codeInsight/completion/CompletionParameters;)Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  11     19     22     26     Ljava/lang/IllegalArgumentException;
            //  15     34     37     41     Ljava/lang/IllegalArgumentException;
            //  26     48     51     55     Ljava/lang/IllegalArgumentException;
            //  41     85     85     89     Ljava/lang/IllegalArgumentException;
            //  100    117    120    124    Ljava/lang/IllegalArgumentException;
            //  165    201    204    208    Ljava/lang/IllegalArgumentException;
            //  219    261    261    265    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        private Collection<OCSymbol> a(@Nullable final String s) {
            final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
                final /* synthetic */ Set val$set = new HashSet();
                
                public boolean accept(final OCSymbol ocSymbol) {
                    return this.val$set.add(ocSymbol.getPresentableName());
                }
            };
            final Processor<OCSymbol> processor = (Processor<OCSymbol>)new Processor<OCSymbol>() {
                @Contract("null -> true")
                public boolean process(final OCSymbol ocSymbol) {
                    if (ocSymbol instanceof OCClassSymbol) {
                        ((OCClassSymbol)ocSymbol).processMembersInAllCategories((String)null, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)collectProcessor);
                        for (final String s : ((OCClassSymbol)ocSymbol).getProtocolNames()) {
                            if (!s.equals(ocSymbol.getName())) {
                                OCGlobalProjectSymbolsCache.processTopLevelSymbols(ClassSymbolsProvider.this.myProject, (Processor<OCSymbol>)this, s);
                            }
                        }
                    }
                    return true;
                }
            };
            Collection results;
            try {
                OCGlobalProjectSymbolsCache.processTopLevelSymbols(this.myProject, (Processor<OCSymbol>)processor, s);
                results = collectProcessor.getResults();
                if (results == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "collectMethodsForClass"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            return (Collection<OCSymbol>)results;
        }
        
        @NotNull
        private Collection<OCSymbol> a() {
            Collection<OCSymbol> collectValuesFromProject;
            try {
                collectValuesFromProject = this.collectValuesFromProject(this.myProject, (com.intellij.openapi.util.Condition<OCSymbol>)(p0 -> {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_0        
                    //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
                    //     4: ifne            21
                    //     7: aload_0        
                    //     8: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
                    //    11: ifeq            48
                    //    14: goto            21
                    //    17: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    20: athrow         
                    //    21: aload_0        
                    //    22: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                    //    25: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
                    //    30: ifnonnull       48
                    //    33: goto            40
                    //    36: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    39: athrow         
                    //    40: iconst_1       
                    //    41: goto            49
                    //    44: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    47: athrow         
                    //    48: iconst_0       
                    //    49: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                
                    //  -----  -----  -----  -----  ------------------------------------
                    //  0      14     17     21     Ljava/lang/IllegalArgumentException;
                    //  7      33     36     40     Ljava/lang/IllegalArgumentException;
                    //  21     44     44     48     Ljava/lang/IllegalArgumentException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
                }));
                if (collectValuesFromProject == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "collectAllClasses"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            return (Collection<OCSymbol>)collectValuesFromProject;
        }
        
        @NotNull
        private Collection<OCSymbol> a(@NotNull final String s, @NotNull final PrefixMatcher prefixMatcher) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "collectFunctions"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                if (prefixMatcher == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefixMatcher", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "collectFunctions"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            final HashSet set = new HashSet();
            final String[] split = s.split("::", -1);
            String s2 = null;
            Label_0128: {
                try {
                    if (split.length >= 2) {
                        s2 = split[split.length - 2];
                        break Label_0128;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
                s2 = null;
            }
            final String s3 = s2;
            final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
                protected boolean accept(final OCSymbol ocSymbol) {
                    if ((ocSymbol instanceof OCFunctionSymbol || a(ocSymbol)) && prefixMatcher.prefixMatches(((OCSymbolWithQualifiedName)ocSymbol).getNameWithParent())) {
                        final OCQualifiedName resolvedQualifiedName = ((OCSymbolWithQualifiedName)ocSymbol).getResolvedQualifiedName();
                        if (resolvedQualifiedName != null) {
                            final String canonicalName = resolvedQualifiedName.getCanonicalName(true);
                            return ClassSymbolsProvider.this.createPrefixMatcher(s).prefixMatches(canonicalName) && set.add(canonicalName);
                        }
                    }
                    return false;
                }
            };
            Collection results;
            try {
                new ReadAction<OCQualifiedName>() {
                    protected void run(@NotNull final Result<OCQualifiedName> result) throws Throwable {
                        try {
                            if (result == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$5", "run"));
                            }
                        }
                        catch (Throwable t) {
                            throw a(t);
                        }
                        boolean b = false;
                        Label_0099: {
                            Label_0075: {
                                try {
                                    if (s3 == null) {
                                        break Label_0099;
                                    }
                                    if (!s3.isEmpty()) {
                                        break Label_0075;
                                    }
                                }
                                catch (Throwable t2) {
                                    throw a(t2);
                                }
                                b = true;
                                break Label_0099;
                            }
                            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(ClassSymbolsProvider.this.myProject, (Processor<OCSymbol>)(ocSymbol -> {
                                try {
                                    if (ocSymbol instanceof OCNamespaceSymbol) {
                                        ((OCNamespaceSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)collectProcessor);
                                    }
                                }
                                catch (IllegalArgumentException ex) {
                                    throw a(ex);
                                }
                                return true;
                            }), s3);
                            try {
                                if (collectProcessor.getResults().isEmpty()) {
                                    OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(ClassSymbolsProvider.this.myProject, (Processor<OCSymbol>)collectProcessor, null, b);
                                }
                            }
                            catch (Throwable t3) {
                                throw a(t3);
                            }
                        }
                    }
                    
                    private static Throwable a(final Throwable t) {
                        return t;
                    }
                }.execute();
                results = collectProcessor.getResults();
                if (results == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "collectFunctions"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
            return (Collection<OCSymbol>)results;
        }
        
        private static boolean a(final OCSymbol ocSymbol) {
            boolean b = false;
            Label_0035: {
                Label_0026: {
                    try {
                        if (!(ocSymbol instanceof OCNamespaceSymbol)) {
                            break Label_0026;
                        }
                        final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                        final OCSymbolKind ocSymbolKind = ocNamespaceSymbol.getKind();
                        final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                        if (ocSymbolKind != ocSymbolKind2) {
                            break Label_0026;
                        }
                        break Label_0026;
                    }
                    catch (IllegalArgumentException ex) {
                        throw c(ex);
                    }
                    try {
                        final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                        final OCSymbolKind ocSymbolKind = ocNamespaceSymbol.getKind();
                        final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                        if (ocSymbolKind != ocSymbolKind2) {
                            b = true;
                            break Label_0035;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                }
                b = false;
            }
            boolean found = b;
            if (found) {
                final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
                    protected boolean accept(final OCSymbol ocSymbol) {
                        return ocSymbol instanceof OCFunctionSymbol || ocSymbol instanceof OCNamespaceSymbol;
                    }
                };
                ((OCNamespaceSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)findFirstProcessor);
                found = findFirstProcessor.isFound();
            }
            return found;
        }
        
        private Collection<OCSymbol> a(final PrefixMatcher prefixMatcher) {
            final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
                final /* synthetic */ Set val$set = new HashSet();
                
                protected boolean accept(final OCSymbol ocSymbol) {
                    return ocSymbol instanceof OCMethodSymbol && prefixMatcher.prefixMatches(ocSymbol.getPresentableName()) && this.val$set.add(ocSymbol.getPresentableName());
                }
            };
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(this.myProject, (Processor<OCSymbol>)collectProcessor, null);
            return (Collection<OCSymbol>)collectProcessor.getResults();
        }
        
        @Nullable
        @Override
        protected InsertHandler<LookupElement> createInsertHandler(@NotNull final OCSymbol ocSymbol) {
            try {
                if (ocSymbol == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "createInsertHandler"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                if (ocSymbol instanceof OCMethodSymbol) {
                    return (InsertHandler<LookupElement>)new LookupElementInsertHandler(']', insertionContext -> insertionContext.getDocument().getText(TextRange.create(0, insertionContext.getTailOffset())).contains("["));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            Label_0105: {
                Label_0092: {
                    try {
                        if (ocSymbol instanceof OCNamespaceSymbol) {
                            break Label_0092;
                        }
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = ocSymbol2 instanceof OCFunctionSymbol;
                        if (b) {
                            break Label_0092;
                        }
                        break Label_0105;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw c(ex3);
                    }
                    try {
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = ocSymbol2 instanceof OCFunctionSymbol;
                        if (b) {
                            return (InsertHandler<LookupElement>)new CppLookupElementInsertHandler();
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw c(ex4);
                    }
                }
                try {
                    if (ocSymbol instanceof OCClassSymbol) {
                        return (InsertHandler<LookupElement>)new OCClassLookupElementInsertHandler();
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw c(ex5);
                }
            }
            return (InsertHandler<LookupElement>)new LookupElementInsertHandler(' ');
        }
        
        @Nullable
        @Override
        public String getPrefix(@NotNull String substring, final int n) {
            try {
                if (substring == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "getPrefix"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            final Matcher matcher = OCSymbolicBreakpointPropertiesPanel.OBJC_METHOD_PATTERN.matcher(substring);
            if (matcher.matches()) {
                final String a = a(substring, matcher, n, 4);
                try {
                    if (a != null) {
                        return a;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                return a(substring, matcher, n, 2);
            }
            substring = substring.substring(0, n);
            final int lastIndex = substring.lastIndexOf("::");
            try {
                if (lastIndex != -1) {
                    return substring.substring(lastIndex + 2);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            return substring;
        }
        
        private static String a(@NotNull final String s, @NotNull final Matcher matcher, final int n, final int n2) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "getCurrentToken"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                if (matcher == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "m", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider", "getCurrentToken"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            Label_0113: {
                try {
                    if (matcher.start(n2) > n) {
                        return null;
                    }
                    final int n3 = n;
                    final Matcher matcher2 = matcher;
                    final int n4 = n2;
                    final int n5 = matcher2.end(n4);
                    if (n3 <= n5) {
                        break Label_0113;
                    }
                    return null;
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
                try {
                    final int n3 = n;
                    final Matcher matcher2 = matcher;
                    final int n4 = n2;
                    final int n5 = matcher2.end(n4);
                    if (n3 <= n5) {
                        return s.substring(matcher.start(n2), n);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw c(ex4);
                }
            }
            return null;
        }
        
        private static IllegalArgumentException c(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class CppLookupElementInsertHandler extends LookupElementInsertHandler
    {
        @Override
        public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
            if (lookupElement.getObject() instanceof OCSymbolWithQualifiedName) {
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)lookupElement.getObject();
                final OCQualifiedName resolvedQualifiedName = ocSymbolWithQualifiedName.getResolvedQualifiedName(new OCResolveContext((PsiElement)ocSymbolWithQualifiedName.getContainingOCFile()));
                if (resolvedQualifiedName != null) {
                    String s = resolvedQualifiedName.getCanonicalName(true);
                    if (ocSymbolWithQualifiedName instanceof OCFunctionSymbol) {
                        s = s + "(" + StringUtil.join((Collection)ContainerUtil.map((Collection)((OCFunctionSymbol)ocSymbolWithQualifiedName).getParameterSymbols(), ocDeclaratorSymbol -> StringUtil.trimStart(ocDeclaratorSymbol.getResolvedType().getCanonicalName(), "::").replace(" *", "*")), ", ") + ")";
                        if (ocSymbolWithQualifiedName.isConst()) {
                            s += " const";
                        }
                    }
                    insertionContext.getDocument().replaceString(0, insertionContext.getTailOffset(), (CharSequence)s.substring(2));
                }
                if (!(lookupElement.getObject() instanceof OCFunctionSymbol)) {
                    LookupElementInsertHandler.addCompletionString(insertionContext, insertionContext.getDocument().getTextLength(), "::");
                    LookupElementInsertHandler.reInvokeCompletion(insertionContext);
                }
            }
        }
    }
    
    private static class LookupElementInsertHandler implements InsertHandler<LookupElement>
    {
        private final char myChar;
        private final Condition<InsertionContext> myCondition;
        
        private LookupElementInsertHandler() {
            this(' ');
        }
        
        private LookupElementInsertHandler(final char c) {
            this(c, (Condition<InsertionContext>)null);
        }
        
        private LookupElementInsertHandler(final char myChar, @Nullable final Condition<InsertionContext> myCondition) {
            this.myChar = myChar;
            this.myCondition = myCondition;
        }
        
        public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
            Label_0025: {
                try {
                    if (insertionContext.getCompletionChar() == this.myChar) {
                        return;
                    }
                    final LookupElementInsertHandler lookupElementInsertHandler = this;
                    final Condition<InsertionContext> condition = lookupElementInsertHandler.myCondition;
                    if (condition != null) {
                        break Label_0025;
                    }
                    break Label_0025;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final LookupElementInsertHandler lookupElementInsertHandler = this;
                    final Condition<InsertionContext> condition = lookupElementInsertHandler.myCondition;
                    if (condition != null) {
                        if (!this.myCondition.value((Object)insertionContext)) {
                            return;
                        }
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            final int tailOffset = insertionContext.getTailOffset();
            final CharSequence charsSequence = insertionContext.getDocument().getCharsSequence();
            try {
                if (tailOffset == charsSequence.length()) {
                    addCompletionString(insertionContext, tailOffset, String.valueOf(this.myChar));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        
        protected static void addCompletionString(@NotNull final InsertionContext insertionContext, final int n, @NotNull final String s) {
            try {
                if (insertionContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$LookupElementInsertHandler", "addCompletionString"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "string", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$LookupElementInsertHandler", "addCompletionString"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            insertionContext.getDocument().insertString(n, (CharSequence)s);
            insertionContext.getEditor().getCaretModel().moveCaretRelatively(s.length(), 0, false, false, true);
        }
        
        protected static void reInvokeCompletion(@NotNull final InsertionContext insertionContext) {
            try {
                if (insertionContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$LookupElementInsertHandler", "reInvokeCompletion"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final IllegalArgumentException ex2;
            insertionContext.setLaterRunnable(() -> {
                try {
                    if (insertionContext == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$LookupElementInsertHandler", "lambda$reInvokeCompletion$0"));
                        throw ex2;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                new CodeCompletionHandlerBase(CompletionType.BASIC).invokeCompletion(insertionContext.getProject(), insertionContext.getEditor());
            });
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class OCClassLookupElementInsertHandler extends LookupElementInsertHandler
    {
        private OCClassLookupElementInsertHandler() {
            super(' ');
        }
        
        @Override
        public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
            final CharSequence charsSequence = insertionContext.getDocument().getCharsSequence();
            if (insertionContext.getStartOffset() == 0 || (charsSequence.length() > 0 && charsSequence.charAt(insertionContext.getStartOffset() - 1) != '[')) {
                insertionContext.getDocument().insertString(insertionContext.getStartOffset(), (CharSequence)String.valueOf('['));
            }
            super.handleInsert(insertionContext, lookupElement);
            LookupElementInsertHandler.reInvokeCompletion(insertionContext);
        }
    }
}
