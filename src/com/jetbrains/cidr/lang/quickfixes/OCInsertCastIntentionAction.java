// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.template.Expression;
import java.util.Arrays;
import com.jetbrains.cidr.lang.parser.OCElementType;
import java.util.ArrayList;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.editor.liveTemplates.OCTemplateExpression;
import com.intellij.codeInsight.template.TemplateBuilderImpl;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import kotlin.NoWhenBranchMatchedException;
import com.jetbrains.cidr.lang.psi.OCCastKind;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.psi.OCExpression;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0016J \u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0014J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J*\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J#\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0012\u001a\u00020\u0013H\u0096\u0002J\u0010\u0010 \u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!" }, d2 = { "Lcom/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction;", "Lcom/jetbrains/cidr/lang/quickfixes/OCPsiElementQuickFix;", "Lcom/jetbrains/cidr/lang/psi/OCExpression;", "expression", "type", "Lcom/jetbrains/cidr/lang/types/OCType;", "myInsertBridgeCast", "", "(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;Z)V", "myNewType", "getCastStub", "Lcom/jetbrains/cidr/lang/quickfixes/CastStub;", "kind", "Lcom/jetbrains/cidr/lang/psi/OCCastKind;", "getCastText", "", "getFamilyName", "getPreferredCastKind", "file", "Lcom/intellij/psi/PsiFile;", "project", "Lcom/intellij/openapi/project/Project;", "getTextInternal", "insertBridgeCast", "", "editor", "Lcom/intellij/openapi/editor/Editor;", "result", "Lcom/jetbrains/cidr/lang/psi/OCCastExpression;", "insertCast", "exprToCast", "invoke", "isEnum", "cidr-lang" })
public final class OCInsertCastIntentionAction extends OCPsiElementQuickFix<OCExpression>
{
    private final OCType myNewType;
    private final boolean myInsertBridgeCast;
    
    @NotNull
    @Override
    protected String getTextInternal() {
        return "Cast expression to '" + this.myNewType.getName(this.myElementPtr.getElement()) + "'";
    }
    
    @NotNull
    public String getFamilyName() {
        return "Cast expression";
    }
    
