// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.OCLog;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.PlatformUtils;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.Stack;
import java.util.Map;
import java.util.List;
import com.intellij.util.NotNullProducer;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.codeInspection.InspectionToolProvider;

public class OCInspectionToolProvider implements InspectionToolProvider
{
    private static final ExtensionPointName<NotNullProducer<List<Class>>> STANDALONE_INSPECTIONS_PROVIDER;
    private List<Class> myAllInspectionClasses;
    private Map<String, Class> myNameToInspectionMap;
    private Map<Class, String> myFakeInspectionsToNameMap;
    private Map<String, Class> myNameToFakeInspectionsMap;
    private Stack<Class> myFakeInspectionsPool;
    private static OCInspectionToolProvider instance;
    
    private OCInspectionToolProvider() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: new             Ljava/util/ArrayList;
        //     8: dup            
        //     9: invokespecial   java/util/ArrayList.<init>:()V
        //    12: putfield        com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.myAllInspectionClasses:Ljava/util/List;
        //    15: aload_0        
        //    16: new             Lcom/intellij/util/containers/HashMap;
        //    19: dup            
        //    20: invokespecial   com/intellij/util/containers/HashMap.<init>:()V
        //    23: putfield        com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.myNameToInspectionMap:Ljava/util/Map;
        //    26: aload_0        
        //    27: new             Lcom/intellij/util/containers/HashMap;
        //    30: dup            
        //    31: invokespecial   com/intellij/util/containers/HashMap.<init>:()V
        //    34: putfield        com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.myFakeInspectionsToNameMap:Ljava/util/Map;
        //    37: aload_0        
        //    38: new             Lcom/intellij/util/containers/HashMap;
        //    41: dup            
        //    42: invokespecial   com/intellij/util/containers/HashMap.<init>:()V
        //    45: putfield        com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.myNameToFakeInspectionsMap:Ljava/util/Map;
        //    48: aload_0        
        //    49: new             Lcom/intellij/util/containers/Stack;
        //    52: dup            
        //    53: invokespecial   com/intellij/util/containers/Stack.<init>:()V
        //    56: putfield        com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.myFakeInspectionsPool:Lcom/intellij/util/containers/Stack;
        //    59: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections;.class
        //    61: invokevirtual   java/lang/Class.getDeclaredClasses:()[Ljava/lang/Class;
        //    64: astore_1       
        //    65: aload_1        
        //    66: arraylength    
        //    67: istore_2       
        //    68: iconst_0       
        //    69: istore_3       
        //    70: iload_3        
        //    71: iload_2        
        //    72: if_icmpge       227
        //    75: aload_1        
        //    76: iload_3        
        //    77: aaload         
        //    78: astore          4
        //    80: aload           4
        //    82: invokevirtual   java/lang/Class.getModifiers:()I
        //    85: invokestatic    java/lang/reflect/Modifier.isPublic:(I)Z
        //    88: ifeq            221
        //    91: aload           4
        //    93: invokevirtual   java/lang/Class.getModifiers:()I
        //    96: invokestatic    java/lang/reflect/Modifier.isAbstract:(I)Z
        //    99: ifne            221
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   108: athrow         
        //   109: invokestatic    com/intellij/util/PlatformUtils.isAppCode:()Z
        //   112: ifne            138
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   121: athrow         
        //   122: aload_0        
        //   123: aload           4
        //   125: invokevirtual   com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.isObjCInspection:(Ljava/lang/Class;)Z
        //   128: ifne            221
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   137: athrow         
        //   138: aload_0        
        //   139: getfield        com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.myAllInspectionClasses:Ljava/util/List;
        //   142: aload           4
        //   144: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   149: pop            
        //   150: aload_0        
        //   151: aload           4
        //   153: invokevirtual   com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.isClangAnalyzerInspection:(Ljava/lang/Class;)Z
        //   156: ifeq            172
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.myFakeInspectionsPool:Lcom/intellij/util/containers/Stack;
        //   163: aload           4
        //   165: invokevirtual   com/intellij/util/containers/Stack.add:(Ljava/lang/Object;)Z
        //   168: pop            
        //   169: goto            195
        //   172: aload_0        
        //   173: getfield        com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.myNameToInspectionMap:Ljava/util/Map;
        //   176: aload           4
        //   178: invokevirtual   java/lang/Class.newInstance:()Ljava/lang/Object;
        //   181: checkcast       Lcom/jetbrains/cidr/lang/inspections/OCInspection;
        //   184: invokevirtual   com/jetbrains/cidr/lang/inspections/OCInspection.getDisplayName:()Ljava/lang/String;
        //   187: aload           4
        //   189: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   194: pop            
        //   195: goto            221
        //   198: astore          5
        //   200: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   203: aload           5
        //   205: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/Throwable;)V
        //   208: goto            221
        //   211: astore          5
        //   213: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   216: aload           5
        //   218: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/Throwable;)V
        //   221: iinc            3, 1
        //   224: goto            70
        //   227: aload_0        
        //   228: getfield        com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.myAllInspectionClasses:Ljava/util/List;
        //   231: aload_0        
        //   232: invokevirtual   com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.getStandaloneInspectionClasses:()[Ljava/lang/Class;
        //   235: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   238: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   243: pop            
        //   244: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  109    131    134    138    Ljava/lang/InstantiationException;
        //  91     115    118    122    Ljava/lang/InstantiationException;
        //  80     102    105    109    Ljava/lang/InstantiationException;
        //  150    195    198    211    Ljava/lang/InstantiationException;
        //  150    195    211    221    Ljava/lang/IllegalAccessException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public static OCInspectionToolProvider getInstance() {
        try {
            if (OCInspectionToolProvider.instance == null) {
                OCInspectionToolProvider.instance = new OCInspectionToolProvider();
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return OCInspectionToolProvider.instance;
    }
    
    @NotNull
    public Class[] getInspectionClasses() {
        Class[] array;
        try {
            array = this.myAllInspectionClasses.toArray(new Class[this.myAllInspectionClasses.size()]);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider", "getInspectionClasses"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return array;
    }
    
    public Class[] getStandaloneInspectionClasses() {
        final ArrayList<Object> list = new ArrayList<Object>();
        Label_0147: {
            Label_0089: {
                try {
                    list.addAll(Arrays.asList(OCGlobalUnusedInspection.class, OCUnusedGlobalDeclarationInspection.class, OCUnusedStructInspection.class, OCUnusedTemplateParameterInspection.class, OCUnusedMacroInspection.class, OCLoopDoesntUseConditionVariableInspection.class, OCSimplifyInspection.class, OCDFAInspection.class));
                    if (PlatformUtils.isAppCode()) {
                        break Label_0089;
                    }
                    final Application application = ApplicationManager.getApplication();
                    final boolean b = application.isUnitTestMode();
                    if (b) {
                        break Label_0089;
                    }
                    break Label_0147;
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                try {
                    final Application application = ApplicationManager.getApplication();
                    final boolean b = application.isUnitTestMode();
                    if (b) {
                        list.addAll(Arrays.asList(OCUnusedMethodInspection.class, OCUnusedClassInspection.class, OCUnusedInstanceVariableInspection.class, OCUnusedPropertyInspection.class, OCNotReleasedIvarInspection.class, OCNotLocalizedStringInspection.class, OCLegacyObjCLiteralInspection.class));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
            try {
                if (PlatformUtils.isCLion()) {
                    list.add(ClangTidyInspection.class);
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        final NotNullProducer[] array = (NotNullProducer[])Extensions.getExtensions((ExtensionPointName)OCInspectionToolProvider.STANDALONE_INSPECTIONS_PROVIDER);
        for (int length = array.length, i = 0; i < length; ++i) {
            list.addAll((Collection<?>)array[i].produce());
        }
        return list.toArray(new Class[list.size()]);
    }
    
    public boolean isObjCInspection(final Class clazz) {
        return OCInspection.ObjC.class.isAssignableFrom(clazz);
    }
    
    public boolean isClangCompilerInspection(final Class clazz) {
        return OCInspections.ClangCompilerIssues.class.equals(clazz.getSuperclass());
    }
    
    public boolean isClangAnalyzerInspection(final Class clazz) {
        return OCInspections.ClangAnalyzerIssue.class.equals(clazz.getSuperclass());
    }
    
    @Nullable
    public Class getInspectionByName(final String s) {
        return this.myNameToInspectionMap.get(s);
    }
    
    @Nullable
    public synchronized Class getFakeInspection(final String s) {
        final Class clazz = this.myNameToFakeInspectionsMap.get(s);
        try {
            if (clazz != null) {
                return clazz;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final Class clazz2 = (Class)this.myFakeInspectionsPool.pop();
        try {
            if (clazz2 != null) {
                this.myFakeInspectionsToNameMap.put(clazz2, s);
                this.myNameToFakeInspectionsMap.put(s, clazz2);
                return clazz2;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        OCLog.LOG.error("Too few fake inspections");
        return null;
    }
    
    @Nullable
    public synchronized String getFakeInspectionName(final Class clazz) {
        return this.myFakeInspectionsToNameMap.get(clazz);
    }
    
    static {
        STANDALONE_INSPECTIONS_PROVIDER = ExtensionPointName.create("cidr.lang.standaloneInspectionToolProvider");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
