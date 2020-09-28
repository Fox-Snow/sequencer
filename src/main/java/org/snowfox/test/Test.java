package org.snowfox.test;

import org.snowfox.Sequencer;

/**
 * @program: sequencer
 * @description: 测试类
 * @author: snowfox
 * @create: 2020-09-28 16:13
 */
public class Test {


    public static void main(String[] args) throws Exception {
        Sequencer.getInstance().start(new MysqlStorage());

        for(int i =0;i<10;i++){
            long num = Sequencer.getInstance().getNumber("timeId");
            System.out.println("timeId num==="+String.valueOf(num));
        }

        for(int j =0;j<20;j++){
            long num = Sequencer.getInstance().getNumber("wId");
            System.out.println("wId num==="+String.valueOf(num));
        }

        while (true){

        }

    }
}
