package com.jcut.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcut.carousel.mapper.CarouselMapper;
import com.jcut.carousel.service.CarouselService;
import com.jcut.pojo.Carousel;
import com.jcut.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/13 20:24
 **/
@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Cacheable(value = "list.carousel",key = "#root.methodName",cacheManager = "cacheManagerDay")
    @Override
    public R list() {
        QueryWrapper<Carousel> carouselQueryWrapper = new QueryWrapper<>();
        carouselQueryWrapper.orderByDesc("priority");
        List<Carousel> list = carouselMapper.selectList(carouselQueryWrapper);
        List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());
        R ok = R.ok(collect);
          log.info("CarouselServiceImpl.list业务结束,结果:{}",ok);
        return ok;
    }
}
