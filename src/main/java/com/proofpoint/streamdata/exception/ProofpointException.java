package com.proofpoint.streamdata.exception;

public class ProofpointException extends RuntimeException {

    public ProofpointException(String errorMessage) {
        super(errorMessage);
    }
}
