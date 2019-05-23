package me.walker.kafkademo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserInfo implements Serializable{
    private static final long serialVersionUID = -5164882458569535463L;

    private Long tbl_id;
    private String owner;
    private String tbl_name;
    private String tbl_type;
}
