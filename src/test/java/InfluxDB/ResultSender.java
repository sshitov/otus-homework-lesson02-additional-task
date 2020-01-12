package InfluxDB;

import Selenium.FindJavaCourseForQa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

public class ResultSender {

    // initialize logger
    public static final Logger logger = LogManager.getLogger(FindJavaCourseForQa.class.getName());

    // connect to database
    private static final InfluxDB INFLXUDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
    private static final String DATABASE = "selenium";


    static{
        INFLXUDB.setDatabase(DATABASE);
    }

    public static void send(final Point point){
       INFLXUDB.write(point);

       // log result test that write to database
       logger.debug(point);
    }

}