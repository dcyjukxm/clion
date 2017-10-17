// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBFrameOrBuilder
{
    private int bitField0_;
    private int number_;
    private Object function_;
    private Object file_;
    private int line_;
    private long pc_;
    private Language language_;
    private boolean optimized_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Model.access$5800();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$5900().ensureFieldAccessorsInitialized((Class)LLDBFrame.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.function_ = "";
        this.file_ = "";
        this.language_ = Language.LanguageUnknown;
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.function_ = "";
        this.file_ = "";
        this.language_ = Language.LanguageUnknown;
        this.a();
    }
    
    private void a() {
        if (LLDBFrame.access$6300()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.number_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.function_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.file_ = "";
        this.bitField0_ &= 0xFFFFFFFB;
        this.line_ = 0;
        this.bitField0_ &= 0xFFFFFFF7;
        this.pc_ = 0L;
        this.bitField0_ &= 0xFFFFFFEF;
        this.language_ = Language.LanguageUnknown;
        this.bitField0_ &= 0xFFFFFFDF;
        this.optimized_ = false;
        this.bitField0_ &= 0xFFFFFFBF;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Model.access$5800();
    }
    
    public LLDBFrame getDefaultInstanceForType() {
        return LLDBFrame.getDefaultInstance();
    }
    
    public LLDBFrame build() {
        final LLDBFrame buildPartial = this.buildPartial();
        try {
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return buildPartial;
    }
    
    public LLDBFrame buildPartial() {
        final LLDBFrame lldbFrame = new LLDBFrame((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        LLDBFrame.access$6502(lldbFrame, this.number_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        LLDBFrame.access$6602(lldbFrame, this.function_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        LLDBFrame.access$6702(lldbFrame, this.file_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        LLDBFrame.access$6802(lldbFrame, this.line_);
        if ((bitField0_ & 0x10) == 0x10) {
            n |= 0x10;
        }
        LLDBFrame.access$6902(lldbFrame, this.pc_);
        if ((bitField0_ & 0x20) == 0x20) {
            n |= 0x20;
        }
        LLDBFrame.access$7002(lldbFrame, this.language_);
        if ((bitField0_ & 0x40) == 0x40) {
            n |= 0x40;
        }
        LLDBFrame.access$7102(lldbFrame, this.optimized_);
        LLDBFrame.access$7202(lldbFrame, n);
        this.onBuilt();
        return lldbFrame;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof LLDBFrame) {
                return this.mergeFrom((LLDBFrame)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final LLDBFrame lldbFrame) {
        try {
            if (lldbFrame == LLDBFrame.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (lldbFrame.hasNumber()) {
                this.setNumber(lldbFrame.getNumber());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (lldbFrame.hasFunction()) {
                this.bitField0_ |= 0x2;
                this.function_ = LLDBFrame.access$6600(lldbFrame);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (lldbFrame.hasFile()) {
                this.bitField0_ |= 0x4;
                this.file_ = LLDBFrame.access$6700(lldbFrame);
                this.onChanged();
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (lldbFrame.hasLine()) {
                this.setLine(lldbFrame.getLine());
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        try {
            if (lldbFrame.hasPc()) {
                this.setPc(lldbFrame.getPc());
            }
        }
        catch (NullPointerException ex6) {
            throw b(ex6);
        }
        try {
            if (lldbFrame.hasLanguage()) {
                this.setLanguage(lldbFrame.getLanguage());
            }
        }
        catch (NullPointerException ex7) {
            throw b(ex7);
        }
        try {
            if (lldbFrame.hasOptimized()) {
                this.setOptimized(lldbFrame.getOptimized());
            }
        }
        catch (NullPointerException ex8) {
            throw b(ex8);
        }
        this.mergeUnknownFields(lldbFrame.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasNumber()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        LLDBFrame lldbFrame = null;
        try {
            lldbFrame = (LLDBFrame)LLDBFrame.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            lldbFrame = (LLDBFrame)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (lldbFrame != null) {
                    this.mergeFrom(lldbFrame);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasNumber() {
        try {
            if ((this.bitField0_ & 0x1) == 0x1) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public int getNumber() {
        return this.number_;
    }
    
    public Builder setNumber(final int number_) {
        this.bitField0_ |= 0x1;
        this.number_ = number_;
        this.onChanged();
        return this;
    }
    
    public Builder clearNumber() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.number_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasFunction() {
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getFunction() {
        final Object function_ = this.function_;
        if (!(function_ instanceof String)) {
            return (String)(this.function_ = ((ByteString)function_).toStringUtf8());
        }
        return (String)function_;
    }
    
    public ByteString getFunctionBytes() {
        final Object function_ = this.function_;
        if (function_ instanceof String) {
            return (ByteString)(this.function_ = ByteString.copyFromUtf8((String)function_));
        }
        return (ByteString)function_;
    }
    
    public Builder setFunction(final String function_) {
        try {
            if (function_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.function_ = function_;
        this.onChanged();
        return this;
    }
    
    public Builder clearFunction() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.function_ = LLDBFrame.getDefaultInstance().getFunction();
        this.onChanged();
        return this;
    }
    
    public Builder setFunctionBytes(final ByteString function_) {
        try {
            if (function_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.function_ = function_;
        this.onChanged();
        return this;
    }
    
    public boolean hasFile() {
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getFile() {
        final Object file_ = this.file_;
        if (!(file_ instanceof String)) {
            return (String)(this.file_ = ((ByteString)file_).toStringUtf8());
        }
        return (String)file_;
    }
    
    public ByteString getFileBytes() {
        final Object file_ = this.file_;
        if (file_ instanceof String) {
            return (ByteString)(this.file_ = ByteString.copyFromUtf8((String)file_));
        }
        return (ByteString)file_;
    }
    
    public Builder setFile(final String file_) {
        try {
            if (file_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.file_ = file_;
        this.onChanged();
        return this;
    }
    
    public Builder clearFile() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.file_ = LLDBFrame.getDefaultInstance().getFile();
        this.onChanged();
        return this;
    }
    
    public Builder setFileBytes(final ByteString file_) {
        try {
            if (file_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.file_ = file_;
        this.onChanged();
        return this;
    }
    
    public boolean hasLine() {
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public int getLine() {
        return this.line_;
    }
    
    public Builder setLine(final int line_) {
        this.bitField0_ |= 0x8;
        this.line_ = line_;
        this.onChanged();
        return this;
    }
    
    public Builder clearLine() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.line_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasPc() {
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public long getPc() {
        return this.pc_;
    }
    
    public Builder setPc(final long pc_) {
        this.bitField0_ |= 0x10;
        this.pc_ = pc_;
        this.onChanged();
        return this;
    }
    
    public Builder clearPc() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.pc_ = 0L;
        this.onChanged();
        return this;
    }
    
    public boolean hasLanguage() {
        try {
            if ((this.bitField0_ & 0x20) == 0x20) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public Language getLanguage() {
        return this.language_;
    }
    
    public Builder setLanguage(final Language language_) {
        try {
            if (language_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x20;
        this.language_ = language_;
        this.onChanged();
        return this;
    }
    
    public Builder clearLanguage() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.language_ = Language.LanguageUnknown;
        this.onChanged();
        return this;
    }
    
    public boolean hasOptimized() {
        try {
            if ((this.bitField0_ & 0x40) == 0x40) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean getOptimized() {
        return this.optimized_;
    }
    
    public Builder setOptimized(final boolean optimized_) {
        this.bitField0_ |= 0x40;
        this.optimized_ = optimized_;
        this.onChanged();
        return this;
    }
    
    public Builder clearOptimized() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.optimized_ = false;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