    @Override
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        OCExpression ocExpression = null;
        Label_0037: {
            try {
                Intrinsics.checkParameterIsNotNull((Object)project, "project");
                Intrinsics.checkParameterIsNotNull((Object)psiFile, "file");
                ocExpression = (OCExpression)this.myElementPtr.getElement();
                if (ocExpression != null) {
                    break Label_0037;
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            return;
        }
        final OCExpression ocExpression3;
        final OCExpression ocExpression2 = ocExpression3 = ocExpression;
        Intrinsics.checkExpressionValueIsNotNull((Object)ocExpression3, "expression");
        this.a(ocExpression3, this.a(psiFile, ocExpression2, project), psiFile, editor);
    }
    
    private final void a(final OCExpression p0, final OCCastKind p1, final PsiFile p2, final Editor p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCCastKind;)Lcom/jetbrains/cidr/lang/quickfixes/CastStub;
        //     6: astore          7
        //     8: aload           7
        //    10: invokevirtual   com/jetbrains/cidr/lang/quickfixes/CastStub.component1:()Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //    13: astore          5
        //    15: aload           7
        //    17: invokevirtual   com/jetbrains/cidr/lang/quickfixes/CastStub.component2:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    20: astore          6
        //    22: aconst_null    
        //    23: astore          7
        //    25: aload           5
        //    27: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    32: dup            
        //    33: ifnonnull       46
        //    36: invokestatic    kotlin/jvm/internal/Intrinsics.throwNpe:()V
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //    45: athrow         
        //    46: aload_0        
        //    47: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    50: aload_1        
        //    51: checkcast       Lcom/intellij/psi/PsiElement;
        //    54: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    57: aload_3        
        //    58: checkcast       Lcom/intellij/psi/PsiElement;
        //    61: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.typeElementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    64: checkcast       Lcom/intellij/psi/PsiElement;
        //    67: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.replace:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    72: pop            
        //    73: aload_1        
        //    74: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    79: invokeinterface com/intellij/psi/PsiElement.getChildren:()[Lcom/intellij/psi/PsiElement;
        //    84: checkcast       [Ljava/lang/Object;
        //    87: aload_1        
        //    88: invokestatic    kotlin/collections/ArraysKt.indexOf:([Ljava/lang/Object;Ljava/lang/Object;)I
        //    91: istore          7
        //    93: iload           7
        //    95: iconst_m1      
        //    96: if_icmpne       104
        //    99: return         
        //   100: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   103: athrow         
        //   104: aload_1        
        //   105: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   110: invokeinterface com/intellij/psi/PsiElement.copy:()Lcom/intellij/psi/PsiElement;
        //   115: invokeinterface com/intellij/psi/PsiElement.getChildren:()[Lcom/intellij/psi/PsiElement;
        //   120: iload           7
        //   122: aaload         
        //   123: astore          8
        //   125: aload_2        
        //   126: getstatic       com/jetbrains/cidr/lang/psi/OCCastKind.C_STYLE_CAST:Lcom/jetbrains/cidr/lang/psi/OCCastKind;
        //   129: invokestatic    kotlin/jvm/internal/Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   132: iconst_1       
        //   133: ixor           
        //   134: ifeq            189
        //   137: aload           8
        //   139: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   142: ifeq            189
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   151: athrow         
        //   152: aload           8
        //   154: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   157: invokeinterface com/jetbrains/cidr/lang/psi/OCParenthesizedExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   162: dup            
        //   163: ifnull          183
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   172: athrow         
        //   173: checkcast       Lcom/intellij/psi/PsiElement;
        //   176: goto            186
        //   179: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   182: athrow         
        //   183: pop            
        //   184: aload           8
        //   186: goto            191
        //   189: aload           8
        //   191: astore          9
        //   193: aload           6
        //   195: checkcast       Lcom/intellij/psi/PsiElement;
        //   198: aload           9
        //   200: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   203: pop            
        //   204: aload_1        
        //   205: checkcast       Lcom/intellij/psi/PsiElement;
        //   208: aload           5
        //   210: checkcast       Lcom/intellij/psi/PsiElement;
        //   213: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   216: dup            
        //   217: ifnonnull       234
        //   220: new             Lkotlin/TypeCastException;
        //   223: dup            
        //   224: ldc             "null cannot be cast to non-null type com.jetbrains.cidr.lang.psi.OCCastExpression"
        //   226: invokespecial   kotlin/TypeCastException.<init>:(Ljava/lang/String;)V
        //   229: athrow         
        //   230: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   233: athrow         
        //   234: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //   237: astore          10
        //   239: aload_0        
        //   240: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myInsertBridgeCast:Z
        //   243: ifeq            292
        //   246: aload           4
        //   248: ifnull          292
        //   251: goto            258
        //   254: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   257: athrow         
        //   258: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   261: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   266: ifne            292
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   275: athrow         
        //   276: aload_0        
        //   277: aload_1        
        //   278: aload           4
        //   280: aload           10
        //   282: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/openapi/editor/Editor;Lcom/jetbrains/cidr/lang/psi/OCCastExpression;)V
        //   285: goto            292
        //   288: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   291: athrow         
        //   292: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  25     39     42     46     Lkotlin/TypeCastException;
        //  93     100    100    104    Lkotlin/TypeCastException;
        //  125    145    148    152    Lkotlin/TypeCastException;
        //  137    166    169    173    Lkotlin/TypeCastException;
        //  152    179    179    183    Lkotlin/TypeCastException;
        //  193    230    230    234    Lkotlin/TypeCastException;
        //  239    251    254    258    Lkotlin/TypeCastException;
        //  246    269    272    276    Lkotlin/TypeCastException;
        //  258    285    288    292    Lkotlin/TypeCastException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0152:
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
    
    private final CastStub a(final OCExpression p0, final OCCastKind p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_2        
        //     2: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCCastKind;)Ljava/lang/String;
        //     5: astore_3       
        //     6: aload_2        
        //     7: getstatic       com/jetbrains/cidr/lang/psi/OCCastKind.C_STYLE_CAST:Lcom/jetbrains/cidr/lang/psi/OCCastKind;
        //    10: invokestatic    kotlin/jvm/internal/Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    13: iconst_1       
        //    14: ixor           
        //    15: ifne            46
        //    18: aload_1        
        //    19: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //    22: ifne            46
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //    31: athrow         
        //    32: aload_1        
        //    33: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConditionalExpression;
        //    36: ifeq            54
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //    45: athrow         
        //    46: iconst_1       
        //    47: goto            55
        //    50: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //    53: athrow         
        //    54: iconst_0       
        //    55: istore          4
        //    57: iload           4
        //    59: ifeq            71
        //    62: ldc             "(1)"
        //    64: goto            73
        //    67: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //    70: athrow         
        //    71: ldc             "1"
        //    73: astore          5
        //    75: new             Ljava/lang/StringBuilder;
        //    78: dup            
        //    79: invokespecial   java/lang/StringBuilder.<init>:()V
        //    82: aload_3        
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: aload           5
        //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    91: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    94: aload_1        
        //    95: checkcast       Lcom/intellij/psi/PsiElement;
        //    98: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   101: dup            
        //   102: ifnonnull       119
        //   105: new             Lkotlin/TypeCastException;
        //   108: dup            
        //   109: ldc             "null cannot be cast to non-null type com.jetbrains.cidr.lang.psi.OCCastExpression"
        //   111: invokespecial   kotlin/TypeCastException.<init>:(Ljava/lang/String;)V
        //   114: athrow         
        //   115: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   118: athrow         
        //   119: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //   122: astore          6
        //   124: new             Lcom/jetbrains/cidr/lang/quickfixes/CastStub;
        //   127: dup            
        //   128: aload           6
        //   130: iload           4
        //   132: ifeq            191
        //   135: aload_2        
        //   136: getstatic       com/jetbrains/cidr/lang/psi/OCCastKind.C_STYLE_CAST:Lcom/jetbrains/cidr/lang/psi/OCCastKind;
        //   139: invokestatic    kotlin/jvm/internal/Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   142: ifeq            191
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   151: athrow         
        //   152: aload           6
        //   154: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   159: dup            
        //   160: ifnonnull       173
        //   163: new             Lkotlin/TypeCastException;
        //   166: dup            
        //   167: ldc             "null cannot be cast to non-null type com.jetbrains.cidr.lang.psi.OCParenthesizedExpression"
        //   169: invokespecial   kotlin/TypeCastException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   176: invokeinterface com/jetbrains/cidr/lang/psi/OCParenthesizedExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   181: dup            
        //   182: ifnonnull       188
        //   185: invokestatic    kotlin/jvm/internal/Intrinsics.throwNpe:()V
        //   188: goto            205
        //   191: aload           6
        //   193: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   198: dup            
        //   199: ifnonnull       205
        //   202: invokestatic    kotlin/jvm/internal/Intrinsics.throwNpe:()V
        //   205: dup            
        //   206: ldc             "if (addParens && kind ==\u2026  else castExpr.operand!!"
        //   208: invokestatic    kotlin/jvm/internal/Intrinsics.checkExpressionValueIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
        //   211: invokespecial   com/jetbrains/cidr/lang/quickfixes/CastStub.<init>:(Lcom/jetbrains/cidr/lang/psi/OCCastExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)V
        //   214: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  6      25     28     32     Lkotlin/TypeCastException;
        //  18     39     42     46     Lkotlin/TypeCastException;
        //  32     50     50     54     Lkotlin/TypeCastException;
        //  57     67     67     71     Lkotlin/TypeCastException;
        //  75     115    115    119    Lkotlin/TypeCastException;
        //  124    145    148    152    Lkotlin/TypeCastException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
    
    private final String a(final OCCastKind ocCastKind) {
        try {
            switch (OCInsertCastIntentionAction$WhenMappings.$EnumSwitchMapping$0[ocCastKind.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    return "" + ocCastKind.getTokenText() + "<int>";
                }
                case 5: {
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        try {
            if (this.myInsertBridgeCast) {
                return "(__bridge int)";
            }
        }
        catch (TypeCastException ex2) {
            throw b(ex2);
        }
        return "(int)";
    }
    
    private final void a(final OCExpression ocExpression, final Editor editor, final OCCastExpression ocCastExpression) {
        PsiDocumentManager.getInstance(ocExpression.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
        final PsiElement psi = ocCastExpression.getNode().getChildren(OCTokenTypes.BRIDGE_CAST_KEYWORDS)[0].getPsi();
        final TemplateBuilderImpl templateBuilderImpl = new TemplateBuilderImpl(psi);
        final IElementType[] types = OCTokenTypes.BRIDGE_CAST_KEYWORDS.getTypes();
        final TemplateBuilderImpl templateBuilderImpl2 = templateBuilderImpl;
        final PsiElement psiElement = psi;
        final OCTemplateExpression ocTemplateExpression = new(com.jetbrains.cidr.lang.editor.liveTemplates.OCTemplateExpression.class);
        final String s = "__bridge";
        final IElementType[] array = types;
        final String s2 = s;
        final OCTemplateExpression ocTemplateExpression2 = ocTemplateExpression;
        final OCTemplateExpression ocTemplateExpression3 = ocTemplateExpression;
        final PsiElement psiElement2 = psiElement;
        final TemplateBuilderImpl templateBuilderImpl3 = templateBuilderImpl2;
        final IElementType[] array2 = array;
        final ArrayList<String> list = new ArrayList<String>(array.length);
        for (int i = 0; i < array2.length; ++i) {
            final IElementType elementType = array2[i];
            final ArrayList<String> list2 = list;
            final IElementType elementType2 = elementType;
            final ArrayList<String> list3 = list2;
            final IElementType elementType3 = elementType2;
            if (elementType3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.jetbrains.cidr.lang.parser.OCElementType");
            }
            list3.add(((OCElementType)elementType3).getName());
        }
        final ArrayList<String> list4 = list;
        final TemplateBuilderImpl templateBuilderImpl4 = templateBuilderImpl3;
        final PsiElement psiElement3 = psiElement2;
        final OCTemplateExpression ocTemplateExpression4 = ocTemplateExpression3;
        final OCTemplateExpression ocTemplateExpression5 = ocTemplateExpression2;
        final String s3 = s2;
        final ArrayList<String> list5 = list4;
        final String s4 = s3;
        final OCTemplateExpression ocTemplateExpression6 = ocTemplateExpression5;
        final OCTemplateExpression ocTemplateExpression7 = ocTemplateExpression4;
        final PsiElement psiElement4 = psiElement3;
        final TemplateBuilderImpl templateBuilderImpl5 = templateBuilderImpl4;
        final ArrayList<String> list6 = list5;
        final String[] array3 = list6.toArray(new String[list6.size()]);
        if (array3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        final String[] array4 = array3;
        final TemplateBuilderImpl templateBuilderImpl6 = templateBuilderImpl5;
        final PsiElement psiElement5 = psiElement4;
        final OCTemplateExpression ocTemplateExpression8 = ocTemplateExpression7;
        final OCTemplateExpression ocTemplateExpression9 = ocTemplateExpression6;
        final String s5 = s4;
        final String[] array5 = array4;
        new OCTemplateExpression(s5, (String[])Arrays.copyOf(array5, array5.length));
        templateBuilderImpl6.replaceElement(psiElement5, ocTemplateExpression8);
        templateBuilderImpl.run(editor, false);
    }
    
    private final OCCastKind a(final PsiFile p0, final OCExpression p1, final Project p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myInsertBridgeCast:Z
        //     4: ifne            65
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    11: ifeq            65
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //    20: athrow         
        //    21: aload_1        
        //    22: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    25: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    30: ifeq            65
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //    39: athrow         
        //    40: aload_3        
        //    41: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //    44: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //    46: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //    49: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    52: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.USE_MODERN_CASTS:Z
        //    55: ifne            73
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //    64: athrow         
        //    65: getstatic       com/jetbrains/cidr/lang/psi/OCCastKind.C_STYLE_CAST:Lcom/jetbrains/cidr/lang/psi/OCCastKind;
        //    68: areturn        
        //    69: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //    72: athrow         
        //    73: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    76: dup            
        //    77: aload_2        
        //    78: checkcast       Lcom/intellij/psi/PsiElement;
        //    81: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    84: astore          4
        //    86: aload_2        
        //    87: aload           4
        //    89: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    94: aload_3        
        //    95: iconst_0       
        //    96: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.decayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //    99: astore          5
        //   101: aload           5
        //   103: dup            
        //   104: ldc             "oldType"
        //   106: invokestatic    kotlin/jvm/internal/Intrinsics.checkExpressionValueIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
        //   109: aload_3        
        //   110: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionActionKt.access$decomposeCV:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/quickfixes/CVDecompositionResult;
        //   113: astore          6
        //   115: aload_0        
        //   116: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   119: aload_3        
        //   120: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionActionKt.access$decomposeCV:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/quickfixes/CVDecompositionResult;
        //   123: astore          7
        //   125: aload           7
        //   127: invokevirtual   com/jetbrains/cidr/lang/quickfixes/CVDecompositionResult.getCv:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   130: aload           6
        //   132: invokevirtual   com/jetbrains/cidr/lang/quickfixes/CVDecompositionResult.getCv:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   135: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isSuperset:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Z
        //   138: ifne            149
        //   141: iconst_1       
        //   142: goto            150
        //   145: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   148: athrow         
        //   149: iconst_0       
        //   150: istore          8
        //   152: aload           6
        //   154: invokevirtual   com/jetbrains/cidr/lang/quickfixes/CVDecompositionResult.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   157: aload           7
        //   159: invokevirtual   com/jetbrains/cidr/lang/quickfixes/CVDecompositionResult.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   162: aload_2        
        //   163: aload           4
        //   165: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionActionKt.access$getRefTypes:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/quickfixes/RefTypes;
        //   168: astore          9
        //   170: iload           8
        //   172: ifeq            220
        //   175: aload           9
        //   177: ifnull          220
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   186: athrow         
        //   187: aload           9
        //   189: invokevirtual   com/jetbrains/cidr/lang/quickfixes/RefTypes.getOld:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   192: aload           9
        //   194: invokevirtual   com/jetbrains/cidr/lang/quickfixes/RefTypes.getNew:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   197: aload           4
        //   199: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   202: ifeq            220
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   211: athrow         
        //   212: getstatic       com/jetbrains/cidr/lang/psi/OCCastKind.CONST_CAST:Lcom/jetbrains/cidr/lang/psi/OCCastKind;
        //   215: areturn        
        //   216: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   219: athrow         
        //   220: iload           8
        //   222: ifne            640
        //   225: aload           5
        //   227: aload_0        
        //   228: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   231: aload_2        
        //   232: aload           4
        //   234: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionActionKt.access$getRefTypes:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/quickfixes/RefTypes;
        //   237: astore          10
        //   239: aload           10
        //   241: ifnull          297
        //   244: aload           10
        //   246: invokevirtual   com/jetbrains/cidr/lang/quickfixes/RefTypes.getNew:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   249: aload           10
        //   251: invokevirtual   com/jetbrains/cidr/lang/quickfixes/RefTypes.getOld:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   254: aload           4
        //   256: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   259: istore          11
        //   261: iload           11
        //   263: ifeq            294
        //   266: aload           10
        //   268: invokevirtual   com/jetbrains/cidr/lang/quickfixes/RefTypes.getNew:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   271: aload           4
        //   273: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isPolymorphic:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   276: ifeq            294
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   285: athrow         
        //   286: getstatic       com/jetbrains/cidr/lang/psi/OCCastKind.DYNAMIC_CAST:Lcom/jetbrains/cidr/lang/psi/OCCastKind;
        //   289: areturn        
        //   290: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   293: athrow         
        //   294: goto            300
        //   297: iconst_0       
        //   298: istore          11
        //   300: aload_0        
        //   301: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   304: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   307: ifne            492
        //   310: aload           10
        //   312: ifnull          373
        //   315: goto            322
        //   318: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   321: athrow         
        //   322: aload           10
        //   324: invokevirtual   com/jetbrains/cidr/lang/quickfixes/RefTypes.isPointer:()Z
        //   327: ifeq            373
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   336: athrow         
        //   337: aload           10
        //   339: invokevirtual   com/jetbrains/cidr/lang/quickfixes/RefTypes.getOld:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   342: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   345: ifne            492
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   354: athrow         
        //   355: aload           10
        //   357: invokevirtual   com/jetbrains/cidr/lang/quickfixes/RefTypes.getNew:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   360: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   363: ifne            492
        //   366: goto            373
        //   369: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   372: athrow         
        //   373: iload           11
        //   375: ifne            492
        //   378: goto            385
        //   381: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   384: athrow         
        //   385: aload_0        
        //   386: aload_0        
        //   387: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   390: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   393: ifeq            418
        //   396: goto            403
        //   399: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   402: athrow         
        //   403: aload           5
        //   405: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isArithmeticType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   408: ifne            492
        //   411: goto            418
        //   414: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   417: athrow         
        //   418: aload_0        
        //   419: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   422: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isArithmeticType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   425: ifeq            451
        //   428: goto            435
        //   431: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   434: athrow         
        //   435: aload_0        
        //   436: aload           5
        //   438: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   441: ifne            492
        //   444: goto            451
        //   447: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   450: athrow         
        //   451: aload_0        
        //   452: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   455: aload           5
        //   457: aload_2        
        //   458: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   461: aload_2        
        //   462: checkcast       Lcom/intellij/psi/PsiElement;
        //   465: iconst_1       
        //   466: iconst_1       
        //   467: aload           4
        //   469: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   472: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   475: aload_2        
        //   476: checkcast       Lcom/intellij/psi/PsiElement;
        //   479: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   482: ifne            500
        //   485: goto            492
        //   488: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   491: athrow         
        //   492: getstatic       com/jetbrains/cidr/lang/psi/OCCastKind.STATIC_CAST:Lcom/jetbrains/cidr/lang/psi/OCCastKind;
        //   495: areturn        
        //   496: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   499: athrow         
        //   500: aload           5
        //   502: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
        //   505: ifeq            527
        //   508: aload_0        
        //   509: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   512: aload           4
        //   514: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isIntegralType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   517: ifne            632
        //   520: goto            527
        //   523: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   526: athrow         
        //   527: aload           5
        //   529: aload           4
        //   531: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isIntegralType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   534: ifeq            561
        //   537: goto            544
        //   540: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   543: athrow         
        //   544: aload_0        
        //   545: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   548: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
        //   551: ifne            632
        //   554: goto            561
        //   557: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   560: athrow         
        //   561: aload           5
        //   563: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
        //   566: ifeq            593
        //   569: goto            576
        //   572: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   575: athrow         
        //   576: aload_0        
        //   577: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   580: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
        //   583: ifne            632
        //   586: goto            593
        //   589: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   592: athrow         
        //   593: aload_0        
        //   594: getfield        com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.myNewType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   597: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   600: ifeq            640
        //   603: goto            610
        //   606: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   609: athrow         
        //   610: aload_2        
        //   611: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   614: aload           4
        //   616: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   619: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isLValue:()Z
        //   622: ifeq            640
        //   625: goto            632
        //   628: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   631: athrow         
        //   632: getstatic       com/jetbrains/cidr/lang/psi/OCCastKind.REINTERPRET_CAST:Lcom/jetbrains/cidr/lang/psi/OCCastKind;
        //   635: areturn        
        //   636: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.b:(Lkotlin/TypeCastException;)Lkotlin/TypeCastException;
        //   639: athrow         
        //   640: getstatic       com/jetbrains/cidr/lang/psi/OCCastKind.C_STYLE_CAST:Lcom/jetbrains/cidr/lang/psi/OCCastKind;
        //   643: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  0      14     17     21     Lkotlin/TypeCastException;
        //  7      33     36     40     Lkotlin/TypeCastException;
        //  21     58     61     65     Lkotlin/TypeCastException;
        //  40     69     69     73     Lkotlin/TypeCastException;
        //  125    145    145    149    Lkotlin/TypeCastException;
        //  170    180    183    187    Lkotlin/TypeCastException;
        //  175    205    208    212    Lkotlin/TypeCastException;
        //  187    216    216    220    Lkotlin/TypeCastException;
        //  261    279    282    286    Lkotlin/TypeCastException;
        //  266    290    290    294    Lkotlin/TypeCastException;
        //  300    315    318    322    Lkotlin/TypeCastException;
        //  310    330    333    337    Lkotlin/TypeCastException;
        //  322    348    351    355    Lkotlin/TypeCastException;
        //  337    366    369    373    Lkotlin/TypeCastException;
        //  355    378    381    385    Lkotlin/TypeCastException;
        //  373    396    399    403    Lkotlin/TypeCastException;
        //  385    411    414    418    Lkotlin/TypeCastException;
        //  403    428    431    435    Lkotlin/TypeCastException;
        //  418    444    447    451    Lkotlin/TypeCastException;
        //  435    485    488    492    Lkotlin/TypeCastException;
        //  451    496    496    500    Lkotlin/TypeCastException;
        //  500    520    523    527    Lkotlin/TypeCastException;
        //  508    537    540    544    Lkotlin/TypeCastException;
        //  527    554    557    561    Lkotlin/TypeCastException;
        //  544    569    572    576    Lkotlin/TypeCastException;
        //  561    586    589    593    Lkotlin/TypeCastException;
        //  576    603    606    610    Lkotlin/TypeCastException;
        //  593    625    628    632    Lkotlin/TypeCastException;
        //  610    636    636    640    Lkotlin/TypeCastException;
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private final boolean a(final OCType ocType) {
        Label_0024: {
            try {
                if (!(ocType instanceof OCStructType)) {
                    return false;
                }
                final OCType ocType2 = ocType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final boolean b = ocStructType.isEnum();
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            try {
                final OCType ocType2 = ocType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final boolean b = ocStructType.isEnum();
                if (b) {
                    return true;
                }
            }
            catch (TypeCastException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public OCInsertCastIntentionAction(@NotNull final OCExpression ocExpression, @NotNull final OCType ocType, final boolean myInsertBridgeCast) {
        Intrinsics.checkParameterIsNotNull((Object)ocExpression, "expression");
        Intrinsics.checkParameterIsNotNull((Object)ocType, "type");
        super((PsiElement)ocExpression);
        this.myInsertBridgeCast = myInsertBridgeCast;
        OCType myNewType = null;
        Label_0084: {
            Label_0083: {
                if (ocType instanceof OCPointerType) {
                    Label_0057: {
                        try {
                            if (!ocType.isPointerToObject()) {
                                break Label_0083;
                            }
                            final OCType ocType2 = ocType;
                            final OCPointerType ocPointerType = (OCPointerType)ocType2;
                            final ARCAttribute arcAttribute = ocPointerType.getARCAttribute();
                            if (arcAttribute != null) {
                                break Label_0057;
                            }
                            break Label_0083;
                        }
                        catch (TypeCastException ex) {
                            throw b(ex);
                        }
                        try {
                            final OCType ocType2 = ocType;
                            final OCPointerType ocPointerType = (OCPointerType)ocType2;
                            final ARCAttribute arcAttribute = ocPointerType.getARCAttribute();
                            if (arcAttribute != null) {
                                final OCPointerType to = OCPointerType.to(((OCPointerType)ocType).getRefType());
                                Intrinsics.checkExpressionValueIsNotNull((Object)to, "OCPointerType.to(type.refType)");
                                myNewType = to;
                                break Label_0084;
                            }
                        }
                        catch (TypeCastException ex2) {
                            throw b(ex2);
                        }
                    }
                }
            }
            myNewType = ocType;
        }
        this.myNewType = myNewType;
    }
    
    public OCInsertCastIntentionAction(@NotNull final OCExpression ocExpression, @NotNull final OCType ocType) {
        this(ocExpression, ocType, false, 4, null);
    }
    
    private static TypeCastException b(final TypeCastException ex) {
        return ex;
    }
}
