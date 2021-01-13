package org.apache.commons.chain;

import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;

public class ChainBaseTest extends ChainBase {
    public ChainBaseTest() {
        super();
        addCommand(new Step1());
        addCommand(new Step2());
        addCommand(new Step3());
        addCommand(new Step4());
        addCommand(new Step5());
    }

    public static void main(String[] args) throws Exception {
        // 执行责任链模式
        Command process = new ChainBaseTest();
        Context ctx = new ContextBase();
        process.execute(ctx);
    }
}
