package top.xmindguoguo.java8.issue; // 这个是文件夹的名称 俗称包名

public class TestClass { // 这个是类名
    public static void main(String[] args) {
        aiXin(); // 调用方法 使用创建的角色 开始玩游戏
    }

    /**
     * 打印心形
     */
    public static void aiXin() { // 声明方法 类似于 玩游戏创建角色
        for (float y = (float) 1.5; y > -1.5; y -= 0.1) {
            for (float x = (float) -1.5; x < 1.5; x += 0.05) {
                float a = x * x + y * y - 1;
                if ((a * a * a - x * x * y * y * y) <= 0.0) {
                    System.out.print("^");
                } else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
