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
     * ������
     * @param tableDescriptor
     */
    public void createTable(HTableDescriptor tableDescriptor) throws Exception{
        Admin admin = BaseConfig.getConnection().getAdmin();
        //�ж�tablename�Ƿ����
        if (!admin.tableExists(tableDescriptor.getTableName())) {
            admin.createTable(tableDescriptor);
        }else {
			logger.info("��"+tableDescriptor.getTableName()+"�Ѵ��ڣ�");
		}
        admin.close();
    }
    public void addTableColumn(String tableName,HColumnDescriptor columnDescriptor) throws Exception {
        Admin admin = BaseConfig.getConnection().getAdmin();
        admin.addColumn(TableName.valueOf(tableName),columnDescriptor);
        logger.info("����ӳɹ�");
        admin.close();
    }


    /**
     *  ��������
     * @param putData
     *  @param tableName
     */
    public void putData(Put putData,String tableName) throws Exception{
        Table table = BaseConfig.getConnection().getTable(TableName.valueOf(tableName));
        table.put(putData);
        logger.info("�������ݳɹ�");
        table.close();
    }

    /**
     * ɾ������
     * @param delData
     *  @param tableName
     */
    public void delData(Delete delData,String tableName) throws Exception{
        Table table = BaseConfig.getConnection().getTable(TableName.valueOf(tableName));
        table.delete(delData);
        table.close();
    }

    /**
     * ��ѯ����
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
     * ��ѯ����
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