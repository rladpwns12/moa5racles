package com.moa.message;

public class RoleMessage {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_PRE_HOST = "ROLE_PRE_HOST";
    public static final String ROLE_HOST = "ROLE_HOST";
    public static final String REGISTHOST = "^(/registhost)"; //!(role_host,role_pre_host)
    public static final String HOSTPAGE = "^(/hostpage)";   //host
    public static final String  KEEP= "^(/storeboard/keep)";  //host
    public static final String ADMIN = "^(/admin)"; //admin
}
