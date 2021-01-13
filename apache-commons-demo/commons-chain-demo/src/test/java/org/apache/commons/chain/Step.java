package org.apache.commons.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 责任链的模式进行实现
 * 
 * @ClassName Step
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年9月6日 下午4:09:30
 *
 */
public class Step {

}

class Step1 implements Command {

    @Override
    public boolean execute(Context context) throws Exception {
        context.put("name", this.getClass().getName() + "guoguo");
        System.out.println(this.getClass().getName());
        return false;
    }

}

class Step2 implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println(this.getClass().getName());
        return false;
    }
}

class Step3 implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println(this.getClass().getName());
        return false;
    }
}

class Step4 implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println(this.getClass().getName());
        return false;
    }
}

class Step5 implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println(this.getClass().getName());
        return false;
    }
}
