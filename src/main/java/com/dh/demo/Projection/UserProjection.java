package com.dh.demo.Projection;

import com.dh.demo.Models.Article;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface UserProjection {
    @Value( "#{target.userName + ' - > ' + target.permissionLevel}" )
    String getUserNameAndPermissionLevel();

    String getEmail();
    List<Article> getArticles();
}
