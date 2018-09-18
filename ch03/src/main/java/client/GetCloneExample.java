package client;

// cc GetCloneExample Example application retrieving data from HBase
import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import util.HBaseHelper;

public class GetCloneExample {


  public static void main(String[] args) throws IOException {
//    Configuration conf = HBaseConfiguration.create();
//    // zk config
//    conf.set("hbase.rootdir", "hdfs://crm-master1:9000/hbase");
//    conf.set("hbase.zookeeper.quorum", "crm-slave1,crm-slave2,crm-master2");
    Configuration conf =  HbaseConf.getConf();

    HBaseHelper helper = HBaseHelper.getHelper(conf);
    if (!helper.existsTable("testtable")) {
      helper.createTable("testtable", "colfam1");
    }
    Connection connection = ConnectionFactory.createConnection(conf);
    Table table = connection.getTable(TableName.valueOf("testtable"));

    // vv GetCloneExample
//    Get get1 = new Get(Bytes.toBytes("row1"));
//    get1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));

    Get get1 = new Get(Bytes.toBytes("row1"));
    get1.addColumn(Bytes.toBytes("colfam2"), Bytes.toBytes("qual2"));


    Get get2 = new Get(get1);
    Result result = table.get(get2);

    System.out.println("Result : " + result);

//    System.out.println("Result : " + result.getValue(Bytes.toBytes("colfam2"), Bytes.toBytes("qual2")).toString());
    System.out.println("Result : " +  Bytes.toString(result.getValue(Bytes.toBytes("colfam2"), Bytes.toBytes("qual2"))));

    // wpp 打印相关值信息
    List<Cell> cells = result.listCells();
    for (int i = 0; i < cells.size(); i++) {
      Cell cell = cells.get(i);
      System.out.println(cell.toString());
      System.out.println("cell value:" + Bytes.toString(cell.getValue() ));
    }


    // ^^ GetCloneExample
    table.close();
    connection.close();
    helper.close();
  }
}
