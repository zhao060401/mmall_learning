package com.mmall.service;

        import com.mmall.common.ServerResponse;
        import com.mmall.vo.CartVo;
        import org.springframework.stereotype.Service;

@Service
public interface ICartService {
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);
    public ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count) ;
    public ServerResponse<CartVo> delete(Integer userId, String productIds) ;
    public ServerResponse<CartVo> list(Integer userId) ;
    ServerResponse<CartVo> selectOrUnSelectAll(Integer userId, Integer checked,Integer productId);
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
