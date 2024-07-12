package com.ljq.project.exception;

import com.alibaba.fastjson.JSONObject;
import com.chengyi.medtrack.common.config.CommonConfig;
import com.chengyi.medtrack.common.http.JsonResult;
import com.ljq.project.dto.response.ResultResponse;
import com.ljq.project.enums.TipEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * transactionExceptionHandler
     * <p>
     * 事务异常处理
     * </p>
     *
     * @param e 异常信息
     * @return java.lang.Object
     */
    @ExceptionHandler(value = TransactionException2.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object transactionExceptionHandler(TransactionException2 e) {
        log.error("\n==Unhandled TransactionException:", e);
        return ResultResponse.fail(TipEnum.FAIL.getCode(),e.getMessage());
    }

    /**
     * 默认异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("\n==Unhandled Exception:", e);
        JSONObject jobj = new JSONObject();
            jobj.put("msg", e.getMessage());
            jobj.put("cause", e.getCause());

        ResponseEntity responseEntity;
        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jobj);
        return responseEntity;
    }

    /**
     * argumentNotValidException
     * <p>
     * 验证类错误处理
     * </p>
     *
     * @param exception 异常信息
     * @return java.lang.Object
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object argumentNotValidException(MethodArgumentNotValidException exception) {
        String msg = "";
        List<ObjectError> objectErrorList = exception.getBindingResult().getAllErrors();
        for (int i = 0, len = objectErrorList.size(); i < len; i++) {
            msg += objectErrorList.get(i).getDefaultMessage() + "\n";
        }
        msg = StringUtils.trimTrailingWhitespace(msg);

        return ResultResponse.fail(TipEnum.FAIL.getCode(), exception.getMessage());
    }
}
