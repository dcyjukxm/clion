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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ChangePrompt_BroadcastOrBuilder
{
    private int bitField0_;
    private Object prompt_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$5500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$5600().ensureFieldAccessorsInitialized((Class)ChangePrompt_Broadcast.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.prompt_ = "";
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.prompt_ = "";
        this.a();
    }
    
    private void a() {
        if (ChangePrompt_Broadcast.access$6000()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.prompt_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Broadcasts.access$5500();
    }
    
    public ChangePrompt_Broadcast getDefaultInstanceForType() {
        return ChangePrompt_Broadcast.getDefaultInstance();
    }
    
    public ChangePrompt_Broadcast build() {
        final ChangePrompt_Broadcast buildPartial = this.buildPartial();
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
    
    public ChangePrompt_Broadcast buildPartial() {
        final ChangePrompt_Broadcast changePrompt_Broadcast = new ChangePrompt_Broadcast((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        ChangePrompt_Broadcast.access$6202(changePrompt_Broadcast, this.prompt_);
        ChangePrompt_Broadcast.access$6302(changePrompt_Broadcast, n);
        this.onBuilt();
        return changePrompt_Broadcast;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof ChangePrompt_Broadcast) {
                return this.mergeFrom((ChangePrompt_Broadcast)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ChangePrompt_Broadcast changePrompt_Broadcast) {
        try {
            if (changePrompt_Broadcast == ChangePrompt_Broadcast.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (changePrompt_Broadcast.hasPrompt()) {
                this.bitField0_ |= 0x1;
                this.prompt_ = ChangePrompt_Broadcast.access$6200(changePrompt_Broadcast);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        this.mergeUnknownFields(changePrompt_Broadcast.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasPrompt()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ChangePrompt_Broadcast changePrompt_Broadcast = null;
        try {
            changePrompt_Broadcast = (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            changePrompt_Broadcast = (ChangePrompt_Broadcast)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (changePrompt_Broadcast != null) {
                    this.mergeFrom(changePrompt_Broadcast);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasPrompt() {
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
    
    public String getPrompt() {
        final Object prompt_ = this.prompt_;
        if (!(prompt_ instanceof String)) {
            return (String)(this.prompt_ = ((ByteString)prompt_).toStringUtf8());
        }
        return (String)prompt_;
    }
    
    public ByteString getPromptBytes() {
        final Object prompt_ = this.prompt_;
        if (prompt_ instanceof String) {
            return (ByteString)(this.prompt_ = ByteString.copyFromUtf8((String)prompt_));
        }
        return (ByteString)prompt_;
    }
    
    public Builder setPrompt(final String prompt_) {
        try {
            if (prompt_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.prompt_ = prompt_;
        this.onChanged();
        return this;
    }
    
    public Builder clearPrompt() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.prompt_ = ChangePrompt_Broadcast.getDefaultInstance().getPrompt();
        this.onChanged();
        return this;
    }
    
    public Builder setPromptBytes(final ByteString prompt_) {
        try {
            if (prompt_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.prompt_ = prompt_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
