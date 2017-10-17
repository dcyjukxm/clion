// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Collections;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

public class OCGenerateDestructorHandler extends OCCCppGenerateHandlerBase<OCStructSymbol, OCDeclaratorSymbol, OCCppActionContext<OCStructSymbol, OCDeclaratorSymbol>>
{
    @Override
    protected String getActionTitle() {
        return "Generate Destructor";
    }
    
    @Override
    protected String getMembersChooserTitle() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected boolean enableChooseDialog(final Collection<OCDeclaratorSymbol> collection) {
        return false;
    }
    
    @Override
    protected boolean allowEmptySelection(final OCCppActionContext<OCStructSymbol, OCDeclaratorSymbol> ocCppActionContext) {
        return true;
    }
    
    @Nullable
    @Override
    protected OCCppActionContext<OCStructSymbol, OCDeclaratorSymbol> evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        return new OCCppActionContext<OCStructSymbol, OCDeclaratorSymbol>(ocStructSymbol, psiElement) {
            @NotNull
            @Override
            public Collection<OCDeclaratorSymbol> getMemberCandidates() {
                List<OCDeclaratorSymbol> emptyList;
                try {
                    emptyList = Collections.emptyList();
                    if (emptyList == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler$1", "getMemberCandidates"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return emptyList;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
    }
    
    @Override
    protected boolean allowUnions() {
        return true;
    }
    
    @NotNull
    @Override
    protected List<OCGenerateUtil.Replacement> getReplacements(@NotNull final OCCaretLocation p0, @NotNull final OCCppActionContext<OCStructSymbol, OCDeclaratorSymbol> p1, @NotNull final List<OCDeclaratorSymbol> p2) {
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
        //    18: ldc             "location"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getReplacements"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "actionContext"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getReplacements"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "fields"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getReplacements"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   131: athrow         
        //   132: aload_2        
        //   133: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   136: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   139: astore          4
        //   141: aload           4
        //   143: new             Ljava/lang/StringBuilder;
        //   146: dup            
        //   147: invokespecial   java/lang/StringBuilder.<init>:()V
        //   150: ldc             "~"
        //   152: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   155: aload           4
        //   157: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getName:()Ljava/lang/String;
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   166: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
        //   169: dup            
        //   170: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
        //   173: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   176: ifne            270
        //   179: ldc             "Destructor is already defined.\nDo you wish to continue?"
        //   181: astore          5
        //   183: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   186: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   191: ifeq            202
        //   194: iconst_1       
        //   195: goto            214
        //   198: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   201: athrow         
        //   202: aload           5
        //   204: aload_0        
        //   205: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.getActionTitle:()Ljava/lang/String;
        //   208: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   211: invokestatic    com/intellij/openapi/ui/Messages.showYesNoDialog:(Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //   214: istore          6
        //   216: iload           6
        //   218: ifeq            270
        //   221: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   224: dup            
        //   225: ifnonnull       269
        //   228: goto            235
        //   231: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   234: athrow         
        //   235: new             Ljava/lang/IllegalStateException;
        //   238: dup            
        //   239: ldc             "@NotNull method %s.%s must not return null"
        //   241: ldc             2
        //   243: anewarray       Ljava/lang/Object;
        //   246: dup            
        //   247: ldc             0
        //   249: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler"
        //   251: aastore        
        //   252: dup            
        //   253: ldc             1
        //   255: ldc             "getReplacements"
        //   257: aastore        
        //   258: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   261: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   264: athrow         
        //   265: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   268: athrow         
        //   269: areturn        
        //   270: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   273: aload_3        
        //   274: invokeinterface java/util/List.isEmpty:()Z
        //   279: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(Z)Z
        //   282: pop            
        //   283: aload           4
        //   285: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   288: astore          5
        //   290: aload           5
        //   292: ifnull          310
        //   295: aload           5
        //   297: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   300: ifne            359
        //   303: goto            310
        //   306: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   309: athrow         
        //   310: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   313: dup            
        //   314: ifnonnull       358
        //   317: goto            324
        //   320: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   323: athrow         
        //   324: new             Ljava/lang/IllegalStateException;
        //   327: dup            
        //   328: ldc             "@NotNull method %s.%s must not return null"
        //   330: ldc             2
        //   332: anewarray       Ljava/lang/Object;
        //   335: dup            
        //   336: ldc             0
        //   338: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler"
        //   340: aastore        
        //   341: dup            
        //   342: ldc             1
        //   344: ldc             "getReplacements"
        //   346: aastore        
        //   347: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   350: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   353: athrow         
        //   354: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   357: athrow         
        //   358: areturn        
        //   359: aload           5
        //   361: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   364: astore          6
        //   366: new             Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   369: dup            
        //   370: new             Ljava/lang/StringBuilder;
        //   373: dup            
        //   374: invokespecial   java/lang/StringBuilder.<init>:()V
        //   377: ldc             "~"
        //   379: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   382: aload           4
        //   384: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getName:()Ljava/lang/String;
        //   387: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   390: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   393: invokespecial   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.<init>:(Ljava/lang/String;)V
        //   396: aload           4
        //   398: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   401: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   404: if_acmpne       415
        //   407: iconst_1       
        //   408: goto            416
        //   411: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   414: athrow         
        //   415: iconst_0       
        //   416: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setIsVirtual:(Z)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   419: iconst_1       
        //   420: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setIsCtorOrDtor:(Z)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   423: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   426: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   429: aload           4
        //   431: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setContainer:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   434: aload_1        
        //   435: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getProject:()Lcom/intellij/openapi/project/Project;
        //   438: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.get:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   441: astore          7
        //   443: aload_1        
        //   444: aload           6
        //   446: aload_2        
        //   447: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   450: aload           7
        //   452: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   455: aconst_null    
        //   456: aload_0        
        //   457: aload_2        
        //   458: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.getInlinePolicy:(Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;
        //   461: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getNewFunctionsReplacements:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/psi/OCStructLike;Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;)Ljava/util/List;
        //   464: dup            
        //   465: ifnonnull       502
        //   468: new             Ljava/lang/IllegalStateException;
        //   471: dup            
        //   472: ldc             "@NotNull method %s.%s must not return null"
        //   474: ldc             2
        //   476: anewarray       Ljava/lang/Object;
        //   479: dup            
        //   480: ldc             0
        //   482: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler"
        //   484: aastore        
        //   485: dup            
        //   486: ldc             1
        //   488: ldc             "getReplacements"
        //   490: aastore        
        //   491: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   494: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   497: athrow         
        //   498: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   501: athrow         
        //   502: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext<Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;)Ljava/util/List<Lcom/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      40     40     44     Ljava/lang/UnsupportedOperationException;
        //  44     84     84     88     Ljava/lang/UnsupportedOperationException;
        //  88     128    128    132    Ljava/lang/UnsupportedOperationException;
        //  183    198    198    202    Ljava/lang/UnsupportedOperationException;
        //  216    228    231    235    Ljava/lang/UnsupportedOperationException;
        //  221    265    265    269    Ljava/lang/UnsupportedOperationException;
        //  290    303    306    310    Ljava/lang/UnsupportedOperationException;
        //  295    317    320    324    Ljava/lang/UnsupportedOperationException;
        //  310    354    354    358    Ljava/lang/UnsupportedOperationException;
        //  366    411    411    415    Ljava/lang/UnsupportedOperationException;
        //  443    498    498    502    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0310:
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
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}
