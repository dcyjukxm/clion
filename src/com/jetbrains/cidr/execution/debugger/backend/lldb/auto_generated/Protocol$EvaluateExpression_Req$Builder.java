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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements EvaluateExpression_ReqOrBuilder
{
    private int bitField0_;
    private Object expression_;
    private int threadId_;
    private int frameIndex_;
    private Model.Language language_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$29200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$29300().ensureFieldAccessorsInitialized((Class)EvaluateExpression_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.expression_ = "";
        this.language_ = Model.Language.LanguageUnknown;
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.expression_ = "";
        this.language_ = Model.Language.LanguageUnknown;
        this.b();
    }
    
    private void b() {
        if (EvaluateExpression_Req.access$29700()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.expression_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.threadId_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.frameIndex_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.language_ = Model.Language.LanguageUnknown;
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$29200();
    }
    
    public EvaluateExpression_Req getDefaultInstanceForType() {
        return EvaluateExpression_Req.getDefaultInstance();
    }
    
    public EvaluateExpression_Req build() {
        final EvaluateExpression_Req buildPartial = this.buildPartial();
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
    
    public EvaluateExpression_Req buildPartial() {
        final EvaluateExpression_Req evaluateExpression_Req = new EvaluateExpression_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        EvaluateExpression_Req.access$29902(evaluateExpression_Req, this.expression_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        EvaluateExpression_Req.access$30002(evaluateExpression_Req, this.threadId_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        EvaluateExpression_Req.access$30102(evaluateExpression_Req, this.frameIndex_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        EvaluateExpression_Req.access$30202(evaluateExpression_Req, this.language_);
        EvaluateExpression_Req.access$30302(evaluateExpression_Req, n);
        this.onBuilt();
        return evaluateExpression_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof EvaluateExpression_Req) {
                return this.mergeFrom((EvaluateExpression_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final EvaluateExpression_Req evaluateExpression_Req) {
        try {
            if (evaluateExpression_Req == EvaluateExpression_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (evaluateExpression_Req.hasExpression()) {
                this.bitField0_ |= 0x1;
                this.expression_ = EvaluateExpression_Req.access$29900(evaluateExpression_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (evaluateExpression_Req.hasThreadId()) {
                this.setThreadId(evaluateExpression_Req.getThreadId());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (evaluateExpression_Req.hasFrameIndex()) {
                this.setFrameIndex(evaluateExpression_Req.getFrameIndex());
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (evaluateExpression_Req.hasLanguage()) {
                this.setLanguage(evaluateExpression_Req.getLanguage());
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        this.mergeUnknownFields(evaluateExpression_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasExpression()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasThreadId()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.hasFrameIndex()) {
                return false;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        EvaluateExpression_Req evaluateExpression_Req = null;
        try {
            evaluateExpression_Req = (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            evaluateExpression_Req = (EvaluateExpression_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (evaluateExpression_Req != null) {
                    this.mergeFrom(evaluateExpression_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasExpression() {
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
    
    public String getExpression() {
        final Object expression_ = this.expression_;
        if (!(expression_ instanceof String)) {
            return (String)(this.expression_ = ((ByteString)expression_).toStringUtf8());
        }
        return (String)expression_;
    }
    
    public ByteString getExpressionBytes() {
        final Object expression_ = this.expression_;
        if (expression_ instanceof String) {
            return (ByteString)(this.expression_ = ByteString.copyFromUtf8((String)expression_));
        }
        return (ByteString)expression_;
    }
    
    public Builder setExpression(final String expression_) {
        try {
            if (expression_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.expression_ = expression_;
        this.onChanged();
        return this;
    }
    
    public Builder clearExpression() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.expression_ = EvaluateExpression_Req.getDefaultInstance().getExpression();
        this.onChanged();
        return this;
    }
    
    public Builder setExpressionBytes(final ByteString expression_) {
        try {
            if (expression_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.expression_ = expression_;
        this.onChanged();
        return this;
    }
    
    public boolean hasThreadId() {
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
    
    public int getThreadId() {
        return this.threadId_;
    }
    
    public Builder setThreadId(final int threadId_) {
        this.bitField0_ |= 0x2;
        this.threadId_ = threadId_;
        this.onChanged();
        return this;
    }
    
    public Builder clearThreadId() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.threadId_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasFrameIndex() {
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
    
    public int getFrameIndex() {
        return this.frameIndex_;
    }
    
    public Builder setFrameIndex(final int frameIndex_) {
        this.bitField0_ |= 0x4;
        this.frameIndex_ = frameIndex_;
        this.onChanged();
        return this;
    }
    
    public Builder clearFrameIndex() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.frameIndex_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasLanguage() {
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
    
    public Model.Language getLanguage() {
        return this.language_;
    }
    
    public Builder setLanguage(final Model.Language language_) {
        try {
            if (language_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x8;
        this.language_ = language_;
        this.onChanged();
        return this;
    }
    
    public Builder clearLanguage() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.language_ = Model.Language.LanguageUnknown;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
