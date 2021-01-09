package top.xmindguoguo.java8.issue.queue;

//无需恢复执行队列，示例类
public class TestNoRecoverRunningQueue extends NoRecoverRunningQueue<TestModel> {

    @Override
    protected void doing(TestModel model) {
//        System.out.println(model.getName());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            // Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestNoRecoverRunningQueue testRecoverRunningQueue = new TestNoRecoverRunningQueue();
        Thread.sleep(2000);
        for (int i = 0; i < 11; i++) {
            String key = "key" + Math.random();
            TestModel testModel = new TestModel();
            testModel.setName(key);
            testModel.setPassword("1111");
            testRecoverRunningQueue.put(key, testModel);
        }

        Thread.sleep(2000);
        for (int i = 0; i < 1100; i++) {
            String key = "key" + Math.random();
            TestModel testModel = new TestModel();
            testModel.setName(key);
            testModel.setPassword("1111");
            testRecoverRunningQueue.put(key, testModel);
        }
        System.out.println("ok");
        testRecoverRunningQueue.shutdown();
    }

}

class TestModel {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
