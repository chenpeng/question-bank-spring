package io.chenpeng.questionbank.support;

import io.chenpeng.questionbank.enumeration.AuthEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ModelAttribute
    public void getBobyInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException {
        String path = request.getRequestURI();

        //获取header里的手机号和密码
//        String mobile = request.getHeader("mobile");
//        String password = request.getHeader("password");
//        if (StringUtils.isEmpty(mobile)) {
//            throw new AuthException(AuthEnum.ERROR_MOBILE_NOT_NULL,
//                    "mobile不能为空");
//        } else if (StringUtils.isEmpty(password)) {
//            throw new AuthException(AuthEnum.ERROR_PASSWORD_NOT_NULL,
//                    "password不能为空");
//        } else if (!auth(mobile, password)) {
//            throw new AuthException(AuthEnum.ERROR_AUTH_INVALID,
//                    "身份认证失败");
//        }
    }

    private boolean auth(String mobile, String password) throws IOException {
        // TODO 身份验证
        return true;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("请求参数不合法", e);
        List<String> list = ValidationErrorBuilder.fromBindingErrors(e.getBindingResult()).getErrors();
        return new ResponseEntity<>(list.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    private ResponseEntity authExceptionHandler(AuthException e) {
        LOGGER.error("身份验证失败", e.getMessage());
        return new ResponseEntity<>(e.getMsg(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity serviceExceptionHandler(Exception e) {
        LOGGER.error("系统出现异常", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
