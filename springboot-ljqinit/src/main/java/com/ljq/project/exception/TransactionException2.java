package com.ljq.project.exception;

/**
 *
 * <p>
 * 自定义异常处理
 * </p>
 *
 * @author wlj
 * @since 2021/3/31
 */
public class TransactionException2 extends RuntimeException {
    // private static final long serialVersionUID = 1L;

    public TransactionException2() {
        super();
    }

    public TransactionException2(String message) {
        super(message);
    }

}
