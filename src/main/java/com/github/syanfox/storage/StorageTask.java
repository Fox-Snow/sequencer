package com.github.syanfox.storage;

import com.github.syanfox.entity.SerialNumber;
import com.github.syanfox.order.OrderNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * @program: sequencer
 * @description: 存储执行
 * @author: snowfox
 * @create: 2020-09-28 15:46
 */
public class StorageTask extends TimerTask {

    //private static Logger log = LoggerFactory.getLogger(StorageTask.class);
    private OrderNumber orderNumber;
    private SequenceStorage sequenceStorage;

    private List<SerialNumber> old_list = new ArrayList<>();

    public StorageTask(SequenceStorage sequenceStorage){
        orderNumber = new OrderNumber(sequenceStorage);
        this.sequenceStorage =sequenceStorage;
    }

    @Override
    public void run() {


        List<SerialNumber> list = handle(orderNumber.getList());
        if(list != null && list.size() !=0){
            boolean result = sequenceStorage.storage(list);
            if(result){
                //log.info("Storage success ...");
                deepCopy(orderNumber.getList());
            }
        }
        else{
            //log.info("The data did not change ...");
        }

    }



    public void deepCopy(List<SerialNumber> list){
        if(list != null && list.size() > 0){
            old_list.clear();
            list.stream().forEach((item)->{
                SerialNumber serialNumber = new SerialNumber();
                serialNumber.setType(item.getType());
                serialNumber.setNum(item.getNum());
                old_list.add(serialNumber);
            });
        }
    }


    public List<SerialNumber> handle(List<SerialNumber> list){
        List<SerialNumber> list_diff = new ArrayList<>();


        if(old_list == null || old_list.size() == 0){
            list.stream().forEach((item)->{
                SerialNumber serialNumber = new SerialNumber();
                serialNumber.setType(item.getType());
                serialNumber.setNum(item.getNum());
                list_diff.add(serialNumber);
            });

            return list_diff;
        }

        if(list != null && list.size() > 0){
            for(int i=0; i<list.size();i++){
                SerialNumber serialNumber = list.get(i);
                for(int j=0;j<old_list.size();j++){
                    SerialNumber sn = old_list.get(j);
                    if(sn.getType().equals(serialNumber.getType())){
                        if(sn.getNum() != serialNumber.getNum()){
                            SerialNumber number = new SerialNumber();
                            number.setType(serialNumber.getType());
                            number.setNum(serialNumber.getNum());
                            number.setPrefix(serialNumber.getPrefix());
                            list_diff.add(number);
                        }

                        break;
                    }
                }
            }
        }

        return list_diff;
    }
}
