package com.harrison.controller;



import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.LinkVo;
import com.harrison.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 友链(Link)表控制层
 *
 * @author Harrison
 * @date 2023-02-16 20:28:01
 * @Description:  
 */
@RestController
@RequestMapping("/link")
public class LinkController{
    @Autowired
    LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult<List<LinkVo>> getAllLink() {
        return linkService.getAllLink();
    }
    
}

