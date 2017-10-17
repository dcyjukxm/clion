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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements TargetProcessOutput_BroadcastOrBuilder
{
    private int bitField0_;
    private Object text_;
    private Model.OutputType outputType_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$2700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$2800().ensureFieldAccessorsInitialized((Class)TargetProcessOutput_Broadcast.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.text_ = "";
        this.outputType_ = Model.OutputType.OutputTypeStdout;
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.text_ = "";
        this.outputType_ = Model.OutputType.OutputTypeStdout;
        this.a();
    }
    
    private void a() {
        if (TargetProcessOutput_Broadcast.access$3200()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.text_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.outputType_ = Model.OutputType.OutputTypeStdout;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Broadcasts.access$2700();
    }
    
    public TargetProcessOutput_Broadcast getDefaultInstanceForType() {
        return TargetProcessOutput_Broadcast.getDefaultInstance();
    }
    
    public TargetProcessOutput_Broadcast build() {
        final TargetProcessOutput_Broadcast buildPartial = this.buildPartial();
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
    
    public TargetProcessOutput_Broadcast buildPartial() {
        final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast = new TargetProcessOutput_Broadcast((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        TargetProcessOutput_Broadcast.access$3402(targetProcessOutput_Broadcast, this.text_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        TargetProcessOutput_Broadcast.access$3502(targetProcessOutput_Broadcast, this.outputType_);
        TargetProcessOutput_Broadcast.access$3602(targetProcessOutput_Broadcast, n);
        this.onBuilt();
        return targetProcessOutput_Broadcast;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof TargetProcessOutput_Broadcast) {
                return this.mergeFrom((TargetProcessOutput_Broadcast)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast) {
        try {
            if (targetProcessOutput_Broadcast == TargetProcessOutput_Broadcast.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (targetProcessOutput_Broadcast.hasText()) {
                this.bitField0_ |= 0x1;
                this.text_ = TargetProcessOutput_Broadcast.access$3400(targetProcessOutput_Broadcast);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (targetProcessOutput_Broadcast.hasOutputType()) {
                this.setOutputType(targetProcessOutput_Broadcast.getOutputType());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(targetProcessOutput_Broadcast.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasText()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasOutputType()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        TargetProcessOutput_Broadcast targetProcessOutput_Broadcast = null;
        try {
            targetProcessOutput_Broadcast = (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            targetProcessOutput_Broadcast = (TargetProcessOutput_Broadcast)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (targetProcessOutput_Broadcast != null) {
                    this.mergeFrom(targetProcessOutput_Broadcast);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasText() {
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
    
    public String getText() {
        final Object text_ = this.text_;
        if (!(text_ instanceof String)) {
            return (String)(this.text_ = ((ByteString)text_).toStringUtf8());
        }
        return (String)text_;
    }
    
    public ByteString getTextBytes() {
        final Object text_ = this.text_;
        if (text_ instanceof String) {
            return (ByteString)(this.text_ = ByteString.copyFromUtf8((String)text_));
        }
        return (ByteString)text_;
    }
    
    public Builder setText(final String text_) {
        try {
            if (text_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.text_ = text_;
        this.onChanged();
        return this;
    }
    
    public Builder clearText() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.text_ = TargetProcessOutput_Broadcast.getDefaultInstance().getText();
        this.onChanged();
        return this;
    }
    
    public Builder setTextBytes(final ByteString text_) {
        try {
            if (text_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.text_ = text_;
        this.onChanged();
        return this;
    }
    
    public boolean hasOutputType() {
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
    
    public Model.OutputType getOutputType() {
        return this.outputType_;
    }
    
    public Builder setOutputType(final Model.OutputType outputType_) {
        try {
            if (outputType_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.outputType_ = outputType_;
        this.onChanged();
        return this;
    }
    
    public Builder clearOutputType() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.outputType_ = Model.OutputType.OutputTypeStdout;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
