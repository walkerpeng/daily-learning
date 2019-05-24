package me.walker.controller;

import me.walker.common.exception.BizException;
import me.walker.common.exception.ErrorEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    //occur exception
    @PostMapping("exception/unknownException")
    public Object occurUnknownException() throws Exception {
        throw new Exception();
    }

    //occur exception explicitly
    @GetMapping("exception/businessException")
    public Object occurBusinessException(){
        throw new BizException(ErrorEnum.UNKNOWN_EXCEPTION);
    }

}
