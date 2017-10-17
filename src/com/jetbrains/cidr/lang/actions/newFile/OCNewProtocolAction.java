// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileType;
import icons.CidrLangIcons;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.OCFileType;

public class OCNewProtocolAction extends OCNewFileActionBase
{
    private static final String PROTOCOL_TEMPLATE_NAME = "Objective-C Protocol File.h";
    
    public OCNewProtocolAction() {
        super((FileType)OCFileType.INSTANCE, OCBundle.message("create.protocol.title", new Object[0]), OCBundle.message("create.protocol.description", new Object[0]), CidrLangIcons.CodeAssistantProtocol);
    }
    
    @NotNull
    @Override
    protected String getDefaultName() {
        String defaultClassPrefix;
        try {
            defaultClassPrefix = this.getDefaultClassPrefix();
            if (defaultClassPrefix == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewProtocolAction", "getDefaultName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return defaultClassPrefix;
    }
    
    @Override
    protected String getFileTemplate(final String s) {
        return "Objective-C Protocol File.h";
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s) {
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(null, s + ".h");
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewProtocolAction", "getNewFileNames"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return newFileNames;
    }
    
    @NotNull
    @Override
    protected CreateFileDialogBase createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/actions/newFile/OCNewProtocolAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        CreateProtocolDialog createProtocolDialog;
        try {
            createProtocolDialog = new CreateProtocolDialog(s);
            if (createProtocolDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewProtocolAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return createProtocolDialog;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    protected class CreateProtocolDialog extends CreateFileDialogBase
    {
        public CreateProtocolDialog(final String s) {
            super(OCBundle.message("create.protocol.dialog.title", new Object[0]), s, null);
        }
    }
}
