// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileType;
import icons.CidrLangIcons;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.OCFileType;

public class OCNewHeaderFileAction extends OCNewFileWithSelectedExtensionAction<CreateFileDialog>
{
    public OCNewHeaderFileAction() {
        super((FileType)OCFileType.INSTANCE, OCBundle.message("create.cpp.header.title", new Object[0]), OCBundle.message("create.cpp.header.title", new Object[0]), CidrLangIcons.FileType_h);
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s) {
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(null, s + "." + ((CreateFileDialog)this.myState.dialogPeer).myFileExtensionPair.myHeaderExt);
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction", "getNewFileNames"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return newFileNames;
    }
    
    @Nullable
    @Override
    protected String getFileTemplate(final String s) {
        return headerTemplate();
    }
    
    @NotNull
    public static String headerTemplate() {
        String s;
        try {
            s = "C Header File.h";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction", "headerTemplate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isValidName(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: aload_1        
        //     8: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    11: ldc             "."
        //    13: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    16: aload_0        
        //    17: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //    20: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState.dialogPeer:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase;
        //    23: checkcast       Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog;
        //    26: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.myFileExtensionPair:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;
        //    29: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair.myHeaderExt:Ljava/lang/String;
        //    32: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    35: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    38: astore_2       
        //    39: aload_0        
        //    40: aload_2        
        //    41: invokespecial   com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.isValidName:(Ljava/lang/String;)Z
        //    44: ifeq            86
        //    47: aload_2        
        //    48: invokestatic    com/intellij/openapi/util/io/FileUtilRt.getExtension:(Ljava/lang/String;)Ljava/lang/String;
        //    51: invokevirtual   java/lang/String.isEmpty:()Z
        //    54: ifne            86
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    63: athrow         
        //    64: aload_2        
        //    65: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl.isHeaderFile:(Ljava/lang/String;)Z
        //    68: ifeq            86
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    77: athrow         
        //    78: iconst_1       
        //    79: goto            87
        //    82: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    85: athrow         
        //    86: iconst_0       
        //    87: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  39     57     60     64     Ljava/lang/IllegalStateException;
        //  47     71     74     78     Ljava/lang/IllegalStateException;
        //  64     82     82     86     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0064:
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
    
    @NotNull
    @Override
    protected CreateFileDialog createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        MyCreateFileDialog myCreateFileDialog;
        try {
            myCreateFileDialog = new MyCreateFileDialog(s);
            if (myCreateFileDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return myCreateFileDialog;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    private class MyCreateFileDialog extends CreateFileDialog
    {
        public MyCreateFileDialog(final String s) {
            super(s);
        }
        
        @NotNull
        @Override
        protected String getPresentableName(@NotNull final OCCodeStyleSettings.FileExtensionPair fileExtensionPair) {
            try {
                if (fileExtensionPair == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction$MyCreateFileDialog", "getPresentableName"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            String string;
            try {
                string = "." + fileExtensionPair.myHeaderExt;
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction$MyCreateFileDialog", "getPresentableName"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            return string;
        }
        
        private static IllegalArgumentException c(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
