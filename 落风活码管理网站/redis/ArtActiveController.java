package com.qhit.artActive.controller; 

import com.qhit.adminUser.pojo.AdminUser;
import com.qhit.artActive.pojo.ArtActive;
import com.qhit.artActive.service.IArtActiveService;
import com.qhit.artActive.service.impl.ArtActiveServiceImpl;
import com.qhit.buseActive.pojo.BuseActive;
import com.qhit.buseActive.service.IBuseActiveService;
import com.qhit.uitls.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource; 
import java.util.List; 
import org.springframework.web.bind.annotation.RestController; 

/** 
* Created by GeneratorCode on 2019/05/08
*/ 

@RestController 
@RequestMapping("/artActive") 
public class ArtActiveController {
    ArtActiveServiceImpl a=new ArtActiveServiceImpl();

    @Resource 
    IArtActiveService artActiveService;
    @Resource
    IBuseActiveService buseActiveService;

    @RequestMapping("/insert") 
    public void insert(ArtActive artActive) {
        artActiveService.insert(artActive);
    } 

    @RequestMapping("/delete") 
    public void delete(Integer acid) { 
        artActiveService.delete(acid); 
    } 

    @RequestMapping("/update") 
    public void update(ArtActive artActive) { 
        artActiveService.update(artActive); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(ArtActive artActive) { 
        artActiveService.updateSelective(artActive); 
    } 

    @RequestMapping("/load") 
    public ArtActive load(Integer acid) { 
        ArtActive artActive = artActiveService.findById(acid); 
        return artActive; 
    }
    @RequestMapping("/dianzan")
    public BuseActive dianzan(Integer acid){
        RedisUtils redisUtils = new RedisUtils();
        //redisUtils取用户信息
        AdminUser adminUser = (AdminUser)redisUtils.get("BaseUser");
        int i = adminUser.getAid();
        BuseActive buseActive = buseActiveService.dainzan(acid,i);
        //判断有没有点过赞
        if (buseActive.getBaid()!=0){
            buseActiveService.deleteByaid(i);
        }else {
                buseActive.setAid(i);
                buseActive.setAcid(acid);
                buseActiveService.insert(buseActive);
        }
        return buseActive;
    }
    @RequestMapping("/count")
    public BuseActive count(Integer acid){
        BuseActive buseActive = buseActiveService.count(acid);
        return buseActive;
    }

    @RequestMapping("/list") 
    public List<ArtActive> list()  { 
        List<ArtActive> list = artActiveService.findAll(); 
        return list; 
    } 

    @RequestMapping("/search") 
    public List<ArtActive> search(ArtActive artActive) {
        List<ArtActive> list = artActiveService.search(artActive);
        return list;
    }
} 
