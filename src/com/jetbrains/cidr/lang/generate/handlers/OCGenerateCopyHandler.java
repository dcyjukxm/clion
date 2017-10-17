// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.psi.PsiElement;
import javax.swing.JComponent;
import java.util.Map;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateMethodActionContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.settings.OCEnumComboOption;

public class OCGenerateCopyHandler extends OCGenerateMethodHandler
{
    private static final OCEnumComboOption<DepthMode> DEPTH_MODE;
    
    @Override
    protected String getActionTitle() {
        return "Generate -copyWithZone:";
    }
    
    @Override
    protected String[] getMethodNames() {
        return new String[] { "copyWithZone:" };
    }
    
    @Nullable
    @Override
    protected String getParentProtocol() {
        return "NSCopying";
    }
    
    protected boolean allowEmptySelection(final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        return true;
    }
    
    @NotNull
    protected DepthMode defaultDepthMode(@Nullable final OCCodeStyleSettings ocCodeStyleSettings) {
        DepthMode depthMode = null;
        Label_0031: {
            Label_0018: {
                try {
                    if (ocCodeStyleSettings == null) {
                        break Label_0018;
                    }
                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                    final boolean b = ocCodeStyleSettings2.COPY_IS_DEEP;
                    if (b) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                    final boolean b = ocCodeStyleSettings2.COPY_IS_DEEP;
                    if (b) {
                        final DepthMode depthMode2;
                        depthMode = (depthMode2 = DepthMode.DEEP);
                        break Label_0031;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            DepthMode depthMode2;
            depthMode = (depthMode2 = DepthMode.SHALLOW);
            try {
                if (depthMode2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler", "defaultDepthMode"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return depthMode;
    }
    
    @Override
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler", "loadOptions"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler", "loadOptions"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        list.add((Pair<OCOption, Object>)new Pair((Object)OCGenerateCopyHandler.DEPTH_MODE, (Object)this.defaultDepthMode(ocCodeStyleSettings)));
        super.loadOptions(psiFile, editor, ocGenerateMethodActionContext, ocCodeStyleSettings, list);
    }
    
    @Override
    protected void saveOptions(final PsiFile psiFile, @NotNull final OCCodeStyleSettings ocCodeStyleSettings, final Map<OCOption, Object> map) {
        try {
            if (ocCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler", "saveOptions"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        boolean copy_IS_DEEP = false;
        Label_0067: {
            try {
                if (OCClassActionHandlerBase.getOption(map, (OCOption<Object, JComponent>)OCGenerateCopyHandler.DEPTH_MODE) == DepthMode.DEEP) {
                    copy_IS_DEEP = true;
                    break Label_0067;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            copy_IS_DEEP = false;
        }
        ocCodeStyleSettings.COPY_IS_DEEP = copy_IS_DEEP;
        super.saveOptions(psiFile, ocCodeStyleSettings, map);
    }
    
    @NotNull
    @Override
    protected String getInsertText(@NotNull final PsiElement p0, @Nullable final PsiElement p1, @NotNull final List<OCInstanceVariableSymbol> p2, @NotNull final OCGenerateMethodActionContext p3) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getInsertText"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "ivars"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getInsertText"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "actionContext"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "getInsertText"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   132: athrow         
        //   133: aload_1        
        //   134: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //   137: ifeq            213
        //   140: new             Ljava/lang/StringBuilder;
        //   143: dup            
        //   144: invokespecial   java/lang/StringBuilder.<init>:()V
        //   147: aload           4
        //   149: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext.getBaseMethod:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   152: aload_1        
        //   153: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.methodSignature:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: ldc             ";"
        //   161: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   164: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   167: dup            
        //   168: ifnonnull       212
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   177: athrow         
        //   178: new             Ljava/lang/IllegalStateException;
        //   181: dup            
        //   182: ldc             "@NotNull method %s.%s must not return null"
        //   184: ldc             2
        //   186: anewarray       Ljava/lang/Object;
        //   189: dup            
        //   190: ldc             0
        //   192: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler"
        //   194: aastore        
        //   195: dup            
        //   196: ldc             1
        //   198: ldc             "getInsertText"
        //   200: aastore        
        //   201: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   204: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   207: athrow         
        //   208: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   211: athrow         
        //   212: areturn        
        //   213: new             Ljava/util/ArrayList;
        //   216: dup            
        //   217: invokespecial   java/util/ArrayList.<init>:()V
        //   220: astore          5
        //   222: aload_0        
        //   223: aload           4
        //   225: getstatic       com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.DEPTH_MODE:Lcom/jetbrains/cidr/lang/settings/OCEnumComboOption;
        //   228: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.getOption:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljava/lang/Object;
        //   231: checkcast       Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler$DepthMode;
        //   234: astore          6
        //   236: new             Ljava/lang/StringBuilder;
        //   239: dup            
        //   240: invokespecial   java/lang/StringBuilder.<init>:()V
        //   243: astore          7
        //   245: aload_3        
        //   246: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   251: astore          8
        //   253: aload           8
        //   255: invokeinterface java/util/Iterator.hasNext:()Z
        //   260: ifeq            485
        //   263: aload           8
        //   265: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   270: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   273: astore          9
        //   275: aload           9
        //   277: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getAssociatedProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   282: astore          10
        //   284: aload           9
        //   286: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   291: aload_1        
        //   292: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   297: iconst_1       
        //   298: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   301: astore          11
        //   303: aload           10
        //   305: ifnull          431
        //   308: aload           10
        //   310: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   315: ifne            431
        //   318: goto            325
        //   321: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   324: athrow         
        //   325: aload           10
        //   327: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.COPY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   330: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   335: ifeq            355
        //   338: goto            345
        //   341: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   344: athrow         
        //   345: getstatic       com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler$DepthMode.SHALLOW:Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler$DepthMode;
        //   348: goto            357
        //   351: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   354: athrow         
        //   355: aload           6
        //   357: astore          12
        //   359: aload           5
        //   361: new             Ljava/lang/StringBuilder;
        //   364: dup            
        //   365: invokespecial   java/lang/StringBuilder.<init>:()V
        //   368: ldc             "copy."
        //   370: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   373: aload           10
        //   375: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getName:()Ljava/lang/String;
        //   380: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   383: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   386: new             Ljava/lang/StringBuilder;
        //   389: dup            
        //   390: invokespecial   java/lang/StringBuilder.<init>:()V
        //   393: ldc             "self."
        //   395: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   398: aload           10
        //   400: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getName:()Ljava/lang/String;
        //   405: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   408: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   411: aload           11
        //   413: aload_1        
        //   414: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   419: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   422: aload           12
        //   424: iconst_0       
        //   425: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler$DepthMode;Z)V
        //   428: goto            482
        //   431: aload           5
        //   433: new             Ljava/lang/StringBuilder;
        //   436: dup            
        //   437: invokespecial   java/lang/StringBuilder.<init>:()V
        //   440: ldc             "copy->"
        //   442: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   445: aload           9
        //   447: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getName:()Ljava/lang/String;
        //   452: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   455: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   458: aload           9
        //   460: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getName:()Ljava/lang/String;
        //   465: aload           11
        //   467: aload_1        
        //   468: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   473: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   476: aload           6
        //   478: iconst_0       
        //   479: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler$DepthMode;Z)V
        //   482: goto            253
        //   485: aload           5
        //   487: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   492: astore          8
        //   494: aload           8
        //   496: invokeinterface java/util/Iterator.hasNext:()Z
        //   501: ifeq            527
        //   504: aload           8
        //   506: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   511: checkcast       Ljava/lang/String;
        //   514: astore          9
        //   516: aload           7
        //   518: aload           9
        //   520: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   523: pop            
        //   524: goto            494
        //   527: aload           4
        //   529: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   532: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getSuperType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   535: astore          8
        //   537: aload           8
        //   539: ifnull          554
        //   542: aload           8
        //   544: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getImplementation:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   547: goto            555
        //   550: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   553: athrow         
        //   554: aconst_null    
        //   555: astore          9
        //   557: aload           9
        //   559: ifnull          598
        //   562: aload           9
        //   564: ldc             "copyWithZone:"
        //   566: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //   568: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
        //   571: dup            
        //   572: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
        //   575: iconst_1       
        //   576: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.processMembersInAllCategories:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;Z)Z
        //   581: ifne            598
        //   584: goto            591
        //   587: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   590: athrow         
        //   591: ldc             "OC Overridden CopyWithZone Body 1.m"
        //   593: astore          10
        //   595: goto            619
        //   598: aload           5
        //   600: invokeinterface java/util/List.size:()I
        //   605: ifle            615
        //   608: ldc             "OC Overridden CopyWithZone Body 2.m"
        //   610: astore          10
        //   612: goto            619
        //   615: ldc             "OC Overridden CopyWithZone Body 3.m"
        //   617: astore          10
        //   619: aload           4
        //   621: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext.getBaseMethod:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   624: aload           10
        //   626: aload           7
        //   628: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   631: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   634: aload_1        
        //   635: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.methodFromTemplate:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   638: dup            
        //   639: ifnonnull       676
        //   642: new             Ljava/lang/IllegalStateException;
        //   645: dup            
        //   646: ldc             "@NotNull method %s.%s must not return null"
        //   648: ldc             2
        //   650: anewarray       Ljava/lang/Object;
        //   653: dup            
        //   654: ldc             0
        //   656: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler"
        //   658: aastore        
        //   659: dup            
        //   660: ldc             1
        //   662: ldc             "getInsertText"
        //   664: aastore        
        //   665: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   668: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   671: athrow         
        //   672: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCopyHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   675: athrow         
        //   676: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;>;Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     129    129    133    Ljava/lang/IllegalStateException;
        //  133    171    174    178    Ljava/lang/IllegalStateException;
        //  140    208    208    212    Ljava/lang/IllegalStateException;
        //  303    318    321    325    Ljava/lang/IllegalStateException;
        //  308    338    341    345    Ljava/lang/IllegalStateException;
        //  325    351    351    355    Ljava/lang/IllegalStateException;
        //  537    550    550    554    Ljava/lang/IllegalStateException;
        //  557    584    587    591    Ljava/lang/IllegalStateException;
        //  619    672    672    676    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0325:
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
    
    private static void a(final List<String> list, final String s, final String s2, final OCType ocType, final OCFile ocFile, final DepthMode depthMode, final boolean b) {
        Label_0210: {
            Label_0164: {
                Label_0084: {
                    Label_0064: {
                        Label_0022: {
                            try {
                                if (!ocType.isPointerToObject()) {
                                    break Label_0210;
                                }
                                final DepthMode depthMode2 = depthMode;
                                final DepthMode depthMode3 = DepthMode.DEEP;
                                if (depthMode2 == depthMode3) {
                                    break Label_0022;
                                }
                                break Label_0064;
                            }
                            catch (IllegalStateException ex) {
                                throw a(ex);
                            }
                            try {
                                final DepthMode depthMode2 = depthMode;
                                final DepthMode depthMode3 = DepthMode.DEEP;
                                if (depthMode2 == depthMode3) {
                                    list.add(s + "=[" + s2 + " copy];\n");
                                    return;
                                }
                            }
                            catch (IllegalStateException ex2) {
                                throw a(ex2);
                            }
                        }
                        try {
                            if (b) {
                                break Label_0164;
                            }
                            final OCFile ocFile2 = ocFile;
                            final boolean b2 = OCCompilerFeatures.isArcEnabled((PsiFile)ocFile2);
                            if (b2) {
                                break Label_0084;
                            }
                            break Label_0084;
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                    }
                    try {
                        final OCFile ocFile2 = ocFile;
                        final boolean b2 = OCCompilerFeatures.isArcEnabled((PsiFile)ocFile2);
                        if (b2) {
                            list.add(s + "=" + s2 + ";\n");
                            return;
                        }
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                }
                list.add(s + "=[" + s2 + " retain];\n");
                return;
                try {
                    if (!OCCompilerFeatures.isArcEnabled((PsiFile)ocFile)) {
                        list.add("[" + s + " retain];\n");
                    }
                    return;
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            try {
                if (!b) {
                    list.add(s + "=" + s2 + ";\n");
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        OCGenerateMethodHandler.processStructFields(ocType, (Processor<OCDeclaratorSymbol>)(ocDeclaratorSymbol -> {
            a(list, s + "." + ocDeclaratorSymbol.getName(), s2 + "." + ocDeclaratorSymbol.getName(), ocDeclaratorSymbol.getResolvedType(), ocFile, depthMode, true);
            return true;
        }));
    }
    
    static {
        DEPTH_MODE = new OCEnumComboOption<DepthMode>("Depth", DepthMode.values(), new String[] { "deep", "shallow" });
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    protected enum DepthMode
    {
        DEEP, 
        SHALLOW;
    }
}
