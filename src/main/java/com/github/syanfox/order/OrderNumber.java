package com.github.syanfox.order;

import com.github.syanfox.entity.SerialNumber;
import com.github.syanfox.storage.SequenceStorage;

import java.util.*;

/**
 * @program: sequencer
 * @description:
 * @author: snowfox
 * @create: 2020-09-28 15:23
 */
public  class OrderNumber {

    public static Map<String, SerialNumber> orderNums = new HashMap<>();

    private SequenceStorage sequenceStorage;

    public OrderNumber(SequenceStorage sequenceStorage){
        this.sequenceStorage = sequenceStorage;
    }

    public synchronized  List<SerialNumber>  getList(){
        List<SerialNumber> serialNumbers = new ArrayList<>();
        Iterator<Map.Entry<String, SerialNumber>> entries = orderNums.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, SerialNumber> entry = entries.next();
            serialNumbers.add(entry.getValue());
        }
        return serialNumbers;
    }

    /**
     * 初始化数据
     * @throws Exception
     */
    public synchronized void init() throws Exception {
        List<SerialNumber> numberList = sequenceStorage.getStarted();
        if(numberList == null || numberList.size() ==0 ){
            throw  new Exception("No initialization data...");
        }
        OrderNumber.orderNums.clear();
        numberList.stream().forEach(item -> {
            OrderNumber.orderNums.put(item.getType(),item);
        });

    }

    /**
     * 获取自增序列号
     * @param type
     * @return
     */
    public synchronized  String getOrderNumber(String type){
        if(orderNums.containsKey(type)){
            SerialNumber serialNumber =  orderNums.get(type);
            if(serialNumber  == null){
                return String.valueOf(0L);
            }

            long newNum =serialNumber.getNum()+1;
            serialNumber.setNum(newNum);
            orderNums.put(type,serialNumber);
            if(serialNumber.getPrefix() == null || "".equals(serialNumber.getPrefix())){
                return String.valueOf(newNum);
            }
            return serialNumber.getPrefix()+String.valueOf(newNum);
        }

        return String.valueOf(0L);
    }

    /**
     * 存储数据
     * @return
     */
    public synchronized boolean storage(){
        List<SerialNumber> numberList = getList();
        return sequenceStorage.storage(numberList);
    }

}
