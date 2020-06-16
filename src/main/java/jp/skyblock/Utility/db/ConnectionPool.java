/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 ConnectionPool.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Utility.db;

public class ConnectionPool {

    public static void  initializeInstance(String path){
        new ConnectionPool4J().configLoader(path);
    };

}
