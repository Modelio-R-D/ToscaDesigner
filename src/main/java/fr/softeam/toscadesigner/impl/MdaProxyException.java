package fr.softeam.toscadesigner.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8043b6ed-ab86-4572-8c88-c5d12122aee9")
public class MdaProxyException extends Exception {
    @objid ("7089fbd0-666f-4c6f-9733-b0954c8ee131")
    public  MdaProxyException(MdaProxyExceptionReason reason, List<MRef> missingRefs) {
        super(missingRefs.stream().map((r) -> r.toString())
                .collect(Collectors.joining(" ", MdaProxyExceptionReason.MISSING_MDA_ELEMENT.toString(), "")));
    }

    @objid ("25ae1ac8-540d-45ef-95d8-bb20d6bb9e5a")
    public  MdaProxyException(Throwable e) {
        super(e);
    }

    @objid ("8c3da9ea-e2b4-42ff-87b6-0347a01d7278")
    public  MdaProxyException(String message, Exception e) {
        super(message, e);
    }

    @objid ("2c939848-1969-4189-9361-2240dd1882bb")
    public  MdaProxyException(String message) {
        super(message);
    }

    @objid ("f4a924f4-be34-46cd-8576-c9efad0a6e3b")
    public enum MdaProxyExceptionReason {
        @objid ("cbc0627e-1a95-47ed-b267-2d3abe2787c0")
        MISSING_MDA_ELEMENT;

    }

}
