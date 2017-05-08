package com.base;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class BaseConfig {

    /**
     * ����hbase����
     * @return
     */
    public static Connection getConnection() throws Exception{
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "192.168.56.11");
        conf.set("hbase.master", "192.168.56.11:9000");
        Connection conn = ConnectionFactory.createConnection(conf);
        return conn;
    }
}
