// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import com.intellij.util.ui.UIUtil;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.OCIcons;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Map;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import com.intellij.util.Processor;
import java.util.Collections;
import java.util.Set;
import com.intellij.util.CommonProcessors;
import java.util.HashSet;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementActionContext;
import com.jetbrains.cidr.lang.settings.OCBooleanOption;

public class OCImplementOCMethodsHandler extends OCOverrideImplementMethodsHandlerBase
{
    private static final OCBooleanOption SHOW_OPTIONAL;
    private static final OCBooleanOption SHOW_SYNTHESIZED_ACCESSORS;
    
    protected String getNoMembersMessage(@NotNull final OCOverrideImplementActionContext ocOverrideImplementActionContext) {
        try {
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return ocOverrideImplementActionContext.getParentNameUppercase() + " has no methods to implement";
    }
    
    protected boolean defaultShowOptional() {
        return false;
    }
    
    protected boolean defaultShowSynthesizedAccessors() {
        return false;
    }
    
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCOverrideImplementActionContext ocOverrideImplementActionContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        list.add((Pair<OCOption, Object>)new Pair((Object)OCImplementOCMethodsHandler.SHOW_OPTIONAL, (Object)this.defaultShowOptional()));
        list.add((Pair<OCOption, Object>)new Pair((Object)OCImplementOCMethodsHandler.SHOW_SYNTHESIZED_ACCESSORS, (Object)this.defaultShowSynthesizedAccessors()));
        super.loadOptions(psiFile, editor, (C)ocOverrideImplementActionContext, ocCodeStyleSettings, list);
    }
    
