package org.snowfox.storage;

import org.snowfox.order.OrderNumber;

import java.util.TimerTask;

/**
 * @program: sequencer
 * @description: 存储执行
 * @author: snowfox
 * @create: 2020-09-28 15:46
 */
public class StorageTask extends TimerTask {

    private OrderNumber orderNumber;
    private SequenceStorage sequenceStorage;

    public StorageTask(SequenceStorage sequenceStorage){
        orderNumber = new OrderNumber(sequenceStorage);
        this.sequenceStorage =sequenceStorage;
    }

    @Override
    public void run() {
        boolean result = sequenceStorage.storage(orderNumber.getList());
    }
}
