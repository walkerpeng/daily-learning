package me.walker.kafkademo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class RealTimeEstimateResult implements Serializable{
    private static final long serialVersionUID = -5164882458569535463L;

    private int id;
    private String modelName;
    private String result;
    private String createTime;
    private String modifiedTime;
    private String version;
    private String modelPlat;
}
