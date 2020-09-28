package org.snowfox;

import org.snowfox.entity.SerialNumber;
import org.snowfox.order.OrderNumber;
import org.snowfox.storage.SequenceStorage;
import org.snowfox.storage.StorageTask;

import java.util.Timer;


/**
 * @program: sequencer
 * @description: sequencer instance
 * @author: snowfox
 * @create: 2020-09-28 15:06
 */
public class Sequencer {

    private OrderNumber orderNumber ;

    private static class SequencerClassInstance{
          private static final Sequencer instance=new Sequencer();
    }

    private Sequencer(){}

    public static Sequencer getInstance(){
        return SequencerClassInstance.instance;
    }

    public void start(SequenceStorage sequenceStorage) throws Exception {
        orderNumber = new OrderNumber(sequenceStorage);
        orderNumber.init();
        Timer timer = new Timer();
        timer.schedule(new StorageTask(sequenceStorage), 60 * 1000);
    }


    public Long getNumber(String type){
        return orderNumber.getOrderNumber(type);
    }

    public boolean storage(){
        return orderNumber.storage();
    }


}
