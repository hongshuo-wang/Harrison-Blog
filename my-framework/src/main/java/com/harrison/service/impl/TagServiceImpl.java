package com.harrison.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.mapper.TagMapper;
import com.harrison.domain.entity.Tag;
import com.harrison.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-21 23:23:17
 * @Description:
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

