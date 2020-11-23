# sequencer
自增ID 处理

###调用方法

'''
Sequencer.getInstance().start(new MysqlStorage());    
 
for(int i =0;i<10;i++){
    long num = Sequencer.getInstance().getNumber("timeId");
    System.out.println("timeId num==="+String.valueOf(num));
}