    @NotNull
    @Override
    protected OCOverrideImplementActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        OCOverrideImplementActionContext ocOverrideImplementActionContext;
        try {
            ocOverrideImplementActionContext = new OCOverrideImplementActionContext(ocClassSymbol, ocClassSymbol.getResolvedType(true), psiElement) {
                @NotNull
                @Override
                public Collection<OCMethodSymbol> getMemberCandidates() {
                    final CommonProcessors.CollectProcessor<OCMethodSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCMethodSymbol>() {
                        final /* synthetic */ Set val$names = new HashSet();
                        
                        protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                            if (this.val$names.add(ocMethodSymbol.getName())) {
                                OCOverrideImplementActionContext.this.myAbstractMethods.add(ocMethodSymbol);
                                return true;
                            }
                            return false;
                        }
                    };
                    Collection results = null;
                    Label_0077: {
                        List<OCMethodSymbol> list = null;
                        Label_0042: {
                            try {
                                if (this.getType().getClassSymbol() != null) {
                                    break Label_0077;
                                }
                                list = Collections.emptyList();
                                if (list == null) {
                                    break Label_0042;
                                }
                                return list;
                            }
                            catch (IllegalStateException ex) {
                                throw b(ex);
                            }
                            try {
                                list = Collections.emptyList();
                                if (list == null) {
                                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler$1", "getMemberCandidates"));
                                }
                            }
                            catch (IllegalStateException ex2) {
                                throw b(ex2);
                            }
                        }
                        return list;
                        try {
                            this.getType().processInterfaceMethods(this.getInterfaceSymbol(), null, (Processor<OCMethodSymbol>)collectProcessor, psiElement, true);
                            results = collectProcessor.getResults();
                            if (results == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler$1", "getMemberCandidates"));
                            }
                        }
                        catch (IllegalStateException ex3) {
                            throw b(ex3);
                        }
                    }
                    return (Collection<OCMethodSymbol>)results;
                }
                
                private static IllegalStateException b(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "evaluateActionContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return ocOverrideImplementActionContext;
    }
    
    @NotNull
    protected Collection<OCMethodSymbol> getSelectedCandidates(@NotNull final OCOverrideImplementActionContext ocOverrideImplementActionContext, final Editor editor, @NotNull final PsiFile psiFile, @NotNull final List<OCMethodSymbol> list) {
        try {
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidates", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        if (this.selectAllCandidates()) {
            final ArrayList<OCMethodSymbol> list2 = new ArrayList<OCMethodSymbol>();
            for (final OCMethodSymbol ocMethodSymbol : ocOverrideImplementActionContext.getMemberCandidates()) {
                try {
                    if (ocMethodSymbol.isOptional()) {
                        continue;
                    }
                    list2.add(ocMethodSymbol);
                }
                catch (IllegalArgumentException ex4) {
                    throw c(ex4);
                }
            }
            ArrayList<OCMethodSymbol> list3;
            try {
                list3 = list2;
                if (list3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "getSelectedCandidates"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            return list3;
        }
        Collection<M> selectedCandidates;
        try {
            selectedCandidates = super.getSelectedCandidates((C)ocOverrideImplementActionContext, editor, psiFile, (List<M>)list);
            if (selectedCandidates == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        return (Collection<OCMethodSymbol>)selectedCandidates;
    }
    
    protected boolean selectAllCandidates() {
        return true;
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "Select Methods to Implement";
    }
    
    @Override
    protected String generateMethodText(final OCMethodSymbol ocMethodSymbol, final PsiElement psiElement, final OCOverrideImplementActionContext ocOverrideImplementActionContext) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final OCPropertySymbol generatedFromProperty = ocMethodSymbol.getGeneratedFromProperty();
        HashMap<String, String> hashMap2 = null;
        String s = null;
        String s2 = null;
        Label_0053: {
            Label_0042: {
                try {
                    if (generatedFromProperty == null) {
                        return OCCallableUtil.methodFromTemplate(ocMethodSymbol, "OC Implemented Method Body.m", psiElement, hashMap);
                    }
                    hashMap2 = hashMap;
                    s = "IVAR_IS_AVAILABLE";
                    final OCOverrideImplementActionContext ocOverrideImplementActionContext2 = ocOverrideImplementActionContext;
                    final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
                    final boolean b = ocOverrideImplementActionContext2.isIvarAvailable(ocPropertySymbol);
                    if (b) {
                        break Label_0042;
                    }
                    break Label_0042;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    hashMap2 = hashMap;
                    s = "IVAR_IS_AVAILABLE";
                    final OCOverrideImplementActionContext ocOverrideImplementActionContext2 = ocOverrideImplementActionContext;
                    final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
                    final boolean b = ocOverrideImplementActionContext2.isIvarAvailable(ocPropertySymbol);
                    if (b) {
                        s2 = "true";
                        break Label_0053;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            s2 = "false";
        }
        hashMap2.put(s, s2);
        return OCCallableUtil.methodFromTemplate(ocMethodSymbol, "OC Implemented Method Body.m", psiElement, hashMap);
    }
    
    protected OCMemberChooserObject[] getChooserNodes(final OCMemberChooser p0, final Collection<OCMethodSymbol> p1, final OCOverrideImplementActionContext p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore          5
        //     9: iload           4
        //    11: ifle            32
        //    14: aload_1        
        //    15: getstatic       com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.SHOW_OPTIONAL:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //    18: iconst_1       
        //    19: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    22: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.setOptionSelection:(Lcom/jetbrains/cidr/lang/settings/OCOption;Ljava/lang/Object;)V
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: iload           4
        //    34: iconst_1       
        //    35: if_icmple       56
        //    38: aload_1        
        //    39: getstatic       com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.SHOW_SYNTHESIZED_ACCESSORS:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //    42: iconst_1       
        //    43: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    46: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.setOptionSelection:(Lcom/jetbrains/cidr/lang/settings/OCOption;Ljava/lang/Object;)V
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_1        
        //    57: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.getOptionSelections:()Ljava/util/Map;
        //    60: getstatic       com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.SHOW_OPTIONAL:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //    63: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.getOption:(Ljava/util/Map;Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljava/lang/Object;
        //    66: checkcast       Ljava/lang/Boolean;
        //    69: astore          6
        //    71: aload_1        
        //    72: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.getOptionSelections:()Ljava/util/Map;
        //    75: getstatic       com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.SHOW_SYNTHESIZED_ACCESSORS:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //    78: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.getOption:(Ljava/util/Map;Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljava/lang/Object;
        //    81: checkcast       Ljava/lang/Boolean;
        //    84: astore          7
        //    86: aload_2        
        //    87: invokeinterface java/util/Collection.isEmpty:()Z
        //    92: ifeq            110
        //    95: aload_0        
        //    96: aload_1        
        //    97: aload           5
        //    99: aload_3        
        //   100: iload           4
        //   102: invokespecial   com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementMethodsHandlerBase.getChooserNodes:(Lcom/jetbrains/cidr/lang/generate/OCMemberChooser;Ljava/util/Collection;Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;I)[Lcom/jetbrains/cidr/lang/generate/OCMemberChooserObject;
        //   105: areturn        
        //   106: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_2        
        //   111: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   116: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   121: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   124: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   129: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   132: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getImplementation:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   137: astore          8
        //   139: new             Ljava/util/HashSet;
        //   142: dup            
        //   143: invokespecial   java/util/HashSet.<init>:()V
        //   146: astore          9
        //   148: aload           8
        //   150: ifnull          175
        //   153: aload           8
        //   155: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCSynthesizeSymbol;.class
        //   157: aload           9
        //   159: invokedynamic   process:(Ljava/util/Set;)Lcom/intellij/util/Processor;
        //   164: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.processMembers:(Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //   167: pop            
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: iconst_0       
        //   176: istore          10
        //   178: iconst_0       
        //   179: istore          11
        //   181: aload_2        
        //   182: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   187: astore          12
        //   189: aload           12
        //   191: invokeinterface java/util/Iterator.hasNext:()Z
        //   196: ifeq            362
        //   199: aload           12
        //   201: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   206: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   209: astore          13
        //   211: aload           13
        //   213: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   218: astore          14
        //   220: aload           13
        //   222: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isOptional:()Z
        //   227: ifeq            255
        //   230: iinc            10, 1
        //   233: aload           6
        //   235: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   238: ifne            255
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: goto            189
        //   251: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: aload           14
        //   257: ifnull          349
        //   260: aload           9
        //   262: aload           14
        //   264: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getName:()Ljava/lang/String;
        //   269: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   274: ifne            304
        //   277: goto            284
        //   280: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   283: athrow         
        //   284: aload           14
        //   286: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   291: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsAutosynthesis:(Lcom/intellij/psi/PsiFile;)Z
        //   294: ifeq            349
        //   297: goto            304
        //   300: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: aload           14
        //   306: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   311: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //   314: ifne            349
        //   317: goto            324
        //   320: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   323: athrow         
        //   324: iinc            11, 1
        //   327: aload           7
        //   329: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   332: ifne            349
        //   335: goto            342
        //   338: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   341: athrow         
        //   342: goto            189
        //   345: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   348: athrow         
        //   349: aload           5
        //   351: aload           13
        //   353: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   358: pop            
        //   359: goto            189
        //   362: aload_1        
        //   363: getstatic       com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.SHOW_OPTIONAL:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //   366: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.getOptionComponent:(Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljavax/swing/JComponent;
        //   369: checkcast       Ljavax/swing/JCheckBox;
        //   372: iload           10
        //   374: ifne            385
        //   377: iconst_1       
        //   378: goto            386
        //   381: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   384: athrow         
        //   385: iconst_0       
        //   386: ldc             "override.implement.show.no.optional.members"
        //   388: iload           10
        //   390: aload_2        
        //   391: invokeinterface java/util/Collection.size:()I
        //   396: if_icmpne       407
        //   399: iconst_1       
        //   400: goto            408
        //   403: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   406: athrow         
        //   407: iconst_0       
        //   408: ldc             "override.implement.show.only.optional.members"
        //   410: invokestatic    com/jetbrains/cidr/lang/settings/OCBooleanOption.setStates:(Ljavax/swing/JCheckBox;ZLjava/lang/String;ZLjava/lang/String;)V
        //   413: aload_1        
        //   414: getstatic       com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.SHOW_SYNTHESIZED_ACCESSORS:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //   417: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.getOptionComponent:(Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljavax/swing/JComponent;
        //   420: checkcast       Ljavax/swing/JCheckBox;
        //   423: iload           11
        //   425: ifne            436
        //   428: iconst_1       
        //   429: goto            437
        //   432: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   435: athrow         
        //   436: iconst_0       
        //   437: ldc             "override.implement.show.no.synthesized.accessors"
        //   439: iload           11
        //   441: aload_2        
        //   442: invokeinterface java/util/Collection.size:()I
        //   447: if_icmpne       458
        //   450: iconst_1       
        //   451: goto            459
        //   454: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   457: athrow         
        //   458: iconst_0       
        //   459: ldc             "override.implement.show.only.synthesized.accessors"
        //   461: invokestatic    com/jetbrains/cidr/lang/settings/OCBooleanOption.setStates:(Ljavax/swing/JCheckBox;ZLjava/lang/String;ZLjava/lang/String;)V
        //   464: aload_3        
        //   465: aload_2        
        //   466: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.createParentsMap:(Ljava/util/Collection;)Ljava/util/Map;
        //   469: astore          12
        //   471: aload           5
        //   473: ldc             Lcom/jetbrains/cidr/lang/generate/OCMemberChooserObject;.class
        //   475: aload_3        
        //   476: aload           12
        //   478: invokedynamic   fun:(Lcom/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext;Ljava/util/Map;)Lcom/intellij/util/Function;
        //   483: invokestatic    com/intellij/util/containers/ContainerUtil.map2Array:(Ljava/util/Collection;Ljava/lang/Class;Lcom/intellij/util/Function;)[Ljava/lang/Object;
        //   486: checkcast       [Lcom/jetbrains/cidr/lang/generate/OCMemberChooserObject;
        //   489: astore          13
        //   491: aload           13
        //   493: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/generate/OCMemberChooser;Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;>;Lcom/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext;I)[Lcom/jetbrains/cidr/lang/generate/OCMemberChooserObject;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  9      25     28     32     Ljava/lang/IllegalArgumentException;
        //  32     49     52     56     Ljava/lang/IllegalArgumentException;
        //  86     106    106    110    Ljava/lang/IllegalArgumentException;
        //  148    168    171    175    Ljava/lang/IllegalArgumentException;
        //  220    241    244    248    Ljava/lang/IllegalArgumentException;
        //  230    251    251    255    Ljava/lang/IllegalArgumentException;
        //  255    277    280    284    Ljava/lang/IllegalArgumentException;
        //  260    297    300    304    Ljava/lang/IllegalArgumentException;
        //  284    317    320    324    Ljava/lang/IllegalArgumentException;
        //  304    335    338    342    Ljava/lang/IllegalArgumentException;
        //  324    345    345    349    Ljava/lang/IllegalArgumentException;
        //  362    381    381    385    Ljava/lang/IllegalArgumentException;
        //  386    403    403    407    Ljava/lang/IllegalArgumentException;
        //  408    432    432    436    Ljava/lang/IllegalArgumentException;
        //  437    454    454    458    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0284:
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
    
    protected void optionValueChanged(final OCMemberChooser ocMemberChooser, final Collection<OCMethodSymbol> collection, final OCOption ocOption, final OCOverrideImplementActionContext ocOverrideImplementActionContext) {
        Label_0027: {
            try {
                if (OCImplementOCMethodsHandler.SHOW_OPTIONAL.equals(ocOption)) {
                    break Label_0027;
                }
                final OCBooleanOption ocBooleanOption = OCImplementOCMethodsHandler.SHOW_SYNTHESIZED_ACCESSORS;
                final OCOption ocOption2 = ocOption;
                final boolean b = ocBooleanOption.equals(ocOption2);
                if (b) {
                    break Label_0027;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCBooleanOption ocBooleanOption = OCImplementOCMethodsHandler.SHOW_SYNTHESIZED_ACCESSORS;
                final OCOption ocOption2 = ocOption;
                final boolean b = ocBooleanOption.equals(ocOption2);
                if (b) {
                    ocMemberChooser.resetElements(this.getChooserNodes(ocMemberChooser, collection, ocOverrideImplementActionContext, 0));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
    }
    
    @Override
    protected void performAction(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final OCOverrideImplementActionContext ocOverrideImplementActionContext, @NotNull final List<OCMethodSymbol> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        try {
            if (ocOverrideImplementActionContext.evaluateAvailableIvars(psiFile, list, this.getActionTitle())) {
                super.performAction(project, editor, psiFile, ocOverrideImplementActionContext, list);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
    }
    
    static {
        SHOW_OPTIONAL = new OCBooleanOption(UIUtil.removeMnemonic(OCBundle.message("override.implement.show.optional.members", new Object[0])));
        SHOW_SYNTHESIZED_ACCESSORS = new OCBooleanOption(OCBundle.message("override.implement.show.synthesized.accessors", new Object[0]));
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
