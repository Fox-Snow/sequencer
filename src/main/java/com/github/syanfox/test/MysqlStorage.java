package com.github.syanfox.test;

import com.github.syanfox.entity.SerialNumber;
import com.github.syanfox.storage.SequenceStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sequencer
 * @description: mysql  存储数据
 * @author: snowfox
 * @create: 2020-09-28 16:09
 */
public class MysqlStorage implements SequenceStorage {
    @Override
    public boolean storage(List<SerialNumber> numbers) {
        numbers.stream().forEach(item -> {
            System.out.println("存储了:"+item.getType()+"---序号:"+item.getNum());
        });
        return true;
    }

    @Override
    public List<SerialNumber> getStarted() {
        List<SerialNumber> numberList = new ArrayList<>();

        SerialNumber serialNumber = new SerialNumber();
        serialNumber.setType("timeId");
        serialNumber.setNum(10L);
        numberList.add(serialNumber);


        SerialNumber serialNumber1 = new SerialNumber();
        serialNumber1.setType("wId");
        serialNumber1.setNum(100L);
        numberList.add(serialNumber1);
        return numberList;
    }
}
