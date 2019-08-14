package com.proofpoint.streamdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonData {
    @JsonProperty("file")
    private String fileName;

    @JsonProperty("line_number")
    private int lineNumber;

    @JsonProperty("line")
    private String lineData;

    public String getFileName() {
        return fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLineData() {
        return lineData;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "fileName='" + fileName + '\'' +
                ", lineNumber=" + lineNumber +
                ", lineData='" + lineData + '\'' +
                '}';
    }
}
