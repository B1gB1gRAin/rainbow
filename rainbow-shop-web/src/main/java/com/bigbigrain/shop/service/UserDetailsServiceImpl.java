package com.bigbigrain.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bigbigrain.entity.Seller;
import com.bigbigrain.sellergoods.service.ISellerService;
/**
 * 认证类
 * @author zhangy
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	private ISellerService iSellerService;
	
	public void setiSellerService(ISellerService iSellerService) {
		this.iSellerService = iSellerService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();  
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        EntityWrapper<Seller> wrapper = new EntityWrapper<Seller>();
        wrapper.eq("seller_id", username);
        Seller seller = iSellerService.selectOne(wrapper);
        if( seller != null ) {
        	if("1".equals(seller.getStatus())) {
        		return new User(username, seller.getPassword(), grantedAuths);
        	}else {
        		return null;
        	}
        }else {
        	return null;
        }
	}

}
