package client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class HbaseConf {

    public static Configuration getConf(){

        Configuration conf = HBaseConfiguration.create();
        // zk config
        conf.set("hbase.rootdir", "hdfs://crm-master1:9000/hbase");
        conf.set("hbase.zookeeper.quorum", "crm-slave1,crm-slave2,crm-master2");

        return conf;
    }
}
