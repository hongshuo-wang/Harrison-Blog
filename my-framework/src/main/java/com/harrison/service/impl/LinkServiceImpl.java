package com.harrison.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.constants.SystemConstants;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.LinkVo;
import com.harrison.mapper.LinkMapper;
import com.harrison.domain.entity.Link;
import com.harrison.service.LinkService;
import com.harrison.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-16 19:52:05
 * @Description:
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult<List<LinkVo>> getAllLink() {
        // 查询审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        // 转Vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        // 返回
        return ResponseResult.okResult(linkVos);
    }
}

