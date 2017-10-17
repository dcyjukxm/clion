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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements DispatchInput_ReqOrBuilder
{
    private int bitField0_;
    private Object input_;
    private Model.DispatchTarget target_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$48300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$48400().ensureFieldAccessorsInitialized((Class)DispatchInput_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.input_ = "";
        this.target_ = Model.DispatchTarget.DispatchTargetProcess;
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.input_ = "";
        this.target_ = Model.DispatchTarget.DispatchTargetProcess;
        this.a();
    }
    
    private void a() {
        if (DispatchInput_Req.access$48800()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.input_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.target_ = Model.DispatchTarget.DispatchTargetProcess;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$48300();
    }
    
    public DispatchInput_Req getDefaultInstanceForType() {
        return DispatchInput_Req.getDefaultInstance();
    }
    
    public DispatchInput_Req build() {
        final DispatchInput_Req buildPartial = this.buildPartial();
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
    
    public DispatchInput_Req buildPartial() {
        final DispatchInput_Req dispatchInput_Req = new DispatchInput_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        DispatchInput_Req.access$49002(dispatchInput_Req, this.input_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        DispatchInput_Req.access$49102(dispatchInput_Req, this.target_);
        DispatchInput_Req.access$49202(dispatchInput_Req, n);
        this.onBuilt();
        return dispatchInput_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof DispatchInput_Req) {
                return this.mergeFrom((DispatchInput_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final DispatchInput_Req dispatchInput_Req) {
        try {
            if (dispatchInput_Req == DispatchInput_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (dispatchInput_Req.hasInput()) {
                this.bitField0_ |= 0x1;
                this.input_ = DispatchInput_Req.access$49000(dispatchInput_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (dispatchInput_Req.hasTarget()) {
                this.setTarget(dispatchInput_Req.getTarget());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(dispatchInput_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasInput()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasTarget()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        DispatchInput_Req dispatchInput_Req = null;
        try {
            dispatchInput_Req = (DispatchInput_Req)DispatchInput_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            dispatchInput_Req = (DispatchInput_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (dispatchInput_Req != null) {
                    this.mergeFrom(dispatchInput_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasInput() {
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
    
    public String getInput() {
        final Object input_ = this.input_;
        if (!(input_ instanceof String)) {
            return (String)(this.input_ = ((ByteString)input_).toStringUtf8());
        }
        return (String)input_;
    }
    
    public ByteString getInputBytes() {
        final Object input_ = this.input_;
        if (input_ instanceof String) {
            return (ByteString)(this.input_ = ByteString.copyFromUtf8((String)input_));
        }
        return (ByteString)input_;
    }
    
    public Builder setInput(final String input_) {
        try {
            if (input_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.input_ = input_;
        this.onChanged();
        return this;
    }
    
    public Builder clearInput() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.input_ = DispatchInput_Req.getDefaultInstance().getInput();
        this.onChanged();
        return this;
    }
    
    public Builder setInputBytes(final ByteString input_) {
        try {
            if (input_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.input_ = input_;
        this.onChanged();
        return this;
    }
    
    public boolean hasTarget() {
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
    
    public Model.DispatchTarget getTarget() {
        return this.target_;
    }
    
    public Builder setTarget(final Model.DispatchTarget target_) {
        try {
            if (target_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.target_ = target_;
        this.onChanged();
        return this;
    }
    
    public Builder clearTarget() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.target_ = Model.DispatchTarget.DispatchTargetProcess;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
