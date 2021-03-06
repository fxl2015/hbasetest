package com.base;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDaoImpl implements BaseDao {
    static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);


    /**
     * 创建表
     * @param tableDescriptor
     */
    public void createTable(HTableDescriptor tableDescriptor) throws Exception{
        Admin admin = BaseConfig.getConnection().getAdmin();
        //判断tablename是否存在
        if (!admin.tableExists(tableDescriptor.getTableName())) {
            admin.createTable(tableDescriptor);
        }else {
			logger.info("表"+tableDescriptor.getTableName()+"已存在！");
		}
        admin.close();
    }
    public void addTableColumn(String tableName,HColumnDescriptor columnDescriptor) throws Exception {
        Admin admin = BaseConfig.getConnection().getAdmin();
        admin.addColumn(TableName.valueOf(tableName),columnDescriptor);
        logger.info("列添加成功");
        admin.close();
    }


    /**
     *  新增数据
     * @param putData
     *  @param tableName
     */
    public void putData(Put putData,String tableName) throws Exception{
        Table table = BaseConfig.getConnection().getTable(TableName.valueOf(tableName));
        table.put(putData);
        logger.info("新增数据成功");
        table.close();
    }

    /**
     * 删除数据
     * @param delData
     *  @param tableName
     */
    public void delData(Delete delData,String tableName) throws Exception{
        Table table = BaseConfig.getConnection().getTable(TableName.valueOf(tableName));
        table.delete(delData);
        table.close();
    }

    /**
     * 查询数据
     * @param scan
     *  @param tableName
     * @return
     */
    public ResultScanner scanData(Scan scan,String tableName) throws Exception{
        Table table = BaseConfig.getConnection().getTable(TableName.valueOf(tableName));
        ResultScanner rs =  table.getScanner(scan);
        table.close();
        return rs;
    }

    /**
     * 查询数据
     * @param get
     *  @param tableName
     * @return
     */
    public Result getData(Get get,String tableName) throws Exception{
        Table table = BaseConfig.getConnection().getTable(TableName.valueOf(tableName));
        Result result = table.get(get);
        table.close();
        return result;
    }
}