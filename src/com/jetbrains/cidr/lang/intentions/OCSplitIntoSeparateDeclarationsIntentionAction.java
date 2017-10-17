// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.refactoring.util.OCNormalizeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.IntentionAction;

public class OCSplitIntoSeparateDeclarationsIntentionAction implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Split into separate declarations";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, final PsiFile p2) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_2        
        //    45: aload_3        
        //    46: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/intellij/psi/PsiElement;
        //    49: astore          4
        //    51: aload           4
        //    53: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    56: ifne            65
        //    59: iconst_0       
        //    60: ireturn        
        //    61: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    64: athrow         
        //    65: aload           4
        //    67: ldc             Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;.class
        //    69: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    72: ifnull          83
        //    75: iconst_1       
        //    76: goto            84
        //    79: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    82: athrow         
        //    83: iconst_0       
        //    84: istore          5
        //    86: aload           4
        //    88: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    91: ifeq            167
        //    94: iload           5
        //    96: ifeq            131
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   105: athrow         
        //   106: aload           4
        //   108: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   113: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   118: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   121: ifeq            165
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   130: athrow         
        //   131: aload           4
        //   133: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   136: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   141: invokeinterface java/util/List.size:()I
        //   146: iconst_1       
        //   147: if_icmple       165
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   156: athrow         
        //   157: iconst_1       
        //   158: goto            166
        //   161: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   164: athrow         
        //   165: iconst_0       
        //   166: ireturn        
        //   167: aload           4
        //   169: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   172: ifeq            258
        //   175: iload           5
        //   177: ifeq            217
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   186: athrow         
        //   187: aload           4
        //   189: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   194: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   199: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   204: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   207: ifeq            256
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   216: athrow         
        //   217: aload           4
        //   219: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   224: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   227: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   232: invokeinterface java/util/List.size:()I
        //   237: iconst_1       
        //   238: if_icmple       256
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   247: athrow         
        //   248: iconst_1       
        //   249: goto            257
        //   252: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   255: athrow         
        //   256: iconst_0       
        //   257: ireturn        
        //   258: aload           4
        //   260: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   263: ifeq            307
        //   266: aload           4
        //   268: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   273: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList;
        //   276: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList.getProperties:()Ljava/util/List;
        //   281: invokeinterface java/util/List.size:()I
        //   286: iconst_1       
        //   287: if_icmple       305
        //   290: goto            297
        //   293: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   296: athrow         
        //   297: iconst_1       
        //   298: goto            306
        //   301: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   304: athrow         
        //   305: iconst_0       
        //   306: ireturn        
        //   307: iconst_0       
        //   308: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  51     61     61     65     Lcom/intellij/util/IncorrectOperationException;
        //  65     79     79     83     Lcom/intellij/util/IncorrectOperationException;
        //  86     99     102    106    Lcom/intellij/util/IncorrectOperationException;
        //  94     124    127    131    Lcom/intellij/util/IncorrectOperationException;
        //  106    150    153    157    Lcom/intellij/util/IncorrectOperationException;
        //  131    161    161    165    Lcom/intellij/util/IncorrectOperationException;
        //  167    180    183    187    Lcom/intellij/util/IncorrectOperationException;
        //  175    210    213    217    Lcom/intellij/util/IncorrectOperationException;
        //  187    241    244    248    Lcom/intellij/util/IncorrectOperationException;
        //  217    252    252    256    Lcom/intellij/util/IncorrectOperationException;
        //  258    290    293    297    Lcom/intellij/util/IncorrectOperationException;
        //  266    301    301    305    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0106:
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
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCSplitIntoSeparateDeclarationsIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (!FileModificationService.getInstance().prepareFileForWrite(psiFile)) {
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        final PsiElement a = a(editor, psiFile);
        try {
            if (a instanceof OCDeclaration) {
                OCNormalizeUtil.normalizeDeclaration((OCDeclaration)a);
                return;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        try {
            if (a instanceof OCDeclarator) {
                OCNormalizeUtil.normalizeDeclarator((OCDeclarator)a);
                return;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        try {
            if (a instanceof OCSynthesizeProperty) {
                OCNormalizeUtil.normalizeSynthesizeStatement((OCSynthesizeProperty)a);
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    @Nullable
    private static PsiElement a(final Editor editor, final PsiFile psiFile) {
        final PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());
        final OCSymbolDeclarator ocSymbolDeclarator = OCElementUtil.getAdjacentParentOfType(element, OCDeclarator.class, OCSynthesizeProperty.class);
        if (ocSymbolDeclarator == null) {
            final OCTypeElement ocTypeElement = OCElementUtil.getAdjacentParentOfType(element, (Class<? extends OCTypeElement>)OCTypeElement.class);
            Label_0080: {
                try {
                    if (ocTypeElement == null) {
                        return (PsiElement)ocSymbolDeclarator;
                    }
                    final OCTypeElement ocTypeElement2 = ocTypeElement;
                    final PsiElement psiElement = ocTypeElement2.getParent();
                    final boolean b = psiElement instanceof OCDeclaration;
                    if (b) {
                        break Label_0080;
                    }
                    return (PsiElement)ocSymbolDeclarator;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCTypeElement ocTypeElement2 = ocTypeElement;
                    final PsiElement psiElement = ocTypeElement2.getParent();
                    final boolean b = psiElement instanceof OCDeclaration;
                    if (b) {
                        return ocTypeElement.getParent();
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
        }
        return (PsiElement)ocSymbolDeclarator;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
