package com.harrison.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harrison.domain.entity.Link;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.LinkVo;

import java.util.List;

/**
 * 友链(Link)表服务接口
 *
 * @author Harrison
 * @date 2023-02-16 19:52:05
 * @Description:
 */
public interface LinkService extends IService<Link> {

    /**
     * 获取友链
     * @return
     */
    ResponseResult<List<LinkVo>> getAllLink();
}

