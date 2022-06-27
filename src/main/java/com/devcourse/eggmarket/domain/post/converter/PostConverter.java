package com.devcourse.eggmarket.domain.post.converter;

import com.devcourse.eggmarket.domain.post.dto.PostRequest;
import com.devcourse.eggmarket.domain.post.dto.PostRequest.UpdatePurchaseInfo;
import com.devcourse.eggmarket.domain.post.dto.PostResponse;
import com.devcourse.eggmarket.domain.post.model.Category;
import com.devcourse.eggmarket.domain.post.model.Post;
import com.devcourse.eggmarket.domain.post.model.PostStatus;
import com.devcourse.eggmarket.domain.user.dto.UserResponse;
import com.devcourse.eggmarket.domain.user.model.User;
import java.util.Collections;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    public Post saveToPost(PostRequest.Save request, User seller) {
        return Post.builder()
            .title(request.title())
            .content(request.content())
            .category(Category.valueOf(request.category()))
            .price(request.price())
            .seller(seller)
            .build();
    }

    public void updateToPost(PostRequest.UpdatePost request, Post post) {
        post.updateTitle(request.title());
        post.updateContent(request.content());
        post.updatePrice(request.price());
        post.updateCategory(Category.valueOf(request.category()));
    }

    public void updateToPurchase(UpdatePurchaseInfo request, Post post,
        User buyer) {
        post.updatePurchaseInfo(
            PostStatus.valueOf(request.postStatus()),
            buyer
        );
    }

    public PostResponse.SinglePost singlePost(Post post, boolean likeOfMe) {
        User seller = post.getSeller();

        return new PostResponse.SinglePost(
            post.getId(),
            new UserResponse(
                seller.getId(),
                seller.getNickName(),
                seller.getMannerTemperature(),
                seller.getRole().toString(),
                seller.getImagePath()),
            post.getPrice(),
            post.getTitle(),
            post.getContent(),
            post.getPostStatus().name(),
            post.getCategory().name(),
            post.getCreatedAt(),
            post.getAttentionCount(),
            0, // TODO : Comment 개수
            likeOfMe,
            Collections.emptyList()
        );
    }
}
