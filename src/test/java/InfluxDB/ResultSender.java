package InfluxDB;

import Selenium.DSL;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

public class ResultSender extends DSL {

    private static final InfluxDB INFLXUDB = InfluxDBFactory.connect(myConfig.dbUrl(), myConfig.dbLogin(), myConfig.dbPassword());
    private static final String DATABASE = myConfig.dbName();

    static{
        INFLXUDB.setDatabase(DATABASE);
    }

    public static void send(final Point point){
       INFLXUDB.write(point);

       // log result test that write to database
       logger.debug(point);
    }

}