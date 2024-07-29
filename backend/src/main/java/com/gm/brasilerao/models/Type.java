package com.gm.brasilerao.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Type {

    @JsonProperty("type")
    public TypeName typeName;

    public Type(){

    };

    @Override
    public String toString() {
        return typeName.name ;
    }
}
